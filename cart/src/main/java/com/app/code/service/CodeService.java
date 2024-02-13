package com.app.code.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.code.mapper.CodeMapper;
import com.app.code.model.CodeExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CodeService {

	@Autowired
	private CodeMapper codeMapper;

	public int getCodeListCount(CodeExDto codeExDto) throws Exception {
		return codeMapper.getCodeListCount(codeExDto);
	}

	public List<CodeExDto> getCodeList(CodeExDto codeExDto) throws Exception {
		return codeMapper.getCodeList(codeExDto);
	}

	public CodeExDto getCode(CodeExDto codeExDto) throws Exception {
		return codeMapper.getCode(codeExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertCode(CodeExDto codeExDto) throws Exception {
		codeMapper.insertCode(codeExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateCode(CodeExDto codeExDto) throws Exception {
		codeMapper.updateCode(codeExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteCode(CodeExDto codeExDto) throws Exception {
		codeMapper.deleteCode(codeExDto);
	}
}
