package com.amudy.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MvcCustomException extends RuntimeException {
    private final ErrorCode errorCode;
}
