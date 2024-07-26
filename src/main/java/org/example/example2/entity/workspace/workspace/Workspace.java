package org.example.example2.entity.workspace.workspace;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.example.example2.entity.workspace.TimeEntity;
import org.example.example2.entity.workspace.group.Group;
import org.example.example2.entity.workspace.lms.enums.CurriculumProgressStatus;
import org.example.example2.entity.workspace.lms.enums.LearningStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "workspace")
public class Workspace extends TimeEntity {
	@OneToMany(mappedBy = "workspace")
	List<Group> groupList = new ArrayList<>();

	@OneToMany(mappedBy = "workspace")
	List<WorkspaceUser> workspaceUserList = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "workspace_id")
	private Long id;

	@Column(name = "uuid", nullable = false, unique = true)
	private String uuid;

	@Column(name = "pin_number", length = 6, nullable = false, unique = true)
	private String pinNumber;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "profile", nullable = false)
	private String profile;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "user_id", nullable = false)
	private String userId; // user uuid

	@Builder
	public Workspace(String uuid, String pinNumber, String name, String profile, String description, String userId) {
		this.uuid = uuid;
		this.name = name;
		this.profile = profile;
		this.pinNumber = pinNumber;
		this.description = description;
		this.userId = userId;
	}
}
