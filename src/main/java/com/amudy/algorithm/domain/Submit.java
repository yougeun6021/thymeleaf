package com.amudy.algorithm.domain;


import com.amudy.algorithm.dto.UpdateSubmitDetailDto;
import com.amudy.global.baseEntity.BaseTimeEntity;
import com.amudy.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Submit extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUBMIT_ID")
    private Long id;

    @JoinColumn(name = "MEMBER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "PROBLEM_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Problem problem;

    @Column(columnDefinition = "TEXT")
    private String code;

    @Column(columnDefinition = "TEXT")
    private String method;

    @Column(columnDefinition = "TEXT")
    private String etc;

    private Submit(Member member){
        this.member = member;
    }

    public static Submit from(Member member){
        return new Submit(member);
    }

    public void updateProblem(Problem problem){
        this.problem = problem;
    }
    public void updateDetail(UpdateSubmitDetailDto updateSubmitDetailDto){
        this.code = updateSubmitDetailDto.getCode();
        this.method = updateSubmitDetailDto.getMethod();
        this.etc = updateSubmitDetailDto.getEtc();
    }


}
