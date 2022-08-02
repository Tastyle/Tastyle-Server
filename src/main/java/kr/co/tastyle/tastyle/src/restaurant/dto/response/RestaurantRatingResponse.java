package kr.co.tastyle.tastyle.src.restaurant.dto.response;

import kr.co.tastyle.tastyle.src.restaurant.domain.Restaurant;
import kr.co.tastyle.tastyle.src.restaurant.domain.RestaurantImages;
import kr.co.tastyle.tastyle.src.restaurant.domain.enums.RestaurantType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRatingResponse {
    private Long id;
    private String restaurantName;
    private String address;
    private double latitude;
    private double longitude;
    @Enumerated(EnumType.STRING)
    private RestaurantType type;
    private List<RestaurantImagesResponse> restaurantImgUrlList;

    public static RestaurantRatingResponse of(Restaurant restaurant) {
        return RestaurantRatingResponse.builder()
                .id(restaurant.getId())
                .restaurantName(restaurant.getRestaurantName())
                .address(restaurant.getAddress())
                .latitude(restaurant.getLatitude())
                .longitude(restaurant.getLongitude())
                .type(restaurant.getType())
                .restaurantImgUrlList(RestaurantImagesResponse.ofList(restaurant.getRestaurantImagesList()))
                .build();
    }

    public static List<RestaurantRatingResponse> ofList(List<Restaurant> restaurantList) {
        return restaurantList.stream()
                .map(RestaurantRatingResponse::of)
                .collect(Collectors.toList());
    }
}
