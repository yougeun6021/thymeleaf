package com.amudy.algorithm.dto;

import com.amudy.algorithm.domain.Level;
import com.amudy.algorithm.domain.Problem;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class SearchProblemDto {
    private Long id;
    private String name;
    private LocalDate date;
    private Long number;
    private String link;
    private Level level;

    public SearchProblemDto(Problem problem){
        this.id = problem.getId();
        this.name = problem.getName();
        this.date = problem.getDate();
        this.number = problem.getNumber();
        this.link = problem.getLink();
        this.level = problem.getLevel();
    }


}
