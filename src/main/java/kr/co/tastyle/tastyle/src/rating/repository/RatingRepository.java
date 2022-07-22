package kr.co.tastyle.tastyle.src.rating.repository;

import kr.co.tastyle.tastyle.src.rating.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating findByUserIdAndRestaurantId(Long userId, Long restaurantId);
}
