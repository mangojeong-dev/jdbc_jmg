package org.edu.member.vo;

import lombok.*;
import org.edu.member.common.JDBCUtill;

// VO(Value Object)
// 값 자체를 표현하고 의미를 갖는 객체

@AllArgsConstructor@NoArgsConstructor@Getter@Setter@ToString
public class Member {
    private int memberNo;
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberRole;
    private char deletedYn;
    private int dept_no;
    private String dept_name;

}
