package kr.co.tastyle.tastyle.src.user.dto.request;

import kr.co.tastyle.tastyle.src.user.domain.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMyPageRequest {
    private String profileImgUrl;
    private String nickname;
    private String introduction;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private String birthDate;
}
