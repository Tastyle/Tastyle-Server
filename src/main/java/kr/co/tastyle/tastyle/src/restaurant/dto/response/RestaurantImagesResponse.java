package kr.co.tastyle.tastyle.src.restaurant.dto.response;

import kr.co.tastyle.tastyle.src.restaurant.domain.RestaurantImages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantImagesResponse {
    private int imageOrder;
    private String restaurantImageUrl;

    public static RestaurantImagesResponse of(RestaurantImages restaurantImages) {
        return RestaurantImagesResponse.builder()
                .imageOrder(restaurantImages.getImageOrder())
                .restaurantImageUrl(restaurantImages.getRestaurantImageUrl())
                .build();
    }

}
