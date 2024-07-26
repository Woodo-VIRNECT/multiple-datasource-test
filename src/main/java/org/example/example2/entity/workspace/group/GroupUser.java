package org.example.example2.entity.workspace.group;


import java.util.ArrayList;
import java.util.List;

import org.example.example2.entity.workspace.TimeEntity;
import org.example.example2.entity.workspace.workspace.WorkspaceUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "group_user")
public class GroupUser extends TimeEntity {
	@OneToMany(mappedBy = "groupUser")
	List<GroupUserPermission> permissions = new ArrayList<>();
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_user_id")
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(name = "favorite")
	private Favorite favorite;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	private Group group;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workspace_user_id")
	private WorkspaceUser workspaceUser;

	@Builder
	public GroupUser(Favorite favorite, Group group, WorkspaceUser workspaceUser) {
		this.favorite = favorite;
		this.group = group;
		this.workspaceUser = workspaceUser;
	}
}
