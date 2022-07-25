package kr.co.tastyle.tastyle.src.area.repository;

import kr.co.tastyle.tastyle.src.area.domain.SubArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubAreaRepository extends JpaRepository<SubArea, Long> {
    List<SubArea> findAllByMainAreaId(Long mainAreaId);
}
