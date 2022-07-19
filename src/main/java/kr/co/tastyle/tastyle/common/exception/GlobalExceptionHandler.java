package kr.co.tastyle.tastyle.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponse> commonException(CommonException e) {
        log.error("Handle commonException: {}", e.getMessage());
        return ErrorResponse.toCommonExceptionEntity(e);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> maxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error("Handle maxUploadSizeExceededException: {}", e.getMessage());
        return ErrorResponse.toFileUploadExceptionEntity(e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validationException(MethodArgumentNotValidException e) {
        log.error("Handle validationException: {}", e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return ErrorResponse.toValidationException(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> allHandleException(Exception e){
        log.error("Handle All Exception: {}", e.getMessage());
        return ErrorResponse.toAllExceptionEntity(e);
    }
}
