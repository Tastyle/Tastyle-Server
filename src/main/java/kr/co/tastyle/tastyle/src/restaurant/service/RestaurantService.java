package kr.co.tastyle.tastyle.src.restaurant.service;

import kr.co.tastyle.tastyle.src.restaurant.domain.Restaurant;
import kr.co.tastyle.tastyle.src.restaurant.dto.response.RestaurantRatingResponse;
import kr.co.tastyle.tastyle.src.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public List<RestaurantRatingResponse> getRestaurantListForRating(List<Long> subAreaId, Pageable pageable) {
        List<Restaurant> restaurantList = restaurantRepository.findAllBySubAreaIdIn(subAreaId, pageable);
        return RestaurantRatingResponse.ofList(restaurantList);
    }
}
