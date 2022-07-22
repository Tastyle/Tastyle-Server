package kr.co.tastyle.tastyle.src.rating.controller;

import kr.co.tastyle.tastyle.common.response.CommonResponse;
import kr.co.tastyle.tastyle.common.response.ResponseCode;
import kr.co.tastyle.tastyle.src.rating.dto.request.RateRestaurantRequest;
import kr.co.tastyle.tastyle.src.rating.service.RatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rating")
public class RatingRestController {
    private final RatingService ratingService;

    @PostMapping("")
    public CommonResponse<Void> createRating(@RequestBody RateRestaurantRequest rateRestaurantRequest) {
        ratingService.createRating(rateRestaurantRequest);
        log.info("[RatingRestController] createRating");
        return new CommonResponse(ResponseCode.SUCCESS);
    }

    @PatchMapping("")
    public CommonResponse<Void> updateRating(@RequestBody RateRestaurantRequest rateRestaurantRequest) {
        ratingService.updateRating(rateRestaurantRequest);
        log.info("[RatingRestController] updateRating");
        return new CommonResponse(ResponseCode.SUCCESS);
    }
}
