package com.example.demo;

import com.example.demo.dto.UserResponseDTO;
import com.example.demo.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

//    @Test
//    void testGetAllUsers() {
//        // Arrange
//        Pageable pageable = PageRequest.of(0, 10);
//        User user = new User("John Doe", "john.doe@example.com");
//        Page<User> userPage = new PageImpl<>(Collections.singletonList(user), pageable, 1);
//        when(userRepository.findAll(pageable)).thenReturn(userPage);
//
//        // Act
//        Page<UserResponseDTO> result = userService.getAllUsers(pageable);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(1, result.getTotalElements());
//        verify(userRepository, times(1)).findAll(pageable);
//    }

    @Test
    void testGetUserById() {
        // Arrange
        Long userId = 1L;
        User user = new User("John Doe", "john.doe@example.com");
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        UserResponseDTO result = userService.getUserById(userId);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getId());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetUserById_NotFound() {
        // Arrange
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(userId));
        verify(userRepository, times(1)).findById(userId);
    }
}