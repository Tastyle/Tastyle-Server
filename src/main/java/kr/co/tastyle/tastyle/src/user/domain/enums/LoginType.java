package kr.co.tastyle.tastyle.src.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginType {
    KAKAO("카카오"),
    GOOGLE("구글"),
    APPLE("애플"),
    ;

    private final String description;
}
