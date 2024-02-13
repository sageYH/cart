package com.app.member.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.member.model.MemberExDto;
import com.base.DaoBaseMapper;

/**
* <pre>
* com.app.member.mapper
* MemberMapper.java
*
* </pre>
* @date : 
* @version : 
* @author : 
*/

@Repository("memberMapper")
@SuppressWarnings({ "unchecked", "deprecation" })
public class MemberMapper  extends DaoBaseMapper {
//	public class MemberMapper  extends DaoBaseMapper {

	public int getMemberListCount( MemberExDto memberExDto ) throws Exception {
		return (Integer)selectByPk("memberMapper.getMemberListCount", memberExDto);
	}
	
	public List<MemberExDto> getMemberList( MemberExDto memberExDto ) throws Exception {
		return (List<MemberExDto>)list("memberMapper.getMemberList", memberExDto);
	}
	
	public MemberExDto getMember( MemberExDto memberExDto ) throws Exception {
		return (MemberExDto) selectByPk("memberMapper.getMember", memberExDto);
	}

	public void insertMember( MemberExDto memberExDto ) throws Exception {
		insert("memberMapper.insertMember", memberExDto);
	}
	
	public void updateMember( MemberExDto memberExDto ) throws Exception {
		update("memberMapper.updateMember", memberExDto);
	}

	public void deleteMember( MemberExDto memberExDto ) throws Exception {
		delete("memberMapper.deleteMember", memberExDto);
	}

}
