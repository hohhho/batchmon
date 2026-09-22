package com.hoopio.batchmon.common.exception;

import java.time.LocalDateTime;

/**
 * 에러 응답 DTO
 * @param code
 * @param message
 * @param timeStamp
 */
public record ErrorResponse(
    String code,
    String message,
    LocalDateTime timeStamp
) {

    public static ErrorResponse of(ErrorCode errorCode, String message) {
        return new ErrorResponse(errorCode.getCode(), message, LocalDateTime.now());
    }
}
