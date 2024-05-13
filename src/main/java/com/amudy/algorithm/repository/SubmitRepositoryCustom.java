package com.amudy.algorithm.repository;

import com.amudy.algorithm.domain.Submit;
import com.amudy.algorithm.domain.SubmitSearchCond;
import com.amudy.algorithm.dto.SearchSubmitDto;

import java.util.List;

public interface SubmitRepositoryCustom {
    List<Submit> searchSubmit(SubmitSearchCond submitSearchCond);
}
