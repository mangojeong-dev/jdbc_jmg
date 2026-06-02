package org.edu.member.dao;

import org.edu.member.common.JDBCUtill;
import org.edu.member.vo.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDaoImpl implements MemberDao{

    // JDBCUtil을 통해 Connection 객체 가져오기
    private Connection conn = JDBCUtill.getConnection();


    // 회원 등록
    @Override
    public int create(Member member) throws SQLException {
        /*
        Statement를 사용하는 경우 sql문
        String sql = "INSERT INTO members VALUES (DEFAULT, "
                + member.getMemberId() + ", "
                + member.getMemberPw() +", "
                + member.getMemberName()+ ", "
                + member.getMemberRole()+", 'N');";
         */


        // PreparedStatement
        // -> Statement의 자식
        // 더 향상된 기능 제공
        // ?(위치 홀더)를 이용해 SQL에 작성되는 리터럴을 동적으로 제어
        // 오타 위험 감소, 가독성 증가
        // mysql에선 세미콜론 안 쓰는게 관례

        String sql = "INSERT INTO members VALUES (DEFAULT, ?, ?, ?, ?,'N')";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, member.getMemberId());
            pstmt.setString(2, member.getMemberPw());
            pstmt.setString(3, member.getMemberName());
            pstmt.setString(4, member.getMemberRole());

            // SELECT : executeQuery() -> Resultset 반환
            // DML : executeUpdate() 해주자 -> 성공한 행의 개수(int) 반환
            int result = pstmt.executeUpdate();

            // 삽입한 후에 커밋까지
            if(result > 0) conn.commit();
            return result;  // 성공한 행의 개수 반환까지

        }
    }

    @Override
    public int update(Member member) throws SQLException {
        String sql = "UPDATE members SET name = ?, role = ? WHERE no = ?";

        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setString(1,member.getMemberName());
        psmt.setString(2,member.getMemberRole());
        psmt.setInt(3,member.getMemberNo());

        int result = psmt.executeUpdate();

        // 삽입한 후에 커밋까지
        if(result > 0) conn.commit();
        return result;  // 성공한 행의 개수 반환까지
    }
}
