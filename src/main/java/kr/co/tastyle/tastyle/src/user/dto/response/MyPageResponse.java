package kr.co.tastyle.tastyle.src.user.dto.response;

import kr.co.tastyle.tastyle.src.user.domain.User;
import kr.co.tastyle.tastyle.src.user.domain.enums.Sex;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPageResponse {
    private String profileImgUrl;
    private String nickname;
    private String introduction;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private String birthDate;

    public static MyPageResponse of(User user) {
        return MyPageResponse.builder()
                .profileImgUrl(user.getProfileImgUrl())
                .nickname(user.getNickname())
                .introduction(user.getIntroduction())
                .sex(user.getSex())
                .birthDate(user.getBirthDate())
                .build();
    }
}
