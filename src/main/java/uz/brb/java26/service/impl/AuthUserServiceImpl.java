package uz.brb.java26.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import uz.brb.java26.config.CustomUserDetailsService;
import uz.brb.java26.dto.request.LoginRequest;
import uz.brb.java26.dto.request.RegisterRequest;
import uz.brb.java26.dto.response.Response;
import uz.brb.java26.entity.AuthUser;
import uz.brb.java26.enums.ActionType;
import uz.brb.java26.enums.EntityType;
import uz.brb.java26.enums.Role;
import uz.brb.java26.exception.ResourceNotFoundException;
import uz.brb.java26.repository.AuthUserRepository;
import uz.brb.java26.service.logic.AuditService;
import uz.brb.java26.service.AuthUserService;
import uz.brb.java26.util.JWTUtil;
import uz.brb.java26.util.RequestUtils;

import java.time.LocalDateTime;
import java.util.Optional;

import static uz.brb.java26.util.PasswordHasher.hashPassword;
import static uz.brb.java26.util.PasswordValidator.validatePassword;
import static uz.brb.java26.util.Util.localDateTimeFormatter;

@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService {
    private final JWTUtil jwtUtil;
    private final AuthUserRepository authUserRepository;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final AuditService auditService;
    private final RequestUtils requestUtils;

    @Override
    public Response<?> register(RegisterRequest registerRequest) {
        Optional<AuthUser> byUsername = authUserRepository.findByUsername(registerRequest.getUsername());
        if (byUsername.isPresent()) {
            return Response.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .status(HttpStatus.BAD_REQUEST)
                    .success(false)
                    .message("Username already exists")
                    .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                    .build();
        }
        AuthUser authUser = new AuthUser();
        authUser.setFullName(registerRequest.getFullName());
        authUser.setUsername(registerRequest.getUsername());
        authUser.setPassword(hashPassword(registerRequest.getPassword()));
        authUser.setRole(Role.USER);
        authUserRepository.save(authUser);
        return Response.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .success(true)
                .message("AuthUser successfully register")
                .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                .build();
    }

    @Override
    public Response<?> login(LoginRequest loginRequest) {
        AuthUser authUser = authUserRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("AuthUser not found by username: " + loginRequest.getUsername()));
        if (authUser.getUsername() == null) {
            return Response.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .status(HttpStatus.NOT_FOUND)
                    .success(false)
                    .message("Username not found")
                    .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                    .build();
        }
        if (!validatePassword(loginRequest.getPassword(), authUser.getPassword())) {
            return Response.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .status(HttpStatus.BAD_REQUEST)
                    .success(false)
                    .message("Invalid password")
                    .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                    .build();
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());
        String jwtToken = jwtUtil.generateToken(userDetails.getUsername());

        auditService.log(
                ActionType.LOGIN_SUCCESS,
                EntityType.AUTH_USER,
                authUser.getId(),
                null,
                authUser,
                authUser.getUsername(),
                "User login successfully",
                requestUtils.getClientIp()
        );

        return Response.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .success(true)
                .message(jwtToken)
                .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                .build();
    }

    @Override
    public Response<?> me(AuthUser authUser) {
        auditService.log(
                ActionType.FOUND,
                EntityType.AUTH_USER,
                authUser.getId(),
                null,
                authUser,
                authUser.getUsername(),
                "User found successfully",
                requestUtils.getClientIp()
        );

        return Response.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .success(true)
                .message("Current User")
                .data(authUser)
                .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                .build();
    }
}