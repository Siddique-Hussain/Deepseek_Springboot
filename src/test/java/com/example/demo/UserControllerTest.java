package com.example.demo;

import com.example.demo.dto.UserResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

//    @Test
//    void testGetAllUsers() {
//        // Arrange
//        Pageable pageable = PageRequest.of(0, 10);
//        UserResponseDTO userResponseDTO = new UserResponseDTO();
//        userResponseDTO.setId(1L);
//        userResponseDTO.setName("John Doe");
//        userResponseDTO.setEmail("john.doe@example.com");
//        Page<UserResponseDTO> userPage = new PageImpl<>(Collections.singletonList(userResponseDTO), pageable, 1);
//        when(userService.getAllUsers(pageable)).thenReturn(userPage);
//
//        // Act
//        ResponseEntity<Page<UserResponseDTO>> response = userController.getAllUsers(0, 10, "id", "asc");
//
//        // Assert
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(1, response.getBody().getTotalElements());
//        verify(userService, times(1)).getAllUsers(pageable);
//    }

    @Test
    void testGetUserById() {
        // Arrange
        Long userId = 1L;
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userId);
        userResponseDTO.setName("John Doe");
        userResponseDTO.setEmail("john.doe@example.com");
        when(userService.getUserById(userId)).thenReturn(userResponseDTO);

        // Act
        UserResponseDTO response = userController.getUserById(userId);

        // Assert
        assertNotNull(response);
        //assertEquals(HttpStatus.OK, response.getStatusCode());
        //assertEquals(userId, response.getBody().getId());
        verify(userService, times(1)).getUserById(userId);
    }
}