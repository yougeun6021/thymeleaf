package com.amudy.member.domain;

import com.amudy.baseEntity.BaseEntity;
import com.amudy.baseEntity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(unique = true)
    private String nickname;
    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    private Member(String email,String password,String nickname){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = MemberRole.ROLE_NORMAL;
    }

    public static Member createdNormalMember(String email,String password,String nickname){
        return new Member(email,password,nickname);
    }
}
