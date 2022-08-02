package kr.co.tastyle.tastyle.src.restaurant.repository;

import kr.co.tastyle.tastyle.src.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.restaurantImagesList WHERE r.subArea.id IN (:subAreaId)")
    List<Restaurant> findAllBySubAreaIdIn(List<Long> subAreaId);
}
