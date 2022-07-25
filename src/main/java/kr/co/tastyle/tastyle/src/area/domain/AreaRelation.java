package kr.co.tastyle.tastyle.src.area.domain;

import kr.co.tastyle.tastyle.common.domain.BaseTimeEntity;
import kr.co.tastyle.tastyle.common.domain.Status;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@Table(name = "area_relation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AreaRelation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String relation_name;

    @Enumerated(EnumType.STRING)
    private Status status;
}
