package com.app.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.base.ResponseMap;
import com.base.error.RestApiException;
import com.base.error.UserErrorCode;
import com.common.utils.Excel;
import com.common.utils.MapUtil;

import lombok.RequiredArgsConstructor;

@Controller
@Lazy
@RequiredArgsConstructor
public class CommonController {

	@RequestMapping("/app/common/excelUpload.do")
	@ResponseBody
	public Object excelUpload(HttpServletResponse response,HttpServletRequest request,
			MultipartFile file1, String params) throws Exception {
		//params(columnNames)
		ResponseMap responseMap = null;

		if (file1 == null || file1.getSize() < 1) {
			throw new RestApiException(UserErrorCode.EXPECTATION_FAILED);
		}
		Map<String,Object> map = MapUtil.getStrToMap(params);
		List<Map<String,Object>> list = Excel.excelToList(file1, map);
		System.out.println("excel upload data...."+MapUtil.getListToStr(list));
		int cnt = list.size();
		if(cnt > 0) {
			responseMap = new ResponseMap("Y","");
		}
		else {
			responseMap = new ResponseMap("N","Not exists data.");
		}
		
		return responseMap.getResultMap();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/app/common/excelDownload.do")
	@ResponseBody
	public Object excelDownload(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), list(List<map>):data
		ResponseMap responseMap = null;

		Map<String,Object> map = MapUtil.getStrToMap(params);
		Map<String,Object> m1 = new HashMap<String,Object>();
		m1.put("xlsName", map.get("xlsName"));
		m1.put("sheetName", map.get("sheetName"));
		m1.put("columnHeaders", map.get("columnHeaders"));
		m1.put("columnNames", map.get("columnNames"));
		try {
			Excel.downloadExcel(m1, (List<Map<String,Object>>)map.get("list"), response, request);
			responseMap = new ResponseMap("Y","");
		}catch(Exception e) {
			responseMap = new ResponseMap("N","download error.");
		}
		
		return responseMap.getResultMap();
	}

}
