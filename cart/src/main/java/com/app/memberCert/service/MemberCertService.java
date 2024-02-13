package com.app.memberCert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.memberCert.mapper.MemberCertMapper;
import com.app.memberCert.model.MemberCertExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MemberCertService {

	@Autowired
	private MemberCertMapper memberCertMapper;

	public int getMemberCertListCount(MemberCertExDto memberCertExDto) throws Exception {
		return memberCertMapper.getMemberCertListCount(memberCertExDto);
	}

	public List<MemberCertExDto> getMemberCertList(MemberCertExDto memberCertExDto) throws Exception {
		return memberCertMapper.getMemberCertList(memberCertExDto);
	}

	public MemberCertExDto getMemberCert(MemberCertExDto memberCertExDto) throws Exception {
		return memberCertMapper.getMemberCert(memberCertExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertMemberCert(MemberCertExDto memberCertExDto) throws Exception {
		memberCertMapper.insertMemberCert(memberCertExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateMemberCert(MemberCertExDto memberCertExDto) throws Exception {
		memberCertMapper.updateMemberCert(memberCertExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteMemberCert(MemberCertExDto memberCertExDto) throws Exception {
		memberCertMapper.deleteMemberCert(memberCertExDto);
	}
}
