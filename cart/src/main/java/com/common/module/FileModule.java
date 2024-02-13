package com.common.module;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class FileModule {
	//req : fileOrgNm,filePath
	//resp : fileId(36byte),fileSize,fileOrgNm,filePath,resultYn,resultMsg
	public static Map<String,Object> fileUpload(MultipartFile file,Map<String,Object> map){
		Map<String,Object> rMap = new HashMap<String,Object>();
		rMap.put("fileOrgNm", map.get("fileOrgNm"));
		rMap.put("filePath", map.get("filePath"));
		rMap.put("resultYn", "N");
		rMap.put("resultMsg", "");
		rMap.put("fileSize", 0);
		try {
			if (file == null || file.getSize() < 1) {
				rMap.put("resultMsg", "Not exists file.");
				return rMap;
			}
			rMap.put("fileSize", file.getSize());
			String fileId = UUID.randomUUID().toString();
			rMap.put("fileId", fileId);
			String sPath = map.get("filePath")==null?"":map.get("filePath").toString();
			if (sPath.equals("")) {
				rMap.put("resultMsg", "Not exists file path.");
				return rMap;
			}
			File f 	= new File(sPath);
			if(!f.exists()) {
		    	f.mkdirs();
		    }
			String fileNm = sPath + fileId;
			File transFile = new File(fileNm);
			file.transferTo( transFile );
			
			
			rMap.put("resultYn", "Y");
		}catch(Exception e) {
			rMap.put("resultMsg", "file write error.");
		}
		
		return rMap;
	}
	
	public static int fileDownload(HttpServletRequest request, HttpServletResponse response, Map<String,Object> map) {
		////map(fileId,filePath,fileOrgNm)
		int iRtn = 0;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			String fileId = map.get("fileId")==null?"":map.get("fileId").toString();
			String filePath = map.get("filePath")==null?"":map.get("filePath").toString();
			String fileOrgNm = map.get("fileOrgNm")==null?"":map.get("fileOrgNm").toString();
			if (fileId.equals("")) return -10;
			if (filePath.equals("")) return -20;
			if (fileOrgNm.equals("")) return -30;
			
			File dFile  = new File(filePath, fileId);
			iRtn = (int) dFile.length();
			if (iRtn < 1) return iRtn;

			try {
				setDisposition(fileOrgNm,request,response);
//				response.setContentType("application/octet-stream; charset=utf-8");
//				response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileOrgNm, "UTF-8"));
				
				in = new BufferedInputStream(new FileInputStream(dFile));
				out = new BufferedOutputStream(response.getOutputStream());
				byte[] buffer = new byte[4096];
			 	int bytesRead=0;
			 	
			 	while ((bytesRead = in.read(buffer))!=-1) {
					out.write(buffer, 0, bytesRead);
				}
				out.flush();
			}catch(Exception e) {
				iRtn = -1;
			}
			
			iRtn = 1;	//success
		}
		finally {
			try{
				if (in != null) in.close();
				if (out != null) out.close();
			}
			catch(Exception e) {}
		}
		
		return iRtn;
	}

	public static int fileDownload(HttpServletRequest request, HttpServletResponse response, File dFile, String fileOrgNm) {
		////map(fileId,filePath,fileOrgNm)
		int iRtn = 0;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			iRtn = (int) dFile.length();
			if (iRtn < 1) return iRtn;

			try {
				setDisposition(fileOrgNm,request,response);
				
				in = new BufferedInputStream(new FileInputStream(dFile));
				out = new BufferedOutputStream(response.getOutputStream());
				byte[] buffer = new byte[4096];
			 	int bytesRead=0;
			 	
			 	while ((bytesRead = in.read(buffer))!=-1) {
					out.write(buffer, 0, bytesRead);
				}
				out.flush();
			}catch(Exception e) {
				iRtn = -1;
			}
			
			iRtn = 1;	//success
		}
		finally {
			try{
				if (in != null) in.close();
				if (out != null) out.close();
			}
			catch(Exception e) {}
		}
		
		return iRtn;
	}

	private static void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String browser = getBrowser(request);
		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;
		
		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) {		// IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
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
			throw new IOException("Not supported browser");
		}
		
		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)){
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
    }
    /**
     * 브라우저 구분 얻기.
     *
     * @param request
     * @return String
     */
    private static String getBrowser(HttpServletRequest request) {
    	String header = request.getHeader("User-Agent");
        if (header.indexOf("MSIE") > -1) {
            return "MSIE";
        } else if (header.indexOf("Trident") > -1) {	// IE11 문자열 깨짐 방지
            return "Trident";
        } else if (header.indexOf("Chrome") > -1) {
            return "Chrome";
        } else if (header.indexOf("Opera") > -1) {
            return "Opera";
        }
        return "Firefox";
    }

}
