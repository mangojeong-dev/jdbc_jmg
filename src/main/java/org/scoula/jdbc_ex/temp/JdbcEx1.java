package org.scoula.jdbc_ex.temp;

import java.sql.*;

public class JdbcEx1 {
    public static void main(String[] args) {
        // JDBC 흐름
        // JDBC : java와 db를 연결할 수 있게 해주는 java api
        // connection -> statement -> resultset
        // connection : java와 db를 연결해주는 통로
        // statement : sql문을 db로 보내는 객체
        // resultset : select 결과 저장 객체

        // [1단계] : JDBC 객체 참조 변수 선언
        Connection conn = null;
        // DB 연결 정보를 담은 객체
        // -> JAVA와 DB 사이를 연결해주는 통로

        // [2단게] : Connection 객체를 통해 JAVA에서 작성된 SQL 문을
        // DB로 전달해 수행한 후에 JAVA로 돌아오는 역할의 객체
        Statement stmt = null;

        // [3단계] : select 결과 저장 객체
        // SELECT 질의 성공 시 반환되는
        // 결과 행의 집합(Result Set)을 나타내는 객체
        ResultSet rs = null;

        try {
            // 참조변수에 알맞은 객체 대입하기

            // 1. DB 연결에 필요한 MYSQL JDBC DRIVER 메모리 로드하기
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 연결 정보를 담은 Connection 정보 생성
            // DriverManager : JDBC driver를 통해 Connection 객체를 만든다
            String url = "jdbc:mysql://localhost:3306/jdbc_ex";
            String id = "scoula";
            String password = "1234";

            conn = DriverManager.getConnection(url, id, password);
            // System.out.println("연결 확인 : " + conn);

            // 3. Statement 객체에 적재할 sql문 작성
            String sql = "SELECT * FROM users";

            // 4. Statement 객체 생성
            stmt = conn.createStatement();

            // 5. SQL문을 Statement에 넣고 DB로 전달해
            // 수행한 후 결과를 반환 받아와서
            // select는 resultset에, DML은 int로 반환
            rs = stmt.executeQuery(sql);


            while (rs.next()) {
                // rs.next() : 참조하고 있는 Resultset 객체의
                // 첫 번째 컬럼부터 순서대로 한 행씩 이동하며
                // 다음 행이 있으면 true 반환
                // System.out.println(rs.getString("ID"));
                System.out.println(rs.getString("NAME"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // 사용한 JDBC 객체 자원 반환
            // -> 자원 반환 순서는 객체 생성 순서의 역순

            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
