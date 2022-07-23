package kr.co.tastyle.tastyle.src.user.dto.request;

import kr.co.tastyle.tastyle.src.user.domain.enums.Sex;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String nickname;
    private String profileImgUrl;
    private String introduction;
    private Sex sex;
    private String birthDate;
}
