package kr.co.tastyle.tastyle.src.auth.service;

import kr.co.tastyle.tastyle.common.exception.CommonException;
import kr.co.tastyle.tastyle.common.exception.ErrorCode;
import kr.co.tastyle.tastyle.common.response.ResponseCode;
import kr.co.tastyle.tastyle.jwt.JwtProvider;
import kr.co.tastyle.tastyle.jwt.SecurityUser;
import kr.co.tastyle.tastyle.src.auth.client.ClientGoogle;
import kr.co.tastyle.tastyle.src.auth.dto.request.LoginRequest;
import kr.co.tastyle.tastyle.src.auth.dto.response.LoginResponse;
import kr.co.tastyle.tastyle.src.auth.client.ClientKakao;
import kr.co.tastyle.tastyle.src.user.domain.User;
import kr.co.tastyle.tastyle.src.user.domain.enums.DeviceType;
import kr.co.tastyle.tastyle.src.user.domain.enums.LoginType;
import kr.co.tastyle.tastyle.src.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static kr.co.tastyle.tastyle.src.user.domain.enums.LoginType.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final ClientKakao clientKakao;
    private final ClientGoogle clientGoogle;
    private final JwtProvider jwtProvider;

    @Transactional
    public Pair<ResponseCode, LoginResponse> login(LoginRequest loginRequest) {
        String socialId = getSocialId(loginRequest.getLoginType(), loginRequest.getAccessToken());
        Optional<User> optionalUser = userRepository.findBySocialId(socialId);
        boolean isNewMember = optionalUser.isPresent();

        // 회원가입
        if (!isNewMember) {
            User newUser = saveNewUser(socialId, loginRequest);
            Pair<String, String> tokens = createToken(newUser);

            return Pair.of(ResponseCode.CREATED, LoginResponse.of(tokens.getFirst(), tokens.getSecond()));
        }

        // 기존 유저 로그인
        User user = optionalUser.get();
        Pair<String, String> tokens = createToken(user);

        return Pair.of(ResponseCode.SUCCESS, LoginResponse.of(tokens.getFirst(), tokens.getSecond()));
    }

    private Pair<String, String> createToken(User user) {
        SecurityUser securityUser = SecurityUser.of(user);
        String accessToken = jwtProvider.createAccessToken(securityUser);
        String refreshToken = jwtProvider.createRefreshToken(securityUser);

        return Pair.of(accessToken, refreshToken);
    }

    private User saveNewUser(String socialId, LoginRequest loginRequest) {
        LoginType loginType = loginRequest.getLoginType();
        String deviceToken = loginRequest.getDeviceToken();
        DeviceType deviceType = loginRequest.getDeviceType();
        return userRepository.save(User.of(socialId, loginType, deviceToken, deviceType));
    }

    private String getSocialId(LoginType loginType, String accessToken) {
        if (loginType.equals(KAKAO)) {
            return getKakaoInfo(accessToken);
        }
        else if (loginType.equals(GOOGLE)) {
            return getGoogleInfo(accessToken);
        }
        else {
            throw new CommonException(ErrorCode.INVALID_LOGIN_TYPE);
        }
    }

    private String getGoogleInfo(String accessToken) {
        String socialId = clientGoogle.getUserInfo(accessToken);
        return socialId;
    }

    private String getKakaoInfo(String accessToken) {
        String socialId = clientKakao.getUserInfo(accessToken);
        return socialId;
    }
}
