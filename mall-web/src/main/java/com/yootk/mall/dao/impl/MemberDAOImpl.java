package com.yootk.mall.dao.impl;

import com.yootk.common.dao.abs.AbstractDAO;
import com.yootk.common.mvc.annotation.Repository;
import com.yootk.mall.dao.IMemberDAO;
import com.yootk.mall.vo.Goods;
import com.yootk.mall.vo.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Repository
public class MemberDAOImpl extends AbstractDAO implements IMemberDAO {

	@Override
	public boolean doCreate(Member vo) throws SQLException {
		String sql = "INSERT INTO member(mid, name, password, level) VALUES (?, ?, ?, ?)";
		super.pstmt = super.connection.prepareStatement(sql);
		super.pstmt.setString(1, vo.getMid());
		super.pstmt.setString(2, vo.getName());
		super.pstmt.setString(3, vo.getPassword());
		super.pstmt.setInt(4, vo.getLevel());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doEdit(Member vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<String> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Member findById(String id) throws SQLException {
		String sql = "SELECT mid,name,password,level FROM member WHERE mid=?" ;
		super.pstmt = super.connection.prepareStatement(sql) ;
		super.pstmt.setString(1, id);
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			Member vo = new Member() ;
			vo.setMid(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setPassword(rs.getString(3));
			vo.setLevel(rs.getInt(4));
			return vo ;
		}
		return null;
	}

	@Override
	public List<Member> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> findSplit(Integer currentPage, Integer lineSize) throws SQLException {
		List<Member> all = new ArrayList<Member>();
		String sql = "SELECT mid,name,level FROM member LIMIT " + (currentPage - 1) * lineSize + "," + lineSize;
		super.pstmt = super.connection.prepareStatement(sql);
		return super.handleResultToList(super.pstmt.executeQuery(), Member.class);
	}

	@Override
	public List<Member> findSplit(Integer currentPage, Integer lineSize,String column, String keyWord) throws SQLException {
		List<Goods> all = new ArrayList<Goods>();
		String sql = "SELECT mid,name,level FROM member WHERE " + column + " LIKE ? LIMIT " + (currentPage - 1) * lineSize + "," + lineSize;
		super.pstmt = super.connection.prepareStatement(sql);
		super.pstmt.setString(1, "%" + keyWord + "%");
		return super.handleResultToList(super.pstmt.executeQuery(),Member.class);
	}

	@Override
	public Long getAllCount() throws SQLException {
		return super.countHandle("member");
	}

	@Override
	public Long getAllCount(String column, String keyWord) throws SQLException {
		return super.countHandle("member",column,keyWord) ;
	}

}
