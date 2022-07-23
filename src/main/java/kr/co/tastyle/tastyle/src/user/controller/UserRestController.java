package kr.co.tastyle.tastyle.src.user.controller;

import kr.co.tastyle.tastyle.common.response.CommonResponse;
import kr.co.tastyle.tastyle.common.response.ResponseCode;
import kr.co.tastyle.tastyle.jwt.SecurityUser;
import kr.co.tastyle.tastyle.src.user.dto.request.SignUpRequest;
import kr.co.tastyle.tastyle.src.user.dto.response.MyPageResponse;
import kr.co.tastyle.tastyle.src.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController {
    private final UserService userService;

    /**
     * 회원가입 시 정보 입력
     */
    @PostMapping("/sign-up")
    public CommonResponse<Void> signUp (@RequestBody SignUpRequest signUpRequest) {
        log.info("[UserRestController] signUp");
        userService.signUp(signUpRequest);
        return new CommonResponse(ResponseCode.SUCCESS);
    }

    /**
     * 마이 페이지 조회
     */
    @GetMapping("")
    public CommonResponse<MyPageResponse> getMyPage() {
        log.info("[UserRestController] getMyPage");
        MyPageResponse response = userService.getMyPage();
        return new CommonResponse<>(response);
    }
}
