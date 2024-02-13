package com.app.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.member.mapper.MemberMapper;
import com.app.member.model.MemberExDto;

@Service
@Lazy
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;

	public int getMemberListCount(MemberExDto memberExDto) throws Exception {
		return memberMapper.getMemberListCount(memberExDto);
	}

	public List<MemberExDto> getMemberList(MemberExDto memberExDto) throws Exception {
		return memberMapper.getMemberList(memberExDto);
	}

	public MemberExDto getMember(MemberExDto memberExDto) throws Exception {
		return memberMapper.getMember(memberExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void insertMember(MemberExDto memberExDto) throws Exception {
		memberMapper.insertMember(memberExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void updateMember(MemberExDto memberExDto) throws Exception {
		memberMapper.updateMember(memberExDto);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public void deleteMember(MemberExDto memberExDto) throws Exception {
		memberMapper.deleteMember(memberExDto);
	}
}
