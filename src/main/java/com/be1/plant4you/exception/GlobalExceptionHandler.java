package com.be1.plant4you.exception;

import com.be1.plant4you.dto.response.ex.ExResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExResponse> boardExceptionHandler(BoardException e, HttpServletRequest request) {
        log.error("", e);
        return makeBoardResponseEntity(e.getErrorCode(), request);
    }

    private ResponseEntity<ExResponse> makeBoardResponseEntity(ErrorCode errorCode, HttpServletRequest request) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(
                        ExResponse.builder()
                                .status(errorCode.getHttpStatus().value())
                                .error(errorCode.getHttpStatus())
                                .message(errorCode.getMessage())
                                .path(request.getRequestURI())
                                .build()
                );
    }
}