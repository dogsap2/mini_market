package com.cafe24.dk4750.miniMarket.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.dk4750.miniMarket.mapper.CompanyCommentMapper;
import com.cafe24.dk4750.miniMarket.vo.CompanyComment;
import com.cafe24.dk4750.miniMarket.vo.CompanyCommentAndMember;

@Service
@Transactional
public class CompanyCommentService {
	@Autowired
	private CompanyCommentMapper companyCommentMapper;
	// 댓글 입력
	public int addCompanyComment(CompanyComment companyComment) {
		return companyCommentMapper.insertCompanyComment(companyComment);
	}
	// CompanyItem 댓글 리스트(페이징)
	public Map<String, Object> getCompayCommentList(int companyItemNo, int currentPage) {
		int rowPerPage = 5;
		int beginRow = (currentPage-1)*rowPerPage;
		Map<String, Object> map = new HashMap<>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		map.put("companyItemNo", companyItemNo);
		// lastPage
		int totalRow = companyCommentMapper.getTotalRow(companyItemNo);
		int lastPage = totalRow/rowPerPage;
		if(totalRow%rowPerPage != 0) {
			lastPage += 1;
		}
		// list와 lastPage를 Map에 담는다
		List<CompanyCommentAndMember> list = companyCommentMapper.selectCompanyCommentList(map);
		Map<String, Object> map2 = new HashMap<>();
		map2.put("list", list);
		map2.put("lastPage", lastPage);
		
		return map2;
	}
}
