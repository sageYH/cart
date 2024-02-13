package com.app.memberCert.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.memberCert.model.MemberCertExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.memberCert.mapper
* MemberCertMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("memberCertMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class MemberCertMapper  extends DaoBaseMapper {
//	public class MemberCertMapper  extends DaoBaseMapper {

	public int getMemberCertListCount( MemberCertExDto memberCertExDto ) throws Exception {
		return (Integer)selectByPk("memberCertMapper.getMemberCertListCount", memberCertExDto);
	}
	
	public List<MemberCertExDto> getMemberCertList( MemberCertExDto memberCertExDto ) throws Exception {
		return (List<MemberCertExDto>)list("memberCertMapper.getMemberCertList", memberCertExDto);
	}
	
	public MemberCertExDto getMemberCert( MemberCertExDto memberCertExDto ) throws Exception {
		return (MemberCertExDto) selectByPk("memberCertMapper.getMemberCert", memberCertExDto);
	}

	public void insertMemberCert( MemberCertExDto memberCertExDto ) throws Exception {
		insert("memberCertMapper.insertMemberCert", memberCertExDto);
	}
	
	public void updateMemberCert( MemberCertExDto memberCertExDto ) throws Exception {
		update("memberCertMapper.updateMemberCert", memberCertExDto);
	}

	public void deleteMemberCert( MemberCertExDto memberCertExDto ) throws Exception {
		delete("memberCertMapper.deleteMemberCert", memberCertExDto);
	}

}
