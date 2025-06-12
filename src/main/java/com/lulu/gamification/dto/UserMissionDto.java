package com.lulu.gamification.dto;

import com.lulu.gamification.model.Mission;
import com.lulu.gamification.model.User;
import lombok.Data;

@Data
public class UserMissionDto {
    private User user;
    private Mission mission;
    private boolean isFinished = false;

}
