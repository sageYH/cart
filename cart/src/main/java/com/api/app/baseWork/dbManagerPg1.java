package com.api.app.baseWork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.common.utils.TDataSet;

public class dbManagerPg1 {
	public Connection connect() throws SQLException {

		Connection conn = null;
		
		String sDBUrl = "jdbc:postgresql://localhost:5432/test";
		String sDBUser = "test";
		String sDBPW = "test123!";

		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(sDBUrl,sDBUser,sDBPW);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		return conn;
	}

	public boolean close(Connection conn) throws SQLException {
		boolean b_ck = true;
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se){
			b_ck = false;
		}
		return b_ck;
	}

	public TDataSet getResultData(ResultSet rs, ResultSetMetaData rsmd, int page, int rows){
		TDataSet tDs = new TDataSet();

		try{
			if ((rs == null)||(!rs.next())) return tDs;

			rs.last();
			tDs.totRows = rs.getRow();
			
			if (rows == 0) rows = tDs.totRows;
			tDs.totPages = tDs.totRows / rows;
			tDs.totPages = (tDs.totRows % rows) > 0 ? tDs.totPages + 1 : tDs.totPages;
			if (tDs.totPages < 1) tDs.totPages = 1;

			tDs.pages = page;
			if (page > tDs.totPages) tDs.pages = tDs.totPages;
			if (page < 1) tDs.pages = 1;
			int iprow = (tDs.pages - 1) * rows + 1;
			rs.absolute(iprow);

			int cols = rsmd.getColumnCount();
			for (int i = 0; i < rows; i++){
				tDs.rows++;
				for (int cols_num = 1; cols_num <= cols; cols_num++)	{
					String rtn_v = "";
					int cType = rsmd.getColumnType(cols_num);
					if (cType==93){
						SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
						if (rs.getTimestamp(cols_num)!=null){
							rtn_v = formatter.format(rs.getTimestamp(cols_num).getTime());
							formatter = new SimpleDateFormat("HHmmss", Locale.KOREA);
							rtn_v = rtn_v + " " + formatter.format(rs.getTimestamp(cols_num).getTime());
						}
					}
					else if (cType==-2 ||cType==-3 || cType==-4){
						tDs.setValue(rsmd.getColumnName(cols_num),i,rs.getBlob(cols_num));
						continue;
					}
					else{
						rtn_v = rs.getString(cols_num)==null?"":rs.getString(cols_num);
					}
					tDs.setValue(rsmd.getColumnName(cols_num),i,rtn_v);
				}
				if (!rs.next()) break;
			}
		}
		catch(Exception e){
			tDs.setMessage("011", e.getMessage());
			tDs.totRows = 0;
			tDs.rows = 0;
		}

		return tDs;
	}

	public TDataSet query(Connection conn, String sql, int page, int rows) throws SQLException {
		Statement st = null;
		ResultSet rs = null;

		TDataSet tDs = new TDataSet();

		try{
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery(sql);

			ResultSetMetaData rsmeta = rs.getMetaData();
			tDs = getResultData(rs,rsmeta,page,rows);
			if (!tDs.errorCode.equals("")) tDs.errorDoc = sql;
		}catch (SQLException e){
			tDs.setMessage("011",e.getMessage());
			tDs.errorDoc = sql;
			tDs.totRows = 0;
			tDs.rows = 0;
		}finally{
			try { if ( rs != null) { rs.close();} } catch(Exception ex) {}
			try { if ( st != null) { st.close();} } catch(Exception ex) {}
		}
		return tDs;
	}

	public TDataSet query(String sql,int page, int rows) throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		TDataSet tDs = new TDataSet();

		try{
			conn = connect();

			if (conn == null) {
				tDs.setMessage("012", "connection Error.");
				tDs.errorDoc = sql;
				tDs.totRows = 0;
				tDs.rows = 0;
				return tDs;
			}
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery(sql);
			
			ResultSetMetaData rsmeta = rs.getMetaData();
			tDs = getResultData(rs,rsmeta,page,rows);
			if (!tDs.errorCode.equals("")) tDs.errorDoc = sql;
		}catch (SQLException e){
			tDs.setMessage("011", e.getMessage());
			tDs.errorDoc = sql;
			tDs.totRows = 0;
			tDs.rows = 0;
		}finally{
			try { if ( rs != null) { rs.close();} } catch(Exception ex) {}
			try { if ( st != null) { st.close();} } catch(Exception ex) {}
			try { if ( conn != null) { conn.close();} } catch(Exception ex) {}
		}
		return tDs;
	}

	public TDataSet query(String sql) throws SQLException {
		return query(sql,1,0);
	}

	public TDataSet execute(String sql) throws SQLException {
		//errorCode: 01.not found 02.query Error 03.execute Error
		TDataSet tDs = new TDataSet();
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			tDs.setValue("RCOUNT", 0);
			conn = connect();
			ps = conn.prepareStatement(sql);
			int ir = ps.executeUpdate();
			tDs.setValue("RCOUNT", ir);
		}catch (SQLException e){
			tDs.setMessage("03", e.toString());
			tDs.errorDoc = sql;
		}finally{
			try { if ( ps != null) { ps.close();} } catch(Exception ex) {}
			try { if ( conn != null) { conn.close();} } catch(Exception ex) {}
		}
		return tDs;
	}

	public TDataSet execute(Connection conn, String sql) throws SQLException {
		TDataSet tDs = new TDataSet();
		PreparedStatement ps = null;
		try{
			tDs.setValue("RCOUNT", 0);
			ps = conn.prepareStatement(sql);
			int ir = ps.executeUpdate();
			tDs.setValue("RCOUNT", ir);
		}catch (SQLException e){
			tDs.setMessage("03", e.toString());
			tDs.errorDoc = sql;
		}finally{
			try { if ( ps != null) { ps.close();} } catch(Exception ex) {}
		}
		return tDs;
	}

	public int getIntValue(java.sql.ResultSet rs, int cols_num) {
		try{
			if (cols_num != 0) {
				return rs.getInt(cols_num);
			}
			return 0;
		}
		catch(Exception e){return 0;}
	}

}
