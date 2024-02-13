package com.app.sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import com.api.base.ApiData;
import com.api.base.RestCall;
import com.api.base.ResultApiData;
import com.app.code.model.CodeExDto;
import com.app.sample.mapper.SampleMapper;
import com.common.module.FileModule;
import com.common.utils.MapUtil;

@Service
public class SampleService {
	@Value("${restInfo.url.local}")
	private String apiUrl;

	@Autowired
	private SampleMapper sampleMapper;

	public ApiData<List<CodeExDto>> inf_code_getCodePageList(final CodeExDto params) throws Exception{
		final String urlPath = apiUrl + "/inf/code_getCodePageList";
		final ResultApiData<ApiData<List<CodeExDto>>> resData = RestCall.client(urlPath)
			.post(params, new ParameterizedTypeReference<ApiData<List<CodeExDto>>>(){});
		//API 호출중 에러가 발생 검사
		if (resData.hasError()){
			return ApiData.<List<CodeExDto>>builder().code("01").message("api call error.").build();
		}
		//업무 응답코드가 정상 검사
		final ApiData<List<CodeExDto>> apiData = resData.getBody();
		if(!"00".equals(apiData.getCode())){
			return ApiData.<List<CodeExDto>>builder().code("02").message("api business error.").build();
		}
		return apiData;
	}

	public ApiData<Map<String,Object>> inf_file_fileUpload(String params, MultipartFile file1) throws Exception{
		if (file1.isEmpty()) {
			return ApiData.<Map<String,Object>>builder().code("01").message("file not found.").build();
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
		body.set("cdId", "C001");
		body.set("cdNm", "test");
		
		body.add("file1", fileResource);
		
		final String urlPath = apiUrl + "/inf/file_fileUpload";
		RestCall restCall = RestCall.client(urlPath);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_MIXED);
		restCall.sethttpHeaders(headers);

		final ResultApiData<ApiData<Map<String,Object>>> resData = 
				restCall.post(body, new ParameterizedTypeReference<ApiData<Map<String,Object>>>(){});

		//API 호출중 에러가 발생 검사
		if (resData.hasError()){
			return ApiData.<Map<String,Object>>builder().code("02").message("api call error.").build();
		}
		//업무 응답코드가 정상 검사
		final ApiData<Map<String,Object>> apiData = resData.getBody();
		if(!"00".equals(apiData.getCode())){
			return ApiData.<Map<String,Object>>builder().code(apiData.getCode())
					.message(apiData.getMessage()).build();
		}
		
		return apiData;
	}

	public ApiData<Map<String,Object>> inf_file_fileDownload(
			HttpServletRequest request,HttpServletResponse response, Map<String,Object> params) throws Exception{

		final String urlPath = apiUrl + "/inf/file_fileDownload";

		final ResultApiData<ApiData<Map<String,Object>>> resData = RestCall.client(urlPath)
//				.setHeader(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//				.setHeader(HttpHeaders.ACCEPT,MediaType.toString(Collections.singletonList(MediaType.APPLICATION_JSON)))
				.post(params, new ParameterizedTypeReference<ApiData<Map<String,Object>>>(){});
			//API 호출중 에러가 발생 검사
			if (resData.hasError()){
				return resData.getBody();
			}
			//업무 응답코드가 정상 검사
			final ApiData<Map<String,Object>> apiData = resData.getBody();
			if(!"00".equals(apiData.getCode())){
				return resData.getBody();
			}
			
			Map<String, Object> map = apiData.getBody();
			String fileOrgNm = map.get("fileOrgNm")==null?"":map.get("fileOrgNm").toString();
			File file1 = new File(fileOrgNm);
			try {
				
				BufferedWriter bw = null;
				bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1),"ISO_8859_1"));
				bw.write(map.get("file").toString());
				bw.close();

				apiData.setBody(new HashMap<String,Object>());
				map.clear();
				
			}catch(Exception e) {
				System.out.println("error..."+e.toString());
			}
			
			int iRtn = FileModule.fileDownload(request, response, file1, fileOrgNm);
			if (iRtn != 1) {
				return ApiData.<Map<String,Object>>builder().code("03").message("api file download error.").build();
			}
			
			return ApiData.<Map<String,Object>>builder().code("00").build();
	}


	public List<Map<String,Object>> getDeptList(Map<String,Object> map) throws Exception {
		return sampleMapper.getDeptList(map);
	}
}
