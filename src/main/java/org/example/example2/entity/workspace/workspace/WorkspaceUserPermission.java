package org.example.example2.entity.workspace.workspace;


import java.time.LocalDateTime;
import java.util.List;

import org.example.example2.entity.workspace.TimeEntity;
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
@Table(name = "workspace_user_permission")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkspaceUserPermission extends TimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "workspace_user_permission_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workspace_role_id")
	private WorkspaceRole workspaceRole;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workspace_permission_id")
	private WorkspacePermission workspacePermission;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workspace_user")
	private WorkspaceUser workspaceUser;

	@Builder
	public WorkspaceUserPermission(WorkspaceRole workspaceRole, WorkspacePermission workspacePermission, WorkspaceUser workspaceUser) {
		this.workspaceRole = workspaceRole;
		this.workspacePermission = workspacePermission;
		this.workspaceUser = workspaceUser;
	}

	public boolean isMaster() {
		return workspaceRole.getRole().isMaster();
	}

	public boolean isNotMaster() {
		return !isMaster();
	}

	public boolean isManager() {
		return workspaceRole.getRole().isManager();
	}

	public boolean isNotManager() {
		return !isManager();
	}

	public boolean isWorkspaceManagementUser() {
		return isMaster() || isManager();
	}
}
