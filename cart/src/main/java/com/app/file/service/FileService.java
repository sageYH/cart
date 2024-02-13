package com.app.file.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.file.mapper.FileMapper;
import com.app.file.model.FileExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FileService {

	@Autowired
	private FileMapper fileMapper;

	public int getFileListCount(FileExDto fileExDto) throws Exception {
		return fileMapper.getFileListCount(fileExDto);
	}

	public List<FileExDto> getFileList(FileExDto fileExDto) throws Exception {
		return fileMapper.getFileList(fileExDto);
	}

	public FileExDto getFile(FileExDto fileExDto) throws Exception {
		return fileMapper.getFile(fileExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertFile(FileExDto fileExDto) throws Exception {
		fileMapper.insertFile(fileExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateFile(FileExDto fileExDto) throws Exception {
		fileMapper.updateFile(fileExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteFile(FileExDto fileExDto) throws Exception {
		fileMapper.deleteFile(fileExDto);
	}
}
