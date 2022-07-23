package kr.co.tastyle.tastyle.src.user.service;

import kr.co.tastyle.tastyle.common.exception.CommonException;
import kr.co.tastyle.tastyle.common.exception.ErrorCode;
import kr.co.tastyle.tastyle.jwt.SecurityUser;
import kr.co.tastyle.tastyle.jwt.SecurityUtil;
import kr.co.tastyle.tastyle.src.user.domain.User;
import kr.co.tastyle.tastyle.src.user.dto.request.SignUpRequest;
import kr.co.tastyle.tastyle.src.user.dto.response.MyPageResponse;
import kr.co.tastyle.tastyle.src.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void signUp(SignUpRequest signUpRequest) {
        User user = userRepository.findById(SecurityUtil.getUserId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        user.signUp(signUpRequest);
    }

    @Transactional
    public MyPageResponse getMyPage() {
        return MyPageResponse.of(SecurityUtil.getUser());
    }
}
