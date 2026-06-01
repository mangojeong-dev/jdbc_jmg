package org.scoula.jdbc_ex;

import org.junit.jupiter.api.*;
import org.scoula.jdbc_ex.common.JdbcUtill;

import java.sql.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CrudTest {
    Connection conn = JdbcUtill.getConnection();

    @AfterAll
    static void tearDown() {
        JdbcUtill.close();
    }

    @Test
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

}