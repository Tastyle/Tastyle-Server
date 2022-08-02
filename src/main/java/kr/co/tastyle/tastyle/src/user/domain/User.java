package kr.co.tastyle.tastyle.src.user.domain;

import kr.co.tastyle.tastyle.common.domain.BaseTimeEntity;
import kr.co.tastyle.tastyle.common.domain.Status;
import kr.co.tastyle.tastyle.jwt.SecurityUser;
import kr.co.tastyle.tastyle.src.user.domain.enums.*;
import kr.co.tastyle.tastyle.src.user.dto.request.SignUpRequest;
import kr.co.tastyle.tastyle.src.user.dto.request.UpdateMyPageRequest;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import static kr.co.tastyle.tastyle.common.domain.Status.NORMAL;
import static kr.co.tastyle.tastyle.src.user.domain.enums.UserStatus.DELETED;
import static kr.co.tastyle.tastyle.src.user.domain.enums.UserStatus.NOT_COMPLETED;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Where(clause = "STATUS != 'DELETED'")
@Table(name = "users")
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
    private UserStatus status;

    public static User of(String socialId, LoginType loginType, String deviceToken, DeviceType deviceType) {
        return User.builder()
                .socialId(socialId)
                .loginType(loginType)
                .deviceToken(deviceToken)
                .deviceType(deviceType)
                .role(Role.ROLE_USER)
                .status(NOT_COMPLETED)
                .build();
    }

    public void signUp(SignUpRequest signUpRequest) {
        this.nickname = signUpRequest.getNickname();
        this.profileImgUrl = signUpRequest.getProfileImgUrl();
        this.introduction = signUpRequest.getIntroduction();
        this.sex = signUpRequest.getSex();
        this.birthDate = signUpRequest.getBirthDate();
        this.status = UserStatus.NORMAL;
    }

    public void updateMyPage(UpdateMyPageRequest myPageRequest) {
        this.nickname = myPageRequest.getNickname();
        this.profileImgUrl = myPageRequest.getProfileImgUrl();
        this.introduction = myPageRequest.getIntroduction();
        this.sex = myPageRequest.getSex();
        this.birthDate = myPageRequest.getBirthDate();
    }

    public void deleteUser() {
        this.status = UserStatus.DELETED;
    }
}
