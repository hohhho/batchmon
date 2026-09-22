package com.hoopio.batchmon.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    JOB_NOT_FOUND(HttpStatus.NOT_FOUND, "JOB_NOT_FOUND", "등록되지 않은 잡입니다"),
    API_NOT_FOUND(HttpStatus.NOT_FOUND, "API_NOT_FOUND", "존재하지 않는 API 경로입니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "INVALID_INPUT", "요청 값이 올바르지 않습니다."),
    INVALID_REQUEST_BODY(HttpStatus.BAD_REQUEST, "INVALID_REQUEST_BODY", "요청 본문을 읽을 수 없습니다."),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", "서버 오류가 발생했습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;

    // @AllArgsConstructor 로 대체 가능
    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
