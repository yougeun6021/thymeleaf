package com.amudy.algorithm.repository;

import com.amudy.algorithm.domain.Submit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubmitRepository extends JpaRepository<Submit,Long>,SubmitRepositoryCustom {
    @Modifying
    @Query("delete from Submit s where s.id in :idList")
    void deleteSubmits(@Param("idList") List<Long> idList);

    @Modifying
    @Query("update Submit s set s.problem = null where s.problem.id in :problemIdList")
    void updateProblemNull(@Param("problemIdList") List<Long> idList);

    @Query("select s from Submit s left join fetch s.problem p where s.id = :submitId ")
    Optional<Submit> findByIdFetchProblem(@Param("submitId") Long id);
}
