package com.virnect.workspace.domain.lms;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.virnect.workspace.domain.TimeEntity;
import com.virnect.workspace.domain.lms.enums.CurriculumProgressStatus;
import com.virnect.workspace.domain.lms.enums.LearningStatus;
import com.virnect.workspace.dto.request.lms.CurriculumCreateRequest;
import com.virnect.workspace.dto.request.lms.CurriculumUpdateRequest;

@Audited
@Getter
@Entity
@Table(name = "curriculum",
	indexes = {
		@Index(name = "idx_workspace_uuid", columnList = "workspace_uuid"),
		@Index(name = "idx_start_date_time", columnList = "start_date_time"),
		@Index(name = "idx_end_date_time", columnList = "end_date_time")
	})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Curriculum extends TimeEntity {
	@Id
	@Column(name = "curriculum_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "workspace_uuid", nullable = false)
	private String workspaceUUID;

	@Column(name = "registrar_id", nullable = false)
	private String registrarId;

	@Column(name = "modifier_id", nullable = false)
	private String modifierId;

	@Column(name = "title", length = 30, nullable = false)
	private String title;

	@Column(name = "description", length = 1000)
	private String description;

	@Column(name = "start_date_time")
	private LocalDateTime startDateTime;

	@Column(name = "end_date_time")
	private LocalDateTime endDateTime;

	@Column(name = "is_public", nullable = false)
	private boolean isPublic;

	@Enumerated(EnumType.STRING)
	@Column(name = "progress_status", length = 20, nullable = false)
	private CurriculumProgressStatus progressStatus;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Column(name = "enabled_quiz", nullable = false)
	private boolean enabledQuiz;

	public static Curriculum create(String workspaceUUID, String userUUID, CurriculumCreateRequest request) {
		return Curriculum.builder()
			.workspaceUUID(workspaceUUID)
			.registrarId(userUUID)
			.modifierId(userUUID)
			.title(request.getTitle())
			.description(request.getDescription())
			.startDateTime(request.getStartDate())
			.endDateTime(request.getEndDate())
			.isPublic(true)
			.progressStatus(CurriculumProgressStatus.BEFORE_DEPLOYMENT)
			.isDeleted(false)
			.enabledQuiz(true)
			.build();
	}

	public static Curriculum duplicate(Curriculum curriculum, String userUUID) {

		String copiedTitle = curriculum.title + "_copy";

		if (copiedTitle.length() > 30) {
			copiedTitle = copiedTitle.substring(0, 25) + "_copy";
		}

		return Curriculum.builder()
			.workspaceUUID(curriculum.getWorkspaceUUID())
			.registrarId(userUUID)
			.modifierId(userUUID)
			.title(copiedTitle)
			.description(curriculum.getDescription())
			.progressStatus(CurriculumProgressStatus.BEFORE_DEPLOYMENT)
			.isDeleted(false)
			.enabledQuiz(curriculum.isEnabledQuiz())
			.isPublic(curriculum.isPublic())
			.build();
	}

	public int getCompletionRate(List<Learner> learners) {
		long totalLearners = learners.size();
		long completedLearners = learners.stream()
			.filter(a -> a.getLearningStatus() == LearningStatus.COMPLETED)
			.count();
		return (totalLearners > 0) ? (int)(completedLearners * 100 / totalLearners) : 0;
	}

	public void update(String userUUID, CurriculumUpdateRequest request) {
		this.modifierId = userUUID;
		this.title = request.getTitle();
		this.description = request.getDescription();
		this.startDateTime = request.getStartDate();
		this.endDateTime = request.getEndDate();
		updateProgressStatus(startDateTime, endDateTime);
		this.enabledQuiz = request.getEnabledQuiz();
	}

	private void updateProgressStatus(LocalDateTime startDate, LocalDateTime endDate) {
		if (isNotDeployed() || isEnd()) {
			return;
		}

		LocalDateTime today = LocalDateTime.now();

		if (startDate.isAfter(today)) {
			updateToStandBy();
			return;
		}

		if ((startDate.isBefore(today) || startDate.isEqual(today)) && (endDate == null || endDate.isAfter(today))) {
			updateToInProgress();
			return;
		}

		updateToDeadline();
	}

	private void updateToDeadline() {
		this.progressStatus = CurriculumProgressStatus.DEADLINE;
	}

	private void updateToInProgress() {
		this.progressStatus = CurriculumProgressStatus.IN_PROGRESS;
	}

	private void updateToStandBy() {
		this.progressStatus = CurriculumProgressStatus.STAND_BY;
	}

	public void deployedBy(String userUUID) {
		this.modifierId = userUUID;
		LocalDateTime today = LocalDateTime.now();
		if (startDateTime.isAfter(today)) {
			updateToStandBy();
			return;
		}

		if ((startDateTime.isBefore(today) || startDateTime.isEqual(today)) &&
			(endDateTime == null || endDateTime.isAfter(today))) {
			updateToInProgress();
			return;
		}

		updateToDeadline();
	}

	public boolean isDeployed() {
		return this.progressStatus.isDeployed();
	}

	public boolean isBeforeDeployment() {
		return this.progressStatus.isBeforeDeployment();
	}

	public void endBy(String userUUID) {
		this.progressStatus = CurriculumProgressStatus.END;
		this.modifierId = userUUID;
	}

	public boolean isEnd() {
		return this.progressStatus.isEnd();
	}

	public boolean isInProgress() {
		return this.progressStatus.isInProgress();
	}

	public void updatePublicStatusBy(boolean isPublic, String userUUID) {
		this.modifierId = userUUID;
		this.isPublic = isPublic;
	}

	public boolean isNotDeployed() {
		return !isDeployed();
	}

	public boolean isBeforeDeadline() {
		return this.progressStatus.isBeforeDeadline();
	}
}
