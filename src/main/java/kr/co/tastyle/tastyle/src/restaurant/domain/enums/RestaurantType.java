package kr.co.tastyle.tastyle.src.restaurant.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RestaurantType {
    RESTAURANT("음식점"),
    CAFE("카페"),
    BRA("술집")

    ;
    private final String descriptions;
}
