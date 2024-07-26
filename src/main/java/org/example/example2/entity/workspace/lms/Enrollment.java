package com.virnect.workspace.domain.lms;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.virnect.workspace.domain.TimeEntity;
import com.virnect.workspace.domain.lms.enums.LearningStatus;

@Entity
@Builder
@Getter
@Table(name = "enrollment", indexes = @Index(name = "idx_learner_id", columnList = "learner_id"))
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Enrollment extends TimeEntity {
	@Id
	@Column(name = "enrollment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "learner_id")
	private Learner learner;

	@Column(name = "sequence", nullable = false)
	private Integer sequence;

	@Column(name = "start_date", nullable = false)
	private LocalDateTime startDate;

	@Column(name = "end_date")
	private LocalDateTime endDate;

	@Column(name = "score")
	private Integer score;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	public static Enrollment create(Learner learner, int sequence) {
		return Enrollment.builder()
			.learner(learner)
			.sequence(sequence)
			.startDate(LocalDateTime.now())
			.isDeleted(false)
			.build();
	}

	public boolean isSubmitted() {
		if (this.learner.getCurriculum().isEnabledQuiz()) {
			return this.score != null;
		}
		return endDate != null;
	}

	public void updateScore(int score) {
		this.score = score;
	}

	public void complete() {
		updateEndDate(LocalDateTime.now());
		this.learner.updateLearningStatus(LearningStatus.COMPLETED);
	}

	public boolean hasEndDate() {
		return endDate != null;
	}

	public void updateEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
}
