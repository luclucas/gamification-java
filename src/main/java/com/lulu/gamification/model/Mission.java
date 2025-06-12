package com.lulu.gamification.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Schema(description = "Entidade que representa uma missão no sistema")
@Table(name = "missions")

public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private Integer xpPointsReward;
}
