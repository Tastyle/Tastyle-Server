package kr.co.tastyle.tastyle.src.user.domain;

import kr.co.tastyle.tastyle.common.domain.BaseTimeEntity;
import kr.co.tastyle.tastyle.common.domain.Status;
import kr.co.tastyle.tastyle.src.user.domain.enums.DeviceType;
import kr.co.tastyle.tastyle.src.user.domain.enums.LoginType;
import kr.co.tastyle.tastyle.src.user.domain.enums.Role;
import kr.co.tastyle.tastyle.src.user.domain.enums.Sex;
import lombok.*;

import javax.persistence.*;

import static kr.co.tastyle.tastyle.common.domain.Status.NORMAL;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "user")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 12)
    private String nickname;

    private String introduction;

    private String profileImgUrl;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private String birthDate;

    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    private String socialId;

    private String deviceToken;

    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    public static User of(String socialId, LoginType loginType, String deviceToken, DeviceType deviceType) {
        return User.builder()
                .socialId(socialId)
                .loginType(loginType)
                .deviceToken(deviceToken)
                .deviceType(deviceType)
                .role(Role.ROLE_USER)
                .status(NORMAL)
                .build();
    }
}
