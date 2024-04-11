package com.harjoitustyo.movieapp;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.harjoitustyo.movieapp.domain.User;
import com.harjoitustyo.movieapp.repository.UserRepository;
import com.harjoitustyo.movieapp.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
    }

    @Test
    void registerNewUserAccount_ShouldCreateNewUser_WhenRegistrationIsSuccessful() {
        // Mock the behavior of the password encoder to return a hashed string
        when(passwordEncoder.encode(user.getPassword())).thenReturn("hashedPassword");
        
        // Mock the userRepository to return the user when save is called
        when(userRepository.save(any(User.class))).thenReturn(user);
        
        // Call the method to test
        User createdUser = userService.registerNewUserAccount(user);
        
        // Assert the results
        assertThat(createdUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(createdUser.getPassword()).isEqualTo("hashedPassword");
        // You can add more assertions if you need to verify other user properties or behaviors
    }
}
