package kr.co.tastyle.tastyle.src.area.dto.response;

import kr.co.tastyle.tastyle.src.area.domain.SubArea;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubAreaResponse {
    private Long id;
    private String subAreaName;

    public static SubAreaResponse of(SubArea subArea) {
        return SubAreaResponse.builder()
                .id(subArea.getId())
                .subAreaName(subArea.getSubAreaName())
                .build();
    }

    public static List<SubAreaResponse> ofList(List<SubArea> subAreaList) {
        return subAreaList.stream()
                .map(SubAreaResponse::of)
                .collect(Collectors.toList());
    }
}
