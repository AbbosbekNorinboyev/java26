package uz.brb.java26.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.brb.java26.dto.request.LoginRequest;
import uz.brb.java26.dto.request.RegisterRequest;
import uz.brb.java26.dto.response.Response;
import uz.brb.java26.entity.AuthUser;
import uz.brb.java26.service.AuthUserService;
import uz.brb.java26.util.validator.CurrentUser;

@RestController
@RequestMapping("/api/auths")
@RequiredArgsConstructor
public class AuthUserController {
    private final AuthUserService authUserService;

    @PostMapping("/register")
    public Response<?> register(@RequestBody RegisterRequest registerRequest) {
        return authUserService.register(registerRequest);
    }

    @PostMapping("/login")
    public Response<?> login(@RequestBody LoginRequest loginRequest) {
        return authUserService.login(loginRequest);
    }

    @GetMapping("/me")
    public Response<?> me(@CurrentUser AuthUser authUser) {
        return authUserService.me(authUser);
    }

    @GetMapping("/name")
    Response<?> getByUsernameOrFullName(String name) {
        return authUserService.getByUsernameOrFullName(name);
    }
}