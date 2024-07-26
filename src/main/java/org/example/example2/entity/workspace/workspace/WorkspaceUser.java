package org.example.example2.entity.workspace.workspace;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.example.example2.entity.workspace.TimeEntity;
import org.example.example2.entity.workspace.group.GroupUser;
import org.example.example2.entity.workspace.lms.enums.CurriculumProgressStatus;
import org.example.example2.entity.workspace.lms.enums.LearningStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
@Table(name = "workspace_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkspaceUser extends TimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "workspace_user_id")
	private Long id;

	@Column(name = "user_id", nullable = false)
	private String userId; // user uuid

	@ManyToOne(targetEntity = Workspace.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "workspace_id")
	private Workspace workspace;

	@OneToMany(mappedBy = "workspaceUser")
	private List<GroupUser> groupUserList = new ArrayList<>();

	@Builder
	public WorkspaceUser(String userId, Workspace workspace) {
		this.userId = userId;
		this.workspace = workspace;
	}
}
