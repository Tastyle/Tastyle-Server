package kr.co.tastyle.tastyle.src.user.service;

import kr.co.tastyle.tastyle.common.exception.CommonException;
import kr.co.tastyle.tastyle.common.exception.ErrorCode;
import kr.co.tastyle.tastyle.jwt.SecurityUser;
import kr.co.tastyle.tastyle.src.user.domain.User;
import kr.co.tastyle.tastyle.src.user.dto.request.SignUpRequest;
import kr.co.tastyle.tastyle.src.user.dto.request.UpdateMyPageRequest;
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
    public void signUp(SecurityUser securityUser, SignUpRequest signUpRequest) {
        User user = userRepository.findById(securityUser.getUserId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        user.signUp(signUpRequest);
    }

    @Transactional
    public MyPageResponse getMyPage(SecurityUser securityUser) {
        return MyPageResponse.of(securityUser.getUser());
    }

    @Transactional
    public void updateMyPage(SecurityUser securityUser, UpdateMyPageRequest updateMyPageRequest) {
        User user = userRepository.findById(securityUser.getUserId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        user.updateMyPage(updateMyPageRequest);
    }

    @Transactional
    public void deleteUser(SecurityUser securityUser) {
        User user = userRepository.findById(securityUser.getUserId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        user.deleteUser();
    }
}
