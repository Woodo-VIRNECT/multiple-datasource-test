package org.example.example2.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.example.example2.entity.contents.User;
import org.example.example2.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserRepositoryUnitTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@Test
	void testSaveAndFindById() {
		User user = new User("Test User2");

		when(userRepository.save(any(User.class))).thenReturn(user);
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));

		// Save user
		User savedUser = userService.saveUser(user);

		// Find user by ID
		User foundUser = userService.findUserById(1L);

		assertNotNull(foundUser, "User should not be null");
		assertEquals(savedUser.getId(), foundUser.getId());
		assertEquals(savedUser.getUsername(), foundUser.getUsername());
	}
}
