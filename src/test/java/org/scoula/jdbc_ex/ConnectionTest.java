package org.scoula.jdbc_ex;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.scoula.jdbc_ex.common.JdbcUtill;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionTest {

    @Test
    @DisplayName("jdbc_ex 데이터베이스에 접속한다.")
    public void testConnection() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        // db 연결
        // db 연결 시 필요한 데이터 3가지 : url(ip + port + db명), username, pw
        String url = "jdbc:mysql://127.0.0.1:3306/jdbc_ex";
        String id = "scoula";
        String password = "1234";
        Connection conn = DriverManager.getConnection(url, id, password);
        System.out.println("DB 연결  성공 >> " + conn);
        // 자원 해재 - 해재하지 않으면 ram에 용량 쌓임
        conn.close();
    }

    @Test
    public void testConnection2() throws SQLException {
        try(Connection conn = JdbcUtill.getConnection()){
            System.out.println("DB 연결 성공");
        }
    }
}