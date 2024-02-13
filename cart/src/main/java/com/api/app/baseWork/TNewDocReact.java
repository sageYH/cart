package com.api.app.baseWork;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.springframework.util.SerializationUtils;

import com.common.utils.ClassUtil;
import com.common.utils.TDataSet;

public class TNewDocReact {
	public static final String DIR = "d://temp/src1";

	public static void main(String[] args) {
		test();
	}
	public static void newPath(String path) {
		try {
			File f = new File(path);
			if(!f.exists()) {
				f.mkdirs();
			}
		}
		catch(Exception e) {}
	}
	public static String tocamelCase(String s) {
		String[] parts = s.split("_");
		String camelCaseString = "";
		int cnt = parts.length;
		for(int i=0;i<cnt;i++) {
			if(i==0) {
				camelCaseString = parts[i].toLowerCase();
			}
			else {
				camelCaseString = camelCaseString + toProperCase(parts[i]);
			}
		}
		
		return camelCaseString;
	}
	public static String toProperCase(String s) {
		s = s.trim();
		return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
	}
	public static String getAlias(String s) {
		String[] parts = s.split("_");
		String s1 = "";
		int cnt = parts.length;
		if(cnt < 1) return "bs";
		for(int i=1;i<cnt;i++) {
			s = parts[i].trim();
			s1 = s1 + s.substring(0,1).toUpperCase();
		}
		return s1;
	}
	public static void test(){
		dbManagerPg1 dbCls = new dbManagerPg1();
		try{
			//get class
			StringBuffer sb1 = new StringBuffer();
			sb1.append("select table_nm,duty_div,duty_div_sub,tbl_cls_pfix,tbl_cls_pfix_lc,svc_cls_pfix ");
			sb1.append(",svc_cls_pfix_lc,cls_pfix,message_nm,xml_path,mapper_path,dto_path,service_path ");
			sb1.append(",controller_path,jsp_path,js_path,popup_jsp_path,popup_js_path ");
			sb1.append(" from tempdoc ");
			sb1.append(" where table_nm in ('cw_seq','cw_grp_code','cw_grp_code_lang','cw_cmn_code','cw_cmn_code_lang','cw_user_info','cw_user_grp','cw_user_auth','cw_menu','cw_menu_lang','cw_menu_auth','cw_file','cw_country_info','cw_member','cw_mbr_lvl_base','cw_mbr_lvl_base_lang','cw_mbr_lvl','cw_mbr_pymt_meth','cw_comapny','cw_comp_user','cw_pymt_agcy_comp','cw_comp_authn_key','cw_cart_id_info','cw_point_info','cw_point_info_lang','cw_point_issue','cw_point_paid','cw_coupon_info','cw_coupon_info_lang','cw_coupon_issue','cw_coupon_issue_mbr','cw_coupon_pymt','cw_event','cw_event_lang','cw_event_cont','cw_event_cont_lang','cw_prod_classif','cw_prod_classif_lang','cw_products','cw_products_lang','cw_prod_cont','cw_prod_cont_lang','cw_prod_opt','cw_prod_opt_lang','cw_prod_inc_og','cw_prod_opt_inc_og','cw_comp_prod','cw_prod_exposure','cw_cart','cw_cart_option','cw_deliv_info','cw_order','cw_order_dtl','cw_order_dtl_opt','cw_order_deliv','cw_order_deliv_prod','cw_order_canc','cw_order_canc_prod','cw_order_cncl_deliv','cw_order_cncl_deliv_prod','cw_order_exch','cw_order_exch_dtl','cw_order_exch_deliv','cw_order_exch_deliv_prod','cw_buy_pymt','cw_card_bill','cw_af_comp_bill','cw_terms','cw_terms_dets','cw_terms_dets_lang','cw_user_terms_acpt','cw_mbr_terms_acpt','cw_comm_info','cw_comm_info_lang','cw_comm_send','cw_comm_send_tgt','cw_member_cert','cw_user_cert','cw_board','cw_notice','cw_notice_lang','cw_faq','cw_faq_lang','cw_qna','cw_news','cw_news_lang','cw_mbr_login_hist') "); ///===> table change...
	
			TDataSet ds = dbCls.query(sb1.toString());
			int cnt = ds.getRowCount("table_nm");
			for(int i=0;i<cnt;i++){
				String tNm = ds.getString("table_nm",i);
				ds.setValue("table_alias", i, getAlias(tNm)); //table alias
				TDataSet ds1 = getColInfo(tNm);
				
				ds.setValue("table_nm", i, ds.getString("table_nm",i).toUpperCase());

				setXml(ds, ds1, i);
				setMapper(ds, ds1, i);
				setDto(ds, ds1, i);
				setDtoEx(ds, ds1, i);
				setService(ds, ds1, i);
				setController(ds, ds1, i);
				//setJsp(ds, ds1, i);
				setJsp2(ds, ds1, i);
				//setJs(ds, ds1, i);
				setPopup(ds, ds1, i);
				setPopupJsp(ds, ds1, i);
				setPopupJspSearch(ds, ds1, i);
				setPopupJspDetail(ds, ds1, i);
			}
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		finally{
			try{
			}
			catch(Exception e){}
				System.out.println("End job..........");
		}
	}

	public static TDataSet getColInfo(String tNm) {
		TDataSet ds = new TDataSet();
		try {
			dbManagerPg1 dbCls = new dbManagerPg1();
			StringBuffer sb1 = new StringBuffer();
			sb1.append("select tc.table_name, tc.column_name ");
			sb1.append("	  ,pd.description as t_nm ");
			sb1.append("	  ,pd1.description as c_nm ");
			sb1.append("	  ,tc.udt_name as data_type ");
			sb1.append("	  ,tc.is_nullable ");
			sb1.append("	  ,coalesce(pk.pkey, 'N') as pkey ");
			sb1.append("	  ,tc.char_len, tc.data_precision, tc.data_scale, tc.time_precision ");
			sb1.append("  from ( ");
			sb1.append("	select ut.table_name ");
			sb1.append("		  ,sut.relid, uc.column_name, uc.ordinal_position ");
			sb1.append("		  ,uc.udt_name, uc.is_nullable ");
			sb1.append("		  ,uc.character_maximum_length as char_len ");
			sb1.append("		  ,uc.numeric_precision as data_precision ");
			sb1.append("		  ,uc.numeric_scale as data_scale ");
			sb1.append("		  ,uc.datetime_precision as time_precision ");
			sb1.append("	  from information_schema.tables ut ");
			sb1.append("		  ,pg_stat_user_tables sut ");
			sb1.append("		  ,information_schema.columns uc ");
			sb1.append("	 where ut.table_schema = 'public' ");
			sb1.append("	   and ut.table_name = sut.relname ");
			sb1.append("	   and ut.table_name = uc.table_name ");
			sb1.append("	   and ut.table_name = '"+ tNm +"' ");
			sb1.append("  ) tc ");
			sb1.append("  left outer join pg_description pd on pd.objoid = tc.relid and pd.objsubid = 0 ");
			sb1.append("  left outer join pg_description pd1 on pd1.objoid = tc.relid and pd1.objsubid = tc.ordinal_position ");
			sb1.append("  left outer join pg_attribute pa on pa.attrelid = tc.relid and pa.attname = tc.column_name ");
			sb1.append("  left outer join ( ");
			sb1.append("	select tc.table_name, cc.column_name as column_name, 'Y' as pkey ");
			sb1.append("	  from information_schema.table_constraints tc ");
			sb1.append("		  ,information_schema.key_column_usage cc ");
			sb1.append("	 where tc.constraint_type = 'PRIMARY KEY' ");
			sb1.append("	   and tc.table_catalog = cc.table_catalog ");
			sb1.append("	   and tc.table_schema = cc.table_schema ");
			sb1.append("	   and tc.table_name = cc.table_name ");
			sb1.append("	   and tc.constraint_name = cc.constraint_name ");
			sb1.append("  ) pk on pk.table_name = tc.table_name and pk.column_name = tc.column_name ");
			sb1.append("order by tc.table_name, tc.ordinal_position ");
			
			ds = dbCls.query(sb1.toString());
			int cnt = ds.getRowCount("table_name");
			if(cnt>0) {
				int idx = 0;
				for(int i=0;i<cnt;i++) {
					ds.setValue("table_name", i, ds.getString("table_name",i).toUpperCase());
					ds.setValue("column_name", i, ds.getString("column_name",i).toUpperCase());
					String scamel = tocamelCase(ds.getString("column_name",i));
					ds.setValue("column_name_camel", i, scamel);
					String scamel_first = scamel.substring(0,1).toUpperCase() + scamel.substring(1);
					ds.setValue("column_name_camel_first", i, scamel_first);
					if (ds.getString("pkey",i).equals("Y")) {
						ds.setValue("key", idx, ds.getString("column_name",i));
						ds.setValue("key_name", idx, ds.getString("c_nm",i));
						ds.setValue("key_camel", idx, tocamelCase(ds.getString("column_name",i)));
						idx++;
					}
					//REG_DTM, REG_USER_ID, UPD_DTM, UPD_USER_ID
					String s2 = ds.getString("column_name",i);
					if (s2.equals("REG_DTM") || s2.equals("REG_USER_ID") || s2.equals("UPD_DTM") || s2.equals("UPD_USER_ID")) {
						ds.setValue("base_yn", i, "Y");
					}
					else {
						ds.setValue("base_yn", i, "");
					}
					String s3 = ds.getString("data_type",i);
					if (s3.indexOf("time") < 0) {
						ds.setValue("time_yn", i, "");
					}
					else {
						ds.setValue("time_yn", i, "Y");
					}
					
					if(ds.getString("c_nm",i).indexOf("예비컬럼") > -1) {
						ds.setValue("spare_yn", i, "Y");
					}
					else {
						ds.setValue("spare_yn", i, "N");
					}
				}
			}
			
		}catch(Exception e) {}
		return ds;
	}

	public static void setXml(TDataSet ds, TDataSet ds1, int i){
		BufferedWriter bw = null;
		try{
			String duty_div = ds.getString("duty_div",i);
			String svc_cls_pfix_lc = ds.getString("svc_cls_pfix_lc",i);
			String svc_cls_pfix = ds.getString("svc_cls_pfix",i);
			String cls_pfix = ds.getString("cls_pfix",i);
			String xml_path = ds.getString("xml_path",i);
			int cntCol = ds1.getRowCount("column_name");
			int cntKey = ds1.getRowCount("key");
//			String path = "/src/main/resources/mappers/app/   -- \code\code-sql.xml";
			String path = xml_path+duty_div+"/";
			newPath(DIR + path);
			String fNm = DIR + path + svc_cls_pfix_lc + "-sql.xml";
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fNm),"UTF-8"));
			
			StringBuffer sb1 = new StringBuffer();
			
			sb1.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
			sb1.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis"+"-3-mapper.dtd\">\r\n");
			sb1.append("\r\n");

			sb1.append("<mapper namespace=\""+svc_cls_pfix_lc+"Mapper\">\r\n");
			sb1.append("	<sql id=\"where-"+svc_cls_pfix_lc+"\">\r\n");
			sb1.append("		WHERE 1=1\r\n");
			for (int k=0;k<cntCol;k++) {
				if (ds1.getString("base_yn",k).equals("Y") || ds1.getString("spare_yn",k).equals("Y")) continue;
				sb1.append("			<if test=\""+ds1.getString("column_name_camel",k)+" != null and "+ds1.getString("column_name_camel",k)+" != '' \">\r\n");
				if (ds1.getString("time_yn",k).equals("Y")) {
					sb1.append("			AND "+ds.getString("table_alias",i)+"."+ds1.getString("column_name",k)+" = STR_TO_DATE(#{"+ds1.getString("column_name_camel",k)+"}, '%Y%m%d %h%i%s')\r\n");
				}else {
					sb1.append("			AND "+ds.getString("table_alias",i)+"."+ds1.getString("column_name",k)+" = #{"+ds1.getString("column_name_camel",k)+"}\r\n");
				}
				sb1.append("			</if>\r\n");
			}
			sb1.append("	</sql>\r\n");
			sb1.append("\r\n");
			sb1.append("	<select id=\"get"+svc_cls_pfix+"ListCount\" parameterType=\""+svc_cls_pfix+"ExDto\" resultType=\"Integer\">\r\n");
			sb1.append("	/* "+svc_cls_pfix_lc+"Mapper.get"+svc_cls_pfix+"ListCount */\r\n");
			sb1.append("		SELECT COUNT(1) AS CNT\r\n");
			sb1.append("		FROM "+ds.getString("table_nm",i)+" "+ds.getString("table_alias",i)+"\r\n");
			sb1.append("		<include refid=\"where-"+svc_cls_pfix_lc+"\" />\r\n");
			sb1.append("	</select>\r\n");
			sb1.append("\r\n");
			sb1.append("	<select id=\"get"+svc_cls_pfix+"List\" parameterType=\""+svc_cls_pfix+"ExDto\" resultType=\""+svc_cls_pfix+"ExDto\">\r\n");
			sb1.append("	/* "+svc_cls_pfix_lc+"Mapper.get"+svc_cls_pfix+"List */\r\n");
			sb1.append("		<include refid=\"commonMapper.pagingPrefix\"/>\r\n");
			sb1.append("		SELECT\r\n");
			for (int k=0;k<cntCol;k++) {
//					if (ds1.getString("spare_yn",k).equals("Y")) continue;
				String sCol = ds.getString("table_alias",i)+"."+ds1.getString("column_name",k);
				if (ds1.getString("time_yn",k).equals("Y")) {
					sCol = ds1.getString("column_name",k);
				}
				if (k==0) {
					sb1.append("			"+sCol+"\r\n");
				}else {
					sb1.append("			,"+sCol+"\r\n");
				}
			}
			sb1.append("		FROM "+ds.getString("table_nm",i)+" "+ds.getString("table_alias",i)+"\r\n");
			sb1.append("		<include refid=\"where-"+svc_cls_pfix_lc+"\" />\r\n");
			sb1.append("		ORDER BY\r\n");
			for (int k=0;k<cntKey;k++) {
				if (k==0) {
					sb1.append("			"+ds.getString("table_alias",i)+"."+ds1.getString("key",k)+"\r\n");
				}else {
					sb1.append("			,"+ds.getString("table_alias",i)+"."+ds1.getString("key",k)+"\r\n");
				}
			}
			sb1.append("		<include refid=\"commonMapper.pagingPostfix\"/>\r\n");
			sb1.append("	</select>\r\n");
			sb1.append("\r\n");
			sb1.append("	<select id=\"get"+svc_cls_pfix+"\" parameterType=\""+svc_cls_pfix+"ExDto\" resultType=\""+svc_cls_pfix+"ExDto\">\r\n");
			sb1.append("	/* "+svc_cls_pfix_lc+"Mapper.get"+svc_cls_pfix+" */\r\n");
			sb1.append("		SELECT\r\n");
			for (int k=0;k<cntCol;k++) {
//				if (ds1.getString("spare_yn",k).equals("Y")) continue;
				String sCol = ds.getString("table_alias",i)+"."+ds1.getString("column_name",k);
				if (ds1.getString("time_yn",k).equals("Y")) {
					sCol = ds1.getString("column_name",k);
				}
				if (k==0) {
					sb1.append("			"+sCol+"\r\n");
				}else {
					sb1.append("			,"+sCol+"\r\n");
				}
			}
			sb1.append("		FROM "+ds.getString("table_nm",i)+" "+ds.getString("table_alias",i)+"\r\n");
			sb1.append("		WHERE\r\n");
			for (int k=0;k<cntKey;k++) {
				if (k==0) {
					sb1.append("			"+ds.getString("table_alias",i)+"."+ds1.getString("key",k)+" = #{"+ds1.getString("key_camel",k)+"}\r\n");
				}else {
					sb1.append("			AND "+ds.getString("table_alias",i)+"."+ds1.getString("key",k)+" = #{"+ds1.getString("key_camel",k)+"}\r\n");
				}
			}
			sb1.append("	</select>\r\n");
			sb1.append("\r\n");
			sb1.append("	<insert id=\"insert"+svc_cls_pfix+"\" parameterType=\""+svc_cls_pfix+"ExDto\">\r\n");
			sb1.append("	/* "+svc_cls_pfix_lc+"Mapper.insert"+svc_cls_pfix+" */\r\n");
			sb1.append("		INSERT INTO "+ds.getString("table_nm",i)+" (\r\n");
			for (int k=0;k<cntCol;k++) {
				if (k==0) {
					sb1.append("			"+ds1.getString("column_name",k)+"\r\n");
				}else {
					if (ds1.getString("spare_yn",k).equals("Y")) {
						sb1.append("			<if test=\""+ds1.getString("column_name_camel",k)+" != null\">,"+ds1.getString("column_name",k)+"</if>\r\n");
					}
					else {
						sb1.append("			,"+ds1.getString("column_name",k)+"\r\n");
					}
				}
			}
			sb1.append("		) VALUES (\r\n");
			for (int k=0;k<cntCol;k++) {
				String s9 = ds1.getString("column_name",k);
				String sCm = "#{"+ds1.getString("column_name_camel",k)+"}";
				if (s9.equals("REG_DTM") || s9.equals("UPD_DTM")) {
					sCm = "NOW()";
				}
				else {
					if (ds1.getString("time_yn",k).equals("Y")) {
						sCm = "STR_TO_DATE(#{"+ds1.getString("column_name_camel",k)+"}, '%Y%m%d %h%i%s')";
					}
				}
				if (k==0) {
					sb1.append("			"+sCm+"\r\n");
				}else {
					if (ds1.getString("spare_yn",k).equals("Y")){
						sb1.append("			<if test=\""+ds1.getString("column_name_camel",k)+" != null\">,"+sCm+"</if>\r\n");
					}
					else {
						sb1.append("			,"+sCm+"\r\n");
					}
				}
			}
			sb1.append("		)\r\n");
			sb1.append("	</insert>\r\n");
			sb1.append("\r\n");
			sb1.append("	<update id=\"update"+svc_cls_pfix+"\" parameterType=\""+svc_cls_pfix+"ExDto\">\r\n");
			sb1.append("	/* "+svc_cls_pfix_lc+"Mapper.update"+svc_cls_pfix+" */\r\n");
			sb1.append("		UPDATE "+ds.getString("table_nm",i)+"\r\n");
			sb1.append("		<set>\r\n");
			for (int k=0;k<cntCol;k++) {
// 				if (ds1.getString("spare_yn",k).equals("Y")) continue;
				if (ds1.getString("pkey",k).equals("Y")) continue;
				String s9 = ds1.getString("column_name",k);
				if (s9.equals("REG_DTM") || s9.equals("REG_USER_ID")) continue;
				String sCm = "#{"+ds1.getString("column_name_camel",k)+"}";
				if (s9.equals("UPD_DTM")) {
					sCm = "NOW()";
				}
				else {
					if (ds1.getString("time_yn",k).equals("Y")) {
						sCm = "STR_TO_DATE(#{"+ds1.getString("column_name_camel",k)+"}, '%Y%m%d %h%i%s')";
					}
				}
				sb1.append("		<if test=\""+ds1.getString("column_name_camel",k)+" != null\">\r\n");
				sb1.append("		"+ds1.getString("column_name",k)+" = "+sCm+",\r\n");
				sb1.append("		</if>\r\n");
			}
			sb1.append("		</set>\r\n");
			sb1.append("		WHERE\r\n");
			for (int k=0;k<cntKey;k++) {
				if (k==0) {
					sb1.append("			"+ds1.getString("key",k)+" = #{"+ds1.getString("key_camel",k)+"}\r\n");
				}else {
					sb1.append("			AND "+ds1.getString("key",k)+" = #{"+ds1.getString("key_camel",k)+"}\r\n");
				}
			}
			sb1.append("	</update>\r\n");
			sb1.append("\r\n");
			sb1.append("	<delete id=\"delete"+svc_cls_pfix+"\" parameterType=\""+svc_cls_pfix+"ExDto\">\r\n");
			sb1.append("	/* "+svc_cls_pfix_lc+"Mapper.delete"+svc_cls_pfix+" */\r\n");
			sb1.append("	DELETE FROM "+ds.getString("table_nm",i)+"\r\n");
			sb1.append("	WHERE\r\n");
			for (int k=0;k<cntKey;k++) {
				if (k==0) {
					sb1.append("		"+ds1.getString("key",k)+" = #{"+ds1.getString("key_camel",k)+"}\r\n");
				}else {
					sb1.append("		AND "+ds1.getString("key",k)+" = #{"+ds1.getString("key_camel",k)+"}\r\n");
				}
			}
			sb1.append("	</delete>\r\n");
			sb1.append("</mapper>\r\n");
			
			bw.write(sb1.toString());
		}catch(Exception e){}
		finally{ try{if (bw !=null) bw.close();} catch(Exception e){try { if ( bw != null) { bw.close();} } catch(Exception ex) {}}}
	}

	public static void setMapper(TDataSet ds, TDataSet ds1, int i){
		BufferedWriter bw = null;
		try{
			String duty_div = ds.getString("duty_div",i);
			String svc_cls_pfix = ds.getString("svc_cls_pfix",i);
			String svc_cls_pfix_lc = ds.getString("svc_cls_pfix_lc",i);
			String cls_pfix = ds.getString("cls_pfix",i);
			String mapper_path = ds.getString("mapper_path",i);
// 			String path = "/src/main/java/com/app/   -- code\mapper\CodeMapper.java";
			String path = mapper_path+duty_div+"/mapper/";
			newPath(DIR + path);
			String fNm = DIR + path + svc_cls_pfix + "Mapper.java";
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fNm),"UTF-8"));
			
			StringBuffer sb1 = new StringBuffer();
			sb1.append("package "+cls_pfix+"."+duty_div+".mapper;\r\n");
			sb1.append("\r\n");
			sb1.append("import java.util.List;\r\n");
			sb1.append("\r\n");
			sb1.append("import org.springframework.stereotype.Repository;\r\n");
			sb1.append("\r\n");
			sb1.append("import "+cls_pfix+"."+duty_div+".model."+svc_cls_pfix+"ExDto;\r\n");
			sb1.append("import com.base.DaoBaseMapper;\r\n");
			sb1.append("\r\n");
			sb1.append("/**\r\n");
			sb1.append("* <pre>\r\n");
			sb1.append("* "+cls_pfix+"."+duty_div+".mapper\r\n");
			sb1.append("* "+svc_cls_pfix+"Mapper.java\r\n");
			sb1.append("*\r\n");
			sb1.append("* </pre>\r\n");
			sb1.append("* @date : \r\n");
			sb1.append("* @version : \r\n");
			sb1.append("* @author : \r\n");
			sb1.append("*/\r\n");
			sb1.append("\r\n");
			sb1.append("@Repository(\""+svc_cls_pfix_lc+"Mapper\")\r\n");
			sb1.append("@SuppressWarnings({ \"unchecked\", \"deprecation\" })\r\n");
			sb1.append("public class "+svc_cls_pfix+"Mapper  extends DaoBaseMapper {\r\n");
			sb1.append("//	public class "+svc_cls_pfix+"Mapper  extends DaoBaseMapper {\r\n");
			sb1.append("\r\n");
			sb1.append("	public int get"+svc_cls_pfix+"ListCount( "+svc_cls_pfix+"ExDto "+svc_cls_pfix_lc+"ExDto ) throws Exception {\r\n");
			sb1.append("		return (Integer)selectByPk(\""+svc_cls_pfix_lc+"Mapper.get"+svc_cls_pfix+"ListCount\", "+svc_cls_pfix_lc+"ExDto);\r\n");
			sb1.append("	}\r\n");
			sb1.append("	\r\n");
			sb1.append("	public List<"+svc_cls_pfix+"ExDto> get"+svc_cls_pfix+"List( "+svc_cls_pfix+"ExDto "+svc_cls_pfix_lc+"ExDto ) throws Exception {\r\n");
			sb1.append("		return (List<"+svc_cls_pfix+"ExDto>)list(\""+svc_cls_pfix_lc+"Mapper.get"+svc_cls_pfix+"List\", "+svc_cls_pfix_lc+"ExDto);\r\n");
			sb1.append("	}\r\n");
			sb1.append("	\r\n");
			sb1.append("	public "+svc_cls_pfix+"ExDto get"+svc_cls_pfix+"( "+svc_cls_pfix+"ExDto "+svc_cls_pfix_lc+"ExDto ) throws Exception {\r\n");
			sb1.append("		return ("+svc_cls_pfix+"ExDto) selectByPk(\""+svc_cls_pfix_lc+"Mapper.get"+svc_cls_pfix+"\", "+svc_cls_pfix_lc+"ExDto);\r\n");
			sb1.append("	}\r\n");
			sb1.append("\r\n");
			sb1.append("	public void insert"+svc_cls_pfix+"( "+svc_cls_pfix+"ExDto "+svc_cls_pfix_lc+"ExDto ) throws Exception {\r\n");
			sb1.append("		insert(\""+svc_cls_pfix_lc+"Mapper.insert"+svc_cls_pfix+"\", "+svc_cls_pfix_lc+"ExDto);\r\n");
			sb1.append("	}\r\n");
			sb1.append("	\r\n");
			sb1.append("	public void update"+svc_cls_pfix+"( "+svc_cls_pfix+"ExDto "+svc_cls_pfix_lc+"ExDto ) throws Exception {\r\n");
			sb1.append("		update(\""+svc_cls_pfix_lc+"Mapper.update"+svc_cls_pfix+"\", "+svc_cls_pfix_lc+"ExDto);\r\n");
			sb1.append("	}\r\n");
			sb1.append("\r\n");
			sb1.append("	public void delete"+svc_cls_pfix+"( "+svc_cls_pfix+"ExDto "+svc_cls_pfix_lc+"ExDto ) throws Exception {\r\n");
			sb1.append("		delete(\""+svc_cls_pfix_lc+"Mapper.delete"+svc_cls_pfix+"\", "+svc_cls_pfix_lc+"ExDto);\r\n");
			sb1.append("	}\r\n");
			sb1.append("\r\n");
			sb1.append("}\r\n");
			bw.write(sb1.toString());
		}catch(Exception e){}
		finally{ try{if (bw !=null) bw.close();} catch(Exception e){try { if ( bw != null) { bw.close();} } catch(Exception ex) {}}}
	}

	public static void setDto(TDataSet ds, TDataSet ds1, int i){
		BufferedWriter bw = null;
		try{
			String duty_div = ds.getString("duty_div",i);
			String svc_cls_pfix_lc = ds.getString("svc_cls_pfix_lc",i);
			String svc_cls_pfix = ds.getString("svc_cls_pfix",i);
			String cls_pfix = ds.getString("cls_pfix",i);
			String dto_path = ds.getString("dto_path",i);
			int cntCol = ds1.getRowCount("column_name");

// 			String path = "/src/main/java/com/app/   -- code\model\CodeDto.java";
			String path = dto_path+duty_div+"/model/";
			newPath(DIR + path);
			String fNm = DIR + path + svc_cls_pfix +"Dto.java";
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fNm),"UTF-8"));

			StringBuffer sb1 = new StringBuffer();
			sb1.append("package "+cls_pfix+"."+duty_div+".model;\r\n");
			sb1.append("\r\n");
			sb1.append("import com.base.BaseDto;\r\n");
			sb1.append("\r\n");
			sb1.append("import lombok.Getter;\r\n");
			sb1.append("import lombok.Setter;\r\n");
			sb1.append("\r\n");
			sb1.append("/**\r\n");
			sb1.append("* <pre>\r\n");
			sb1.append("* "+cls_pfix+"."+duty_div+".model\r\n");
			sb1.append("* "+svc_cls_pfix+"Dto.java\r\n");
			sb1.append("* \r\n");
			sb1.append("* </pre>\r\n");
			sb1.append("* @date : \r\n");
			sb1.append("* @version : \r\n");
			sb1.append("* @author : \r\n");
			sb1.append("*/\r\n");
			sb1.append("\r\n");
			sb1.append("@Getter\r\n");
			sb1.append("@Setter\r\n");
			sb1.append("public class "+svc_cls_pfix+"Dto extends BaseDto {\r\n");
			sb1.append("\r\n");
			sb1.append("	@SuppressWarnings(\"unused\")\r\n");
			sb1.append("	private static final long serialVersionUID = 2665419922181909329L;\r\n");
			for (int k=0;k<cntCol;k++) {
// 				if (ds1.getString("spare_yn",k).equals("Y")) continue;
				String s9 = ds1.getString("column_name",k);
				if (s9.equals("REG_DTM") || s9.equals("REG_USER_ID") || s9.equals("UPD_DTM") || s9.equals("UPD_USER_ID")) continue;
				String dType = "String";
				if (ds1.getString("data_type",k).equals("numeric")) {
					dType = "Long";
					int iScale = ds1.getInteger("data_scale",k);
					if (iScale > 0) dType = "Double";
				}
				else if (ds1.getString("data_type",k).indexOf("int") > -1) {
					dType = "int";
				}
				else if (ds1.getString("data_type",k).indexOf("date") > -1) {
					dType = "Timestamp";
				}
				sb1.append("	// "+ds1.getString("c_nm",k)+"\r\n");
				sb1.append("	private "+dType+" "+ds1.getString("column_name_camel",k)+";\r\n");
			}
			sb1.append("\r\n");
			sb1.append("}\r\n");
			bw.write(sb1.toString());
		}catch(Exception e){}
		finally{ try{if (bw !=null) bw.close();} catch(Exception e){try { if ( bw != null) { bw.close();} } catch(Exception ex) {}}}
	}

	public static void setDtoEx(TDataSet ds, TDataSet ds1, int i){
		BufferedWriter bw = null;
		try{
			String duty_div = ds.getString("duty_div",i);
			String svc_cls_pfix_lc = ds.getString("svc_cls_pfix_lc",i);
			String svc_cls_pfix = ds.getString("svc_cls_pfix",i);
			String cls_pfix = ds.getString("cls_pfix",i);
			String dto_path = ds.getString("dto_path",i);
			
// 			String path = "/src/main/java/com/app/   -- code\model\CodeDto.java";
			String path = dto_path+duty_div+"/model/";
			newPath(DIR + path);
			String fNm = DIR + path + svc_cls_pfix + "ExDto.java";
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fNm),"UTF-8"));
			
			StringBuffer sb1 = new StringBuffer();
			sb1.append("package "+cls_pfix+"."+duty_div+".model;\r\n");
			sb1.append("\r\n");
			sb1.append("import lombok.Getter;\r\n");
			sb1.append("import lombok.Setter;\r\n");
			sb1.append("\r\n");
			sb1.append("@Getter\r\n");
			sb1.append("@Setter\r\n");
			sb1.append("public class "+svc_cls_pfix+"ExDto extends "+svc_cls_pfix+"Dto {\r\n");
			sb1.append("\r\n");
			sb1.append("	@SuppressWarnings(\"unused\")\r\n");
			sb1.append("	private static final long serialVersionUID = 6573658552944941027L;\r\n");
			sb1.append("\r\n");
			sb1.append("}\r\n");

			bw.write(sb1.toString());
		}catch(Exception e){}
		finally{ try{if (bw !=null) bw.close();} catch(Exception e){try { if ( bw != null) { bw.close();} } catch(Exception ex) {}}}
	}
	public static void setService(TDataSet ds, TDataSet ds1, int i){
		BufferedWriter bw = null;
		try{
			String duty_div = ds.getString("duty_div",i);
			String svc_cls_pfix = ds.getString("svc_cls_pfix",i);
			String svc_cls_pfix_lc = ds.getString("svc_cls_pfix_lc",i);
			String cls_pfix = ds.getString("cls_pfix",i);
			String service_path = ds.getString("service_path",i);
// 			String path = "/src/main/java/com/app/   -- code\service\CodeService.java";
			String path = service_path+duty_div+"/service/";
			newPath(DIR + path);
			String fNm = DIR + path + svc_cls_pfix + "Service.java";
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fNm),"UTF-8"));
			StringBuffer sb1 = new StringBuffer();
			sb1.append("package "+cls_pfix+"."+duty_div+".service;\r\n");
			sb1.append("\r\n");
			sb1.append("import java.util.List;\r\n");
			sb1.append("\r\n");
			sb1.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
			sb1.append("import org.springframework.context.annotation.Lazy;\r\n");
			sb1.append("import org.springframework.stereotype.Service;\r\n");
			sb1.append("import org.springframework.transaction.annotation.Propagation;\r\n");
			sb1.append("import org.springframework.transaction.annotation.Transactional;\r\n");
			sb1.append("\r\n");
			sb1.append("import "+cls_pfix+"."+duty_div+".mapper."+svc_cls_pfix+"Mapper;\r\n");
			sb1.append("import "+cls_pfix+"."+duty_div+".model."+svc_cls_pfix+"ExDto;\r\n");
			sb1.append("\r\n");
			sb1.append("@Service\r\n");
			sb1.append("@Lazy\r\n");
			sb1.append("@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)\r\n");
			sb1.append("public class "+svc_cls_pfix+"Service {\r\n");
			sb1.append("\r\n");
			sb1.append("	@Autowired\r\n");
			sb1.append("	private "+svc_cls_pfix+"Mapper "+svc_cls_pfix_lc+"Mapper;\r\n");
			sb1.append("\r\n");
			sb1.append("	public int get"+svc_cls_pfix+"ListCount("+svc_cls_pfix+"ExDto "+svc_cls_pfix_lc+"ExDto) throws Exception {\r\n");
			sb1.append("		return "+svc_cls_pfix_lc+"Mapper.get"+svc_cls_pfix+"ListCount("+svc_cls_pfix_lc+"ExDto);\r\n");
			sb1.append("	}\r\n");
			sb1.append("\r\n");
			sb1.append("	public List<"+svc_cls_pfix+"ExDto> get"+svc_cls_pfix+"List("+svc_cls_pfix+"ExDto "+svc_cls_pfix_lc+"ExDto) throws Exception {\r\n");
			sb1.append("		return "+svc_cls_pfix_lc+"Mapper.get"+svc_cls_pfix+"List("+svc_cls_pfix_lc+"ExDto);\r\n");
			sb1.append("	}\r\n");
			sb1.append("\r\n");
			sb1.append("	public "+svc_cls_pfix+"ExDto get"+svc_cls_pfix+"("+svc_cls_pfix+"ExDto "+svc_cls_pfix_lc+"ExDto) throws Exception {\r\n");
			sb1.append("		return "+svc_cls_pfix_lc+"Mapper.get"+svc_cls_pfix+"("+svc_cls_pfix_lc+"ExDto);\r\n");
			sb1.append("	}\r\n");
			sb1.append("\r\n");
			sb1.append("	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)\r\n");
			sb1.append("	public void insert"+svc_cls_pfix+"("+svc_cls_pfix+"ExDto "+svc_cls_pfix_lc+"ExDto) throws Exception {\r\n");
			sb1.append("		"+svc_cls_pfix_lc+"Mapper.insert"+svc_cls_pfix+"("+svc_cls_pfix_lc+"ExDto);\r\n");
			sb1.append("	}\r\n");
			sb1.append("\r\n");
			sb1.append("	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)\r\n");
			sb1.append("	public void update"+svc_cls_pfix+"("+svc_cls_pfix+"ExDto "+svc_cls_pfix_lc+"ExDto) throws Exception {\r\n");
			sb1.append("		"+svc_cls_pfix_lc+"Mapper.update"+svc_cls_pfix+"("+svc_cls_pfix_lc+"ExDto);\r\n");
			sb1.append("	}\r\n");
			sb1.append("\r\n");
			sb1.append("	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)\r\n");
			sb1.append("	public void delete"+svc_cls_pfix+"("+svc_cls_pfix+"ExDto "+svc_cls_pfix_lc+"ExDto) throws Exception {\r\n");
			sb1.append("		"+svc_cls_pfix_lc+"Mapper.delete"+svc_cls_pfix+"("+svc_cls_pfix_lc+"ExDto);\r\n");
			sb1.append("	}\r\n");
			sb1.append("}\r\n");

			bw.write(sb1.toString());
		}catch(Exception e){}
		finally{ try{if (bw !=null) bw.close();} catch(Exception e){try { if ( bw != null) { bw.close();} } catch(Exception ex) {}}}
	}
	public static void setController(TDataSet ds, TDataSet ds1, int i){
		BufferedWriter bw = null;
		try{
			String duty_div = ds.getString("duty_div",i);
			String duty_div_sub = ds.getString("duty_div_sub",i);
			String message_nm_sub = "";
			if (duty_div_sub.length() > 0) {
				message_nm_sub = duty_div_sub.substring(0, 1).toUpperCase() + duty_div_sub.substring(1);
				duty_div_sub = "/" + duty_div_sub;
			}
			String svc_cls_pfix = ds.getString("svc_cls_pfix",i);
			String svc_cls_pfix_lc = ds.getString("svc_cls_pfix_lc",i);
			String cls_pfix = ds.getString("cls_pfix",i);
			String controller_path = ds.getString("controller_path",i);
			int cntCol = ds1.getRowCount("column_name");
			
// 			String path = "/src/main/java/com/app/   -- code\controller\CodeController.java";
			String path = controller_path+duty_div+"/controller/";
			newPath(DIR + path);
			String fNm = DIR + path + svc_cls_pfix + "Controller.java";
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fNm),"UTF-8"));

			StringBuffer sb1 = new StringBuffer();
			sb1.append("package "+cls_pfix+"."+duty_div+".controller;\r\n");
			sb1.append("\r\n");
			sb1.append("import java.util.HashMap;\r\n");
			sb1.append("import java.util.List;\r\n");
			sb1.append("import java.util.Map;\r\n");
			sb1.append("\r\n");
			sb1.append("import javax.servlet.http.HttpServletRequest;\r\n");
			sb1.append("import javax.servlet.http.HttpServletResponse;\r\n");
			sb1.append("import javax.validation.Valid;\r\n");
			sb1.append("\r\n");
			sb1.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
			sb1.append("import org.springframework.context.annotation.Lazy;\r\n");
			sb1.append("import org.springframework.stereotype.Controller;\r\n");
			sb1.append("import org.springframework.ui.Model;\r\n");
			sb1.append("import org.springframework.web.bind.annotation.RequestMapping;\r\n");
			sb1.append("import org.springframework.web.bind.annotation.RequestMethod;\r\n");
			sb1.append("import org.springframework.web.bind.annotation.ResponseBody;\r\n");
			sb1.append("\r\n");
			sb1.append("import "+cls_pfix+"."+duty_div+".model."+svc_cls_pfix+"ExDto;\r\n");
			sb1.append("import "+cls_pfix+"."+duty_div+".service."+svc_cls_pfix+"Service;\r\n");
			sb1.append("import com.base.ResponseList;\r\n");
			sb1.append("import com.base.ResponseMap;\r\n");
			sb1.append("import com.common.utils.ClassUtil;\r\n");
			sb1.append("import com.common.utils.Excel;\r\n");
			sb1.append("import com.common.utils.MapUtil;\r\n");
			sb1.append("\r\n");
			sb1.append("import lombok.RequiredArgsConstructor;\r\n");
			sb1.append("\r\n");
			sb1.append("/**\r\n");
			sb1.append("* "+ds1.getString("t_nm",i)+" 관리\r\n");
			sb1.append("*\r\n");
			sb1.append("* @author\r\n");
			sb1.append("*\r\n");
			sb1.append("*/\r\n");
			sb1.append("\r\n");
			sb1.append("@Controller\r\n");
			sb1.append("@Lazy\r\n");
			sb1.append("@RequiredArgsConstructor\r\n");
			sb1.append("public class "+svc_cls_pfix+"Controller {\r\n");
			sb1.append("\r\n");
			sb1.append("	@Autowired\r\n");
			sb1.append("	private "+svc_cls_pfix+"Service "+svc_cls_pfix_lc+"Service;\r\n");
			sb1.append("\r\n");
			sb1.append("	@RequestMapping(\"app/"+duty_div+"/"+svc_cls_pfix_lc+"List.do\")\r\n");
			sb1.append("	public String "+svc_cls_pfix_lc+"List() throws Exception {\r\n");
			sb1.append("		return \"app/"+duty_div+"/"+svc_cls_pfix_lc+"List\";\r\n");
			sb1.append("	}\r\n");
			sb1.append("\r\n");
			sb1.append("	@RequestMapping(\"app/popup/"+duty_div+"/"+svc_cls_pfix_lc+"Pop.do\")\r\n");
			sb1.append("	public String "+svc_cls_pfix_lc+"Pop(HttpServletRequest request, Model model) throws Exception {\r\n");
			sb1.append("		model = MapUtil.requestToModel(request, model);\r\n");
			sb1.append("		return \"app/popup/"+duty_div+"/"+svc_cls_pfix_lc+"Pop\";\r\n");
			sb1.append("	}\r\n");
			sb1.append("\r\n");
			sb1.append("	@RequestMapping(\"app/popup/"+duty_div+"/"+svc_cls_pfix_lc+"InfoPop.do\")\r\n");
			sb1.append("	public String "+svc_cls_pfix_lc+"InfoPop(HttpServletRequest request, Model model) throws Exception {\r\n");
			sb1.append("		model = MapUtil.requestToModel(request, model);\r\n");
			sb1.append("		return \"app/popup/"+duty_div+"/"+svc_cls_pfix_lc+"InfoPop\";\r\n");
			sb1.append("	}\r\n");
			sb1.append("\r\n");
			sb1.append("	@RequestMapping(\"app/popup/"+duty_div+"/"+svc_cls_pfix_lc+"SearchPop.do\")\r\n");
			sb1.append("	public String "+svc_cls_pfix_lc+"SearchPop(HttpServletRequest request, Model model) throws Exception {\r\n");
			sb1.append("		model = MapUtil.requestToModel(request, model);\r\n");
			sb1.append("		return \"app/popup/"+duty_div+"/"+svc_cls_pfix_lc+"SearchPop\";\r\n");
			sb1.append("	}\r\n");
			sb1.append("\r\n");
			sb1.append("	/**\r\n");
			sb1.append("	* "+ds1.getString("t_nm",i)+" List Page\r\n");
			sb1.append("	*\r\n");
			sb1.append("	* @param "+svc_cls_pfix+"ExDto\r\n");
			sb1.append("	* @return Map<String,Object>\r\n");
			sb1.append("	* @throws Exception\r\n");
			sb1.append("	*/\r\n");
			sb1.append("	@RequestMapping(value = \"app/"+duty_div+"/get"+svc_cls_pfix+"PageList.do\", produces = \"application/json;charset=UTF-8\")\r\n");
			sb1.append("	@ResponseBody\r\n");
			sb1.append("	public Object get"+svc_cls_pfix+"PageList(HttpServletRequest request, @Valid final "+svc_cls_pfix+"ExDto params) throws Exception {\r\n");
			sb1.append("		final int totalRows = this."+svc_cls_pfix_lc+"Service.get"+svc_cls_pfix+"ListCount(params);\r\n");
			sb1.append("		final List<"+svc_cls_pfix+"ExDto> "+svc_cls_pfix_lc+"List = this."+svc_cls_pfix_lc+"Service.get"+svc_cls_pfix+"List(params);\r\n");
			sb1.append("		\r\n");
			sb1.append("		Map<String,Object> mapData = (new ResponseList("+svc_cls_pfix_lc+"List, totalRows)).getResultMap();\r\n");
			sb1.append("		\r\n");
			sb1.append("		return mapData;\r\n");
			sb1.append("	}\r\n");
			sb1.append("\r\n");
			sb1.append("	/**\r\n");
			sb1.append("	* "+ds1.getString("t_nm",i)+" List Inquiry\r\n");
			sb1.append("	*\r\n");
			sb1.append("	* @param "+svc_cls_pfix+"ExDto\r\n");
			sb1.append("	* @return Map<String,Object>\r\n");
			sb1.append("	* @throws Exception\r\n");
			sb1.append("	*/\r\n");
			sb1.append("	@RequestMapping(value = \"app/"+duty_div+"/get"+svc_cls_pfix+"List.do\", produces = \"application/json;charset=UTF-8\")\r\n");
			sb1.append("	@ResponseBody\r\n");
			sb1.append("	public List<"+svc_cls_pfix+"ExDto> get"+svc_cls_pfix+"List(@Valid final "+svc_cls_pfix+"ExDto params) throws Exception {\r\n");
			sb1.append("		final List<"+svc_cls_pfix+"ExDto> listData = this."+svc_cls_pfix_lc+"Service.get"+svc_cls_pfix+"List(params);\r\n");
			sb1.append("		\r\n");
			sb1.append("		return listData;\r\n");
			sb1.append("	}\r\n");
			sb1.append("\r\n");
			sb1.append("	/**\r\n");
			sb1.append("	* <pre>\r\n");
			sb1.append("	* 1. 개요 "+ds1.getString("t_nm",i)+" detail infomation Inquiry\r\n");
			sb1.append("	* </pre>\r\n");
			sb1.append("	*\r\n");
			sb1.append("	* @param "+svc_cls_pfix+"ExDto\r\n");
			sb1.append("	* @return : Object\r\n");
			sb1.append("	* @throws Exception\r\n");
			sb1.append("	* @date : \r\n");
			sb1.append("	* @author : \r\n");
			sb1.append("	*/\r\n");
			sb1.append("	@RequestMapping(\"app/"+duty_div+"/get"+svc_cls_pfix+".do\")\r\n");
			sb1.append("	@ResponseBody\r\n");
			sb1.append("	public Object get"+svc_cls_pfix+"(@Valid final "+svc_cls_pfix+"ExDto params) throws Exception {\r\n");
			sb1.append("		final "+svc_cls_pfix+"ExDto mapData = this."+svc_cls_pfix_lc+"Service.get"+svc_cls_pfix+"(params);\r\n");
			sb1.append("		return mapData;\r\n");
			sb1.append("	}\r\n");
			sb1.append("\r\n");
			sb1.append("	/**\r\n");
			sb1.append("	* "+ds1.getString("t_nm",i)+" insert\r\n");
			sb1.append("	*\r\n");
			sb1.append("	* @param "+svc_cls_pfix+"ExDto\r\n");
			sb1.append("	* @return Map<String,Object>\r\n");
			sb1.append("	* @throws Exception\r\n");
			sb1.append("	*/\r\n");
			sb1.append("	@RequestMapping(method = RequestMethod.POST, value = \"app/"+duty_div+"/insert"+svc_cls_pfix+".do\")\r\n");
			sb1.append("	@ResponseBody\r\n");
			sb1.append("	public Map<String,Object> insert"+svc_cls_pfix+"(@Valid final "+svc_cls_pfix+"ExDto params) throws Exception {\r\n");
			sb1.append("		ResponseMap responseMap = new ResponseMap();\r\n");
			sb1.append("		try {\r\n");
			sb1.append("			this."+svc_cls_pfix_lc+"Service.insert"+svc_cls_pfix+"(params);\r\n");
			sb1.append("			responseMap = (new ResponseMap(\"Y\",\"\"));\r\n");
			sb1.append("		}\r\n");
			sb1.append("		catch(Exception e) {\r\n");
			sb1.append("			responseMap = (new ResponseMap(\"N\",\"fail\"));\r\n");
			sb1.append("		}\r\n");
			sb1.append("		Map<String,Object> mapData = responseMap.getResultMap();\r\n");
			sb1.append("		\r\n");
			sb1.append("		return mapData;\r\n");
			sb1.append("	}\r\n");
			sb1.append("\r\n");
			sb1.append("	/**\r\n");
			sb1.append("	* "+ds1.getString("t_nm",i)+" Update\r\n");
			sb1.append("	*\r\n");
			sb1.append("	* @param "+svc_cls_pfix+"ExDto\r\n");
			sb1.append("	* @return Map<String,Object>\r\n");
			sb1.append("	* @throws Exception\r\n");
			sb1.append("	*/\r\n");
			sb1.append("	@RequestMapping(method = RequestMethod.POST, value = \"app/"+duty_div+"/update"+svc_cls_pfix+".do\")\r\n");
			sb1.append("	@ResponseBody\r\n");
			sb1.append("	public Map<String,Object> update"+svc_cls_pfix+"(@Valid final "+svc_cls_pfix+"ExDto params) throws Exception {\r\n");
			sb1.append("		ResponseMap responseMap = new ResponseMap();\r\n");
			sb1.append("		try {\r\n");
			sb1.append("			this."+svc_cls_pfix_lc+"Service.update"+svc_cls_pfix+"(params);\r\n");
			sb1.append("			responseMap = (new ResponseMap(\"Y\",\"\"));\r\n");
			sb1.append("		}\r\n");
			sb1.append("		catch(Exception e) {\r\n");
			sb1.append("			responseMap = (new ResponseMap(\"N\",\"fail\"));\r\n");
			sb1.append("		}\r\n");
			sb1.append("		Map<String,Object> mapData = responseMap.getResultMap();\r\n");
			sb1.append("		\r\n");
			sb1.append("		return mapData;\r\n");
			sb1.append("	}\r\n");
			sb1.append("\r\n");
			sb1.append("	/**\r\n");
			sb1.append("	* "+ds1.getString("t_nm",i)+" delete\r\n");
			sb1.append("	*\r\n");
			sb1.append("	* @param "+svc_cls_pfix+"ExDto\r\n");
			sb1.append("	* @return ResponseEntity<String>\r\n");
			sb1.append("	* @throws Exception\r\n");
			sb1.append("	*/\r\n");
			sb1.append("	@RequestMapping(method = RequestMethod.POST, value = \"app/"+duty_div+"/delete"+svc_cls_pfix+".do\")\r\n");
			sb1.append("	@ResponseBody\r\n");
			sb1.append("	public Map<String,Object> delete"+svc_cls_pfix+"(@Valid final "+svc_cls_pfix+"ExDto params) throws Exception {\r\n");
			sb1.append("		ResponseMap responseMap = new ResponseMap();\r\n");
			sb1.append("		try {\r\n");
			sb1.append("			this."+svc_cls_pfix_lc+"Service.delete"+svc_cls_pfix+"(params);\r\n");
			sb1.append("			responseMap = (new ResponseMap(\"Y\",\"\"));\r\n");
			sb1.append("		}\r\n");
			sb1.append("		catch(Exception e) {\r\n");
			sb1.append("			responseMap = (new ResponseMap(\"N\",\"fail\"));\r\n");
			sb1.append("		}\r\n");
			sb1.append("		Map<String,Object> mapData = responseMap.getResultMap();\r\n");
			sb1.append("		\r\n");
			sb1.append("		return mapData;\r\n");
			sb1.append("	}\r\n");
			sb1.append("\r\n");
			sb1.append("	@SuppressWarnings(\"unchecked\")\r\n");
			sb1.append("	@RequestMapping(\"app/"+duty_div+"/get"+svc_cls_pfix+"ExcelDown.do\")\r\n");
			sb1.append("	@ResponseBody\r\n");
			sb1.append("	public Object get"+svc_cls_pfix+"ExcelDown(HttpServletResponse response,HttpServletRequest request,\r\n");
			sb1.append("			String params) throws Exception {\r\n");
			sb1.append("		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)\r\n");
			sb1.append("		ResponseMap responseMap = null;\r\n");
			sb1.append("		\r\n");
			sb1.append("		Map<String,Object> map = MapUtil.getStrToMap(params);\r\n");
			sb1.append("\r\n");
			sb1.append("		"+svc_cls_pfix+"ExDto "+svc_cls_pfix_lc+"ExDto = new "+svc_cls_pfix+"ExDto();\r\n");
			sb1.append("		"+svc_cls_pfix_lc+"ExDto = ("+svc_cls_pfix+"ExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get(\"condition\"), "+svc_cls_pfix_lc+"ExDto);\r\n");
			sb1.append("		final List<"+svc_cls_pfix+"ExDto> listData = this."+svc_cls_pfix_lc+"Service.get"+svc_cls_pfix+"List("+svc_cls_pfix_lc+"ExDto);\r\n");
			sb1.append("		\r\n");
			sb1.append("		Map<String,Object> m1 = new HashMap<String,Object>();\r\n");
			sb1.append("		m1.put(\"xlsName\", map.get(\"xlsName\"));\r\n");
			sb1.append("		m1.put(\"sheetName\", map.get(\"sheetName\"));\r\n");
			sb1.append("		m1.put(\"columnHeaders\", map.get(\"columnHeaders\"));\r\n");
			sb1.append("		m1.put(\"columnNames\", map.get(\"columnNames\"));\r\n");
			sb1.append("		try {\r\n");
			sb1.append("			Excel.downloadClsToExcel(m1, listData, response, request);\r\n");
			sb1.append("			responseMap = new ResponseMap(\"Y\",\"\");\r\n");
			sb1.append("		}catch(Exception e) {\r\n");
			sb1.append("			responseMap = new ResponseMap(\"N\",\"download error.\");\r\n");
			sb1.append("		}\r\n");
			sb1.append("		\r\n");
			sb1.append("		return responseMap.getResultMap();\r\n");
			sb1.append("	}\r\n");
			sb1.append("}\r\n");
			
			bw.write(sb1.toString());
		}catch(Exception e){}
		finally{ try{if (bw !=null) bw.close();} catch(Exception e){try { if ( bw != null) { bw.close();} } catch(Exception ex) {}}}
	}
	public static void setJsp(TDataSet ds, TDataSet ds1, int i){
		BufferedWriter bw = null;
		try{
			String duty_div = ds.getString("duty_div",i);
			String duty_div_sub = ds.getString("duty_div_sub",i);
			String svc_cls_pfix_lc = ds.getString("svc_cls_pfix_lc",i);
			String svc_cls_pfix = ds.getString("svc_cls_pfix",i);
			int cntKey = ds1.getRowCount("key");
			String message_nm_sub = "";
			if (duty_div_sub.length() > 0) {
				message_nm_sub = duty_div_sub.substring(0, 1).toUpperCase() + duty_div_sub.substring(1);
				duty_div_sub = "l" + duty_div_sub;
			}
			String jsp_path = ds.getString("jsp_path",i);
			int cntCol = ds1.getRowCount("column_name");
			
// 			String path = "/src/main/resources/templates/app/   -- code\codeList.html";
			String path = jsp_path+duty_div+"/";
			newPath(DIR + path);
			String fNm = DIR + path + svc_cls_pfix_lc + "List.html";
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fNm),"UTF-8"));

			StringBuffer sb1 = new StringBuffer();
			sb1.append("<!DOCTYPE html>\r\n");
			sb1.append("<html xmlns:th=\"http://www.thymeleaf.org\">\r\n");
			sb1.append("\r\n");
			sb1.append("<th:block th:include=\"app/common/head\" th:remove=\"tag\"></th:block>\r\n");
			sb1.append("<script type=\"text/javascript\" src=\"/js/app/"+duty_div+"/"+svc_cls_pfix_lc+"Grid.js\"></script>\r\n");
			sb1.append("<script language=\"javascript\" type=\"text/javascript\">\r\n");
			sb1.append("var grid"+svc_cls_pfix+" = null;\r\n");
			sb1.append("window.onload = function() {\r\n");
			sb1.append("	grid"+svc_cls_pfix+" = setGrid"+svc_cls_pfix+"(\"dvGrid"+svc_cls_pfix+"\");\r\n");
			sb1.append("	\r\n");
			sb1.append("	grid"+svc_cls_pfix+".on(\"click\",function(ev){\r\n");
			sb1.append("		toggleGrid(grid"+svc_cls_pfix+",ev.rowKey);\r\n");
			sb1.append("		/* return ev.stop(); */\r\n");
			sb1.append("	});\r\n");
			sb1.append("	grid"+svc_cls_pfix+".on(\"dblclick\",function(ev){\r\n");
			sb1.append("		if (isEmpty(dblClickGrid"+svc_cls_pfix+")==false){\r\n");
			sb1.append("			dblClickGrid"+svc_cls_pfix+"();\r\n");
			sb1.append("		}\r\n");
			sb1.append("		return ev.stop();\r\n");
			sb1.append("	});\r\n");
			sb1.append("}\r\n");
			sb1.append("\r\n");
			sb1.append("//page\r\n");
			sb1.append("var pageCls"+svc_cls_pfix+" = new pageCls(\"dv"+svc_cls_pfix+"PageList\",fnGetList);\r\n");
			sb1.append("\r\n");
			sb1.append("function fnGetList(){\r\n");
			sb1.append("	setClearGrid(grid"+svc_cls_pfix+");\r\n");
			sb1.append("\r\n");
			sb1.append("	var params = {};\r\n");
			for (int k=0;k<cntCol;k++) {
				if (ds1.getString("spare_yn",k).equals("Y")) continue;
				String s9 = ds1.getString("column_name",k);
				if (s9.equals("REG_DTM") || s9.equals("REG_USER_ID") || s9.equals("UPD_DTM") || s9.equals("UPD_USER_ID")) continue;
				sb1.append("	if (isEmpty(getEVal(\""+ds1.getString("column_name_camel",k)+"\"))==false) params[\""+ds1.getString("column_name_camel",k)+"\"] = getEVal(\""+ds1.getString("column_name_camel",k)+"\");\r\n");
			}
			sb1.append("	//page set\r\n");
			sb1.append("	params = pageCls"+svc_cls_pfix+".getPageInfo(params);\r\n");
			sb1.append("\r\n");
			sb1.append("	var url = \"/app/"+duty_div+"/get"+svc_cls_pfix+"PageList.do\";\r\n");
			sb1.append("	var fData = mapToFormdata(params);\r\n");
			sb1.append("	//add page\r\n");
			sb1.append("	\r\n");
			sb1.append("	getUrlData(url,fData).then((result=>{\r\n");
			sb1.append("		if (isEmpty(result.payloads)){\r\n");
			sb1.append("			alert(\"조회 오류 발생.\");\r\n");
			sb1.append("			return;\r\n");
			sb1.append("		}\r\n");
			sb1.append("		setDataGrid(grid"+svc_cls_pfix+",result.payloads);\r\n");
			sb1.append("		//set total\r\n");
			sb1.append("		pageCls"+svc_cls_pfix+".setTotalRowsReload(result.totalRows);\r\n");
			sb1.append("	}));\r\n");
			sb1.append("}\r\n");
			sb1.append("function fnXls"+svc_cls_pfix+"Down(){\r\n");
			sb1.append("	var url = \"/app/"+duty_div+"/get"+svc_cls_pfix+"ExcelDown.do\";\r\n");
			sb1.append("	var paramXls = {};\r\n");
			for (int k=0;k<cntCol;k++) {
				if (ds1.getString("spare_yn",k).equals("Y")) continue;
				String s9 = ds1.getString("column_name",k);
				if (s9.equals("REG_DTM") || s9.equals("REG_USER_ID") || s9.equals("UPD_DTM") || s9.equals("UPD_USER_ID")) continue;
				sb1.append("	if (isEmpty(getEVal(\""+ds1.getString("column_name_camel",k)+"\"))==false) params[\""+ds1.getString("column_name_camel",k)+"\"] = getEVal(\""+ds1.getString("column_name_camel",k)+"\");\r\n");
			}
			sb1.append("\r\n");
			sb1.append("	var params = {\"xlsName\":\""+ds1.getString("t_nm",i)+"\"\r\n");
			sb1.append("		,\"sheetName\":\"sheet1\"\r\n");
			sb1.append("		,\"columnHeaders\":getColInfoXls(grid"+svc_cls_pfix+",\"header\")\r\n");
			sb1.append("		,\"columnNames\":getColInfoXls(grid"+svc_cls_pfix+",\"name\")\r\n");
			sb1.append("		,\"condition\": paramXls\r\n");
			sb1.append("	};\r\n");
			sb1.append("	var fData = mapToFormdata({\"params\":JSON.stringify(params)});\r\n");
			sb1.append("\r\n");
			sb1.append("	getDownFile(url,fData);\r\n");
			sb1.append("}\r\n");
			sb1.append("\r\n");
			sb1.append("function dblClickGrid"+svc_cls_pfix+"(){\r\n");
			sb1.append("	var map = getFocusedRowDataGrid(grid"+svc_cls_pfix+");\r\n");
			sb1.append("	fnOpen"+svc_cls_pfix+"Dialog(map);\r\n");
			sb1.append("}\r\n");
			sb1.append("function new"+svc_cls_pfix+"Diallog(){\r\n");
			sb1.append("	fnOpen"+svc_cls_pfix+"Dialog();\r\n");
			sb1.append("}\r\n");
			sb1.append("function fnOpen"+svc_cls_pfix+"Dialog(map){\r\n");
			sb1.append("	var url = \"/app/popup/"+duty_div+"/"+svc_cls_pfix_lc+"Pop.do\";\r\n");
			sb1.append("	var params = {};\r\n");
			sb1.append("	if (isEmpty(map)==false){\r\n");
			for (int k=0;k<cntKey;k++) {
				if (ds1.getString("base_yn",k).equals("Y") || ds1.getString("spare_yn",k).equals("Y")) continue;
				sb1.append("		params[\""+ds1.getString("key_camel",k)+"\"] = map[\""+ds1.getString("key_camel",k)+"\"];\r\n");
			}
			sb1.append("	}\r\n");
			sb1.append("	else{\r\n");
			for (int k=0;k<cntKey;k++) {
				if (ds1.getString("base_yn",k).equals("Y") || ds1.getString("spare_yn",k).equals("Y")) continue;
				sb1.append("		params[\""+ds1.getString("key_camel",k)+"\"] = \"\";\r\n");
			}
			sb1.append("	}\r\n");
			sb1.append("	var map = {\"transData\":JSON.stringify(params),\"callbackFunc\":\"fnCB"+svc_cls_pfix+"Pop\"};\r\n");
			sb1.append("	popOpenWindow(\""+svc_cls_pfix_lc+"PopId\",url,map);\r\n");
			sb1.append("}\r\n");
			sb1.append("function fnCB"+svc_cls_pfix+"Pop(map){\r\n");
			sb1.append("	//resultCd,resultMsg\r\n");
			sb1.append("	if (map[\"resultCd\"]==\"Y\"){\r\n");
			sb1.append("		fnGetList();\r\n");
			sb1.append("		alert(\"작업이 완료되었습니다.\");\r\n");
			sb1.append("	}\r\n");
			sb1.append("	else{\r\n");
			sb1.append("		alert(map[\"resultMsg\"]);\r\n");
			sb1.append("	}\r\n");
			sb1.append("}\r\n");
			sb1.append("//"+svc_cls_pfix_lc+" data information\r\n");
			sb1.append("function "+svc_cls_pfix_lc+"InfoDiallog(){\r\n");
			sb1.append("	var map = getFocusedRowDataGrid(grid"+svc_cls_pfix+");\r\n");
			sb1.append("	if (isEmpty(map)){\r\n");
			sb1.append("		alert(\"자료를 선택하세요.\");\r\n");
			sb1.append("		return;\r\n");
			sb1.append("	}\r\n");
			sb1.append("	fn"+svc_cls_pfix+"InfoDialog(map);\r\n");
			sb1.append("}\r\n");
			sb1.append("function fn"+svc_cls_pfix+"InfoDialog(map){\r\n");
			sb1.append("	var url = \"/app/popup/"+duty_div+"/"+svc_cls_pfix_lc+"InfoPop.do\";\r\n");
			sb1.append("	var params = {};\r\n");
			for (int k=0;k<cntKey;k++) {
				if (ds1.getString("base_yn",k).equals("Y") || ds1.getString("spare_yn",k).equals("Y")) continue;
				sb1.append("	params[\""+ds1.getString("key_camel",k)+"\"] = map[\""+ds1.getString("key_camel",k)+"\"];\r\n");
			}
			sb1.append("	var map = {\"transData\":JSON.stringify(params),\"callbackFunc\":\"\"};\r\n");
			sb1.append("	popOpenWindow(\""+svc_cls_pfix_lc+"InfoPopId\",url,map);\r\n");
			sb1.append("}\r\n");
			sb1.append("//"+svc_cls_pfix_lc+" search\r\n");
			sb1.append("function "+svc_cls_pfix_lc+"SearchDiallog(){\r\n");
			sb1.append("	fn"+svc_cls_pfix+"SearchDialog();\r\n");
			sb1.append("}\r\n");
			sb1.append("function fn"+svc_cls_pfix+"SearchDialog(map){\r\n");
			sb1.append("	var url = \"/app/popup/"+duty_div+"/"+svc_cls_pfix_lc+"SearchPop.do\";\r\n");
			sb1.append("	var params = {};\r\n");
			sb1.append("	var map = {\"transData\":JSON.stringify(params),\"callbackFunc\":\"fnCB"+svc_cls_pfix+"SearchPop\"};\r\n");
			sb1.append("	popOpenWindow(\""+svc_cls_pfix_lc+"SearchPopId\",url,map,null,400);\r\n");
			sb1.append("}\r\n");
			sb1.append("function fnCB"+svc_cls_pfix+"SearchPop(map){\r\n");
			sb1.append("	//map 적용\r\n");
			sb1.append("	console.log(\"11.....\"+JSON.stringify(map));\r\n");
			sb1.append("}\r\n");
			sb1.append("</script>\r\n");
			sb1.append("<body>\r\n");
			sb1.append("<th:block th:include=\"app/common/header\" th:remove=\"tag\"></th:block>\r\n");
			sb1.append("<section class=\"\">\r\n");
			sb1.append("	<div class=\"inner\">\r\n");
			sb1.append("		<div id=\"dv"+svc_cls_pfix+"Search\" class=\"main__search\">\r\n");
			sb1.append("			<table class=\"input-item\">\r\n");
			sb1.append("			<colgroup>\r\n");
			sb1.append("				<col width=\"5%\">\r\n");
			sb1.append("				<col width=\"25%\">\r\n");
			sb1.append("				<col width=\"5%\">\r\n");
			sb1.append("				<col width=\"25%\">\r\n");
			sb1.append("				<col width=\"5%\">\r\n");
			sb1.append("				<col width=\"45%\">\r\n");
			sb1.append("			</colgroup>\r\n");
			sb1.append("			<tbody>\r\n");
			sb1.append("				<tr>\r\n");
			sb1.append("					<th scope=\"row\">조회1</th>\r\n");
			sb1.append("					<td>\r\n");
			sb1.append("						<input type=\"text\" name=\"cond1\" id=\"cond1\" placeholder=\"조회1\" style=\"width:80%\" title=\"조회1\">\r\n");
			sb1.append("					</td>\r\n");
			sb1.append("					<th scope=\"row\">조회2</th>\r\n");
			sb1.append("					<td>\r\n");
			sb1.append("						<input type=\"text\" name=\"cond2\" id=\"cond2\" placeholder=\"조회2\" style=\"width:80%\" title=\"조회2\">\r\n");
			sb1.append("					</td>\r\n");
			sb1.append("					<th scope=\"row\">조회3</th>\r\n");
			sb1.append("					<td>\r\n");
			sb1.append("						<input type=\"text\" name=\"cond3\" id=\"cond3\" placeholder=\"조회3\" style=\"width:80%\" title=\"조회3\">\r\n");
			sb1.append("					</td>\r\n");
			sb1.append("				</tr>\r\n");
			sb1.append("			</tbody>\r\n");
			sb1.append("			</table>\r\n");
			sb1.append("\r\n");
			sb1.append("			<div class=\"search__bottom\">\r\n");
			sb1.append("				<div class=\"search__bottom-box\">\r\n");
			sb1.append("					<button type=\"button\" id=\"searchBtn\" class=\"btn btn-primary btn-sm btn-search\" onclick=\"javascript:fnGetList();\"><i class=\"fas fa-search\"></i>조회</button>\r\n");
			sb1.append("				</div>\r\n");
			sb1.append("			</div>\r\n");
			sb1.append("		</div>\r\n");
			sb1.append("		<div class=\"containerGrid\">\r\n");
			sb1.append("			<div class=\"col2-left clearfix\">\r\n");
			sb1.append("				<div id=\"dv"+svc_cls_pfix+"Disp\" class=\"inBlock\">\r\n");
			sb1.append("					<span id=\"totalCnt\" class=\"ma-r-sm fc-mg\">10</span>건 / <span id=\"totalPages\" class=\"fc-lg\"></span>\r\n");
			sb1.append("				</div>\r\n");
			sb1.append("\r\n");
			sb1.append("				<div id=\"dv"+svc_cls_pfix+"PageIdx\" class=\"inBlock\">\r\n");
			sb1.append("					<select>\r\n");
			sb1.append("						<option value=\"10\">10개씩 보기</option>\r\n");
			sb1.append("						<option value=\"15\" selected>15개씩 보기</option>\r\n");
			sb1.append("						<option value=\"30\">30개씩 보기</option>\r\n");
			sb1.append("						<option value=\"50\">50개씩 보기</option>\r\n");
			sb1.append("					</select>\r\n");
			sb1.append("				</div>\r\n");
			sb1.append("\r\n");
			sb1.append("			</div>\r\n");
			sb1.append("			<div class=\"col2-left clearfix\">\r\n");
			sb1.append("				<button type=\"button\" class=\"btn btn-primary btn-sm btn-enter\" onclick=\"fnXls"+svc_cls_pfix+"Down();\">Download</button>\r\n");
			sb1.append("			</div>\r\n");
			sb1.append("\r\n");
			sb1.append("			<div class=\"col2-right clearfix\">\r\n");
			sb1.append("				<button type=\"button\" class=\"btn btn-primary btn-sm btn-modify\" onclick=\"javascript:new"+svc_cls_pfix+"Diallog();\">신규</button>\r\n");
			sb1.append("				<button type=\"button\" class=\"btn btn-primary btn-sm btn-modify\" onclick=\"javascript:dblClickGrid"+svc_cls_pfix+"();\">수정</button>\r\n");
			sb1.append("			</div>\r\n");
			sb1.append("\r\n");
			sb1.append("			<div>\r\n");
			sb1.append("				<div id=\"dvGrid"+svc_cls_pfix+"\"></div>\r\n");
			sb1.append("				<div id=\"dv"+svc_cls_pfix+"PageList\" class=\"table-num\" style=\"margin-top: 10px;\"></div>\r\n");
			sb1.append("			</div>\r\n");
			sb1.append("		</div>\r\n");
			sb1.append("	</div>\r\n");
			sb1.append("</section>\r\n");
			sb1.append("<th:block th:include=\"app/common/footer\" th:remove=\"tag\"></th:block>\r\n");
			sb1.append("</body>\r\n");
			sb1.append("</html>\r\n");
			
			bw.write(sb1.toString());
		}catch(Exception e){}
		finally{ try{if (bw !=null) bw.close();} catch(Exception e){try { if ( bw != null) { bw.close();} } catch(Exception ex) {}}}
	}
	public static void setJsp2(TDataSet ds, TDataSet ds1, int i){
		BufferedWriter bw = null;
		try{
			String duty_div = ds.getString("duty_div",i);
			String duty_div_sub = ds.getString("duty_div_sub",i);
			String svc_cls_pfix_lc = ds.getString("svc_cls_pfix_lc",i);
			String svc_cls_pfix = ds.getString("svc_cls_pfix",i);
			int cntKey = ds1.getRowCount("key");
			String message_nm_sub = "";
			if (duty_div_sub.length() > 0) {
				message_nm_sub = duty_div_sub.substring(0, 1).toUpperCase() + duty_div_sub.substring(1);
				duty_div_sub = "l" + duty_div_sub;
			}
			String jsp_path = ds.getString("jsp_path",i);
			int cntCol = ds1.getRowCount("column_name");
			
// 			String path = "/src/main/resources/templates/app/   -- code\codeList.html";
			String path = jsp_path+duty_div+"/";
			newPath(DIR + path);
			String fNm = DIR + path + svc_cls_pfix_lc + "List.html";
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fNm),"UTF-8"));

			StringBuffer sb1 = new StringBuffer();

			sb1.append("<!DOCTYPE html>\r\n");
			sb1.append("<html xmlns:th=\"http://www.thymeleaf.org\">\r\n");
			sb1.append("\r\n");
			sb1.append("<th:block th:include=\"app/common/head\" th:remove=\"tag\"></th:block>\r\n");
			sb1.append("<script language=\"javascript\" type=\"text/javascript\">\r\n");
			sb1.append("window.onload = function() {\r\n");
			sb1.append("	//\r\n");
			sb1.append("}\r\n");
			sb1.append("\r\n");
			sb1.append("//page\r\n");
			sb1.append("var pageCls"+svc_cls_pfix+" = new pageCls(\"dv"+svc_cls_pfix+"PageList\",fnGetList,\"totalCnt\");\r\n");
			sb1.append("\r\n");
			sb1.append("function fnGetList(){\r\n");
			sb1.append("	var params = {};\r\n");
			for (int k=0;k<cntCol;k++) {
				if (ds1.getString("spare_yn",k).equals("Y")) continue;
				String s9 = ds1.getString("column_name",k);
				if (s9.equals("REG_DTM") || s9.equals("REG_USER_ID") || s9.equals("UPD_DTM") || s9.equals("UPD_USER_ID")) continue;
				sb1.append("	if (isEmpty(getEVal(\""+ds1.getString("column_name_camel",k)+"\"))==false) params[\""+ds1.getString("column_name_camel",k)+"\"] = getEVal(\""+ds1.getString("column_name_camel",k)+"\");\r\n");
			}
			sb1.append("	//page set\r\n");
			sb1.append("	params = pageCls"+svc_cls_pfix+".getPageInfo(params);\r\n");
			sb1.append("\r\n");
			sb1.append("	var url = \"/app/"+duty_div+"/get"+svc_cls_pfix+"PageList.do\";\r\n");
			sb1.append("	//add page\r\n");
			sb1.append("	var rObj = httpSend(url,params);\r\n");
			sb1.append("	var ds = new BDataSet(rObj[\"payloads\"]);\r\n");
			sb1.append("	\r\n");
			sb1.append("	var vArr = [");
			int cntCol3 = 0;
			for (int k=0;k<cntCol;k++) {
				if (ds1.getString("spare_yn",k).equals("Y")) continue;
				String s9 = ds1.getString("column_name",k);
				if (s9.equals("REG_DTM") || s9.equals("REG_USER_ID") || s9.equals("UPD_DTM") || s9.equals("UPD_USER_ID")) continue;
				if (k == 0){
					sb1.append("\""+ds1.getString("column_name_camel",k)+"\"");
				}else{
					sb1.append(",\""+ds1.getString("column_name_camel",k)+"\"");
				}
				cntCol3++;
			}
			sb1.append("];\r\n");
			sb1.append("	setDrawTbl(\"tblIdxList\",\"dvTrList\",vArr,ds,"+ cntCol3 +");\r\n");
			sb1.append("	pageCls"+svc_cls_pfix+".setTotalRowsReload(rObj[\"totalRows\"]);\r\n");
			sb1.append("}\r\n");
			sb1.append("\r\n");
			sb1.append("function fnXls"+svc_cls_pfix+"Down(){\r\n");
			sb1.append("	var url = \"/app/"+duty_div+"/get"+svc_cls_pfix+"ExcelDown.do\";\r\n");
			sb1.append("	var paramXls = {};\r\n");
			for (int k=0;k<cntCol;k++) {
				if (ds1.getString("spare_yn",k).equals("Y")) continue;
				String s9 = ds1.getString("column_name",k);
				if (s9.equals("REG_DTM") || s9.equals("REG_USER_ID") || s9.equals("UPD_DTM") || s9.equals("UPD_USER_ID")) continue;
				sb1.append("	if (isEmpty(getEVal(\""+ds1.getString("column_name_camel",k)+"\"))==false) paramXls[\""+ds1.getString("column_name_camel",k)+"\"] = getEVal(\""+ds1.getString("column_name_camel",k)+"\");\r\n");
			}

			sb1.append("\r\n");
			sb1.append("	var params = {\"xlsName\":\""+ds1.getString("t_nm",0)+"\"\r\n");
			sb1.append("		,\"sheetName\":\"시트\"\r\n");

			sb1.append("		,\"columnHeaders\":[");
			for (int k=0;k<cntCol;k++) {
				if (ds1.getString("spare_yn",k).equals("Y")) continue;
				String s9 = ds1.getString("column_name",k);
				if (s9.equals("REG_DTM") || s9.equals("REG_USER_ID") || s9.equals("UPD_DTM") || s9.equals("UPD_USER_ID")) continue;
				if (k == 0){
					sb1.append("\""+ds1.getString("c_nm",k)+"\"");
				}else{
					sb1.append(",\""+ds1.getString("c_nm",k)+"\"");
				}
			}
			sb1.append("]\r\n");

			sb1.append("		,\"columnNames\":[");
			for (int k=0;k<cntCol;k++) {
				if (ds1.getString("spare_yn",k).equals("Y")) continue;
				String s9 = ds1.getString("column_name",k);
				if (s9.equals("REG_DTM") || s9.equals("REG_USER_ID") || s9.equals("UPD_DTM") || s9.equals("UPD_USER_ID")) continue;
				if (k == 0){
					sb1.append("\""+ds1.getString("column_name_camel",k)+"\"");
				}else{
					sb1.append(",\""+ds1.getString("column_name_camel",k)+"\"");
				}
			}
			sb1.append("]\r\n");

			sb1.append("		,\"condition\": paramXls\r\n");
			sb1.append("	};\r\n");
			sb1.append("	var fData = mapToFormdata({\"params\":JSON.stringify(params)});\r\n");
			sb1.append("\r\n");
			sb1.append("	getDownFile(url,fData);\r\n");
			sb1.append("}\r\n");
			sb1.append("\r\n");
			sb1.append("function fnOpenDialog(mode){\r\n");
			sb1.append("	var key = \"\";\r\n");
			sb1.append("	if (mode == \"MODIFY\"){\r\n");
			sb1.append("		key = getTrKey(\"tblIdxList\");\r\n");
			sb1.append("		if (isEmpty(key)){\r\n");
			sb1.append("			alert(\"수정 할 자료가 존재하지 않습니다.\");\r\n");
			sb1.append("			return false;\r\n");
			sb1.append("		}\r\n");
			sb1.append("	}\r\n");
			sb1.append("	var params = {};\r\n");
			sb1.append("	if (!isEmpty(key)){\r\n");
			sb1.append("		var arr = key.split(\",\");\r\n");
			for(int p=0;p<cntKey;p++){
				sb1.append("		params[\""+ds1.getString("key_camel",p)+"\"] = arr["+p+"].trim();\r\n");
			}
			sb1.append("	}\r\n");
			sb1.append("	"+svc_cls_pfix+"Pop.fnOpen(mode, params, fnGetList);\r\n");
			sb1.append("}\r\n");
			sb1.append("\r\n");
			sb1.append("</script>\r\n");
			sb1.append("\r\n");
			sb1.append("<body><div class=\"wrap\">\r\n");
			sb1.append("<th:block th:include=\"app/common/header\" th:remove=\"tag\"></th:block>\r\n");
			sb1.append("<th:block th:include=\"app/common/gnb\" th:remove=\"tag\"></th:block>\r\n");
			sb1.append("<main>\r\n");
			sb1.append("	<div class=\"sub_wrap\">\r\n");
			sb1.append("		<div class=\"cont\">\r\n");
			sb1.append("			<div class=\"cont-item\" id=\"dv"+svc_cls_pfix+"Search\">\r\n");
			sb1.append("				<div class=\"cont-item-row\">\r\n");
			sb1.append("					<div class=\"item\">\r\n");
			sb1.append("						<label for=\"item01\">검색1</label>\r\n");
			sb1.append("						<div class=\"wrap\">\r\n");
			sb1.append("							<input type=\"text\" name=\"search1\" id=\"search1\" placeholder=\"검색1\" style=\"width:80%\" title=\"검색1\">\r\n");
			sb1.append("						</div>\r\n");
			sb1.append("					</div>\r\n");
			sb1.append("					<div class=\"item\">\r\n");
			sb1.append("						<label for=\"item02\">검색2</label>\r\n");
			sb1.append("						<div class=\"wrap\">\r\n");
			sb1.append("							<input type=\"text\" name=\"search2\" id=\"search2\" placeholder=\"검색2\" style=\"width:80%\" title=\"검색2\">\r\n");
			sb1.append("						</div>\r\n");
			sb1.append("					</div>\r\n");
			sb1.append("				</div>\r\n");
			sb1.append("				<div class=\"cont-item-row cont-item-row-btn\">\r\n");
			sb1.append("					<button type=\"button\" class=\"btn primary default\" onclick=\"fnGetList();\">검색</button>\r\n");
			sb1.append("					<button type=\"button\" class=\"btn white default reset\" onclick=\"clearObjects('dv"+svc_cls_pfix+"Search');\">초기화</button>\r\n");
			sb1.append("				</div>\r\n");
			sb1.append("			</div>\r\n");
			sb1.append("			<div class=\"cont-item h728\">\r\n");
			sb1.append("				<div class=\"bar-top\">\r\n");
			sb1.append("					<div class=\"bar-left\">\r\n");
			sb1.append("						<select class=\"select\" onchange=\"pageCls"+svc_cls_pfix+".setPageRows(getEVal(this));\">\r\n");
			sb1.append("							<option value=\"10\" selected>10건</option>\r\n");
			sb1.append("							<option value=\"20\">20건</option>\r\n");
			sb1.append("							<option value=\"30\">30건</option>\r\n");
			sb1.append("							<option value=\"50\">50건</option>\r\n");
			sb1.append("						</select>\r\n");
			sb1.append("						<div class=\"bar-iblock\">\r\n");
			sb1.append("							<span id=\"totalCnt\" name=\"totalCnt\"></span>\r\n");
			sb1.append("						</div>\r\n");
			sb1.append("					</div>\r\n");
			sb1.append("\r\n");
			sb1.append("					<div class=\"bar-right\">\r\n");
			sb1.append("						<div class=\"pagination-next-btns\">\r\n");
			sb1.append("			                <button type=\"button\" class=\"btn white default\" onclick=\"fnOpenDialog('CREATE');\">신규</button>\r\n");
			sb1.append("							<button type=\"button\" class=\"btn primary default\" onclick=\"fnOpenDialog('MODIFY');\">수정</button>\r\n");
			sb1.append("						</div>\r\n");
			sb1.append("					</div>\r\n");
			sb1.append("				</div>\r\n");
			sb1.append("				<table id=\"tblIdxList\" class=\"gridListTable\">\r\n");
			sb1.append("					<colgroup>\r\n");

			for (int k=0;k<cntCol;k++) {
				if (ds1.getString("spare_yn",k).equals("Y")) continue;
				String s9 = ds1.getString("column_name",k);
				if (s9.equals("REG_DTM") || s9.equals("REG_USER_ID") || s9.equals("UPD_DTM") || s9.equals("UPD_USER_ID")) continue;
				sb1.append("						<col style=\"width:10%;\" />\r\n");
			}

			sb1.append("					</colgroup>\r\n");
			sb1.append("					<thead>\r\n");
			sb1.append("						<tr>\r\n");

			for (int k=0;k<cntCol;k++) {
				if (ds1.getString("spare_yn",k).equals("Y")) continue;
				String s9 = ds1.getString("column_name",k);
				if (s9.equals("REG_DTM") || s9.equals("REG_USER_ID") || s9.equals("UPD_DTM") || s9.equals("UPD_USER_ID")) continue;
				sb1.append("							<th scope=\"col\">"+ds1.getString("c_nm",k)+"</th>\r\n");
			}

			sb1.append("						</tr>\r\n");
			sb1.append("					</thead>\r\n");
			sb1.append("					<tbody></tbody>\r\n");
			sb1.append("				</table>\r\n");
			sb1.append("				<div id=\"dvTrList\" style=\"display:none;\">\r\n");

			String sKey = "";
			for (int k=0;k<cntKey;k++) {
				if (k==0) {
					sKey = "{{"+ds1.getString("key_camel",k)+"}}";
				}else {
					sKey = sKey + ",{{"+ds1.getString("key_camel",k)+"}}";
				}
			}
			sb1.append("				<!-- <tr key='"+sKey+"' ondblclick='fnOpenDialog(\"MODIFY\");'>\r\n");
			for (int k=0;k<cntCol;k++) {
				if (ds1.getString("spare_yn",k).equals("Y")) continue;
				String s9 = ds1.getString("column_name",k);
				if (s9.equals("REG_DTM") || s9.equals("REG_USER_ID") || s9.equals("UPD_DTM") || s9.equals("UPD_USER_ID")) continue;
				sb1.append("						<td>{{"+ds1.getString("column_name_camel",k)+"}}</td>\r\n");
			}
			sb1.append("					 </tr> -->\r\n");

			sb1.append("				</div>\r\n");
			sb1.append("\r\n");
			sb1.append("				<div id=\"dv"+svc_cls_pfix+"PageList\" class=\"pagination\"></div>\r\n");
			sb1.append("			</div>\r\n");
			sb1.append("		</div>\r\n");
			sb1.append("	</div>\r\n");
			sb1.append("\r\n");
			sb1.append("<th:block th:include=\"app/"+duty_div+"/"+duty_div+"Pop\" th:remove=\"tag\"></th:block>\r\n");
			sb1.append("\r\n");
			sb1.append("</main>\r\n");
			sb1.append("<th:block th:include=\"app/common/footer\" th:remove=\"tag\"></th:block>\r\n");
			sb1.append("</div></body>\r\n");
			sb1.append("</html>\r\n");
			
			bw.write(sb1.toString());
		}catch(Exception e){}
		finally{ try{if (bw !=null) bw.close();} catch(Exception e){try { if ( bw != null) { bw.close();} } catch(Exception ex) {}}}
	}
	public static void setJs(TDataSet ds, TDataSet ds1, int i){
		BufferedWriter bw = null;
		try{
			String duty_div = ds.getString("duty_div",i);
			String duty_div_sub = ds.getString("duty_div_sub",i);
			if (duty_div_sub.length() > 0) {
				duty_div_sub = "/" + duty_div_sub;
			}
			String svc_cls_pfix = ds.getString("svc_cls_pfix",i);
			String svc_cls_pfix_lc = ds.getString("svc_cls_pfix_lc",i);
			String js_path = ds.getString("js_path",i);
			int cntCol = ds1.getRowCount("column_name");

// 			String path = "/src/main/resources/static/js/app/   -- code\codeGrid.js";
			String path = js_path+duty_div+"/";
			newPath(DIR + path);
			String fNm = DIR + path + svc_cls_pfix_lc+"Grid.js";
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fNm),"UTF-8"));

			StringBuffer sb1 = new StringBuffer();
			sb1.append("function setGrid"+svc_cls_pfix+"(id){\r\n");
			sb1.append("	return new tui.Grid({\r\n");
			sb1.append("		el: getElement(id),\r\n");
			sb1.append("		scrollX: true,\r\n");
			sb1.append("		scrollY: true,\r\n");
			sb1.append("		enableHorizontalScrollbar: 0,\r\n");
			sb1.append("		bodyHeight: 450,\r\n");
			sb1.append("	    rowHeight: 28,\r\n");
			sb1.append("		width: \"auto\",\r\n");
			sb1.append("		selectionUnit: \"row\",\r\n");
			sb1.append("	    virtualScrolling: true,\r\n");
			sb1.append("	    columnOptions:{resizable:true},\r\n");
			sb1.append("		columns : [\r\n");
			int cntNmr = 0;
			for (int k=0;k<cntCol;k++) {
				if (ds1.getString("spare_yn",k).equals("Y")) continue;
				String s9 = ds1.getString("column_name",k);
// 				if (s9.equals("REG_DTM") || s9.equals("REG_USER_ID") || s9.equals("UPD_DTM") || s9.equals("UPD_USER_ID")) continue;
				sb1.append("		{// "+ds1.getString("c_nm",k)+"\r\n");
				sb1.append("			name : \""+ds1.getString("column_name_camel",k)+"\",\r\n");
				sb1.append("			header : \""+ds1.getString("c_nm",k)+"\",\r\n");
				sb1.append("			width : 150,\r\n");
				sb1.append("			align : \"center\",\r\n");
				if (ds1.getString("time_yn",k).equals("Y")) {
					sb1.append("		formatter : function(value) {\r\n");
					sb1.append("			return getFormatYmdHms(value);\r\n");
					sb1.append("		},\r\n");
				}
				if (ds1.getString("data_type",k).equals("numeric")) {
					cntNmr++;
					sb1.append("			formatter : function(value){return getFormatNum(value);},\r\n");
				}
				sb1.append("		},\r\n");
			}

			sb1.append("		]\r\n");
			sb1.append("	});\r\n");
			sb1.append("}\r\n");
			
			bw.write(sb1.toString());
		}catch(Exception e){}
		finally{ try{if (bw !=null) bw.close();} catch(Exception e){try { if ( bw != null) { bw.close();} } catch(Exception ex) {}}}
	}
	public static void setPopup(TDataSet ds, TDataSet ds1, int i){
		BufferedWriter bw = null;
		try{
			String svc_cls_pfix_lc = ds.getString("svc_cls_pfix_lc",i);
			String svc_cls_pfix = ds.getString("svc_cls_pfix",i);
			String duty_div = ds.getString("duty_div",i);
			String duty_div_sub = ds.getString("duty_div_sub",i);
			String message_nm_sub = "";
			if (duty_div_sub.length() > 0) {
				message_nm_sub = duty_div_sub.substring(0, 1).toUpperCase() + duty_div_sub.substring(1);
				duty_div_sub = "/" + duty_div_sub;
			}
			String popup_jsp_path = ds.getString("popup_jsp_path",i);
			String jsp_path = ds.getString("jsp_path",i);
			int cntCol = ds1.getRowCount("column_name");
			int cntKey = ds1.getRowCount("key");
			
// 			String path = "/src/main/resources/templates/app/popup/   -- code\codePop.html";
			String path = jsp_path+duty_div+"/";
			newPath(DIR + path);
			String fNm = DIR + path + svc_cls_pfix_lc + "Pop.html";
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fNm),"UTF-8"));
			
			StringBuffer sb1 = new StringBuffer();
			sb1.append("<div id=\"div"+svc_cls_pfix+"Pop\" class=\"sub_wrap\" style=\"display:none;width:92%\" title=\""+ds1.getString("t_nm",0)+" 정보\">\r\n");
			sb1.append("	<div class=\"cont\">\r\n");
			sb1.append("		<div class=\"cont-item cont-item01\">\r\n");
			sb1.append("			<div class=\"cont-item-row item-title\">\r\n");
			sb1.append("				<h4>"+ds1.getString("t_nm",0)+" 등록</h4>\r\n");
			sb1.append("			</div>\r\n");
			sb1.append("			<div class=\"cont-item-wrap\">\r\n");

			TDataSet ds2 = new TDataSet();
			byte[] bArr = SerializationUtils.serialize(ds1);
			ds2 = (TDataSet)SerializationUtils.deserialize(bArr);

			int v1 = ds2.getFindRow("column_name", "REG_DTM");
			if (v1 > -1) {ds2.deleteRow(v1);}
			v1 = ds2.getFindRow("column_name", "REG_USER_ID");
			if (v1 > -1) {ds2.deleteRow(v1);}
			v1 = ds2.getFindRow("column_name", "UPD_DTM");
			if (v1 > -1) {ds2.deleteRow(v1);}
			v1 = ds2.getFindRow("column_name", "UPD_USER_ID");
			if (v1 > -1) {ds2.deleteRow(v1);}
			while(true) {
				int v2 = ds2.getFindRow("spare_yn", "Y");
				if (v2 < 0) break;
				ds2.deleteRow(v2);
			}
			int cntCol2 = ds2.getRowCount("column_name");
			if (cntCol % 2 == 1) {
				cntCol2++;
			}
			String fColNm = "";		//first col excluding key
			for (int k=0;k<cntCol2;k++) {
				String scn = ds2.getString("column_name_camel",k);
				int iyn = 0;
				for (int p=0;p<cntKey;p++) {
					String kc = ds1.getString("key_camel",p);
					if (kc.equals(scn)){
						iyn = 1;
						break;
					}
				}
				if (iyn == 0) {
					fColNm = scn;
					break;
				}
			}
			for (int k=0;k<cntCol2;k+=2) {
				String fstBorder = "";
				if (k == 0) fstBorder = " border-top";
				String s0 = ds2.getString("column_name_camel",k);
				if (s0.equals("")) continue;
				String s01 = ds2.getString("column_name_camel",k+1);

				sb1.append("				<div class=\"cont-item-row"+fstBorder+"\">\r\n");
				if (s0.equals("useYn")){
					sb1.append("					<div class=\"item\">\r\n");
					sb1.append("						<h5>사용여부</h5>\r\n");
					sb1.append("						<div class=\"wrap\">\r\n");
					sb1.append("							<label class=\"rb\" for=\"useYn_Y\">사용\r\n");
					sb1.append("								<input type=\"radio\" name=\"useYn_g1\" id=\"useYn_Y\" value=\"Y\" checked=\"checked\">\r\n");
					sb1.append("								<span class=\"checkmark\"></span>\r\n");
					sb1.append("							</label>\r\n");
					sb1.append("							<label class=\"rb\" for=\"useYn_N\">미사용\r\n");
					sb1.append("								<input type=\"radio\" name=\"useYn_g1\" id=\"useYn_N\" value=\"N\">\r\n");
					sb1.append("								<span class=\"checkmark\"></span>\r\n");
					sb1.append("							</label>\r\n");
					sb1.append("						</div>\r\n");
					sb1.append("					</div>\r\n");
				}else{
					sb1.append("					<div class=\"item\">\r\n");
					sb1.append("						<label for=\""+ds2.getString("column_name_camel",k)+"_g1\">"+ds2.getString("c_nm",k)+"</label>\r\n");
					sb1.append("						<div class=\"wrap\">\r\n");
					sb1.append("							<input type=\"text\" id=\""+ds2.getString("column_name_camel",k)+"_g1\" name=\""+ds2.getString("column_name_camel",k)+"_g1\" />\r\n");
					sb1.append("						</div>\r\n");
					sb1.append("					</div>\r\n");
				}
				String s1 = ds2.getString("column_name_camel",k+1);
				if (s1.equals("")){
					sb1.append("					<div class=\"item\">\r\n");
					sb1.append("					</div>\r\n");

				}
				else{
					if (s01.equals("useYn")){
						sb1.append("					<div class=\"item\">\r\n");
						sb1.append("						<h5>사용여부</h5>\r\n");
						sb1.append("						<div class=\"wrap\">\r\n");
						sb1.append("							<label class=\"rb\" for=\"useYn_Y\">사용\r\n");
						sb1.append("								<input type=\"radio\" name=\"useYn_g1\" id=\"useYn_Y\" value=\"Y\" checked=\"checked\">\r\n");
						sb1.append("								<span class=\"checkmark\"></span>\r\n");
						sb1.append("							</label>\r\n");
						sb1.append("							<label class=\"rb\" for=\"useYn_N\">미사용\r\n");
						sb1.append("								<input type=\"radio\" name=\"useYn_g1\" id=\"useYn_N\" value=\"N\">\r\n");
						sb1.append("								<span class=\"checkmark\"></span>\r\n");
						sb1.append("							</label>\r\n");
						sb1.append("						</div>\r\n");
						sb1.append("					</div>\r\n");
					}else{
						sb1.append("					<div class=\"item\">\r\n");
						sb1.append("						<label for=\""+ds2.getString("column_name_camel",k+1)+"_g1\">"+ds2.getString("c_nm",k+1)+"</label>\r\n");
						sb1.append("						<div class=\"wrap\">\r\n");
						sb1.append("							<input type=\"text\" id=\""+ds2.getString("column_name_camel",k+1)+"_g1\" name=\""+ds2.getString("column_name_camel",k+1)+"_g1\" />\r\n");
						sb1.append("						</div>\r\n");
						sb1.append("					</div>\r\n");
					}
				}
				sb1.append("				</div>\r\n");
			}

			sb1.append("				<div class=\"cont-item-row cont-item-row-btn right\">\r\n");
			sb1.append("					<button id=\"saveBtn\" name=\"saveBtn\" class=\"btn default\" onclick=\""+svc_cls_pfix+"Pop.fnSave();\"><span>저장</span></button>\r\n");
			sb1.append("					<button id=\"delBtn\" name=\"delBtn\" class=\"btn default\" onclick=\""+svc_cls_pfix+"Pop.fnDelete();\"><span>삭제</span></button>\r\n");
			sb1.append("					<button class=\"btn write default\" onclick=\""+svc_cls_pfix+"Pop.fnClose();\"><span>닫기</span></button>\r\n");
			sb1.append("				</div>\r\n");
			sb1.append("			</div>\r\n");
			sb1.append("		</div>\r\n");
			sb1.append("	</div>\r\n");
			sb1.append("</div>\r\n");
			sb1.append("<script type=\"text/javascript\">\r\n");
			sb1.append("var "+svc_cls_pfix+"Pop = new $W.load(\"div"+svc_cls_pfix+"Pop\");\r\n");
			sb1.append(""+svc_cls_pfix+"Pop.ready(function(){\r\n");
			sb1.append("	//\r\n");
			sb1.append("});\r\n");
			sb1.append("\r\n");
			sb1.append(""+svc_cls_pfix+"Pop.fnOpen = function(mode, params, func) {\r\n");
			sb1.append("	clearObjects(\"div"+svc_cls_pfix+"Pop\");\r\n");
			sb1.append("	"+svc_cls_pfix+"Pop.mode = mode;\r\n");
			sb1.append("	if (!isEmpty(func)){\r\n");
			sb1.append("		"+svc_cls_pfix+"Pop.func = func;\r\n");
			sb1.append("	}\r\n");
			sb1.append("	setObjVal({\"useYn_g1\":\"Y\"});\r\n");
			sb1.append("\r\n");
			sb1.append("	//draw popup\r\n");
			sb1.append("	layerToggle(\"div"+svc_cls_pfix+"Pop\",{\"mode\":1,\"top\":130,\"scrollYn\":\"Y\"});\r\n");
			sb1.append("\r\n");

			String sKey = "";
			sb1.append("	readOnlyTrue([");
			for (int k=0;k<cntKey;k++) {
				if (k==0) {
					sKey = "\""+ds1.getString("key_camel",k)+"_g1\"";
				}else {
					sKey = sKey + ",\""+ds1.getString("key_camel",k)+"_g1\"";
				}
			}

			sb1.append(""+sKey+"]);\r\n");

			sb1.append("	if(mode == \"MODIFY\") {\r\n");
			sb1.append("		var url = \"/app/"+duty_div+"/get"+svc_cls_pfix+".do\";\r\n");
			sb1.append("		var map = httpSend(url, params);\r\n");
			sb1.append("		if (!isEmpty(map)){\r\n");
			sb1.append("			let chgData = chgMapKey(map,\"_g1\");\r\n");
			sb1.append("			setObjVal(chgData);\r\n");
			sb1.append("		}else{\r\n");
			sb1.append("			alert(\"자료가 존재하지 않습니다.\");\r\n");
			sb1.append("		}\r\n");
			sb1.append("\r\n");
			sb1.append("		displayTrue([\"delBtn\"]);\r\n");
			sb1.append("		getElement(\""+fColNm+"_g1\").focus();\r\n");
			sb1.append("	}else{\r\n");

			sKey = "";
			sb1.append("		readOnlyFalse([");
			for (int k=0;k<cntKey;k++) {
				if (k==0) {
					sKey = "\""+ds1.getString("key_camel",k)+"_g1\"";
				}else {
					sKey = sKey + ",\""+ds1.getString("key_camel",k)+"_g1\"";
				}
			}
			sb1.append(""+sKey+"]);\r\n");

			sb1.append("		displayFalse([\"delBtn\"]);\r\n");
			sb1.append("	}\r\n");
			sb1.append("};\r\n");
			sb1.append("\r\n");
			sb1.append(""+svc_cls_pfix+"Pop.fnSave = function(){\r\n");
			sb1.append("\r\n");
			sb1.append("	var valid = 0 || [\r\n");
			for (int k=0;k<cntCol;k++) {
				if (ds1.getString("spare_yn",k).equals("Y")) continue;
				String s9 = ds1.getString("column_name",k);
				//null??
				if (s9.trim().equals("")) {
					System.out.println("ttt..........");
				}
				if (s9.trim().equals("")) continue;
				if (s9.equals("REG_DTM") || s9.equals("REG_USER_ID") || s9.equals("UPD_DTM") || s9.equals("UPD_USER_ID")) continue;
				if (k==0) {
					sb1.append("		[\""+ds1.getString("column_name_camel",k)+"_g1\",\"\",\"자료("+ds1.getString("c_nm",k)+")를 확인하세요.\"]\r\n");
				}else {
					sb1.append("		,[\""+ds1.getString("column_name_camel",k)+"_g1\",\"\",\"자료("+ds1.getString("c_nm",k)+")를 확인하세요.\"]\r\n");
				}
			}
			sb1.append("\r\n");
			sb1.append("		];\r\n");
			sb1.append("	if (checkValid( valid )==false) return;\r\n");
			sb1.append("\r\n");
			sb1.append("	var ds  = getEleToBDataSet(\"div"+svc_cls_pfix+"Pop\");\r\n");
			sb1.append("	var url = \"/app/"+duty_div+"/update"+svc_cls_pfix+".do\";\r\n");
			sb1.append("	if ("+svc_cls_pfix+"Pop.mode==\"CREATE\"){\r\n");
			sb1.append("		url = \"/app/"+duty_div+"/insert"+svc_cls_pfix+".do\";\r\n");
			sb1.append("	}\r\n");
			sb1.append("\r\n");
			sb1.append("	//data key change\r\n");
			sb1.append("	var sData = chgBackMapKey(ds.getRow(0),\"_g1\");\r\n");
			sb1.append("	var map = httpSend(url, sData);\r\n");
			sb1.append("	if (!isEmpty(map) && !isEmpty(map[\"resultCd\"])){\r\n");
			sb1.append("		if (map[\"resultCd\"] != \"Y\"){\r\n");
			sb1.append("			alert(map[\"resultMsg\"]);\r\n");
			sb1.append("			return false;\r\n");
			sb1.append("		}\r\n");
			sb1.append("		if (!isEmpty("+svc_cls_pfix+"Pop.func)){\r\n");
			sb1.append("			"+svc_cls_pfix+"Pop.func();\r\n");
			sb1.append("			"+svc_cls_pfix+"Pop.fnClose();\r\n");
			sb1.append("		}\r\n");
			sb1.append("	}else{\r\n");
			sb1.append("		alert(\"작업중 오류가 발생했습니다.\");\r\n");
			sb1.append("	}\r\n");
			sb1.append("\r\n");
			sb1.append("};\r\n");
			sb1.append("\r\n");
			sb1.append(""+svc_cls_pfix+"Pop.fnDelete = function(){\r\n");
			sb1.append("	if ("+svc_cls_pfix+"Pop.mode != \"MODIFY\"){\r\n");
			sb1.append("		alert(\"조회후 삭제하세요.\");\r\n");
			sb1.append("		return false;\r\n");
			sb1.append("	}\r\n");
			sb1.append("	if ( confirm(\"자료를 삭제하시겠습니까?\")==false ) return;\r\n");
			sb1.append("\r\n");
			sb1.append("	var url = \"/app/"+duty_div+"/delete"+svc_cls_pfix+".do\";\r\n");
			sb1.append("	var params = {};\r\n");

			for (int k=0;k<cntKey;k++) {
				sb1.append("	params[\""+ds1.getString("key_camel",k)+"\"] = getElement(\""+ds1.getString("key_camel",k)+"_g1\").value;\r\n");
			}

			sb1.append("\r\n");
			sb1.append("	var map = httpSend(url, params);\r\n");
			sb1.append("	if (!isEmpty(map) && !isEmpty(map[\"resultCd\"])){\r\n");
			sb1.append("		if (map[\"resultCd\"] != \"Y\"){\r\n");
			sb1.append("			alert(map[\"resultMsg\"]);\r\n");
			sb1.append("			return false;\r\n");
			sb1.append("		}\r\n");
			sb1.append("		if (!isEmpty("+svc_cls_pfix+"Pop.func)){\r\n");
			sb1.append("			"+svc_cls_pfix+"Pop.func();\r\n");
			sb1.append("			"+svc_cls_pfix+"Pop.fnClose();\r\n");
			sb1.append("		}\r\n");
			sb1.append("	}else{\r\n");
			sb1.append("		alert(\"작업중 오류가 발생했습니다.\");\r\n");
			sb1.append("	}\r\n");
			sb1.append("};\r\n");
			sb1.append("\r\n");
			sb1.append(""+svc_cls_pfix+"Pop.fnClose = function(){\r\n");
			sb1.append("	layerToggle(\"div"+svc_cls_pfix+"Pop\",{\"mode\":0});\r\n");
			sb1.append("};\r\n");
			sb1.append("</script>\r\n");
			
			bw.write(sb1.toString());
		}catch(Exception e){}
		finally{ try{if (bw !=null) bw.close();} catch(Exception e){try { if ( bw != null) { bw.close();} } catch(Exception ex) {}}}
	}
	public static void setPopupJsp(TDataSet ds, TDataSet ds1, int i){
		BufferedWriter bw = null;
		try{
			String svc_cls_pfix_lc = ds.getString("svc_cls_pfix_lc",i);
			String svc_cls_pfix = ds.getString("svc_cls_pfix",i);
			String duty_div = ds.getString("duty_div",i);
			String duty_div_sub = ds.getString("duty_div_sub",i);
			String message_nm_sub = "";
			if (duty_div_sub.length() > 0) {
				message_nm_sub = duty_div_sub.substring(0, 1).toUpperCase() + duty_div_sub.substring(1);
				duty_div_sub = "/" + duty_div_sub;
			}
			String popup_jsp_path = ds.getString("popup_jsp_path",i);
			int cntCol = ds1.getRowCount("column_name");
			int cntKey = ds1.getRowCount("key");
			
// 			String path = "/src/main/resources/templates/app/popup/   -- code\codePop.html";
			String path = popup_jsp_path+duty_div+"/";
			newPath(DIR + path);
			String fNm = DIR + path + svc_cls_pfix_lc+"Pop.html";
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fNm),"UTF-8"));
			
			StringBuffer sb1 = new StringBuffer();
			sb1.append("<!DOCTYPE html>\r\n");
			sb1.append("<html xmlns:th=\"http://www.thymeleaf.org\">\r\n");
			sb1.append("\r\n");
			sb1.append("<th:block th:include=\"app/common/head\" th:remove=\"tag\"></th:block>\r\n");
			sb1.append("<script>\r\n");
			sb1.append("window.onload = function() {\r\n");
			sb1.append("	getReqPopData();\r\n");
			sb1.append("}\r\n");
			sb1.append("var mode = \"\";\r\n");
			sb1.append("function getReqPopData(){\r\n");
			sb1.append("	var map={};\r\n");
			String key1 = "";
			for (int k=0;k<cntKey;k++) {
				if (ds1.getString("base_yn",k).equals("Y") || ds1.getString("spare_yn",k).equals("Y")) continue;
				if (key1.equals("")){
					key1 = ds1.getString("key_camel",k);
				}
				sb1.append("    map[\""+ds1.getString("key_camel",k)+"\"] = '[[${transData."+ds1.getString("key_camel",k)+"}]]';\r\n");
			}
			sb1.append("	if (isEmpty(map[\""+key1+"\"])){\r\n");
			sb1.append("		mode = \"NEW\";\r\n");
			sb1.append("		return;\r\n");
			sb1.append("	}\r\n");
			sb1.append("	else{\r\n");
			for (int k=0;k<cntKey;k++) {
				if (ds1.getString("base_yn",k).equals("Y") || ds1.getString("spare_yn",k).equals("Y")) continue;
				sb1.append("		getEleByObj(\""+ds1.getString("key_camel",k)+"\").setAttribute('readonly', true);\r\n");
			}
			sb1.append("	}\r\n");
			sb1.append("	getData(map);\r\n");
			sb1.append("}\r\n");
			sb1.append("function getData(map){\r\n");
			sb1.append("	var url = \"/app/"+duty_div+"/get"+svc_cls_pfix+".do\";\r\n");
			sb1.append("	var fData = mapToFormdata(map);\r\n");
			sb1.append("	\r\n");
			sb1.append("	getUrlData(url,fData).then((result=>{\r\n");
			sb1.append("		setObjVal(result);\r\n");
			sb1.append("	}));\r\n");
			sb1.append("}\r\n");
			sb1.append("\r\n");
			sb1.append("function saveData(){\r\n");
			sb1.append("	var valid = 0 || [\r\n");
			for (int k=0;k<cntCol;k++) {
				if (ds1.getString("spare_yn",k).equals("Y")) continue;
				String s9 = ds1.getString("column_name",k);
				if (s9.equals("REG_DTM") || s9.equals("REG_USER_ID") || s9.equals("UPD_DTM") || s9.equals("UPD_USER_ID")) continue;
				sb1.append("		[\""+ds1.getString("column_name_camel",k)+"\",\"\",\"자료("+ds1.getString("c_nm",k)+")를 확인하세요.\"]\r\n");
			}
			sb1.append("	];\r\n");
			sb1.append("	if (checkValid( valid )==false) return;\r\n");
			sb1.append("	\r\n");
			sb1.append("	var url = \"/app/"+duty_div+"/update"+svc_cls_pfix+".do\";\r\n");
			sb1.append("	if (mode==\"NEW\"){\r\n");
			sb1.append("		url = \"/app/"+duty_div+"/insert"+svc_cls_pfix+".do\";\r\n");
			sb1.append("	}\r\n");
			sb1.append("	\r\n");
			sb1.append("	var fData = new FormData(getEleByObj(\"form"+svc_cls_pfix+"Pop\"));\r\n");
			sb1.append("	getUrlData(url,fData).then((result=>{\r\n");
			sb1.append("		resultFunc(result);\r\n");
			sb1.append("	}));\r\n");
			sb1.append("}\r\n");
			sb1.append("function resultFunc(data){\r\n");
			sb1.append("	var vFunc = '[[${callbackFunc}]]';\r\n");
			sb1.append("	if (isEmpty(vFunc)) return;\r\n");
			sb1.append("	var func = eval(\"opener.\"+vFunc);\r\n");
			sb1.append("	func(data);\r\n");
			sb1.append("	window.close();\r\n");
			sb1.append("}\r\n");
			sb1.append("function fnClose(){\r\n");
			sb1.append("	window.close();\r\n");
			sb1.append("}\r\n");
			sb1.append("</script>\r\n");
			sb1.append("<body>\r\n");
			sb1.append("<section class=\"\">\r\n");
			sb1.append("<div id=\"dv"+svc_cls_pfix+"Pop\" class=\"modal shot-pop\" title=\""+ds1.getString("t_nm",i)+" 등록\">\r\n");
			sb1.append("	<div class=\"modal__container\">\r\n");
			sb1.append("		<div class=\"modal__content\">\r\n");
			sb1.append("			<form id=\"form"+svc_cls_pfix+"Pop\" name=\"form"+svc_cls_pfix+"Pop\">\r\n");
			sb1.append("			<table>\r\n");
			sb1.append("				<colgroup>\r\n");
			sb1.append("				<col width=\"15%\">\r\n");
			sb1.append("				<col>\r\n");
			sb1.append("				<col width=\"15%\">\r\n");
			sb1.append("				<col>\r\n");
			sb1.append("				</colgroup>\r\n");
			sb1.append("				<tbody>\r\n");

			TDataSet ds2 = new TDataSet();
			ds2 = ds1;
			int v1 = ds2.getFindRow("column_name", "REG_DTM");
			if (v1 > -1) {ds2.deleteRow(v1);}
			v1 = ds2.getFindRow("column_name", "REG_USER_ID");
			if (v1 > -1) {ds2.deleteRow(v1);}
			v1 = ds2.getFindRow("column_name", "UPD_DTM");
			if (v1 > -1) {ds2.deleteRow(v1);}
			v1 = ds2.getFindRow("column_name", "UPD_USER_ID");
			if (v1 > -1) {ds2.deleteRow(v1);}
			while(true) {
				int v2 = ds2.getFindRow("spare_yn", "Y");
				if (v2 < 0) break;
				ds2.deleteRow(v2);
			}
			int cntCol2 = ds2.getRowCount("column_name");
			if (cntCol % 2 == 1) {
				cntCol2++;
			}
			for (int k=0;k<cntCol2;k+=2) {
				String s0 = ds2.getString("column_name_camel",k);
				if (s0.equals("")) continue;
				String sKey = ds2.getString("key",k);
				String sKey2 = ds2.getString("key",k+1);
				String sRo = "";
				String sRo2 = "";
				if (sKey.equals("")==false) sRo = "readonly ";
				if (sKey2.equals("")==false) sRo2 = "readonly ";
				sb1.append("					<tr>\r\n");
				sb1.append("						<th scope=\"row\">"+ds2.getString("c_nm",k)+"</th>\r\n");
				sb1.append("						<td><input type=\"text\" name=\""+ds2.getString("column_name_camel",k)+"\" id=\""+ds2.getString("column_name_camel",k)+"\" style=\"width:70%\" "+sRo+">\r\n");
				sb1.append("						</td>\r\n");
				String s1 = ds2.getString("column_name_camel",k+1);
				if (s1.equals("")){
					sb1.append("					<th>&nbsp;</th>\r\n");
					sb1.append("					<td>\r\n");
					sb1.append("						&nbsp;\r\n");
					sb1.append("					</td>\r\n");
				}
				else{
					sb1.append("					<th scope=\"row\">"+ds2.getString("c_nm",k+1)+"</th>\r\n");
					sb1.append("					<td><input type=\"text\" name=\""+ds2.getString("column_name_camel",k+1)+"\" id=\""+ds2.getString("column_name_camel",k+1)+"\" "+sRo2+">\r\n");
					sb1.append("					</td>\r\n");
				}
				sb1.append("				</tr>\r\n");
			}
			sb1.append("				</tbody>\r\n");
			sb1.append("			</table>\r\n");
			sb1.append("			</form>\r\n");
			sb1.append("		</div>\r\n");
			sb1.append("		<div class=\"modal__footer\">\r\n");
			sb1.append("			<button type=\"button\" class=\"btn btn-primary\" data-popup-close=\"popup-name\" onClick=\"saveData();\">등록</button>\r\n");
			sb1.append("			<button type=\"button\" class=\"btn btn-secondary\" data-popup-close=\"popup-name\" onClick=\"fnClose();\">닫기</button>\r\n");
			sb1.append("		</div>\r\n");
			sb1.append("	</div>\r\n");
			sb1.append("</div>\r\n");
			sb1.append("</section>\r\n");
			sb1.append("</body>\r\n");
			sb1.append("</html>\r\n");
			
			bw.write(sb1.toString());
		}catch(Exception e){}
		finally{ try{if (bw !=null) bw.close();} catch(Exception e){try { if ( bw != null) { bw.close();} } catch(Exception ex) {}}}
	}
	public static void setPopupJspSearch(TDataSet ds, TDataSet ds1, int i){
		BufferedWriter bw = null;
		try{
			String svc_cls_pfix_lc = ds.getString("svc_cls_pfix_lc",i);
			String svc_cls_pfix = ds.getString("svc_cls_pfix",i);
			String duty_div = ds.getString("duty_div",i);
			String duty_div_sub = ds.getString("duty_div_sub",i);
			String message_nm_sub = "";
			if (duty_div_sub.length() > 0) {
				message_nm_sub = duty_div_sub.substring(0, 1).toUpperCase() + duty_div_sub.substring(1);
				duty_div_sub = "/" + duty_div_sub;
			}
			String popup_jsp_path = ds.getString("popup_jsp_path",i);
			int cntCol = ds1.getRowCount("column_name");
// 			String path = "/src/main/resources/templates/app/popup/   -- code\codeSearchPop.html";
			String path = popup_jsp_path+duty_div+"/";
			newPath(DIR + path);
			String fNm = DIR + path + svc_cls_pfix_lc+"SearchPop.html";
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fNm),"UTF-8"));
			StringBuffer sb1 = new StringBuffer();
			sb1.append("<!DOCTYPE html>\r\n");
			sb1.append("<html xmlns:th=\"http://www.thymeleaf.org\">\r\n");
			sb1.append("\r\n");
			sb1.append("<th:block th:include=\"app/common/head\" th:remove=\"tag\"></th:block>\r\n");
			sb1.append("<script type=\"text/javascript\" src=\"/js/app/"+duty_div+"/"+svc_cls_pfix_lc+"Grid.js\"></script>\r\n");
			sb1.append("<script>\r\n");
			sb1.append("var grid"+svc_cls_pfix+" = null;\r\n");
			sb1.append("window.onload = function() {\r\n");
			sb1.append("	grid"+svc_cls_pfix+" = setGrid"+svc_cls_pfix+"(\"dvGrid"+svc_cls_pfix+"Search\");\r\n");
			sb1.append("	grid"+svc_cls_pfix+".setBodyHeight(300);\r\n");
			sb1.append("	fixedColGrid(grid"+svc_cls_pfix+");\r\n");
			sb1.append("\r\n");
			sb1.append("	grid"+svc_cls_pfix+".on(\"click\",function(ev){\r\n");
			sb1.append("		toggleGrid(grid"+svc_cls_pfix+",ev.rowKey);\r\n");
			sb1.append("		/* return ev.stop(); */\r\n");
			sb1.append("	});\r\n");
			sb1.append("	grid"+svc_cls_pfix+".on(\"dblclick\",function(ev){\r\n");
			sb1.append("		if (isEmpty(dblClickGrid"+svc_cls_pfix+")==false){\r\n");
			sb1.append("			dblClickGrid"+svc_cls_pfix+"();\r\n");
			sb1.append("		}\r\n");
			sb1.append("		return ev.stop();\r\n");
			sb1.append("	});\r\n");
			sb1.append("}\r\n");
			sb1.append("function dblClickGrid"+svc_cls_pfix+"(){\r\n");
			sb1.append("	getResultData();\r\n");
			sb1.append("}\r\n");
			sb1.append("\r\n");
			sb1.append("//page\r\n");
			sb1.append("var pageCls"+svc_cls_pfix+" = new pageCls(\"dv"+svc_cls_pfix+"PageList\",fnGetSearchList);\r\n");
			sb1.append("\r\n");
			sb1.append("function fnGetSearchList(){\r\n");
			sb1.append("	setClearGrid(grid"+svc_cls_pfix+");\r\n");
			sb1.append("\r\n");
			sb1.append("	var params = {};\r\n");
			for (int k=0;k<cntCol;k++) {
				if (ds1.getString("spare_yn",k).equals("Y")) continue;
				String s9 = ds1.getString("column_name",k);
				if (s9.equals("REG_DTM") || s9.equals("REG_USER_ID") || s9.equals("UPD_DTM") || s9.equals("UPD_USER_ID")) continue;
				sb1.append("	if (isEmpty(getEVal(\""+ds1.getString("column_name_camel",k)+"\"))==false) params[\""+ds1.getString("column_name_camel",k)+"\"] = getEVal(\""+ds1.getString("column_name_camel",k)+"\");\r\n");
			}
			sb1.append("	//page set\r\n");
			sb1.append("	params = pageCls"+svc_cls_pfix+".getPageInfo(params);\r\n");
			sb1.append("\r\n");
			sb1.append("	var url = \"/app/"+duty_div+"/get"+svc_cls_pfix+"PageList.do\";\r\n");
			sb1.append("	var fData = mapToFormdata(params);\r\n");
			sb1.append("	//add page\r\n");
			sb1.append("	\r\n");
			sb1.append("	getUrlData(url,fData).then((result=>{\r\n");
			sb1.append("		if (isEmpty(result.payloads)){\r\n");
			sb1.append("			alert(\"조회 오류 발생.\");\r\n");
			sb1.append("			return;\r\n");
			sb1.append("		}\r\n");
			sb1.append("		setDataGrid(grid"+svc_cls_pfix+",result.payloads);\r\n");
			sb1.append("		//set total\r\n");
			sb1.append("		pageCls"+svc_cls_pfix+".setTotalRowsReload(result.totalRows);\r\n");
			sb1.append("	}));\r\n");
			sb1.append("}\r\n");
			sb1.append("\r\n");
			sb1.append("\r\n");
			sb1.append("function getResultData(){\r\n");
			sb1.append("	var map = getFocusedRowDataGrid(grid"+svc_cls_pfix+");\r\n");
			sb1.append("	if (map == null){\r\n");
			sb1.append("		alert(\"전송자료가 존재하지 않습니다.\");\r\n");
			sb1.append("		return;\r\n");
			sb1.append("	}\r\n");
			sb1.append("	resultFunc(map);\r\n");
			sb1.append("}\r\n");
			sb1.append("function resultFunc(data){\r\n");
			sb1.append("	var vFunc = '[[${callbackFunc}]]';\r\n");
			sb1.append("	if (isEmpty(vFunc)) return;\r\n");
			sb1.append("	var func = eval(\"opener.\"+vFunc);\r\n");
			sb1.append("	func(data);\r\n");
			sb1.append("	window.close();\r\n");
			sb1.append("}\r\n");
			sb1.append("\r\n");
			sb1.append("function fnClose(){\r\n");
			sb1.append("	window.close();\r\n");
			sb1.append("}\r\n");
			sb1.append("</script>\r\n");
			sb1.append("<body>\r\n");
			sb1.append("<section class=\"\">\r\n");
			sb1.append("	<div id=\"dv"+svc_cls_pfix+"SearchPop\" class=\"modal shot-pop\" title=\""+ds1.getString("t_nm",i)+" 검색\">\r\n");
			sb1.append("		<div class=\"modal__container\">\r\n");
			sb1.append("			<div class=\"modal__content\">\r\n");
			sb1.append("				<div class=\"addr__search\">\r\n");
			sb1.append("					<input type=\"text\" id=\"scCSchNm\" placeholder=\"조건을 입력 하세요.\">\r\n");
			sb1.append("					<button id=\"btnSchCSchInd\" class=\"btn btn-primary\" onclick=\"fnGetSearchList();\"><img src=\"/include/img/icon/search_white_24dp.svg\" alt=\"검색 버튼\"/></button>\r\n");
			sb1.append("				</div>\r\n");
			sb1.append("\r\n");
			sb1.append("				<div class=\"addr__content\">\r\n");
			sb1.append("					<p class=\"search-count\">검색결과 <strong></strong>\r\n");
			sb1.append("						<button type=\"button\" class=\"btn btn-primary btn-sm btn-enter\" style=\"position:absolute;margin-top:-10px;right:20px;\" onclick=\"javascript:fnGetSearchList();\">선택</button>\r\n");
			sb1.append("					</p>\r\n");
			sb1.append("					<div id=\"dvGrid"+svc_cls_pfix+"Search\" class=\"addr-list\" ></div>\r\n");
			sb1.append("					<div id=\"dv"+svc_cls_pfix+"PageList\" class=\"table-num\" style=\"margin-top: 10px;\"></div>\r\n");
			sb1.append("				</div>\r\n");
			sb1.append("			</div>\r\n");
			sb1.append("		</div>\r\n");
			sb1.append("	</div>\r\n");
			sb1.append("</section>\r\n");
			sb1.append("</body>\r\n");
			sb1.append("</html>\r\n");
			
			bw.write(sb1.toString());
		}catch(Exception e){}
		finally{ try{if (bw !=null) bw.close();} catch(Exception e){try { if ( bw != null) { bw.close();} } catch(Exception ex) {}}}
	}
	public static void setPopupJspDetail(TDataSet ds, TDataSet ds1, int i){
		BufferedWriter bw = null;
		try{
			String svc_cls_pfix_lc = ds.getString("svc_cls_pfix_lc",i);
			String svc_cls_pfix = ds.getString("svc_cls_pfix",i);
			String duty_div = ds.getString("duty_div",i);
			String duty_div_sub = ds.getString("duty_div_sub",i);
			String message_nm_sub = "";
			if (duty_div_sub.length() > 0) {
				message_nm_sub = duty_div_sub.substring(0, 1).toUpperCase() + duty_div_sub.substring(1);
				duty_div_sub = "/" + duty_div_sub;
			}
			String popup_jsp_path = ds.getString("popup_jsp_path",i);
			int cntCol = ds1.getRowCount("column_name");
			int cntKey = ds1.getRowCount("key");
			
// 			String path = "/src/main/resources/templates/app/popup/   -- code\codeInfoPop.html";
			String path = popup_jsp_path+duty_div+"/";
			newPath(DIR + path);
			String fNm = DIR + path + svc_cls_pfix_lc+"InfoPop.html";
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fNm),"UTF-8"));
			
			StringBuffer sb1 = new StringBuffer();
			sb1.append("<!DOCTYPE html>\r\n");
			sb1.append("<html xmlns:th=\"http://www.thymeleaf.org\">\r\n");
			sb1.append("\r\n");
			sb1.append("<th:block th:include=\"app/common/head\" th:remove=\"tag\"></th:block>\r\n");
			sb1.append("<script>\r\n");
			sb1.append("window.onload = function() {\r\n");
			sb1.append("	getReqPopData();\r\n");
			sb1.append("}\r\n");
			sb1.append("var mode = \"\";\r\n");
			sb1.append("function getReqPopData(){\r\n");
			sb1.append("	var map={};\r\n");
			String key1 = "";
			for (int k=0;k<cntKey;k++) {
				if (ds1.getString("base_yn",k).equals("Y") || ds1.getString("spare_yn",k).equals("Y")) continue;
				if (key1.equals("")){
					key1 = ds1.getString("key_camel",k);
				}
				sb1.append("    map[\""+ds1.getString("key_camel",k)+"\"] = '[[${transData."+ds1.getString("key_camel",k)+"}]]';\r\n");
			}
			sb1.append("	if (isEmpty(map[\""+key1+"\"])){\r\n");
			sb1.append("		alert(\"조회 할 자료를 선택하세요.\");\r\n");
			sb1.append("		window.close();\r\n");
			sb1.append("	}\r\n");
			sb1.append("	getData(map);\r\n");
			sb1.append("}\r\n");
			sb1.append("function getData(map){\r\n");
			sb1.append("	var url = \"/app/"+duty_div+"/get"+svc_cls_pfix+".do\";\r\n");
			sb1.append("	var fData = mapToFormdata(map);\r\n");
			sb1.append("	\r\n");
			sb1.append("	getUrlData(url,fData).then((result=>{\r\n");
			sb1.append("		setObjVal(result);\r\n");
			sb1.append("	}));\r\n");
			sb1.append("}\r\n");
			sb1.append("function fnClose(){\r\n");
			sb1.append("	window.close();\r\n");
			sb1.append("}\r\n");
			sb1.append("</script>\r\n");
			sb1.append("<body>\r\n");
			sb1.append("<section class=\"\">\r\n");
			sb1.append("	<div id=\"dv"+svc_cls_pfix+"Pop\" class=\"modal shot-pop\" title=\""+ds1.getString("t_nm",i)+" 정보\">\r\n");
			sb1.append("		<div class=\"modal__container\">\r\n");
			sb1.append("			<div class=\"modal__content\">\r\n");
			for (int k=0;k<cntKey;k++) {
				if (ds1.getString("base_yn",k).equals("Y") || ds1.getString("spare_yn",k).equals("Y")) continue;
				sb1.append("				<input type=\"hidden\" name=\""+ds1.getString("key_camel",k)+"\" id=\""+ds1.getString("key_camel",k)+"\">\r\n");
			}
			sb1.append("\r\n");
			sb1.append("				<table>\r\n");
			sb1.append("					<colgroup>\r\n");
			sb1.append("					<col width=\"15%\">\r\n");
			sb1.append("					<col>\r\n");
			sb1.append("					<col width=\"15%\">\r\n");
			sb1.append("					<col>\r\n");
			sb1.append("					</colgroup>\r\n");
			sb1.append("					<tbody>\r\n");
			TDataSet ds2 = new TDataSet();
			ds2 = ds1;
			int v1 = ds2.getFindRow("column_name", "REG_DTM");
			if (v1 > -1) {ds2.deleteRow(v1);}
			v1 = ds2.getFindRow("column_name", "REG_USER_ID");
			if (v1 > -1) {ds2.deleteRow(v1);}
			v1 = ds2.getFindRow("column_name", "UPD_DTM");
			if (v1 > -1) {ds2.deleteRow(v1);}
			v1 = ds2.getFindRow("column_name", "UPD_USER_ID");
			if (v1 > -1) {ds2.deleteRow(v1);}
			while(true) {
				int v2 = ds2.getFindRow("spare_yn", "Y");
				if (v2 < 0) break;
				ds2.deleteRow(v2);
			}
			while(true) {
				int v2 = ds2.getFindRow("pkey", "Y");
				if (v2 < 0) break;
				ds2.deleteRow(v2);
			}
			int cntCol2 = ds2.getRowCount("column_name");
			if (cntCol % 2 == 1) {
				cntCol2++;
			}
			for (int k=0;k<cntCol2;k+=2) {
				String s0 = ds2.getString("column_name_camel",k);
				if (s0.equals("")) continue;
				String sKey = ds2.getString("key",k);
				String sKey2 = ds2.getString("key",k+1);
				String sRo = "";
				String sRo2 = "";
				if (sKey.equals("")==false) sRo = "readonly ";
				if (sKey2.equals("")==false) sRo2 = "readonly ";
				sb1.append("						<tr>\r\n");
				sb1.append("							<th scope=\"row\">"+ds2.getString("c_nm",k)+"</th>\r\n");
				sb1.append("							<td><span name=\""+ds2.getString("column_name_camel",k)+"\" id=\""+ds2.getString("column_name_camel",k)+"\"></span></td>\r\n");
				String s1 = ds2.getString("column_name_camel",k+1);
				if (s1.equals("")){
					sb1.append("					<th>&nbsp;</th>\r\n");
					sb1.append("					<td>\r\n");
					sb1.append("						&nbsp;\r\n");
					sb1.append("					</td>\r\n");
				}
				else{
					sb1.append("							<th scope=\"row\">"+ds2.getString("c_nm",k+1)+"</th>\r\n");
					sb1.append("							<td><span name=\""+ds2.getString("column_name_camel",k+1)+"\" id=\""+ds2.getString("column_name_camel",k+1)+"\"></span></td>\r\n");
				}
				sb1.append("				</tr>\r\n");
			}
			sb1.append("					</tbody>\r\n");
			sb1.append("				</table>\r\n");
			sb1.append("			</div>\r\n");
			sb1.append("			<div class=\"modal__footer\">\r\n");
			sb1.append("				<button type=\"button\" class=\"btn btn-secondary\" data-popup-close=\"popup-name\" onClick=\"fnClose();\">닫기</button>\r\n");
			sb1.append("			</div>\r\n");
			sb1.append("		</div>\r\n");
			sb1.append("	</div>\r\n");
			sb1.append("</section>\r\n");
			sb1.append("</body>\r\n");
			sb1.append("</html>\r\n");
			
			bw.write(sb1.toString());
		}catch(Exception e){}
		finally{ try{if (bw !=null) bw.close();} catch(Exception e){try { if ( bw != null) { bw.close();} } catch(Exception ex) {}}}
	}

}
