package kr.co.tastyle.tastyle.common.exception;

import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Map;

import static kr.co.tastyle.tastyle.common.exception.ErrorCode.MAX_UPLOAD_SIZE;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse<T> {
    private int code;
    private T message;

    private ErrorResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = (T) errorCode.getMessage();
    }

    private ErrorResponse(ErrorCode errorCode, String message) {
        this.code = errorCode.getCode();
        this.message = (T) message;
    }

    // 공통 Exception
    public static ResponseEntity<ErrorResponse> toCommonExceptionEntity(CommonException e) {
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponse.builder()
                        .code(e.getErrorCode().getCode())
                        .message(e.getErrorCode().getMessage())
                        .build());
    }

    // 파일 용량 초과 Exception
    public static ResponseEntity<ErrorResponse> toFileUploadExceptionEntity(MaxUploadSizeExceededException e) {
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.builder()
                        .code(MAX_UPLOAD_SIZE.getCode())
                        .message(MAX_UPLOAD_SIZE.getMessage())
                        .build());
    }

    // DTO @Valid 검증 Exception
    public static ResponseEntity<ErrorResponse> toValidationException(Map<String, String> errors) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.builder()
                        .code(errorCode.getCode())
                        .message(errors)
                        .build());
    }

    public static ResponseEntity<ErrorResponse> toAllExceptionEntity(Exception e) {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .internalServerError()
                .body(ErrorResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }

    public static ErrorResponse fromUnauthorizedException(String message) {
        return new ErrorResponse(ErrorCode.UNAUTHORIZED, message);
    }

    public static ErrorResponse fromForbiddenException(String message) {
        return new ErrorResponse(ErrorCode.FORBIDDEN, message);
    }

    public static ErrorResponse fromExceptionHandlerFilter(CommonException exception) {
        return new ErrorResponse(exception.getErrorCode());
    }

}
