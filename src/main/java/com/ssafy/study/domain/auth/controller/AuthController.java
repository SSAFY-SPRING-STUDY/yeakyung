package com.ssafy.study.domain.auth.controller;

import com.ssafy.study.domain.auth.controller.dto.LoginRequest;
import com.ssafy.study.domain.auth.controller.dto.LoginResponse;
import com.ssafy.study.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.ssafy.study.domain.auth.util.AuthTokenUtils.parseBearerToken;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestHeader("Authorization") String bearerToken) {
        String token = parseBearerToken(bearerToken);
        authService.logout(token);
    }

}
