package org.example.example2.entity.account.user;

import java.time.LocalDateTime;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "secession_user")
public class SecessionUser extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "secession_user_id")
	private Long id;

	@Column(name = "user_uuid", nullable = false)
	private String userUUID;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "nickname", nullable = false)
	private String nickName;

	@Lob
	@Column(name = "reason", nullable = false)
	private String reason;

	@Column(name = "joined_at", nullable = false)
	private LocalDateTime joinDate;

	@Column(name = "service_info", nullable = false)
	private String serviceInfo;

	@Column(name = "join_info", nullable = false)
	private String joinInfo;

	@Column(name = "service_day", nullable = false)
	private Long serviceDay;

	@Column(name = "secessioned_at", nullable = false)
	private LocalDateTime secessionDate;

	@Builder
	public SecessionUser(
		String userUUID, String email, String reason, String nickName,
		LocalDateTime joinDate, String joinInfo, String serviceInfo,
		long serviceDay, LocalDateTime secessionDate, long userId
	) {
		this.userUUID = userUUID;
		this.userId = userId;
		this.email = email;
		this.nickName = nickName;
		this.reason = reason;
		this.joinDate = joinDate;
		this.joinInfo = joinInfo;
		this.serviceInfo = serviceInfo;
		this.serviceDay = serviceDay;
		this.secessionDate = secessionDate;
	}

	public static SecessionUser of(AccountUser accountUser, String reason) {
		LocalDateTime secessionDate = LocalDateTime.now();
		long serviceDays = Period.between(accountUser.getCreatedDate().toLocalDate(), secessionDate.toLocalDate()).getDays();

		return new SecessionUser(
			accountUser.getUuid(),
			accountUser.getEmail(),
			reason,
			accountUser.getNickname(),
			accountUser.getCreatedDate(),
			accountUser.getJoinInfo(),
			accountUser.getServiceInfo(),
			serviceDays,
			secessionDate,
			accountUser.getId()
		);
	}

	@Override
	public String toString() {
		return "SecessionUser{" +
			"id=" + id +
			", userUUID='" + userUUID + '\'' +
			", userId=" + userId +
			", email='" + email + '\'' +
			", nickName='" + nickName + '\'' +
			", reason='" + reason + '\'' +
			", joinDate=" + joinDate +
			", serviceInfo='" + serviceInfo + '\'' +
			", joinInfo='" + joinInfo + '\'' +
			", serviceDay=" + serviceDay +
			", secessionDate=" + secessionDate +
			'}';
	}
}
