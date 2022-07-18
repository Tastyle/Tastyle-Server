package kr.co.tastyle.tastyle.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static kr.co.tastyle.tastyle.common.response.ResponseCode.SUCCESS;

@Getter
@Builder
@AllArgsConstructor
public class CommonResponse<T> {
    private int code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    public CommonResponse(T result) {
        this.code = SUCCESS.getCode();
        this.message = SUCCESS.getMessage();
        this.result = result;
    }

    public CommonResponse(ResponseCode status, T result) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.result = result;
    }
}
