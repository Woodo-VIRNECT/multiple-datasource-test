package com.virnect.workspace.domain.lms;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.virnect.workspace.domain.TimeEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "enrollment_quiz",
	indexes = {
		@Index(name = "idx_enrollment_id", columnList = "enrollment_id"),
		@Index(name = "idx_quiz_id", columnList = "quiz_id"),
	})
public class EnrollmentQuiz extends TimeEntity {
	@Id
	@Column(name = "enrollment_quiz_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "enrollment_id")
	private Enrollment enrollment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quiz_id")
	private Quiz quiz;

	@OneToMany(mappedBy = "enrollmentQuiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Builder.Default
	private List<EnrollmentQuizAnswer> enrollmentQuizAnswers = new ArrayList<>();

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	public static EnrollmentQuiz create(Enrollment enrollment, Quiz quiz) {
		return EnrollmentQuiz.builder()
			.enrollment(enrollment)
			.quiz(quiz)
			.isDeleted(false)
			.build();
	}

	public void addEnrollmentQuizAnswer(EnrollmentQuizAnswer enrollmentQuizAnswer) {
		this.enrollmentQuizAnswers.add(enrollmentQuizAnswer);
		enrollmentQuizAnswer.setEnrollmentQuiz(this);
	}

	public boolean hasAnswers() {
		return enrollmentQuizAnswers != null && !enrollmentQuizAnswers.isEmpty();
	}
}
