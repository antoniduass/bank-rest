package com.example.bankcards.service;

import com.example.bankcards.dto.response.user.UserResponse;
import com.example.bankcards.entity.User;
import com.example.bankcards.mapper.request.UserRequestMapper;
import com.example.bankcards.mapper.response.UserResponseMapper;
import com.example.bankcards.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse getUserById(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return userResponseMapper.toResponse(user);
    }

    public Page<UserResponse> getAllUsers(Pageable pageable){
        return userRepository.findAll(pageable)
                .map(userResponseMapper::toResponse);
    }

    @Transactional
    public void deleteUser(Long userId){
        if (!userRepository.existsById(userId)){
            throw new UserNotFoundException(userId);
        }
        userRepository.deleteById(userId);
    }

    public boolean userExists(Long userId){
        return userRepository.existsById(userId);
    }
}
