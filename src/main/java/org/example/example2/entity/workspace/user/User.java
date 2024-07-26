package org.example.example2.entity.workspace.user;

import org.example.example2.entity.workspace.workspace.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * 해당 테이블의 데이터는 users 스키마(PF-Account 서버)의 users 테이블에서 변경 사항을 감지하는 트리거에 의해 자동 동기화
 * */
@Entity
@Getter
@Table(name = "users",
	indexes = {
		@Index(name = "idx_user_id", columnList = "user_id"),
		@Index(name = "idx_users_uuid", columnList = "uuid")
	})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "uuid", nullable = false)
	private String uuid;

	@Column(name = "master_user_uuid")
	private String masterUserUUID;

	@Column(name = "name")
	private String name;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "email")
	private String email;

	@Column(name = "profile")
	private String profile;

	@Column(name = "user_type")
	@Enumerated(EnumType.STRING)
	private UserType userType;

	@Column(name = "is_deleted")
	private boolean isDeleted;
}
