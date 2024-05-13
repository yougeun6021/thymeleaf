package com.amudy.algorithm.repository;

import com.amudy.algorithm.domain.Problem;
import com.amudy.algorithm.domain.ProblemSearchCond;
import com.amudy.algorithm.domain.SubmitSearchCond;
import com.amudy.global.dto.SelectBoxDto;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import static com.amudy.algorithm.domain.QProblem.problem;
import static java.time.temporal.ChronoField.DAY_OF_WEEK;

@RequiredArgsConstructor
public class ProblemRepositoryImpl implements ProblemRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    @Override
    public List<SelectBoxDto> getSelectBoxProblem(SubmitSearchCond SubmitSearchCond) {
        return queryFactory
                .select(Projections.fields(SelectBoxDto.class,
                        problem.id.as("value"),
                        problem.name.as("text")))
                .from(problem)
                .where(problem.date.isNotNull())
                .where(problem.name.isNotNull().and(problem.name.ne("")))
                .where(betweenDate(SubmitSearchCond))
                .fetch();
    }

    @Override
    public List<Problem> searchProblem(ProblemSearchCond problemSearchcond) {
        return queryFactory
                .select(problem)
                .from(problem)
                .where(problem.date.isNull().or(betweenDate(problemSearchcond)))
                .fetch();
    }
    
    private BooleanExpression betweenDate(ProblemSearchCond problemSearchcond){
        String week = problemSearchcond.getWeak();
        int number = problemSearchcond.getNumber();
        return getBetweenDate(week, number);
    }
    private BooleanExpression betweenDate(SubmitSearchCond SubmitSearchCond){
        String week = SubmitSearchCond.getWeak();
        int number = SubmitSearchCond.getNumber();
        return getBetweenDate(week, number);
    }

    private static BooleanExpression getBetweenDate(String week, int number) {
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
                return problem.date.isNotNull();
        }
        return problem.date.between(monday, sunday);
    }


}
