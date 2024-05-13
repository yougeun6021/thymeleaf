package com.amudy.algorithm.service;

import com.amudy.algorithm.domain.Problem;
import com.amudy.algorithm.domain.Submit;
import com.amudy.algorithm.domain.SubmitSearchCond;
import com.amudy.algorithm.dto.SearchSubmitDto;
import com.amudy.algorithm.dto.SubmitDetailDto;
import com.amudy.algorithm.dto.UpdateSubmitDetailDto;
import com.amudy.algorithm.dto.UpdateSubmitProblemDto;
import com.amudy.algorithm.repository.ProblemRepository;
import com.amudy.algorithm.repository.SubmitRepository;
import com.amudy.global.exception.ApiCustomException;
import com.amudy.global.exception.ErrorCode;
import com.amudy.member.domain.Member;
import com.amudy.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SubmitService {
    private final MemberRepository memberRepository;
    private final ProblemRepository problemRepository;
    private final SubmitRepository submitRepository;

    public Long saveSubmit(String email) {
        Member findMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.NOT_FOUND_MEMBER.getMessage()));
        Submit submit = Submit.from(findMember);
        submitRepository.save(submit);

        return submit.getId();
    }

    @Transactional(readOnly = true)
    public List<SearchSubmitDto> searchSubmit(SubmitSearchCond submitSearchCond){
        List<Submit> result =  submitRepository.searchSubmit(submitSearchCond);
        return result.stream().map(SearchSubmitDto::new).collect(Collectors.toList());
    }

    public Long updateSubmitDetail(Long submitId, UpdateSubmitDetailDto updateSubmitDetailDto){
        Submit submit = submitRepository.findById(submitId)
                .orElseThrow(() -> new ApiCustomException(ErrorCode.NOT_FOUND_SUBMIT));

        submit.updateDetail(updateSubmitDetailDto);

        return submit.getId();
    }

    public Long updateSubmitProblem(Long submitId, UpdateSubmitProblemDto updateSubmitProblemDto){
        Submit submit = submitRepository.findById(submitId)
                .orElseThrow(() -> new ApiCustomException(ErrorCode.NOT_FOUND_SUBMIT));

        Long problemId = updateSubmitProblemDto.getProblemId();
        Problem problem = null;
        if(problemId !=null){
            problem = problemRepository.findById(problemId)
                    .orElseThrow(() -> new ApiCustomException(ErrorCode.NOT_FOUND_PROBLEM));
        }

        submit.updateProblem(problem);

        return submit.getId();
    }

    public void deleteSubmit(List<Long> idList) {
        submitRepository.deleteSubmits(idList);
    }

    public SubmitDetailDto submitDetail(Long id) {
        Submit submit = submitRepository.findByIdFetchProblem(id)
                .orElseThrow(() -> new ApiCustomException(ErrorCode.NOT_FOUND_SUBMIT));

        return new SubmitDetailDto(submit);
    }
}
