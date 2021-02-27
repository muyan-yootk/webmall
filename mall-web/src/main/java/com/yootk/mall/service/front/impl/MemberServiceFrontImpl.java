package com.yootk.mall.service.front.impl;

import com.yootk.common.mvc.annotation.Aspect;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Service;
import com.yootk.common.service.abs.AbstractService;
import com.yootk.mall.dao.IMemberDAO;
import com.yootk.mall.service.front.IMemberServiceFront;
import com.yootk.mall.vo.Member;

@Service
@Aspect
public class MemberServiceFrontImpl extends AbstractService implements IMemberServiceFront {
	@Autowired
	private IMemberDAO memberDAO ;
	@Override
	public Member get(String mid) throws Exception {
		return this.memberDAO.findById(mid);
	}
	@Override
	public boolean login(Member vo) throws Exception {
		Member member = this.memberDAO.findById(vo.getMid()) ;	// 根据mid获取Member信息
		if (member != null) {
			return member.getPassword().equals(vo.getPassword()) ;
		}
		return false;
	}


}
