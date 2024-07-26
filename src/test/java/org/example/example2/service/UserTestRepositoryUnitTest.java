package org.example.example2.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.example.example2.entity.contents.UserTest;
import org.example.example2.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserTestRepositoryUnitTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@Test
	void testSaveAndFindById() {
		UserTest userTest = new UserTest("Test User2");

		when(userRepository.save(any(UserTest.class))).thenReturn(userTest);
		when(userRepository.findById(1L)).thenReturn(Optional.of(userTest));

		// Save user
		UserTest savedUserTest = userService.saveUser(userTest);

		// Find user by ID
		UserTest foundUserTest = userService.findUserById(1L);

		assertNotNull(foundUserTest, "User should not be null");
		assertEquals(savedUserTest.getId(), foundUserTest.getId());
		assertEquals(savedUserTest.getUsername(), foundUserTest.getUsername());
	}
}
