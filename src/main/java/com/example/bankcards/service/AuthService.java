package com.example.bankcards.service;

import com.example.bankcards.dto.request.auth.LoginRequest;
import com.example.bankcards.dto.request.auth.RegisterRequest;
import com.example.bankcards.dto.response.auth.LoginResponse;
import com.example.bankcards.entity.User;
import com.example.bankcards.exception.BadCredentialsException;
import com.example.bankcards.exception.UserAlreadyExistsException;
import com.example.bankcards.mapper.request.UserRequestMapper;
import com.example.bankcards.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService{
    private final UserRepository userRepository;
    private final UserRequestMapper userRequestMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public LoginResponse register(RegisterRequest request){
        if (userRepository.existsByUsername(request.getUsername())){
           throw new UserAlreadyExistsException(request.getUsername());
        }

        User user = userRequestMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);

        String token = jwtService.generateToken(savedUser);

        return LoginResponse.builder()
                .token(token)
                .userId(savedUser.getId())
                .username(savedUser.getUsername())
                .role(savedUser.getRole())
                .timestamp(java.time.LocalDateTime.now())
                .build();
    }

    public LoginResponse login(LoginRequest request){
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(BadCredentialsException::new);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new BadCredentialsException();
        }

        String token = jwtService.generateToken(user);

        return LoginResponse.builder()
                .token(token)
                .userId(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .timestamp(java.time.LocalDateTime.now())
                .build();
    }
}
