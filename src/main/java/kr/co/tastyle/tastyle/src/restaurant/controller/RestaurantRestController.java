package kr.co.tastyle.tastyle.src.restaurant.controller;

import kr.co.tastyle.tastyle.common.response.CommonResponse;
import kr.co.tastyle.tastyle.src.restaurant.dto.response.RestaurantRatingResponse;
import kr.co.tastyle.tastyle.src.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/restaurant")
public class RestaurantRestController {
    private final RestaurantService restaurantService;

    /**
     * Sub Area 선택 시 음식점 리스트 조회 for 평가하기 - 다중 선택 가능
     */
    @GetMapping("")
    public CommonResponse<List<RestaurantRatingResponse>> getRestaurantListForRating(@RequestParam List<Long> subAreaId) {
        log.info("[RestaurantRestController] getRestaurantListForRating");
        List<RestaurantRatingResponse> response = restaurantService.getRestaurantListForRating(subAreaId);
        return new CommonResponse<>(response);
    }
}


