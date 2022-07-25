package kr.co.tastyle.tastyle.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    NORMAL("일반"),
    DELETED("삭제"),

    ;

    private final String description;
}
