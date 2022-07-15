package kr.co.tastyle.tastyle.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponse> commonException(CommonException e) {
        log.error("Handle applicationException: {}", e.getMessage());
        return ErrorResponse.toCommonExceptionEntity(e);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> maxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error("Handle maxUploadSizeExceededException: {}", e.getMessage());
        return ErrorResponse.toFileUploadExceptionEntity(e);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> allHandleException(Exception e){
        log.error("Handle All Exception: {}", e.getMessage());
        return ErrorResponse.toAllExceptionEntity(e);
    }
}
