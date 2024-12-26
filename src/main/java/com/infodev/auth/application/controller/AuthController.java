package com.infodev.auth.application.controller;

import com.infodev.auth.application.presentation.dto.user.LoginRequestTO;
import com.infodev.auth.application.presentation.dto.user.RegisterRequestTO;
import com.infodev.auth.application.service.UserService;
import com.infodev.auth.application.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequestTO loginRequestTO) {
        userService.authenticate(loginRequestTO.getEmail(), loginRequestTO.getPassword());
        String token = jwtUtil.generateToken(loginRequestTO.getEmail());
        Map<String, String> response = new HashMap<>();
        response.put("message", "Login com sucesso!");
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequestTO registerRequestTO) {
        userService.register(registerRequestTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
