package kr.co.tastyle.tastyle.src.area.dto.response;

import kr.co.tastyle.tastyle.src.area.domain.MainArea;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AreaResponse {
    private Long mainAreaId;
    private String mainArea;
    private List<SubAreaResponse> subAreaResponseList;

    public static AreaResponse of(MainArea mainArea) {
        return AreaResponse.builder()
                .mainAreaId(mainArea.getId())
                .mainArea(mainArea.getMainAreaName())
                .subAreaResponseList(SubAreaResponse.ofList(mainArea.getSubAreaList()))
                .build();
    }

    public static List<AreaResponse> ofList(List<MainArea> mainAreaList) {
        return mainAreaList.stream()
                .map(AreaResponse::of)
                .collect(Collectors.toList());
    }

}
