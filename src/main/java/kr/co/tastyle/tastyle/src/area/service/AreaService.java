package kr.co.tastyle.tastyle.src.area.service;

import kr.co.tastyle.tastyle.src.area.domain.MainArea;
import kr.co.tastyle.tastyle.src.area.dto.response.AreaResponse;
import kr.co.tastyle.tastyle.src.area.repository.MainAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AreaService {
    private final MainAreaRepository mainAreaRepository;

    @Transactional
    public List<AreaResponse> getAreaList() {
        List<MainArea> mainAreaList = mainAreaRepository.findAll();
        return AreaResponse.ofList(mainAreaList);
    }
}
