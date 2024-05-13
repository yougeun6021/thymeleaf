package com.amudy.algorithm.dto;

import com.amudy.algorithm.domain.Submit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class SubmitDetailDto {
    private String code;
    private String method;
    private String etc;
    private Long problemId;

    public SubmitDetailDto(Submit submit){
        this.code = submit.getCode() == null ? "" : submit.getCode();
        this.method = submit.getMethod() == null ? "" : submit.getMethod();
        this.etc = submit.getEtc() == null ? "" : submit.getEtc();
        this.problemId = submit.getProblem() == null ? null : submit.getId();
    }
}
