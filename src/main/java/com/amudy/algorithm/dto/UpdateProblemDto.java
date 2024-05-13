package com.amudy.algorithm.dto;

import com.amudy.algorithm.domain.Level;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UpdateProblemDto {
    private Long number;
    private String name;
    private Level level;
    private LocalDate date;
    private String link;

    public void setLevel(String level) {
        if (ObjectUtils.isEmpty(level)) {
            this.level = null;
        } else {
            this.level = Level.valueOf(level);
        }
    }
}
