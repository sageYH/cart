package com.app.seq.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.seq.model.SeqExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.seq.mapper
* SeqMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("seqMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class SeqMapper  extends DaoBaseMapper {
//	public class SeqMapper  extends DaoBaseMapper {

	public int getSeqListCount( SeqExDto seqExDto ) throws Exception {
		return (Integer)selectByPk("seqMapper.getSeqListCount", seqExDto);
	}
	
	public List<SeqExDto> getSeqList( SeqExDto seqExDto ) throws Exception {
		return (List<SeqExDto>)list("seqMapper.getSeqList", seqExDto);
	}
	
	public SeqExDto getSeq( SeqExDto seqExDto ) throws Exception {
		return (SeqExDto) selectByPk("seqMapper.getSeq", seqExDto);
	}

	public void insertSeq( SeqExDto seqExDto ) throws Exception {
		insert("seqMapper.insertSeq", seqExDto);
	}
	
	public void updateSeq( SeqExDto seqExDto ) throws Exception {
		update("seqMapper.updateSeq", seqExDto);
	}

	public void deleteSeq( SeqExDto seqExDto ) throws Exception {
		delete("seqMapper.deleteSeq", seqExDto);
	}

}
