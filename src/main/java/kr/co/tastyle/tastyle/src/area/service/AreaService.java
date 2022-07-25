package kr.co.tastyle.tastyle.src.area.service;

import kr.co.tastyle.tastyle.src.area.domain.SubArea;
import kr.co.tastyle.tastyle.src.area.dto.response.SubAreaResponse;
import kr.co.tastyle.tastyle.src.area.repository.SubAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AreaService {
    private final SubAreaRepository subAreaRepository;

    @Transactional
    public List<SubAreaResponse> getSubAreaList(Long mainAreaId) {
        List<SubArea> subAreaList = subAreaRepository.findAllByMainAreaId(mainAreaId);
        return SubAreaResponse.ofList(subAreaList);
    }
}
