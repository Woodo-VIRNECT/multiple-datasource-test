package org.example.example2.entity.account.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_permission")
public class UserPermission extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_permission_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_user_id")
	private AccountUser accountUser;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "permission_id")
	private Permission permission;

	@Builder
	public UserPermission(AccountUser accountUser, Permission permission) {
		this.accountUser = accountUser;
		this.permission = permission;
	}
}
