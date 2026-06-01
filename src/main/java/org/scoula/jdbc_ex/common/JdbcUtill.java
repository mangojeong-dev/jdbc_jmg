package org.scoula.jdbc_ex.common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtill {
    // TDD로 먼저 테스트
    // -> 추후 정확한 코드로 구현
    static Connection conn = null;
    static {

        Properties properties = new Properties();
        try {
            properties.load(JdbcUtill.class.getResourceAsStream("/application.proterties"));
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String id = properties.getProperty("id");
            String password = properties.getProperty("password");
            Class.forName(driver);
            conn = DriverManager.getConnection(url, id, password);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            System.out.println("예외 처리");
        }

    }

    // method를 new로 객체 생성하지 않고
    // 바로 호출해서 사용하려면
    // method return 앞에 static
    // 아무 때나 호출해서 사용 가능
    public static Connection getConnection() {
        return conn;
    }
    public static void close() {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            conn = null;
        }
    }
}
