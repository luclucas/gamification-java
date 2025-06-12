package com.lulu.gamification.service;

import com.lulu.gamification.model.Mission;
import com.lulu.gamification.repository.MissionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    public Mission createMission(Mission mission){
        return missionRepository.save(mission);
    }
    public Mission findById(Long missionId){
        return missionRepository.findById(missionId).orElseThrow(() -> new EntityNotFoundException("Mission not found"));
    }
}
