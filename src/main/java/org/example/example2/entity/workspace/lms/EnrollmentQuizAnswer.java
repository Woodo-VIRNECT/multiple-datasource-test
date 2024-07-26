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
