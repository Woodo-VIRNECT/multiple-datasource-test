package com.virnect.workspace.domain.lms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.virnect.workspace.domain.TimeEntity;

@Entity
@Audited
@Table(name = "curriculum_constructor",
	indexes = {
		@Index(name = "idx_user_uuid", columnList = "user_uuid"),
		@Index(name = "idx_workspace_uuid", columnList = "workspace_uuid")
	})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CurriculumConstructor extends TimeEntity {
	@Id
	@Column(name = "curriculum_constructor_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_uuid", nullable = false)
	private String userUUID;

	@Column(name = "workspace_uuid", nullable = false)
	private String workspaceUUID;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Builder
	public CurriculumConstructor(Long id, String userUUID, String workspaceUUID, boolean isDeleted) {
		this.id = id;
		this.userUUID = userUUID;
		this.workspaceUUID = workspaceUUID;
		this.isDeleted = isDeleted;
	}

	public static CurriculumConstructor create(String userUUID, String workspaceUUID) {
		return CurriculumConstructor.builder()
			.userUUID(userUUID)
			.workspaceUUID(workspaceUUID)
			.isDeleted(false)
			.build();
	}

	public void delete() {
		this.isDeleted = true;
	}
}
