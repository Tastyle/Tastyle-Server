package kr.co.tastyle.tastyle.src.restaurant.dto.response;

import kr.co.tastyle.tastyle.src.restaurant.domain.Restaurant;
import kr.co.tastyle.tastyle.src.restaurant.domain.RestaurantImages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRatingResponse {
    private Long id;
    private List<RestaurantImagesResponse> restaurantImgUrlList;
    private String name;
    private String address;

    public static RestaurantRatingResponse of(Restaurant restaurant) {
        return RestaurantRatingResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .restaurantImgUrlList(
                        restaurant.getRestaurantImagesList()
                        .stream()
                        .map(restaurantImages -> RestaurantImagesResponse.of(restaurantImages))
                                .limit(3)
                        .collect(Collectors.toList())
                )
                .build();
    }

    public static List<RestaurantRatingResponse> ofList(List<Restaurant> restaurantList) {
        return restaurantList.stream()
                .map(RestaurantRatingResponse::of)
                .collect(Collectors.toList());
    }
}
