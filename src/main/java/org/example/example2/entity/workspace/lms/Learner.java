package com.virnect.workspace.domain.lms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.virnect.workspace.domain.TimeEntity;
import com.virnect.workspace.domain.lms.enums.LearningStatus;

@Audited
@Entity
@Getter
@Table(name = "learner",
	indexes = {
		@Index(name = "idx_curriculum_id", columnList = "curriculum_id"),
		@Index(name = "idx_user_uuid", columnList = "user_uuid")
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

	public static Learner create(Curriculum curriculum, String userUUID) {
		return Learner.builder()
			.curriculum(curriculum)
			.userUUID(userUUID)
			.learningStatus(curriculum.isBeforeDeadline() ? LearningStatus.UNSTARTED : LearningStatus.INCOMPLETE)
			.isDeleted(false)
			.build();
	}

	public void delete() {
		this.isDeleted = true;
	}

	public void updateLearningStatus(LearningStatus learningStatus) {
		if (!isCompleted()) {
			this.learningStatus = learningStatus;
		}
	}

	public boolean isCompleted() {
		return this.learningStatus.isCompleted();
	}

	public boolean isUnstarted() {
		return this.learningStatus.isUnstarted();
	}

	public boolean isInProgress() {
		return this.learningStatus.isInProgress();
	}
}
