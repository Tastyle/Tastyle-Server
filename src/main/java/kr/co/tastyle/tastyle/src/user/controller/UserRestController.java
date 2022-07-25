package kr.co.tastyle.tastyle.src.user.controller;

import kr.co.tastyle.tastyle.common.response.CommonResponse;
import kr.co.tastyle.tastyle.common.response.ResponseCode;
import kr.co.tastyle.tastyle.jwt.SecurityUser;
import kr.co.tastyle.tastyle.src.user.dto.request.SignUpRequest;
import kr.co.tastyle.tastyle.src.user.dto.request.UpdateMyPageRequest;
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
    public CommonResponse<Void> signUp (@AuthenticationPrincipal SecurityUser securityUser, @RequestBody SignUpRequest signUpRequest) {
        log.info("[UserRestController] signUp");
        userService.signUp(securityUser, signUpRequest);
        return new CommonResponse(ResponseCode.SUCCESS);
    }

    /**
     * 마이 페이지 조회
     */
    @GetMapping("")
    public CommonResponse<MyPageResponse> getMyPage(@AuthenticationPrincipal SecurityUser securityUser) {
        log.info("[UserRestController] getMyPage");
        MyPageResponse response = userService.getMyPage(securityUser);
        return new CommonResponse<>(response);
    }

    /**
     * 마이 페이지 정보 수정
     */
    @PatchMapping("")
    public CommonResponse<Void> updateMyPage(@AuthenticationPrincipal SecurityUser securityUser, UpdateMyPageRequest updateMyPageRequest) {
        log.info("[UserRestController] updateMyPage");
        userService.updateMyPage(securityUser, updateMyPageRequest);
        return new CommonResponse(ResponseCode.SUCCESS);
    }
}
