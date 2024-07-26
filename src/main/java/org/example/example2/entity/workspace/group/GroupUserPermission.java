package org.example.example2.entity.workspace.group;


import org.example.example2.entity.workspace.TimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Project: PF-Workspace
 * DATE: 2020-01-08
 * AUTHOR: JohnMark (Chang Jeong Hyeon)
 * EMAIL: practice1356@gmail.com
 * DESCRIPTION:
 */
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "group_user_permission")
public class GroupUserPermission extends TimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_user_permission_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_role_id")
	private GroupRole groupRole;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_permission_id")
	private GroupPermission groupPermission;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_user_id")
	private GroupUser groupUser;

	@Builder
	public GroupUserPermission(GroupRole groupRole, GroupPermission groupPermission, GroupUser groupUser) {
		this.groupRole = groupRole;
		this.groupPermission = groupPermission;
		this.groupUser = groupUser;
	}
}
