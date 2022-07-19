package kr.co.tastyle.tastyle.src.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sex {
    FEMALE("여자"),
    MALE("남자"),
    ;

    private final String description;
}
