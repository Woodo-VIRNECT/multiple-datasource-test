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
@Getter
@Entity
@Table(name = "curriculum",
	indexes = {
		@Index(name = "idx_workspace_uuid", columnList = "workspace_uuid"),
		@Index(name = "idx_start_date_time", columnList = "start_date_time"),
		@Index(name = "idx_end_date_time", columnList = "end_date_time")
	})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Curriculum extends TimeEntity {
	@Id
	@Column(name = "curriculum_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "workspace_uuid", nullable = false)
	private String workspaceUUID;

	@Column(name = "registrar_id", nullable = false)
	private String registrarId;

	@Column(name = "modifier_id", nullable = false)
	private String modifierId;

	@Column(name = "title", length = 30, nullable = false)
	private String title;

	@Column(name = "description", length = 1000)
	private String description;

	@Column(name = "start_date_time")
	private LocalDateTime startDateTime;

	@Column(name = "end_date_time")
	private LocalDateTime endDateTime;

	@Column(name = "is_public", nullable = false)
	private boolean isPublic;

	@Enumerated(EnumType.STRING)
	@Column(name = "progress_status", length = 20, nullable = false)
	private CurriculumProgressStatus progressStatus;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Column(name = "enabled_quiz", nullable = false)
	private boolean enabledQuiz;

}
