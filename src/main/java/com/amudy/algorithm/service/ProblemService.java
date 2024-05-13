package com.amudy.algorithm.service;

import com.amudy.algorithm.domain.Problem;
import com.amudy.algorithm.domain.ProblemSearchCond;
import com.amudy.algorithm.domain.SubmitSearchCond;
import com.amudy.algorithm.dto.SearchProblemDto;
import com.amudy.algorithm.dto.UpdateProblemDto;
import com.amudy.algorithm.repository.ProblemRepository;
import com.amudy.algorithm.repository.SubmitRepository;
import com.amudy.global.dto.SelectBoxDto;
import com.amudy.global.exception.ApiCustomException;
import com.amudy.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProblemService {

    private final ProblemRepository problemRepository;
    private final SubmitRepository submitRepository;
    public Long saveProblem() {
        Problem problem = new Problem();
        problemRepository.save(problem);
        return problem.getId();
    }

    @Transactional(readOnly = true)
    public List<SearchProblemDto> searchProblem(ProblemSearchCond problemSearchcond) {
        List<Problem> result = problemRepository.searchProblem(problemSearchcond);
        return result.stream().map(SearchProblemDto::new).collect(Collectors.toList());
    }

    public Long updateProblem(Long id, UpdateProblemDto updateProblemDto) {
        Problem findProblem = problemRepository.findById(id)
                .orElseThrow(()-> new ApiCustomException(ErrorCode.NOT_FOUND_PROBLEM));
        findProblem.update(updateProblemDto);

        return findProblem.getId();
    }

    public void deleteProblem(List<Long> idList) {
        submitRepository.updateProblemNull(idList);
        problemRepository.deleteProblems(idList);
    }

    @Transactional(readOnly = true)
    public List<SelectBoxDto> selectBoxProblems(SubmitSearchCond SubmitSearchCond) {
        return problemRepository.getSelectBoxProblem(SubmitSearchCond);
    }
}
