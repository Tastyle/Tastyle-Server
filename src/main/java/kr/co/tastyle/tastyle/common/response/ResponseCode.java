package kr.co.tastyle.tastyle.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseCode {
    SUCCESS(HttpStatus.OK, "요청에 성공하였습니다.")
    ;

    private HttpStatus httpStatus;
    private String message;

    ResponseCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
