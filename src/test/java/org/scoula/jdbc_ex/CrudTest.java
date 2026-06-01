package org.scoula.jdbc_ex;

import org.junit.jupiter.api.*;
import org.scoula.jdbc_ex.common.JdbcUtill;

import java.sql.*;

// Order 어노테이션을 보고 테스트 순서를 결정하라는 지시용 어노테이션
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CrudTest {
    Connection conn = JdbcUtill.getConnection();


    // @AfterAll : 모든 테스트가 끝난 후에 1번 실행
    @AfterAll
    static void tearDown() {
        JdbcUtill.close();
    }

    @Test
    // @Order(i) : 테스트를 i번째로 실행하라는 어노테이션
    @Order(1)
    @DisplayName("회원가입 테스트 진행")
    public void insertUser() throws SQLException {
        String sql = "insert into users(id, password, name, role) values(?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "scoula2");
            pstmt.setString(2, "1234");
            pstmt.setString(3, "win");
            pstmt.setString(4, "admin");

            int count = pstmt.executeUpdate();
            // count : 실행된 sql문 row 수
            System.out.println("실행된 sql문 row 수 " +count);
            Assertions.assertEquals(1,count);
        }
    }

    // JDBC 흐름
    // JDBC : java와 db를 연결할 수 있게 해주는 java api


    // connection -> statement -> resultset
    // connection : java와 외부의 db를 연결해주는 통로
    // statement : sql문을 db로 보내는 객체
    // resultset : select 결과 저장 객체

    @Test
    @Order(2)
    @DisplayName("유저 데이터 출력")
    public void selectUser() throws SQLException {
        String sql = "SELECT * FROM users";

        try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
             ) {
            System.out.println("유저 이름 전체 출력");
            while (rs.next()) {
                System.out.println(rs.getString("NAME"));
            }
        }
    }

    @Test
    @Order(3)
    @DisplayName("유저 id 기준 데이터 출력")
    public void selectUserById() throws SQLException {
        String userid = "scoula";
        String sql = "SELECT * FROM users WHERE ID = ?";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) {

            psmt.setString(1,userid);
            try (ResultSet rs = psmt.executeQuery()) {
                if(rs.next()) {
                    System.out.println(rs.getString("ID"));
                }
                else {
                    throw new SQLException("scloula not found");
                }
            }

        }
    }

    @Test
    @DisplayName("특정 user 수정한다.")
    @Order(4)
    public void updateUser() throws SQLException {
        String userid = "scoula2";
        String sql = "UPDATE users SET name = ? WHERE id = ?";

        try (PreparedStatement psmt = conn.prepareStatement(sql)) {
            psmt.setString(1,"Jason");
            psmt.setString(2,userid);

        }
    }
}