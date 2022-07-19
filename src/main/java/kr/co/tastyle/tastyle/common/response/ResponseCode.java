package kr.co.tastyle.tastyle.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS(HttpStatus.OK, 1000, "요청에 성공하였습니다."),
    CREATED(HttpStatus.CREATED, 1001,"생성에 성공하였습니다."),
    ;

    private final HttpStatus httpStatus;
    private int code;
    private String message;
}
