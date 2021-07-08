package BrickWorld.lego.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;                    //회원번호

    @NotEmpty
    private String name;                //회원명

    @NotEmpty
    private String email;               //회원이메일

    @NotEmpty
    private String password;            //회원패스워드

    private int role;                   //회원권한 0(관리자)/1(일반)/2(VIP)

    private LocalDateTime joinDate;     //가입날짜

    @Enumerated(EnumType.STRING)
    private Grade grade;                //회원등급 COMMON,VIP

    @Embedded
    private Address address;            //회원주소

    //생성 메서드
    public Member(String name, String email, String password,Address address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.grade = Grade.COMMON;
        this.address = address;
        this.role = 1;
        this.joinDate = LocalDateTime.now();
    }


}
