package kr.co.tastyle.tastyle.src.area.dto.response;

import kr.co.tastyle.tastyle.src.area.domain.SubArea;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaResponse {
    private List<SubAreaResponse> subAreaList;
}
