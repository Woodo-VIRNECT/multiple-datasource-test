package org.example.example2.entity.workspace.lms;

import java.util.ArrayList;
import java.util.List;

import org.example.example2.entity.workspace.TimeEntity;
import org.example.example2.entity.workspace.lms.enums.QuizType;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@Table(name = "quiz", indexes = @Index(name = "idx_quiz_curriculum_id", columnList = "curriculum_id"))
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

}
