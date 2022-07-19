package kr.co.tastyle.tastyle.src.user.service;

import kr.co.tastyle.tastyle.jwt.SecurityUser;
import kr.co.tastyle.tastyle.src.user.dto.MyPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    @Transactional
    public MyPageResponse getMyPage(SecurityUser securityUser) {
        return MyPageResponse.of(securityUser.getUser());
    }
}
