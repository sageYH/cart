package com.app.file.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.file.model.FileExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.file.mapper
* FileMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("fileMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class FileMapper  extends DaoBaseMapper {
//	public class FileMapper  extends DaoBaseMapper {

	public int getFileListCount( FileExDto fileExDto ) throws Exception {
		return (Integer)selectByPk("fileMapper.getFileListCount", fileExDto);
	}
	
	public List<FileExDto> getFileList( FileExDto fileExDto ) throws Exception {
		return (List<FileExDto>)list("fileMapper.getFileList", fileExDto);
	}
	
	public FileExDto getFile( FileExDto fileExDto ) throws Exception {
		return (FileExDto) selectByPk("fileMapper.getFile", fileExDto);
	}

	public void insertFile( FileExDto fileExDto ) throws Exception {
		insert("fileMapper.insertFile", fileExDto);
	}
	
	public void updateFile( FileExDto fileExDto ) throws Exception {
		update("fileMapper.updateFile", fileExDto);
	}

	public void deleteFile( FileExDto fileExDto ) throws Exception {
		delete("fileMapper.deleteFile", fileExDto);
	}

}
