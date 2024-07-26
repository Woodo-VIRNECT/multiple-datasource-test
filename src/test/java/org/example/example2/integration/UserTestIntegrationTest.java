package org.example.example2.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.example.example2.entity.contents.UserTest;
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
class UserTestIntegrationTest {
	@Autowired
	private UserRepository userRepository;

	@Value("${spring.datasource.db-type}")
	private String dbType;

	@Autowired
	private DataSource dataSource;

	@Test
	void testSaveAndFindById() {
		UserTest userTest = new UserTest("Test User");

		// Save user
		UserTest savedUserTest = userRepository.save(userTest);

		// Find user by ID
		UserTest foundUserTest = userRepository.findById(savedUserTest.getId()).orElse(null);

		assertEquals(savedUserTest.getId(), foundUserTest.getId());
		assertEquals(savedUserTest.getUsername(), foundUserTest.getUsername());

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