package kr.co.tastyle.tastyle.src.user.controller;

import kr.co.tastyle.tastyle.common.response.CommonResponse;
import kr.co.tastyle.tastyle.jwt.SecurityUser;
import kr.co.tastyle.tastyle.src.user.dto.response.MyPageResponse;
import kr.co.tastyle.tastyle.src.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController {
    private final UserService userService;

    @GetMapping("")
    public CommonResponse<MyPageResponse> getMyPage(@AuthenticationPrincipal SecurityUser securityUser) {
        log.info("[UserRestController] getMyPage");
        MyPageResponse response = userService.getMyPage(securityUser);
        return new CommonResponse<>(response);
    }
}
