package com.lulu.gamification.service;

import com.lulu.gamification.model.Mission;
import com.lulu.gamification.model.User;
import com.lulu.gamification.model.UserMission;
import com.lulu.gamification.repository.UserMissionRepository;
import com.lulu.gamification.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;
    private final MissionService missionService;
    private final UserMissionService userMissionService;

    @Transactional
    public User createUser(User user) {
        user.setMissions(new ArrayList<>());
        user.setLevel(0);
        user.setXpToNextLevel(100);
        user.setCurrentXp(0);
        user = userRepository.save(user);

        Mission mission = missionService.findById(1L);
        UserMission userMission = userMissionService.assignMissionToUser(user, mission);
        user.getMissions().add(userMission);
        return userRepository.save(user);
    }

    public User getById(Long userId){
        return userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("user not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User finishMission(Long userId, Long missionId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
//        UserMission userMission = userMissionRepository.findByUserIdAndMissionId(userId, missionId).orElseThrow(() -> new EntityNotFoundException("user mission not found"));
//        userMission.setFinished(true);
        //UserMission userMission = userMissionService.finishMission(userId, missionId);
   //     userMissionRepository.save(userMission);
        Mission mission = missionService.findById(missionId);

        UserMission userMission= userMissionService.finishMission(user, mission);

        user = checkUpUserLevel(user, mission.getXpPointsReward());

        return userRepository.save(user);
    }

    private User checkUpUserLevel(User user, Integer reward){
        user.setCurrentXp(user.getCurrentXp() + reward);
        if (user.getCurrentXp() < user.getXpToNextLevel()){
            user.setXpToNextLevel(user.getXpToNextLevel() - user.getCurrentXp());
        } else {
            user.setLevel(user.getLevel()+1);
            user.setCurrentXp(user.getCurrentXp() - user.getXpToNextLevel());
            user.setXpToNextLevel(user.getXpToNextLevel()*2);
        }
        return  user;
    }
}
