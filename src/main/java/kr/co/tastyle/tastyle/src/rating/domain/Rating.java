package kr.co.tastyle.tastyle.src.rating.domain;

import kr.co.tastyle.tastyle.common.domain.BaseTimeEntity;
import kr.co.tastyle.tastyle.src.restaurant.domain.Restaurant;
import kr.co.tastyle.tastyle.src.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "rating")
public class Rating extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private double rating;

    public static Rating of(User user, Restaurant restaurant, double rating) {
        return Rating.builder()
                .user(user)
                .restaurant(restaurant)
                .rating(rating)
                .build();
    }

    public void updateRating(double rating) {
        this.rating = rating;
    }
}
