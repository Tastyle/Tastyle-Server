package kr.co.tastyle.tastyle.src.restaurant.service;

import kr.co.tastyle.tastyle.src.restaurant.domain.Restaurant;
import kr.co.tastyle.tastyle.src.restaurant.dto.response.RestaurantRatingResponse;
import kr.co.tastyle.tastyle.src.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public List<RestaurantRatingResponse> getRestaurantListForRating(Long subAreaId) {
        List<Restaurant> restaurantList = restaurantRepository.findAllBySubAreaId(subAreaId);
        return RestaurantRatingResponse.ofList(restaurantList);
    }
}
