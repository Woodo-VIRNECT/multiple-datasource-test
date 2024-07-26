package org.example.example2.entity.workspace.lms;


import java.time.LocalDateTime;
import java.util.List;

import org.example.example2.entity.workspace.TimeEntity;
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
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "curriculum_constructor",
	indexes = {
		@Index(name = "idx_curriculum_constructor_user_uuid", columnList = "user_uuid"),
		@Index(name = "idx_curriculum_constructor_workspace_uuid", columnList = "workspace_uuid")
	})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CurriculumConstructor extends TimeEntity {
	@Id
	@Column(name = "curriculum_constructor_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_uuid", nullable = false)
	private String userUUID;

	@Column(name = "workspace_uuid", nullable = false)
	private String workspaceUUID;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Builder
	public CurriculumConstructor(Long id, String userUUID, String workspaceUUID, boolean isDeleted) {
		this.id = id;
		this.userUUID = userUUID;
		this.workspaceUUID = workspaceUUID;
		this.isDeleted = isDeleted;
	}

	public static CurriculumConstructor create(String userUUID, String workspaceUUID) {
		return CurriculumConstructor.builder()
			.userUUID(userUUID)
			.workspaceUUID(workspaceUUID)
			.isDeleted(false)
			.build();
	}

	public void delete() {
		this.isDeleted = true;
	}
}
