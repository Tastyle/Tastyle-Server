package kr.co.tastyle.tastyle.src.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeviceType {
    AOS("AOS"),
    IOS("IOS")
    ;

    private final String description;
}
