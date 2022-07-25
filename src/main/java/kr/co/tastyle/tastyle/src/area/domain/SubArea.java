package kr.co.tastyle.tastyle.src.area.domain;

import kr.co.tastyle.tastyle.common.domain.BaseTimeEntity;
import kr.co.tastyle.tastyle.common.domain.Status;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@Table(name = "sub_area")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SubArea extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_area_id")
    private MainArea mainArea;

    private String subAreaName;

    @Enumerated(EnumType.STRING)
    private Status status;
}
