package com.virnect.workspace.domain.lms;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.virnect.workspace.domain.TimeEntity;
import com.virnect.workspace.domain.lms.enums.QuizType;
import com.virnect.workspace.dto.request.lms.QuizCreateRequest;
import com.virnect.workspace.dto.request.lms.QuizUpdateRequest;

@Getter
@Builder
@Audited
@Entity
@Table(name = "quiz", indexes = @Index(name = "idx_curriculum_id", columnList = "curriculum_id"))
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quiz extends TimeEntity {
	@Id
	@Column(name = "quiz_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curriculum_id")
	private Curriculum curriculum;

	@Column(name = "title", length = 100, nullable = false)
	private String title;

	@Column(name = "description", length = 300)
	private String description;

	@Column(name = "point", nullable = false)
	private Integer point;

	@Column(name = "quiz_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private QuizType quizType;

	@Column(name = "sequence", nullable = false)
	private Long sequence;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Builder.Default
	private List<QuizOption> quizOptions = new ArrayList<>();

	public static Quiz create(Curriculum curriculum, QuizCreateRequest request, long sequence) {
		return Quiz.builder()
			.curriculum(curriculum)
			.title(request.getTitle())
			.description(request.getDescription())
			.point(request.getPoint())
			.quizType(request.getQuizType())
			.sequence(sequence)
			.isDeleted(false)
			.build();
	}

	public static Quiz duplicate(Quiz quiz, Curriculum duplicatedCurriculum) {
		Quiz duplicatedQuiz = Quiz.builder()
			.curriculum(duplicatedCurriculum)
			.title(quiz.title)
			.description(quiz.description)
			.point(quiz.point)
			.quizType(quiz.quizType)
			.sequence(quiz.sequence)
			.isDeleted(false)
			.build();

		for (QuizOption quizOption : quiz.getQuizOptions()) {
			QuizOption duplicateQuizOption = QuizOption.duplicate(duplicatedQuiz, quizOption);
			duplicatedQuiz.addQuizOption(duplicateQuizOption);
		}

		return duplicatedQuiz;
	}

	public void addQuizOption(QuizOption quizOption) {
		this.quizOptions.add(quizOption);
	}

	public void addQuizOptions(List<QuizOption> quizOptions) {
		this.quizOptions.addAll(quizOptions);
	}

	public void delete() {
		this.isDeleted = true;
		this.quizOptions.forEach(QuizOption::delete);
	}

	public void update(QuizUpdateRequest quizUpdateRequest) {
		this.title = quizUpdateRequest.getTitle();
		this.description = quizUpdateRequest.getDescription();
		this.point = quizUpdateRequest.getPoint();
	}

	public void updateSequence(long sequence) {
		this.sequence = sequence;
	}

	public boolean equalsById(Long quizId) {
		return this.id.equals(quizId);
	}

	public boolean equalsBySequence(Long sequence) {
		return this.sequence.equals(sequence);
	}
}
