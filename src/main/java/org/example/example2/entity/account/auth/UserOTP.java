package org.example.example2.entity.account.auth;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_otp",
	indexes = {
		@Index(
			name = "index_email",
			columnList="email"
		)
	})
@NoArgsConstructor
public class UserOTP {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_otp_id")
	Long id;
	@Transient
	List<Integer> scratchCodes;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "secret_key", nullable = false)
	private String secretKey;
	@Column(name = "validation_code", nullable = false)
	private int validationCode;
}
