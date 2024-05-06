package com.amudy.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    EXIST_GROUP_CODE(HttpStatus.BAD_REQUEST,"EXIST_GROUP_CODE"),
    NOT_FOUND_GROUP_CODE(HttpStatus.BAD_REQUEST,"NOT_FOUND_GROUP_CODE");

    private final HttpStatus status;
    private final String message;

}