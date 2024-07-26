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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.virnect.workspace.domain.TimeEntity;

@Audited
@Entity
@Getter
@Table(name = "curriculum_editor",
	indexes = {
		@Index(name = "idx_curriculum_id", columnList = "curriculum_id"),
		@Index(name = "idx_user_uuid", columnList = "user_uuid")
	})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CurriculumEditor extends TimeEntity {
	@Id
	@Column(name = "curriculum_editor_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curriculum_id")
	private Curriculum curriculum;

	@Column(name = "user_uuid", nullable = false)
	private String userUUID;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Builder
	public CurriculumEditor(Long id, Curriculum curriculum, String userUUID, boolean isDeleted) {
		this.id = id;
		this.curriculum = curriculum;
		this.userUUID = userUUID;
		this.isDeleted = isDeleted;
	}

	public static CurriculumEditor create(Curriculum curriculum, String userUUID) {
		return CurriculumEditor.builder()
			.curriculum(curriculum)
			.userUUID(userUUID)
			.isDeleted(false)
			.build();
	}

	public void delete() {
		this.isDeleted = true;
	}
}
