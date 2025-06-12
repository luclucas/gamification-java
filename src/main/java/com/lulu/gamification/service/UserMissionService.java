package com.lulu.gamification.service;

import com.lulu.gamification.model.Mission;
import com.lulu.gamification.model.User;
import com.lulu.gamification.model.UserMission;
import com.lulu.gamification.repository.MissionRepository;
import com.lulu.gamification.repository.UserMissionRepository;
import com.lulu.gamification.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMissionService {

    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;


    public UserMission assignMissionToUser(User user, Mission mission){

        UserMission userMission = new UserMission();
        userMission.setUser(user);
        userMission.setMission(mission);
        userMission.setFinished(false);
        return userMissionRepository.save(userMission);
    }

    public UserMission finishMission(User user, Mission mission){
        UserMission userMission = userMissionRepository.findByUserIdAndMissionId(user.getId(), mission.getId()).orElseThrow(() -> new EntityNotFoundException("UserMission not found"));

        userMission.setFinished(true);
        return userMission;
    }
}
