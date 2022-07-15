package kr.co.tastyle.tastyle.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonException extends RuntimeException {
    private ErrorCode errorCode;
}
