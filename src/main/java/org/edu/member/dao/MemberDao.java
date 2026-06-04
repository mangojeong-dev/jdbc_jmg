package org.edu.member.dao;

import org.edu.member.vo.Member;

import java.sql.SQLException;
import java.util.List;

// DAO(Data Access Object)
// DB와 연결되어 SQL을 수행하고 결과를 반환받는 역할
public interface MemberDao {

    int create(Member member) throws SQLException;
    List<Member> getList() throws SQLException;
    Member get(int memberNo) throws SQLException;
    int update(Member member) throws SQLException;
    int delete(int memberNo) throws SQLException;
}
