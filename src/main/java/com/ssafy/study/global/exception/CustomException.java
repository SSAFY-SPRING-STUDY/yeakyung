package com.ssafy.study.global.exception;

import com.ssafy.study.global.exception.error.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
