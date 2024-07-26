package com.virnect.workspace.domain.lms;

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

@Entity
@Getter
@Table(name = "enrollment_quiz_answer",
	indexes = {
		@Index(name = "idx_enrollment_quiz_id", columnList = "enrollment_quiz_id"),
		@Index(name = "idx_quiz_option_id", columnList = "quiz_option_id")
	})
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnrollmentQuizAnswer extends TimeEntity {
	@Id
	@Column(name = "enrollment_quiz_answer_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "enrollment_quiz_id")
	private EnrollmentQuiz enrollmentQuiz;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quiz_option_id")
	private QuizOption quizOption;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	public static EnrollmentQuizAnswer create(QuizOption quizOption) {
		return EnrollmentQuizAnswer.builder()
			.quizOption(quizOption)
			.isDeleted(false)
			.build();
	}

	public void setEnrollmentQuiz(EnrollmentQuiz enrollmentQuiz) {
		this.enrollmentQuiz = enrollmentQuiz;
	}
}
