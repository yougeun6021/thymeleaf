package com.amudy.algorithm.repository;

import com.amudy.algorithm.domain.Submit;
import com.amudy.algorithm.domain.SubmitSearchCond;
import com.amudy.algorithm.dto.SearchSubmitDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import static com.amudy.algorithm.domain.QProblem.problem;
import static com.amudy.algorithm.domain.QSubmit.submit;
import static com.amudy.member.domain.QMember.member;

@RequiredArgsConstructor
public class SubmitRepositoryImpl implements SubmitRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    @Override
    public List<Submit> searchSubmit(SubmitSearchCond submitSearchCond) {
        return queryFactory
                .select(submit)
                .from(submit)
                .join(submit.member,member)
                .fetchJoin()
                .leftJoin(submit.problem,problem)
                .fetchJoin()
                .where(problem.isNull().or(betweenDate(submitSearchCond)),equalMemberId(submitSearchCond))
                .fetch();

    }
    private BooleanExpression betweenDate(SubmitSearchCond SubmitSearchCond){
        String week = SubmitSearchCond.getWeak();
        int number = SubmitSearchCond.getNumber();
        LocalDate today = LocalDate.now();
        LocalDate monday;
        LocalDate sunday;
        switch (week){
            case "b":
                monday = today.minusWeeks(number).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                sunday = today.minusWeeks(1).with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                break;
            case "t":
                monday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                sunday = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                break;
            case "a":
                monday = today.plusWeeks(1).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                sunday = today.plusWeeks(number).with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                break;
            default:
                return submit.problem.date.isNotNull();
        }
        return submit.problem.date.between(monday,sunday);
    }
    private BooleanExpression equalMemberId(SubmitSearchCond submitSearchCond){
        Long memberId = submitSearchCond.getMemberId();
        return memberId == null ? null : submit.member.id.eq(memberId);
    }
}
