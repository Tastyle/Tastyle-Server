package kr.co.tastyle.tastyle.src.area.repository;

import kr.co.tastyle.tastyle.src.area.domain.MainArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainAreaRepository extends JpaRepository<MainArea, Long> {
    List<MainArea> findAll();

}
