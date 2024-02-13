package com.app.common;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.base.ResponseMap;
import com.common.module.FileModule;
import com.common.utils.MapUtil;

import lombok.RequiredArgsConstructor;

@Controller
@Lazy
@RequiredArgsConstructor
public class FileController {
	
	@Value("${file.path.sample}")
	private String preFixPath;

//	@RequestMapping(value="/app/common/file/fileUpload.do", method = RequestMethod.POST, 
//			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@RequestMapping(value="/app/common/file/fileUpload.do", method = RequestMethod.POST)
	@ResponseBody
	public Object fileUpload(HttpServletResponse response, String params, MultipartFile file1) throws Exception {
		//map(fileOrgNm,filePath), file
		Map<String,Object> fileMap = MapUtil.getStrToMap(params);
		String fileOrgNm = fileMap.get("fileOrgNm")==null?"":fileMap.get("fileOrgNm").toString();
		String filePath = fileMap.get("filePath")==null?"":fileMap.get("filePath").toString();
		filePath = preFixPath + filePath;
		
		ResponseMap responseMap = null;

		if (file1 == null || file1.getSize() < 1) return (new ResponseMap("N","Not found file.").getResultMap());

		//fileOrgNm,filePath
		if (fileOrgNm.equals("")) {
			fileOrgNm = file1.getOriginalFilename();
		}
		Map<String,Object> m1 = new HashMap<String,Object>();
		m1.put("fileOrgNm", fileOrgNm);
		m1.put("filePath", filePath);
		m1 = FileModule.fileUpload(file1, m1);
		if (m1.get("resultYn").toString().equals("Y")) {
			responseMap = new ResponseMap("Y","");
		}
		else {
			responseMap = new ResponseMap("N",m1.get("resultMsg").toString());
		}

		return responseMap.getResultMap();
	}
	
	@RequestMapping(value = "/app/common/file/fileUploadSrc.do", method = RequestMethod.POST)
	@ResponseBody
//	public Object fileUploadSrc(@RequestParam final Map<String,Object> map) throws Exception {
	public Object fileUploadSrc(@RequestBody final Map<String,Object> map) throws Exception {
		String fileOrgNm = map.get("fileOrgNm")==null?"":map.get("fileOrgNm").toString();
		String filePath = map.get("filePath")==null?"":map.get("filePath").toString();
		String file = map.get("file")==null?"":map.get("file").toString();
//		file = java.net.URLDecoder.decode(file,"utf-8");
		filePath = preFixPath + filePath;
		HashMap<String,Object> hMap = new HashMap<String,Object>();
		
		String fileId = UUID.randomUUID().toString();
		hMap.put("fileId", fileId);
		hMap.put("fileOrgNm", fileOrgNm);
		hMap.put("filePath", filePath);
		hMap.put("resultYn", "N");
		hMap.put("resultMsg", "");
		String sPath = map.get("filePath")==null?"":map.get("filePath").toString();

		File f 	= new File(sPath);
		if(!f.exists()) {
	    	f.mkdirs();
	    }

		String fileNm = sPath + fileId;

		FileOutputStream fos = null;
		try {
			Decoder decoder = Base64.getDecoder();
			byte[] decodedByte = decoder.decode(file.split(",")[1]);
			hMap.put("fileSize", decodedByte.length);
			fos = new FileOutputStream(fileNm);
			fos.write(decodedByte);
		}
		finally{
			fos.close();
		}

		return hMap;
	}
	
	@RequestMapping(value="/app/common/file/fileDown.do",method = RequestMethod.POST)
	@ResponseBody
	public Object fileDown(HttpServletRequest request,HttpServletResponse response, String params) throws Exception {
		//map(fileId,filePath,fileOrgNm)
		ResponseMap responseMap = null;
		
		Map<String,Object> fileMap = MapUtil.getStrToMap(params);
		String filePath = fileMap.get("filePath")==null?"":fileMap.get("filePath").toString();
		fileMap.put("filePaht", preFixPath + filePath);
		int iRtn = FileModule.fileDownload(request, response, fileMap);
		if (iRtn == 1) {
			responseMap = new ResponseMap("Y","");
		}
		else {
			responseMap = new ResponseMap("N","download error.");
		}

		return responseMap.getResultMsg();
	}

}
