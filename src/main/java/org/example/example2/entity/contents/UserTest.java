package org.example.example2.entity.contents;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "users_test")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 30, unique = true)
	private String username;


// 	 create method
	public UserTest(String username) {
		this.username = username;
	}
}