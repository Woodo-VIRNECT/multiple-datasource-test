package org.example.example2.service;

import org.example.example2.entity.contents.UserTest;
import org.example.example2.repository.UserRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public UserTest saveUser(UserTest userTest) {
		return userRepository.save(userTest);
	}

	public UserTest findUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
}
