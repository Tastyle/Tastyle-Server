package kr.co.tastyle.tastyle.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 1100, "인증되지 않은 유저입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, 1101, "만료된 토큰입니다."),
    WRONG_TOKEN(HttpStatus.UNAUTHORIZED,1102, "잘못된 토큰입니다."),
    UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, 1103, "지원하지 않는 토큰입니다."),
    WRONG_TOKEN_SIGNATURE(HttpStatus.UNAUTHORIZED, 1104, "잘못된 JWT 서명입니다."),

    /* OAuth */
    INVALID_OAUTH_TOKEN(HttpStatus.UNAUTHORIZED, 1200, "OAuth Access Token이 유효하지 않습니다."),
    INVALID_OAUTH_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 1201, "OAuth 서버에 접근하는 중 에러가 발생했습니다."),
    INVALID_LOGIN_TYPE(HttpStatus.BAD_REQUEST, 1202, "잘못된 로그인 타입입니다."),

    /* User */
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, 1300, "해당 유저를 찾을 수 없습니다."),

    /* Rating */

    /* Restaurant */
    NOT_FOUND_RESTAURANT(HttpStatus.NOT_FOUND, 1400, "해당 음식점을 찾을 수 없습니다."),


    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, 4000, "잘못된 입력값입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, 4001, "권한이 없습니다."),
    MAX_UPLOAD_SIZE(HttpStatus.BAD_REQUEST, 4002, "파일 용량을 초과하였습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 5000,"서버 내부에 에러가 발생했습니다."),

    ;

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
