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
@Getter
@Builder
@Entity
@Table(name = "quiz_option", indexes = @Index(name = "idx_quiz_option_quiz_id", columnList = "quiz_id"))
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuizOption extends TimeEntity {
	@Id
	@Column(name = "quiz_option_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quiz_id")
	private Quiz quiz;

	@Column(name = "content", length = 100, nullable = false)
	private String content;

	@Column(name = "is_answer", nullable = false)
	private boolean isAnswer;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	public static QuizOption create(Quiz quiz, String content, boolean isAnswer) {
		return QuizOption.builder()
			.quiz(quiz)
			.content(content)
			.isAnswer(isAnswer)
			.isDeleted(false)
			.build();
	}
}
