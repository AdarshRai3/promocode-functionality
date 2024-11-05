package com.promocode_functionality.promocode_functionality.services;

import com.promocode_functionality.promocode_functionality.dtos.UserRequest;
import com.promocode_functionality.promocode_functionality.dtos.UserResponse;
import com.promocode_functionality.promocode_functionality.entities.Users;
import com.promocode_functionality.promocode_functionality.exceptions.UserNotFoundException;
import com.promocode_functionality.promocode_functionality.repositories.UsersRespository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class UsersService {

    @Autowired
    private UsersRespository usersRepository;

    public UserResponse createUser (@Valid UserRequest userRequest) {
        Users user = new Users();
        user.setUser_name(userRequest.getUser_name());
        user.setUser_email(userRequest.getUser_email());
        user.setPassword(userRequest.getPassword());
        user.setNotification_preferences(userRequest.getNotification_preferences());

        Users savedUser = usersRepository.save(user);
        return mapToResponse(savedUser);
    }

    public List<UserResponse> getAllUsers() {
        return usersRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
        return mapToResponse(user);
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        user.setUser_name(userRequest.getUser_name());
        user.setUser_email(userRequest.getUser_email());
        user.setPassword(userRequest.getPassword());
        user.setNotification_preferences(userRequest.getNotification_preferences());

        Users updatedUser = usersRepository.save(user);
        return mapToResponse(updatedUser);
    }

    public void deleteUser(Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
        usersRepository.delete(user);
    }

    private UserResponse mapToResponse(Users user) {
        return new UserResponse(
                user.getUser_id(),
                user.getUser_name(),
                user.getUser_email(),
                user.getNotification_preferences()
        );
    }
}
