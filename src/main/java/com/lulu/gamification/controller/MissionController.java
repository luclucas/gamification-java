package com.lulu.gamification.controller;

import com.lulu.gamification.dto.MissionDto;
import com.lulu.gamification.model.Mission;
import com.lulu.gamification.service.MissionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
@Tag(name = "Mission", description = "Mission related operations") // Adicione esta linha
public class MissionController {

    private final MissionService missionService;

    @PostMapping
    public ResponseEntity<Mission> createMission(@Valid @RequestBody MissionDto missionDto){
        Mission mission = new Mission();
        mission.setTitle(missionDto.getTitle());
        mission.setDescription(missionDto.getDescription());
        mission.setXpPointsReward(missionDto.getXpPointsReward());

        Mission createdMission = missionService.createMission(mission);
        return ResponseEntity.ok(mission);
    }
}
