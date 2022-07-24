package kr.co.tastyle.tastyle.src.restaurant.repository;

import kr.co.tastyle.tastyle.src.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAllBySubAreaId(Long subAreaId);
}
