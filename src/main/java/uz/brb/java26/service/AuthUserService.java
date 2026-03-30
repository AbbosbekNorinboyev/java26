package uz.brb.java26.service;

import uz.brb.java26.dto.request.LoginRequest;
import uz.brb.java26.dto.request.RegisterRequest;
import uz.brb.java26.dto.response.Response;
import uz.brb.java26.entity.AuthUser;

public interface AuthUserService {
    Response<?> register(RegisterRequest registerRequest);

    Response<?> login(LoginRequest loginRequest);

    Response<?> me(AuthUser authUser);
}