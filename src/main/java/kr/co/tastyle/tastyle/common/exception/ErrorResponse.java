package kr.co.tastyle.tastyle.common.exception;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import static kr.co.tastyle.tastyle.common.exception.ErrorCode.MAX_UPLOAD_SIZE;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
    private int status;
    private String code;
    private String message;

    public static ResponseEntity<ErrorResponse> toCommonExceptionEntity(CommonException e) {
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(e.getErrorCode().getHttpStatus().value())
                        .code(e.getErrorCode().name())
                        .message(e.getErrorCode().getMessage())
                        .build());
    }

    public static ResponseEntity<ErrorResponse> toFileUploadExceptionEntity(MaxUploadSizeExceededException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .status(MAX_UPLOAD_SIZE.getHttpStatus().value())
                        .code(MAX_UPLOAD_SIZE.name())
                        .message(MAX_UPLOAD_SIZE.getMessage())
                        .build());
    }

    public static ResponseEntity<ErrorResponse> toAllExceptionEntity(Exception e) {
        ErrorCode errorCode = ErrorCode.SERVER_ERROR;
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .code(errorCode.name())
                        .message(errorCode.getMessage())
                        .build());
    }
}
