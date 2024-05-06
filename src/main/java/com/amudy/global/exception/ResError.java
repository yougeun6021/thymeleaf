package com.amudy.global.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResError {
    private String errorName;

    private String message;

    private ResError(String errorName,String message){
        this.errorName = errorName;
        this.message = message;
    }

    public static ResError from(ErrorCode errorCode){
        return new ResError(errorCode.name(), errorCode.getMessage());
    }
}
