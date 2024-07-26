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

import org.hibernate.envers.Audited;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.virnect.workspace.domain.TimeEntity;
import com.virnect.workspace.dto.request.lms.QuizOptionUpdateRequest;

@Getter
@Builder
@Audited
@Entity
@Table(name = "quiz_option", indexes = @Index(name = "idx_quiz_id", columnList = "quiz_id"))
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

	public static QuizOption duplicate(Quiz duplicatedQuiz, QuizOption quizOption) {
		return QuizOption.builder()
			.quiz(duplicatedQuiz)
			.content(quizOption.getContent())
			.isAnswer(quizOption.isAnswer())
			.isDeleted(false)
			.build();
	}

	public void delete() {
		this.isDeleted = true;
	}

	public void update(QuizOptionUpdateRequest updateQuizOption) {
		this.content = updateQuizOption.getContent();
		this.isAnswer = updateQuizOption.getIsAnswer();
	}
}
