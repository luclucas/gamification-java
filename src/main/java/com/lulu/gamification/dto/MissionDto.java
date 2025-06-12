package com.lulu.gamification.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MissionDto {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private Integer xpPointsReward;
}
