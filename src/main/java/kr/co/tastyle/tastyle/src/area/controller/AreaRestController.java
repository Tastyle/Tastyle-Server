package kr.co.tastyle.tastyle.src.area.controller;

import kr.co.tastyle.tastyle.common.response.CommonResponse;
import kr.co.tastyle.tastyle.src.area.dto.response.SubAreaResponse;
import kr.co.tastyle.tastyle.src.area.service.AreaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/area")
public class AreaRestController {
    private final AreaService areaService;

    /**
     * 서브 지역 조회하기
     */
    @GetMapping("/{mainAreaId}")
    public CommonResponse<List<SubAreaResponse>> getSubAreaList(@PathVariable Long mainAreaId) {
        log.info("[AreaRestController] getSubAreaList");
        List<SubAreaResponse> response = areaService.getSubAreaList(mainAreaId);
        return new CommonResponse<>(response);
    }
}

