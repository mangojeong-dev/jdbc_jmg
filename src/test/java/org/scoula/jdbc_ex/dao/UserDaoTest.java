package org.scoula.jdbc_ex.dao;

import org.junit.jupiter.api.*;
import org.scoula.jdbc_ex.common.JdbcUtill;
import org.scoula.jdbc_ex.domain.UserVO;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDaoTest {
    UserDao dao = new UserDaoImpl();

    @AfterAll
    static void tearDown() {
        JdbcUtill.close();
    }

    @Test
    @Order(1)
    @DisplayName("user 등록")
    void create() throws SQLException {
        UserVO user = new UserVO("mg_j","1234","jmg","admin");
        int cnt = dao.create(user);
        Assertions.assertEquals(1,cnt);
    }

    @Test
    @Order(2)
    @DisplayName("UserDao User 목록을 추출")
    void getList() throws SQLException {
        List<UserVO> list = dao.getList();
        for (UserVO vo : list) System.out.println("user 목록 추출 >> "+ vo);
    }

    @Test
    void get() throws SQLException {
    }

    @Test
    void update() throws SQLException {
    }

    @Test
    void delete() throws SQLException {
    }
}