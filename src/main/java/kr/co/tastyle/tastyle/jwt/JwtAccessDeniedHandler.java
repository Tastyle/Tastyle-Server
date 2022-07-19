package kr.co.tastyle.tastyle.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.tastyle.tastyle.common.exception.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;
    private final String MESSAGE = "권한이 없습니다.";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        log.error("JwtAccessDeniedHandler Access Denied Exception: {}", accessDeniedException.getMessage());

        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String json = objectMapper.writeValueAsString(ErrorResponse.fromForbiddenException(MESSAGE));

        response.getWriter().write(json);
    }
}
