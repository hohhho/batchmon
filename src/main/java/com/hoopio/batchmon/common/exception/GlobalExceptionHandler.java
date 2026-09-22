package com.hoopio.batchmon.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // --- client ---
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        ErrorResponse response = ErrorResponse.of(e.getErrorCode(), e.getMessage());
        log.warn("[{}] {}", e.getErrorCode().getCode(), e.getMessage());    // 비즈니스 예외는 warn으로 에러 메시지만 출력

        return new ResponseEntity<>(response, e.getErrorCode().getStatus());
    }

    @ExceptionHandler({NoHandlerFoundException.class, NoResourceFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFound(Exception e) {
        ErrorCode errorCode = ErrorCode.API_NOT_FOUND;
        ErrorResponse response = ErrorResponse.of(errorCode, errorCode.getMessage());
        log.warn("[{}] {}", errorCode.getCode(), e.getMessage());

        return new ResponseEntity<>(response, errorCode.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInputException(MethodArgumentNotValidException e) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT;
        ErrorResponse response = ErrorResponse.of(errorCode, errorCode.getMessage());
        log.warn("[{}] {}", errorCode.getCode(), e.getMessage());

        return new ResponseEntity<>(response, errorCode.getStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestBodyException(HttpMessageNotReadableException e) {
        ErrorCode errorCode = ErrorCode.INVALID_REQUEST_BODY;
        ErrorResponse response = ErrorResponse.of(errorCode, errorCode.getMessage());
        log.warn("[{}] {}", errorCode.getCode(), e.getMessage());

        return new ResponseEntity<>(response, errorCode.getStatus());
    }


    // --- server ---
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllException(Exception e) {
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
        ErrorResponse response = ErrorResponse.of(errorCode, errorCode.getMessage());
        log.error(errorCode.getMessage(), e);

        return new ResponseEntity<>(response, errorCode.getStatus());
    }
}
