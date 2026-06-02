package org.edu.member.run;

import org.edu.member.common.JDBCUtill;
import org.edu.member.service.MemberService;

import java.sql.Connection;
import java.sql.Statement;

public class MemberRun {
    public static void main(String[] args) {
        // connection 생성 확인
        //System.out.println(JDBCUtill.getConnection());
        MemberService memberService = new MemberService();
        memberService.displayMenu();
    }
}
