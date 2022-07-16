package kr.co.tastyle.tastyle.common.exception;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {
    private ErrorCode errorCode;

    public CommonException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
