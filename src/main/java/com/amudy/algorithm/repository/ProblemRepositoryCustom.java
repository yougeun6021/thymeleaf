package com.amudy.algorithm.repository;

import com.amudy.algorithm.domain.Problem;
import com.amudy.algorithm.domain.ProblemSearchCond;
import com.amudy.algorithm.domain.SubmitSearchCond;
import com.amudy.global.dto.SelectBoxDto;

import java.util.List;

public interface ProblemRepositoryCustom {
    List<SelectBoxDto> getSelectBoxProblem(SubmitSearchCond SubmitSearchCond);
    List<Problem> searchProblem(ProblemSearchCond problemSearchcond);
}
