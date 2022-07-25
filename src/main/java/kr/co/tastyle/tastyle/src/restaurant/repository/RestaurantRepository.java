package kr.co.tastyle.tastyle.src.restaurant.repository;

import kr.co.tastyle.tastyle.src.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAllBySubAreaIdIn(List<Long> subAreaId);
}
