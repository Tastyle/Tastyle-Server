package kr.co.tastyle.tastyle.src.auth.dto.request;

import com.sun.istack.NotNull;
import kr.co.tastyle.tastyle.src.user.domain.User;
import kr.co.tastyle.tastyle.src.user.domain.enums.DeviceType;
import kr.co.tastyle.tastyle.src.user.domain.enums.LoginType;
import kr.co.tastyle.tastyle.src.user.domain.enums.Role;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import static kr.co.tastyle.tastyle.common.domain.Status.NORMAL;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class LoginRequest {
    @NotNull
    private String accessToken;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    @NotNull
    private String deviceToken;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;
}
