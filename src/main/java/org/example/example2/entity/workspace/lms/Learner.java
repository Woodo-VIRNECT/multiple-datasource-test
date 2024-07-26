package org.example.example2.entity.workspace.lms;

import org.example.example2.entity.workspace.TimeEntity;
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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "learner",
	indexes = {
		@Index(name = "idx_learner_curriculum_id", columnList = "curriculum_id"),
		@Index(name = "idx_learner_user_uuid", columnList = "user_uuid")
	})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Learner extends TimeEntity {
	@Id
	@Column(name = "learner_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curriculum_id")
	private Curriculum curriculum;

	@Column(name = "user_uuid", nullable = false)
	private String userUUID;

	@Enumerated(EnumType.STRING)
	@Column(name = "learning_status", length = 20, nullable = false)
	private LearningStatus learningStatus;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Builder
	public Learner(Long id, Curriculum curriculum, String userUUID, LearningStatus learningStatus, boolean isDeleted) {
		this.id = id;
		this.curriculum = curriculum;
		this.userUUID = userUUID;
		this.learningStatus = learningStatus;
		this.isDeleted = isDeleted;
	}
}
