package kr.co.tastyle.tastyle.src.rating.service;

import kr.co.tastyle.tastyle.common.exception.CommonException;
import kr.co.tastyle.tastyle.common.exception.ErrorCode;
import kr.co.tastyle.tastyle.jwt.SecurityUtil;
import kr.co.tastyle.tastyle.src.rating.domain.Rating;
import kr.co.tastyle.tastyle.src.rating.dto.request.RateRestaurantRequest;
import kr.co.tastyle.tastyle.src.rating.repository.RatingRepository;
import kr.co.tastyle.tastyle.src.restaurant.domain.Restaurant;
import kr.co.tastyle.tastyle.src.restaurant.repository.RestaurantRepository;
import kr.co.tastyle.tastyle.src.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RatingService {
    private final RestaurantRepository restaurantRepository;
    private final RatingRepository ratingRepository;

    @Transactional
    public void createRating(RateRestaurantRequest rateRestaurantRequest) {
        Restaurant restaurant = restaurantRepository.findById(rateRestaurantRequest.getRestaurantId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESTAURANT));
        User user = SecurityUtil.getUser();

        ratingRepository.save(Rating.of(user, restaurant, rateRestaurantRequest.getRating()));
    }

    @Transactional
    public void updateRating(RateRestaurantRequest rateRestaurantRequest) {
        Restaurant restaurant = restaurantRepository.findById(rateRestaurantRequest.getRestaurantId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESTAURANT));

        Rating rating = ratingRepository.findByUserIdAndRestaurantId(SecurityUtil.getUserId(), restaurant.getId());
        rating.updateRating(rateRestaurantRequest.getRating());
    }
}
