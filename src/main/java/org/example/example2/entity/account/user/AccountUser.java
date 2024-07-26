package org.example.example2.entity.account.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Project: user
 * DATE: 2020-01-07
 * AUTHOR: JohnMark (Chang Jeong Hyeon)
 * EMAIL: practice1356@gmail.com
 * DESCRIPTION:
 */
@Entity
@Table(name = "account_user")
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "uuid"}, callSuper = false)
@NoArgsConstructor
public class AccountUser extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_user_id", nullable = false)
	private Long id;

	@Column(name = "uuid", nullable = false, unique = true)
	private String uuid;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "birth", nullable = false)
	private LocalDate birth;

	@Column(name = "description")
	private String description;

	@Column(name = "profile", nullable = false)
	private String profile;

	@Column(name = "service_info", nullable = false)
	private String serviceInfo;

	@Column(name = "join_info", nullable = false)
	private String joinInfo;

	@Column(name = "user_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserType userType;

	@Column(name = "login_lock", nullable = false)
	@Enumerated(EnumType.STRING)
	private LoginStatus loginLock = LoginStatus.INACTIVE;

	@Column(name = "market_info_receive", nullable = false)
	@Enumerated(EnumType.STRING)
	private AcceptOrReject marketInfoReceive = AcceptOrReject.REJECT;

	@Column(name = "language", nullable = false)
	@Enumerated(EnumType.STRING)
	private Language language;

	@Column(name = "international_number")
	private String internationalNumber;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "recovery_email")
	private String recoveryEmail;

	@Column(name = "question")
	private String question;

	@Column(name = "answer")
	private String answer;

	@Column(name = "password_update_at")
	private LocalDateTime passwordUpdateDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "master_user")
	private AccountUser master;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "master")
	private List<AccountUser> seatAccountUsers;

	@Column(name = "account_non_expired")
	private boolean accountNonExpired = true;

	@Column(name = "account_non_locked")
	private boolean accountNonLocked = true;

	@Column(name = "credentials_non_expired")
	private boolean credentialsNonExpired = true;

	@Column(name = "enabled")
	private boolean enabled = true;

	@Column(name = "account_password_initialized")
	private boolean accountPasswordInitialized;

	@OneToMany(mappedBy = "accountUser", fetch = FetchType.LAZY)
	private List<UserPermission> userPermissionList = new ArrayList<>();

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", uuid='" + uuid + '\'' +
			", name='" + name + '\'' +
			", firstName='" + firstName + '\'' +
			", lastName='" + lastName + '\'' +
			", nickname='" + nickname + '\'' +
			", email='" + email + '\'' +
			", password='" + password + '\'' +
			", birth=" + birth +
			", description='" + description + '\'' +
			", profile='" + profile + '\'' +
			", serviceInfo='" + serviceInfo + '\'' +
			", joinInfo='" + joinInfo + '\'' +
			", userType=" + userType +
			", loginLock=" + loginLock +
			", marketInfoReceive=" + marketInfoReceive +
			", language=" + language +
			", internationalNumber='" + internationalNumber + '\'' +
			", mobile='" + mobile + '\'' +
			", recoveryEmail='" + recoveryEmail + '\'' +
			", question='" + question + '\'' +
			", answer='" + answer + '\'' +
			", passwordUpdateDate=" + passwordUpdateDate +
			", accountNonExpired=" + accountNonExpired +
			", accountNonLocked=" + accountNonLocked +
			", credentialsNonExpired=" + credentialsNonExpired +
			", enabled=" + enabled +
			", accountPasswordInitialized=" + accountPasswordInitialized +
			'}';
	}

	public void updatePassword(String password) {
		this.password = password;
	}
}
