package com.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class Excel {

	private static String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}
	
	private static void setDisposition(String filename, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String browser = getBrowser(request);

		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll(
					"\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\""
					+ new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\""
					+ new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			// throw new RuntimeException("Not supported browser");
			throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix
				+ encodedFilename);

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}
	
	@SuppressWarnings("unused")
	private static CellStyle titleStyle( Workbook workbook ) {
		Font font = workbook.createFont();
		font.setFontHeightInPoints( ( short ) 20 );
		font.setBold( true );
		font.setFontName( "돋움" );
		font.setColor( HSSFColor.HSSFColorPredefined.BLACK.getIndex() );
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont( font );
		cellStyle.setAlignment( HorizontalAlignment.CENTER );
		cellStyle.setVerticalAlignment( VerticalAlignment.CENTER );
		cellStyle.setWrapText( true );
		return cellStyle;
	}

	private static CellStyle headerStyle( Workbook workbook ) {
		Font font = workbook.createFont();
		font.setFontHeightInPoints( ( short ) 11 );
		font.setBold( true );
		font.setFontName( "돋움" );
		font.setColor( HSSFColor.HSSFColorPredefined.BLACK.getIndex() );
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont( font );
		cellStyle.setFillForegroundColor( HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex() );
		cellStyle.setFillPattern( FillPatternType.SOLID_FOREGROUND );
		cellStyle.setBorderBottom( BorderStyle.THIN );
		cellStyle.setBottomBorderColor( HSSFColor.HSSFColorPredefined.BLACK.getIndex() );
		cellStyle.setBorderLeft( BorderStyle.THIN );
		cellStyle.setLeftBorderColor( HSSFColor.HSSFColorPredefined.BLACK.getIndex() );
		cellStyle.setBorderRight( BorderStyle.THIN );
		cellStyle.setRightBorderColor( HSSFColor.HSSFColorPredefined.BLACK.getIndex() );
		cellStyle.setBorderTop( BorderStyle.THIN );
		cellStyle.setTopBorderColor( HSSFColor.HSSFColorPredefined.BLACK.getIndex() );
		cellStyle.setAlignment( HorizontalAlignment.CENTER );
		cellStyle.setVerticalAlignment( VerticalAlignment.CENTER );
		cellStyle.setWrapText( true );
		cellStyle.setDataFormat(workbook.createDataFormat().getFormat("@"));
		return cellStyle;
	}

	private static CellStyle rowStyle( Workbook workbook ) {
		Font font = workbook.createFont();
		font.setFontHeightInPoints( ( short ) 11 );
		font.setBold( false );
		font.setFontName( "돋움" );
		font.setColor( HSSFColor.HSSFColorPredefined.BLACK.getIndex() );
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont( font );
		cellStyle.setFillForegroundColor( HSSFColor.HSSFColorPredefined.WHITE.getIndex() );
		cellStyle.setFillPattern( FillPatternType.SOLID_FOREGROUND );
		cellStyle.setBorderBottom( BorderStyle.THIN );
		cellStyle.setBottomBorderColor( HSSFColor.HSSFColorPredefined.BLACK.getIndex() );
		cellStyle.setBorderLeft( BorderStyle.THIN );
		cellStyle.setLeftBorderColor( HSSFColor.HSSFColorPredefined.BLACK.getIndex() );
		cellStyle.setBorderRight( BorderStyle.THIN );
		cellStyle.setRightBorderColor( HSSFColor.HSSFColorPredefined.BLACK.getIndex() );
		cellStyle.setBorderTop( BorderStyle.THIN );
		cellStyle.setTopBorderColor( HSSFColor.HSSFColorPredefined.BLACK.getIndex() );
		cellStyle.setAlignment( HorizontalAlignment.LEFT );
		cellStyle.setVerticalAlignment( VerticalAlignment.CENTER );
		cellStyle.setWrapText( true );
		cellStyle.setDataFormat(workbook.createDataFormat().getFormat("@"));
		return cellStyle;
	}

	@SuppressWarnings("deprecation")
	private static String getCellStringValue(XSSFCell cell){
		//cell.setCellType(Cell.CELL_TYPE_STRING);
		if(cell == null)
			return "";

		String value = "";

		switch( cell.getCellType() ) {
		case STRING:
			value = cell.getRichStringCellValue().getString();
			break;
		case NUMERIC:
			if( DateUtil.isCellDateFormatted(cell) ) {
				java.util.Date dateValue = cell.getDateCellValue();
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				int year = dateValue.getYear(); // or getYear
				if (year != -1){
					value = dateFormat1.format(dateValue);
				}
			}
			else {

				DecimalFormat dft = new DecimalFormat("########################.########");
				value = dft.format(cell.getNumericCellValue());
			}
			break;

		case FORMULA:
			DecimalFormat dft = new DecimalFormat("########################.########");
			value = dft.format(cell.getNumericCellValue());
			//value =  cell.getCellFormula(); 
			break;

		case BOOLEAN:
			value = new Boolean(cell.getBooleanCellValue()).toString();  	break;

		case ERROR:
		   cell.getErrorCellValue();  break;

		case BLANK: break;

		default: break;
		}
		return value;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void downloadClsToExcel(Map<String,Object> map, List<?> list, HttpServletResponse response,HttpServletRequest request) throws Exception{
		String xlsName = map.get("xlsName").toString();
		String sheetName = map.get("sheetName").toString();
		List<String> columnHeaders = map.get("columnHeaders")==null?null:(List<String>)map.get("columnHeaders");
		List<String> columnNames = (List<String>)map.get("columnNames");
		int cntCols = columnNames.size();
		
		// Workbook 생성
		XSSFWorkbook wb = new XSSFWorkbook();
		// Sheet 생성
		XSSFSheet sheet = wb.createSheet( sheetName );
		// CellStyle 설정
		CellStyle style = headerStyle( wb );
		//title
		// Row 추가. 0부터 시작
		if (columnHeaders != null) {
			Row row0 = sheet.createRow(0);
			for(int i=0;i<cntCols;i++){
				Cell cell = row0.createCell(i);
				cell.setCellStyle(style);
				cell.setCellType( CellType.STRING );
				cell.setCellValue(columnHeaders.get(i));
			}
		}
		//data 추가
		style = rowStyle( wb );
		if (list != null && list.size() > 0){
			int cnt = list.size();
			int addRow = 1;
			if (columnHeaders == null) addRow = 0;
			
			for(int i=0;i<cnt;i++){
				try {
					Map<String,Object> m1 = ClassUtil.getClassToMap(list.get(i));	//class -> map
					
					Row row = sheet.createRow((i+addRow));
					for(int k=0;k<cntCols;k++){
						Cell cell = row.createCell(k);
						cell.setCellStyle(style);
						cell.setCellType( CellType.STRING );
						String s1 = m1.get(columnNames.get(k))==null?"":m1.get(columnNames.get(k)).toString();
						cell.setCellValue(s1);
					}
				}catch(Exception e) {}
			}
			
			if (cnt == 0) {
				for(int i=0;i<100;i++) {
					Row row = sheet.createRow((i+addRow));
					for(int k=0;k<cntCols;k++){
						Cell cell = row.createCell(k);
						cell.setCellStyle(style);
						cell.setCellType( CellType.STRING );
					}
				}
			}
		}
		else {
			int cnt = 100;
			int addRow = 1;
			for(int i=0;i<cnt;i++) {
				Row row = sheet.createRow((i+addRow));
				row.setRowStyle(style);
			}
		}
		//size
		for(int k=0;k<cntCols;k++){
			sheet.autoSizeColumn(k);
			sheet.setColumnWidth(k, (int)(sheet.getColumnWidth(k)*1.2));
		}
		
		//파일다운로드 처리
		String mimetype = "application/x-msdownload"; 

		response.setContentType( mimetype );
		setDisposition( URLEncoder.encode(xlsName, "UTF-8")+".xlsx", request, response );

		BufferedInputStream in = null;
		BufferedOutputStream out = null;

		try{
			out = new BufferedOutputStream( response.getOutputStream() );
			wb.write(out);
			out.flush();
			out.close();
		}catch( Exception ex ){
			ex.printStackTrace();
		}
		finally{
			if( in != null ){
				try{
					in.close();
				}catch( Exception ignore ){
					ignore.printStackTrace();
				}
			}
			if( out != null ){
				try{
					out.flush();
					out.close();
				}catch( Exception ignore ){
					ignore.printStackTrace();
				}
			}
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public static void downloadExcel(Map<String,Object> map, List<Map<String,Object>> list, HttpServletResponse response,HttpServletRequest request) throws Exception{
		//xlsName,sheetName,columnHeaders(list),columnNames(list)
		String xlsName = map.get("xlsName").toString();
		String sheetName = map.get("sheetName").toString();
		List<String> columnHeaders = map.get("columnHeaders")==null?null:(List<String>)map.get("columnHeaders");
		List<String> columnNames = (List<String>)map.get("columnNames");
		int cntCols = columnNames.size();
		
		// Workbook 생성
		XSSFWorkbook wb = new XSSFWorkbook();
		// Sheet 생성
		XSSFSheet sheet = wb.createSheet( sheetName );
		// CellStyle 설정
		CellStyle style = headerStyle( wb );
		//title
		// Row 추가. 0부터 시작
		if (columnHeaders != null) {
			Row row0 = sheet.createRow(0);
			for(int i=0;i<cntCols;i++){
				Cell cell = row0.createCell(i);
				cell.setCellStyle(style);
				cell.setCellType( CellType.STRING );
				cell.setCellValue(columnHeaders.get(i));
			}
		}
		//data 추가
		style = rowStyle( wb );
		if (list != null && list.size() > 0){
			int cnt = list.size();
			int addRow = 1;
			if (columnHeaders == null) addRow = 0;
			
			for(int i=0;i<cnt;i++){
				try {
					Map<String,Object> m1 = list.get(i);
					
					Row row = sheet.createRow((i+addRow));
					for(int k=0;k<cntCols;k++){
						Cell cell = row.createCell(k);
						cell.setCellStyle(style);
						cell.setCellType( CellType.STRING );
						String s1 = m1.get(columnNames.get(k))==null?"":m1.get(columnNames.get(k)).toString();
						cell.setCellValue(s1);
					}
				}catch(Exception e) {}
			}
			
			if (cnt == 0) {
				for(int i=0;i<100;i++) {
					Row row = sheet.createRow((i+addRow));
					for(int k=0;k<cntCols;k++){
						Cell cell = row.createCell(k);
						cell.setCellStyle(style);
						cell.setCellType( CellType.STRING );
					}
				}
			}
		}
		else {
			int cnt = 100;
			int addRow = 1;
			for(int i=0;i<cnt;i++) {
				Row row = sheet.createRow((i+addRow));
				row.setRowStyle(style);
			}
		}
		//size
		for(int k=0;k<cntCols;k++){
			sheet.autoSizeColumn(k);
			sheet.setColumnWidth(k, (int)(sheet.getColumnWidth(k)*1.2));
		}
		
		//파일다운로드 처리
		String mimetype = "application/x-msdownload"; 

		response.setContentType( mimetype );
		setDisposition( URLEncoder.encode(xlsName, "UTF-8")+".xlsx", request, response );

		BufferedInputStream in = null;
		BufferedOutputStream out = null;

		try{
			out = new BufferedOutputStream( response.getOutputStream() );
			wb.write(out);
			out.flush();
			out.close();
		}catch( Exception ex ){
			ex.printStackTrace();
		}
		finally{
			if( in != null ){
				try{
					in.close();
				}catch( Exception ignore ){
					ignore.printStackTrace();
				}
			}
			if( out != null ){
				try{
					out.flush();
					out.close();
				}catch( Exception ignore ){
					ignore.printStackTrace();
				}
			}
		}
		
	}
	
	@SuppressWarnings({ "unchecked" })
	public static List<Map<String,Object>> excelToList(MultipartFile file,Map<String,Object> map) throws Exception{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<String> columnNames = (List<String>)map.get("columnNames");
		
		XSSFWorkbook wb; 
		XSSFSheet sheet;

		try {
			wb=new XSSFWorkbook(file.getInputStream());
			sheet = wb.getSheetAt(0);
			int rowCnt = sheet.getPhysicalNumberOfRows();

			for(int j = 0; j<rowCnt; j++){
				XSSFRow row = sheet.getRow(j);
				if (row == null) {
					continue;
				} else {
					int cellCnt = row.getPhysicalNumberOfCells();

					Map<String,Object> m1 = new HashMap<String,Object>();
					for(int k=0; k<cellCnt; k++){
						XSSFCell cell = row.getCell(k);

						if (cell == null){
							m1.put(columnNames.get(k),"");
							continue;
						}
						String  cellValue = getCellStringValue(cell);

						m1.put(columnNames.get(k),cellValue);
					}
					list.add(m1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
		finally{
			
		}
		
		return list;
	}

	@SuppressWarnings({ "unchecked", "resource" })
	public static List<Map<String,Object>> excelToList(File file,Map<String,Object> map) throws Exception{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<String> columnNames = (List<String>)map.get("columnNames");
		
		XSSFWorkbook wb; 
		XSSFSheet sheet;

		InputStream inStream = null;

		try {
			inStream = new FileInputStream(file);

			wb=new XSSFWorkbook(inStream);
			sheet = wb.getSheetAt(0);
			int rowCnt = sheet.getPhysicalNumberOfRows();

			for(int j = 0; j<rowCnt; j++){
				XSSFRow row = sheet.getRow(j);
				if (row == null) {
					continue;
				} else {
					int cellCnt = row.getPhysicalNumberOfCells();

					Map<String,Object> m1 = new HashMap<String,Object>();
					for(int k=0; k<cellCnt; k++){
						XSSFCell cell = row.getCell(k);

						if (cell == null){
							m1.put(columnNames.get(k),"");
							continue;
						}
						String  cellValue = getCellStringValue(cell);

						m1.put(columnNames.get(k),cellValue);
					}
					list.add(m1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
		finally{
			if( inStream != null ){
				try{
					inStream.close();
				}catch( Exception ignore ){
					ignore.printStackTrace();
				}
			}
		}
		
		return list;
	}

	////////////////////////// DataSet
	@SuppressWarnings("deprecation")
	public static void downloadExcelFrDS(TDataSet dsCond, TDataSet dsData, HttpServletResponse response,HttpServletRequest request) throws Exception{
		String xlsName = dsCond.getString("xlsName");
		String sheetName = dsCond.getString("sheetName");
		int cntCols = dsCond.getRowCount("columnNames");
		int cntHeader = dsCond.getRowCount("columnHeaders");
		String firstNm = "";
		if (cntCols > 0){
			firstNm = dsCond.getString("columnNames");
		}
		
		// Workbook 생성
		XSSFWorkbook wb = new XSSFWorkbook();
		// Sheet 생성
		XSSFSheet sheet = wb.createSheet( sheetName );
		// CellStyle 설정
		CellStyle style = headerStyle( wb );
		//title
		// Row 추가. 0부터 시작
		if (cntHeader > 0) {
			Row row0 = sheet.createRow(0);
			for(int i=0;i<cntCols;i++){
				Cell cell = row0.createCell(i);
				cell.setCellStyle(style);
				cell.setCellType( CellType.STRING );
				cell.setCellValue(dsCond.getString("columnHeaders",i));
			}
		}
		//data 추가
		style = rowStyle( wb );
		if (dsData != null){
			int cnt = 0;
			int addRow = 1;
			if (cntHeader < 1) addRow = 0;
			
			if (!firstNm.equals("")) cnt = dsData.getRowCount(firstNm);
			for(int i=0;i<cnt;i++){
				Row row = sheet.createRow((i+addRow));
				for(int k=0;k<cntCols;k++){
					Cell cell = row.createCell(k);
					cell.setCellStyle(style);
					cell.setCellType( CellType.STRING );
					cell.setCellValue(dsData.getString(dsCond.getString("columnNames", k),i));
				}
			}
			
			if (cnt == 0) {
				for(int i=0;i<100;i++) {
					Row row = sheet.createRow((i+addRow));
					for(int k=0;k<cntCols;k++){
						Cell cell = row.createCell(k);
						cell.setCellStyle(style);
						cell.setCellType( CellType.STRING );
					}
				}
			}
		}
		else {
			int cnt = 100;
			int addRow = 1;
			for(int i=0;i<cnt;i++) {
				Row row = sheet.createRow((i+addRow));
				row.setRowStyle(style);
			}
		}
		//size
		for(int k=0;k<cntCols;k++){
			sheet.autoSizeColumn(k);
			sheet.setColumnWidth(k, (int)(sheet.getColumnWidth(k)*1.2));
		}
		
		//파일다운로드 처리
		String mimetype = "application/x-msdownload"; 

		response.setContentType( mimetype );
		setDisposition( URLEncoder.encode(xlsName, "UTF-8")+".xlsx", request, response );

		BufferedInputStream in = null;
		BufferedOutputStream out = null;

		try{
			out = new BufferedOutputStream( response.getOutputStream() );
			wb.write(out);
			out.flush();
			out.close();
		}catch( Exception ex ){
			ex.printStackTrace();
		}
		finally{
			if( in != null ){
				try{
					in.close();
				}catch( Exception ignore ){
					ignore.printStackTrace();
				}
			}
			if( out != null ){
				try{
					out.flush();
					out.close();
				}catch( Exception ignore ){
					ignore.printStackTrace();
				}
			}
		}
		
	}

	public static TDataSet excelToDs(MultipartFile file,TDataSet ds1) throws Exception{
		TDataSet ds = new TDataSet();
		
		XSSFWorkbook wb; 
		XSSFSheet sheet;

		try {
			wb=new XSSFWorkbook(file.getInputStream());
			sheet = wb.getSheetAt(0);
			int rowCnt = sheet.getPhysicalNumberOfRows();

			for(int j = 0; j<rowCnt; j++){
				XSSFRow row = sheet.getRow(j);
				if (row == null) {
					continue;
				} else {
					int cellCnt = row.getPhysicalNumberOfCells();

					for(int k=0; k<cellCnt; k++){
						XSSFCell cell = row.getCell(k);

						if (cell == null){
							ds.setValue(ds1.getString("columnNames",k),j, "");
							continue;
						}
						String  cellValue = getCellStringValue(cell);

						ds.setValue(ds1.getString("columnNames",k),j, cellValue.trim());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
		finally{
			
		}
		
		return ds;
	}
	@SuppressWarnings("resource")
	public static TDataSet excelToDs(File file,TDataSet ds1) throws Exception{
		TDataSet ds = new TDataSet();
		
		XSSFWorkbook wb; 
		XSSFSheet sheet;

		InputStream inStream = null;

		try {
			inStream = new FileInputStream(file);

			wb=new XSSFWorkbook(inStream);
			sheet = wb.getSheetAt(0);
			int rowCnt = sheet.getPhysicalNumberOfRows();

			for(int j = 0; j<rowCnt; j++){
				XSSFRow row = sheet.getRow(j);
				if (row == null) {
					continue;
				} else {
					int cellCnt = row.getPhysicalNumberOfCells();

					for(int k=0; k<cellCnt; k++){
						XSSFCell cell = row.getCell(k);

						if (cell == null){
							ds.setValue(ds1.getString("columnNames",k),j, "");
							continue;
						}
						String  cellValue = getCellStringValue(cell);

						ds.setValue(ds1.getString("columnNames",k),j, cellValue.trim());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
		finally{
			if( inStream != null ){
				try{
					inStream.close();
				}catch( Exception ignore ){
					ignore.printStackTrace();
				}
			}
		}
		
		return ds;
	}
	////////////////////////// DataSet End.

}
