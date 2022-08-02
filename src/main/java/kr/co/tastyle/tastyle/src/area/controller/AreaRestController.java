package kr.co.tastyle.tastyle.src.area.controller;

import kr.co.tastyle.tastyle.common.response.CommonResponse;
import kr.co.tastyle.tastyle.src.area.dto.response.AreaResponse;
import kr.co.tastyle.tastyle.src.area.dto.response.SubAreaResponse;
import kr.co.tastyle.tastyle.src.area.service.AreaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/areas")
public class AreaRestController {
    private final AreaService areaService;

    /**
     * 서브 지역 조회하기
     */
    @GetMapping("")
    public CommonResponse<List<AreaResponse>> getAreaList() {
        log.info("[AreaRestController] getAreaList");
        List<AreaResponse> response = areaService.getAreaList();
        return new CommonResponse<>(response);
    }
}

