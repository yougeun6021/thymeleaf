package com.amudy.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    EXIST_GROUP_CODE(HttpStatus.BAD_REQUEST,"EXIST_GROUP_CODE"),
    NOT_FOUND_GROUP_CODE(HttpStatus.BAD_REQUEST,"NOT_FOUND_GROUP_CODE"),
    NOT_FOUND_PROBLEM(HttpStatus.BAD_REQUEST,"NOT_FOUND_PROBLEM"),
    NOT_FOUND_MEMBER(HttpStatus.BAD_REQUEST,"NOT_FOUND_MEMBER"),
    NOT_FOUND_SUBMIT(HttpStatus.BAD_REQUEST,"NOT_FOUND_SUBMIT");

    private final HttpStatus status;
    private final String message;

}