package com.be1.plant4you.dto.response.plant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "식물 mbti 결과 반환 DTO")
@Getter
@AllArgsConstructor
public class PlantScoreResponse {

    @Schema(description = "식물 이름", example = "다육이")
    private String name;

    @Schema(description = "식물 이미지 url", example = "http://")
    private String imgUrl;
}