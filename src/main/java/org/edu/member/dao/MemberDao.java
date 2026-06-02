package org.edu.member.dao;

import org.edu.member.vo.Member;

import java.sql.ResultSet;
import java.sql.SQLException;

// DAO(Data Access Object)
// DB와 연결되어 SQL을 수행하고 결과를 반환받는 역할
public interface MemberDao {

    int create(Member member) throws SQLException;
    int update(Member member) throws SQLException;
}
