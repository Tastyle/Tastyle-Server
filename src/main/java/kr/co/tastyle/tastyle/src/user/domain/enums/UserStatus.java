package kr.co.tastyle.tastyle.src.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    NORMAL("일반"),
    DELETED("탈퇴"),
    NOT_COMPLETED("정보 미입력"),

    ;

    private final String description;
}
