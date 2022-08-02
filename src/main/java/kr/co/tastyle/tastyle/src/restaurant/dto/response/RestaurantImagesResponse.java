package kr.co.tastyle.tastyle.src.restaurant.dto.response;

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
public class RestaurantImagesResponse {
    private int imageOrder;
    private String restaurantImageUrl;

    public static RestaurantImagesResponse of(RestaurantImages restaurantImages) {
        return RestaurantImagesResponse.builder()
                .imageOrder(restaurantImages.getOrder())
                .restaurantImageUrl(restaurantImages.getRestaurantImageUrl())
                .build();
    }

    public static List<RestaurantImagesResponse> ofList(List<RestaurantImages> restaurantImagesList) {
        return restaurantImagesList.stream()
                .map(RestaurantImagesResponse::of)
                .collect(Collectors.toList());
    }

}
