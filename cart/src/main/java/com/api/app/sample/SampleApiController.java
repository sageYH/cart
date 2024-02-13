package com.api.app.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.api.base.ApiData;
import com.api.base.PageInfo;
import com.app.code.model.CodeExDto;
import com.app.code.service.CodeService;
import com.common.module.FileModule;
import com.common.utils.FileUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Controller
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SampleApiController {
	@Autowired
	private CodeService codeService;

	@Value("${file.path.rest}")
	private String preFixRestPath;

	@PostMapping("inf/code_getCodePageList")
	@ResponseBody
	public ApiData<List<CodeExDto>> code_getCodePageList(@RequestBody CodeExDto params) throws Exception{
		try {
			int totalCount = codeService.getCodeListCount(params);
			List<CodeExDto> list = codeService.getCodeList(params);

			PageInfo pageInfo = PageInfo.builder().
					pageIdx(params.getPageIdx()).pageRows(params.getPageRows()).totalCount(totalCount).build();
			return ApiData.<List<CodeExDto>>builder().body(list).code("00").pageInfo(pageInfo).build();
		}catch(Exception e) {
			return ApiData.<List<CodeExDto>>builder().code("01").message("Api inquiry Error.").build();
		}
	}

	@PostMapping(value="inf/file_fileUpload",consumes = { "multipart/mixed" })
	@ResponseBody
	public ApiData<Map<String,Object>> file_fileUpload(@Valid @RequestBody MultipartFile file1,
			@Valid @RequestParam Map<String, Object> fileMap,
			@Valid CodeExDto params ) throws Exception{
		try {
			String fileOrgNm = fileMap.get("fileOrgNm")==null?"":fileMap.get("fileOrgNm").toString();
			String filePath = fileMap.get("filePath")==null?"":fileMap.get("filePath").toString();

			filePath = preFixRestPath + filePath;

			if (file1 == null || file1.getSize() < 1) {
				return ApiData.<Map<String,Object>>builder().code("02").message("Not found file.").build();
			}

			//fileOrgNm,filePath
			if (fileOrgNm.equals("")) {
				fileOrgNm = file1.getOriginalFilename();
			}
			Map<String,Object> m1 = new HashMap<String,Object>();
			m1.put("fileOrgNm", fileOrgNm);
			m1.put("filePath", filePath);
			m1 = FileModule.fileUpload(file1, m1);

			return ApiData.<Map<String,Object>>builder().code("00").body(m1).build();
		}catch(Exception e) {
			return ApiData.<Map<String,Object>>builder().code("01").message("Api file upload Error.").build();
		}
	}

	@PostMapping(value="inf/file_fileDownload")
	@ResponseBody
	public ApiData<Map<String,Object>> file_fileDownload( @Valid @RequestBody Map<String, Object> fileMap ) throws Exception{
		try {
			String filePath = fileMap.get("filePath")==null?"":fileMap.get("filePath").toString();
			String fileId = fileMap.get("fileId")==null?"":fileMap.get("fileId").toString();
			filePath = preFixRestPath + filePath;

			fileMap.put("file", FileUtil.readBytesFile(filePath + fileId));
			
			return ApiData.<Map<String,Object>>builder().code("00").body(fileMap).build();
		}catch(Exception e) {
			return ApiData.<Map<String,Object>>builder().code("01").message("Api file download Error.").build();
		}
	}
}
