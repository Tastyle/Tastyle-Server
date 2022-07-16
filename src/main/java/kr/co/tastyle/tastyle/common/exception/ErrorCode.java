package kr.co.tastyle.tastyle.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum ErrorCode {
    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_KAKAO_TOKEN(BAD_REQUEST, "카카오 Access Token이 유효하지 않습니다."),
    INVALID_KAKAO_SERVER_ERROR(BAD_REQUEST, "카카오 로그인 서버에 접근하는 중 에러가 발생했습니다."),
    INVALID_LOGIN_TYPE(BAD_REQUEST, "잘못된 로그인 타입입니다."),
    MAX_UPLOAD_SIZE(BAD_REQUEST, "파일 용량을 초과하였습니다."),
    INVALID_INPUT_VALUE(BAD_REQUEST, "잘못된 입력값입니다."),


    /* 500 INTERNAL_SERVER_ERROR : 서버 내부 에러 */
    SERVER_ERROR(INTERNAL_SERVER_ERROR,"서버 내부에 에러가 발생했습니다."),

    ;

    private final HttpStatus httpStatus;
    private String message;


    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
