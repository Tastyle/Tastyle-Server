package kr.co.tastyle.tastyle.src.area.domain;

import kr.co.tastyle.tastyle.common.domain.BaseTimeEntity;
import kr.co.tastyle.tastyle.common.domain.Status;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@Table(name = "main_area")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MainArea extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mainAreaName;

    @OneToMany(mappedBy = "mainArea", cascade = CascadeType.ALL)
    private List<SubArea> subAreaList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;
}
