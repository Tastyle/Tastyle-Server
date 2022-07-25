package kr.co.tastyle.tastyle.src.auth.controller;

import kr.co.tastyle.tastyle.common.response.CommonResponse;
import kr.co.tastyle.tastyle.common.response.ResponseCode;
import kr.co.tastyle.tastyle.src.auth.dto.request.LoginRequest;
import kr.co.tastyle.tastyle.src.auth.dto.response.LoginResponse;
import kr.co.tastyle.tastyle.src.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthRestController {
    private final AuthService authService;

    /**
     * 소셜 로그인, 회원가입
     */
    @PostMapping("/login")
    public ResponseEntity<CommonResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        log.info("[AuthRestController] login");
        Pair<ResponseCode, LoginResponse> response = authService.login(loginRequest);
        ResponseCode responseCode = response.getFirst();
        LoginResponse loginResponse = response.getSecond();
        return ResponseEntity.status(responseCode.getHttpStatus()).body(new CommonResponse<>(loginResponse));
    }
}
