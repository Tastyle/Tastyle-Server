package kr.co.tastyle.tastyle.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static kr.co.tastyle.tastyle.common.response.ResponseCode.SUCCESS;

@Getter
@AllArgsConstructor
public class CommonResponse<T> {
    private int status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    public CommonResponse(T result) {
        this.status = SUCCESS.getHttpStatus().value();
        this.message = SUCCESS.getMessage();
        this.result = result;
    }

    public CommonResponse(ResponseCode status) {
        this.status = SUCCESS.getHttpStatus().value();
        this.message = status.getMessage();
    }
}
