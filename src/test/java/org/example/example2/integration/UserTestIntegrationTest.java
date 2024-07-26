package org.example.example2.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.example.example2.entity.contents.User;
import org.example.example2.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("local")
@Transactional
class UserIntegrationTest {
	@Autowired
	private UserRepository userRepository;

	@Value("${spring.datasource.db-type}")
	private String dbType;

	@Autowired
	private DataSource dataSource;

	@Test
	void testSaveAndFindById() {
		User user = new User("Test User");

		// Save user
		User savedUser = userRepository.save(user);

		// Find user by ID
		User foundUser = userRepository.findById(savedUser.getId()).orElse(null);

		assertEquals(savedUser.getId(), foundUser.getId());
		assertEquals(savedUser.getUsername(), foundUser.getUsername());

		// Verify the database connection
		verifyDatabaseConnection();
	}

	private void verifyDatabaseConnection() {
		try (Connection connection = dataSource.getConnection()) {
			DatabaseMetaData metaData = connection.getMetaData();
			String dbUrl = metaData.getURL();

			if ("mysql".equalsIgnoreCase(dbType)) {
				assertTrue(dbUrl.contains("mysql"), "Should connect to MySQL");
			} else if ("oracle".equalsIgnoreCase(dbType)) {
				assertTrue(dbUrl.contains("oracle"), "Should connect to Oracle");
			} else {
				fail("Unknown dbType: " + dbType);
			}
		} catch (SQLException e) {
			fail("Failed to get database connection: " + e.getMessage());
		}
	}
}