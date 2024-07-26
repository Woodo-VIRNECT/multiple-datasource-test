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

@Entity
@Getter
@Table(name = "curriculum_editor",
	indexes = {
		@Index(name = "idx_curriculum_editor_curriculum_id", columnList = "curriculum_id"),
		@Index(name = "idx_curriculum_editor_user_uuid", columnList = "user_uuid")
	})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CurriculumEditor extends TimeEntity {
	@Id
	@Column(name = "curriculum_editor_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curriculum_id")
	private Curriculum curriculum;

	@Column(name = "user_uuid", nullable = false)
	private String userUUID;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Builder
	public CurriculumEditor(Long id, Curriculum curriculum, String userUUID, boolean isDeleted) {
		this.id = id;
		this.curriculum = curriculum;
		this.userUUID = userUUID;
		this.isDeleted = isDeleted;
	}

	public static CurriculumEditor create(Curriculum curriculum, String userUUID) {
		return CurriculumEditor.builder()
			.curriculum(curriculum)
			.userUUID(userUUID)
			.isDeleted(false)
			.build();
	}

	public void delete() {
		this.isDeleted = true;
	}
}
