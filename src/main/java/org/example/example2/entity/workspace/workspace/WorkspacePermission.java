package org.example.example2.entity.workspace.workspace;


import org.example.example2.entity.workspace.TimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AccessLevel;
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
@Table(name = "workspace_permission")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkspacePermission extends TimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "workspace_permission_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "permission", nullable = false)
	private Permission permission;

	@Lob
	@Column(name = "description")
	private String description;
}
