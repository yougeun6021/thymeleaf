package com.amudy.algorithm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubmitSearchCond {
    private Long memberId;
    private String weak;
    private int number;
}
