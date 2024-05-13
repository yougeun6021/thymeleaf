package com.amudy.algorithm.domain;

import com.amudy.algorithm.dto.UpdateProblemDto;
import com.amudy.global.baseEntity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
public class Problem extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROBLEM_ID")
    private Long id;

    @Column(name = "PROBLEM_NO")
    private Long number;

    @Column(name = "PROBLEM_NM")
    private String name;

    @Enumerated(EnumType.STRING)
    private Level level;

    private String link;

    @Column(name = "PROBLEM_DT")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public void update(UpdateProblemDto updateProblemDto) {
        this.number = updateProblemDto.getNumber();
        this.name = updateProblemDto.getName();
        this.level = updateProblemDto.getLevel();
        this.link = updateProblemDto.getLink();
        this.date = updateProblemDto.getDate();
    }

}
