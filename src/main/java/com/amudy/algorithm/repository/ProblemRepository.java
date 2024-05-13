package com.amudy.algorithm.repository;


import com.amudy.algorithm.domain.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem,Long>,ProblemRepositoryCustom {
    @Modifying
    @Query("delete from Problem p where p.id in :idList")
    void deleteProblems(@Param("idList") List<Long> idList);
}
