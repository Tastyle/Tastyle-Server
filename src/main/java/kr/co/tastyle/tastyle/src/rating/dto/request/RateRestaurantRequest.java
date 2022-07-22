package kr.co.tastyle.tastyle.src.rating.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RateRestaurantRequest {
    private Long restaurantId;
    private double rating;
}
