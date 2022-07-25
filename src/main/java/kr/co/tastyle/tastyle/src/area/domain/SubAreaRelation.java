package kr.co.tastyle.tastyle.src.area.domain;

import kr.co.tastyle.tastyle.common.domain.BaseTimeEntity;
import kr.co.tastyle.tastyle.common.domain.Status;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@Table(name = "sub_area_relation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SubAreaRelation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_area_id")
    private SubArea subArea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_relation_id")
    private AreaRelation areaRelation;

    private String subAreaName;

    @Enumerated(EnumType.STRING)
    private Status status;
}
