package com.amudy.algorithm.dto;

import com.amudy.algorithm.domain.Problem;
import com.amudy.algorithm.domain.Submit;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class SearchSubmitDto {
    private Long submitId;
    private Long problemId;
    private LocalDate problemDate;
    private String nickname;

    public SearchSubmitDto(Submit submit){
        this.submitId = submit.getId();
        this.nickname = submit.getMember().getNickname();

        Problem findProblem = submit.getProblem();
        if(findProblem != null){
            this.problemId = findProblem.getId();
            this.problemDate = findProblem.getDate();
        }
    }

}
