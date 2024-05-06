package com.amudy.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {ApiCustomException.class})
    public ResponseEntity<ResError> handleApiCustomException(ApiCustomException ex){
        ErrorCode errorCode = ex.getErrorCode();
        log.error(errorCode.getMessage(),ex);
        return new ResponseEntity<>(ResError.from(errorCode),errorCode.getStatus());
    }

    @ExceptionHandler(value = {MvcCustomException.class})
    public ModelAndView handleMvcCustomException(MvcCustomException ex){
        ErrorCode errorCode = ex.getErrorCode();
        log.error(errorCode.getMessage(),ex);

        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.setStatus(errorCode.getStatus());

        return modelAndView;
    }
}
