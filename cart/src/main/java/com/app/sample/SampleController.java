package com.app.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.api.base.ApiData;
import com.api.base.RestCall;
import com.api.base.ResultApiData;
import com.app.code.model.CodeExDto;
import com.app.code.service.CodeService;
import com.base.ResponseList;
import com.base.ResponseMap;
import com.common.utils.ClassUtil;
import com.common.utils.Excel;
import com.common.utils.MapUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.RequiredArgsConstructor;

@Controller
@Lazy
@RequiredArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SampleController {

	@Autowired
	private CodeService codeService;

	@Autowired
	private SampleService sampleService;

	@Value("${restInfo.url.local}")
	private String apiUrl;

	@RequestMapping("app/sample/sampleReact.do")
	public String sampleReact(Model model) throws Exception {
		return "app/sample/sample_react";
	}

	@RequestMapping("app/sample/sample.do")
	public String sample(Model model) throws Exception {
		// thymeleaf test....
		CodeExDto codeExDto = new CodeExDto();
		final List<CodeExDto> codeList = this.codeService.getCodeList(codeExDto);
		//System.out.println("s2..."+MapUtil.getListToStr(codeList));
		model.addAttribute("codeList", MapUtil.getObjToList(codeList));
		// thymeleaf test....end.
		
		return "app/sample/sample";
	}

	@RequestMapping("app/sample/popup/samplePopup.do")
	public String samplePopup(HttpServletRequest request, Model model) throws Exception {
		model = MapUtil.requestToModel(request, model);
		return "app/sample/popup/samplePopup";
	}

	@RequestMapping(value = "app/sample/getSampleCodePageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getSampleCodePageList(HttpServletRequest request, @Valid final CodeExDto params) throws Exception {
//System.out.println("param.................." + MapUtil.getClassToStr(params));
		final int totalRows = this.codeService.getCodeListCount(params);
		final List<CodeExDto> codeList = this.codeService.getCodeList(params);
		
		Map<String,Object> resultMap = (new ResponseList(codeList, totalRows)).getResultMap();
		//System.out.println("s1..."+MapUtil.getMapToStr(resultMap));
		
		return resultMap;
	}

	@RequestMapping(value = "app/api/inf_sample_getCodePageList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object if_sample_getCodePageList(HttpServletRequest request, @Valid final CodeExDto params) throws Exception {
//System.out.println("param.................." + MapUtil.getClassToStr(params));
		ApiData<List<CodeExDto>> apiData = sampleService.inf_code_getCodePageList(params);
		List<CodeExDto> codeList = apiData.getBody();
		int totalRows = apiData.getPageInfo().getTotalCount();
		
		Map<String,Object> resultMap = (new ResponseList(codeList, totalRows)).getResultMap();
		//System.out.println("s1..."+MapUtil.getMapToStr(resultMap));
		
		return resultMap;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("app/sample/getCodeExcelDown.do")
	@ResponseBody
	public Object getCodeExcelDown(HttpServletResponse response,HttpServletRequest request,
			String params) throws Exception {
		//xlsName,sheetName,columnHeaders(list),columnNames(list), condition(Map)
		ResponseMap responseMap = null;
		
		Map<String,Object> map = MapUtil.getStrToMap(params);

		CodeExDto codeExDto = new CodeExDto();
		codeExDto = (CodeExDto)ClassUtil.getMapToClass((Map<String,Object>)map.get("condition"), codeExDto);
		final List<CodeExDto> listData = this.codeService.getCodeList(codeExDto);
		
		Map<String,Object> m1 = new HashMap<String,Object>();
		m1.put("xlsName", map.get("xlsName"));
		m1.put("sheetName", map.get("sheetName"));
		m1.put("columnHeaders", map.get("columnHeaders"));
		m1.put("columnNames", map.get("columnNames"));
		try {
			Excel.downloadClsToExcel(m1, listData, response, request);
			responseMap = new ResponseMap("Y","");
		}catch(Exception e) {
			responseMap = new ResponseMap("N","download error.");
		}
		
		return responseMap.getResultMap();
	}

	@RequestMapping(value="/app/api/inf_file_fileUpload.do", method = RequestMethod.POST)
	@ResponseBody
	public Object inf_file_fileUpload(String params, MultipartFile file1) throws Exception {

		ApiData<Map<String,Object>> apiData = sampleService.inf_file_fileUpload(params, file1);
		Map<String,Object> resultMap = apiData.getBody();
		
		return resultMap;

	}

	@SuppressWarnings("serial")
	@RequestMapping(value="/app/api/inf_file_fileUpload1.do", method = RequestMethod.POST)
	@ResponseBody
	public Object inf_file_fileUpload1(String params, MultipartFile file1) throws Exception {
		if (file1.isEmpty()) {
			return (new HashMap<String, Object>() {{
				put("code", "03");
				put("message", "file not found.");
			}});
		}
		ByteArrayResource fileResource = new ByteArrayResource(file1.getBytes()) {
			
		    @Override
		    public String getFilename() {
				return file1.getOriginalFilename();
		    }
		};

		Map<String,Object> fileMap = MapUtil.getStrToMap(params);
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.setAll(fileMap);
		body.add("file1", fileResource);
		
		final String urlPath = apiUrl + "/inf/file_fileUpload";
		RestCall restCall = RestCall.client(urlPath);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_MIXED);
		restCall.sethttpHeaders(headers);

		final ResultApiData<ApiData<Map<String,Object>>> response = 
				restCall.post(body, new ParameterizedTypeReference<ApiData<Map<String,Object>>>(){});

		//API 호출중 에러가 발생 검사
		if (response.hasError()){
			return (new HashMap<String, Object>() {{
				put("code", "01");
				put("message", "API Call ERROR");
			}});
		}
		//업무 응답코드가 정상 검사
		final ApiData<Map<String,Object>> apiData = response.getBody();
		if(!"00".equals(apiData.getCode())){
			return (new HashMap<String, Object>() {{
				put("code", apiData.getCode());
				put("message", apiData.getMessage());
			}});
		}
		
		Map<String,Object> resultMap = apiData.getBody();
		
		return resultMap;

	}

	@SuppressWarnings("serial")
	@RequestMapping(value="/app/api/inf_file_fileDownload.do", method = RequestMethod.POST)
	@ResponseBody
	public Object inf_file_fileDownload(HttpServletRequest request,HttpServletResponse response, String params) throws Exception {
		//map(fileId,filePath,fileOrgNm)
		Map<String,Object> fileMap = MapUtil.getStrToMap(params);
		ApiData<Map<String,Object>> apiData = sampleService.inf_file_fileDownload(request, response, fileMap);
		if(!"00".equals(apiData.getCode())){
			return (new HashMap<String, Object>() {{
				put("code", apiData.getCode());
				put("message", apiData.getMessage());
			}});
		}
		
		Map<String,Object> resultMap = apiData.getBody();
		
		return resultMap;
	}


	@RequestMapping(value = "/app/sample/getDeptList.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String,Object>> getDeptList(@RequestBody final Map<String,Object> map) throws Exception {
		final List<Map<String,Object>> listData = this.sampleService.getDeptList(map);
		return listData;
	}
}
