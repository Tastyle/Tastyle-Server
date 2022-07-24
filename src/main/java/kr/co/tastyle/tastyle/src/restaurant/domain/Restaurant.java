package kr.co.tastyle.tastyle.src.restaurant.domain;

import kr.co.tastyle.tastyle.common.domain.BaseTimeEntity;
import kr.co.tastyle.tastyle.src.area.domain.SubArea;
import kr.co.tastyle.tastyle.src.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "restaurant_info")
public class Restaurant extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String number;

    private double latitude;

    private double longitude;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<RestaurantImages> restaurantImagesList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_area_id")
    private SubArea subArea;

    private double isClosed;
}
