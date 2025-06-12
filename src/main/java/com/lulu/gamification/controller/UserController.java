package com.lulu.gamification.controller;

import com.lulu.gamification.dto.UserDto;
import com.lulu.gamification.model.User;
import com.lulu.gamification.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "Users related operations") // Adicione esta linha
public class UserController {

    private final UserService userService;


    @GetMapping("/{id}")
    public User getById(@PathParam("id") Long userId){
        return userService.getById(userId);
    }

    @Operation(summary = "Criar usuário", description = "Endpoint para criação de um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());

        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }


    @Operation(summary = "Concluir missao do usuário", description = "Endpoint para concluir a missão de um usuário")
    @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    @PostMapping("/missions")
    public ResponseEntity<User> finishMission(Long userId, Long missionId) {
        User updatedUser = userService.finishMission(userId, missionId);

        return ResponseEntity.ok(updatedUser);
    }

}
