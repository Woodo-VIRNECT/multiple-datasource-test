package com.virnect.workspace.domain.lms;

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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.virnect.workspace.application.content.dto.response.ContentDuplicateResponse;
import com.virnect.workspace.domain.TimeEntity;
import com.virnect.workspace.domain.lms.enums.TargetType;

@Audited
@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "curriculum_content", indexes = @Index(name = "idx_curriculum_id", columnList = "curriculum_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CurriculumContent extends TimeEntity {
	@Id
	@Column(name = "curriculum_content_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curriculum_id")
	private Curriculum curriculum;

	@Column(name = "content_uuid", nullable = false)
	private String contentUUID;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "download_path", nullable = false)
	private String downloadPath;

	@Lob
	@Column(name = "properties", nullable = false)
	private String properties;

	@Column(name = "size")
	private Long size;

	@Column(name = "thumbnail_path")
	private String thumbnailPath;

	@Column(name = "target_path")
	private String targetPath;

	@Enumerated(EnumType.STRING)
	@Column(name = "target_type")
	private TargetType targetType;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	public static CurriculumContent create(ContentDuplicateResponse response, Curriculum curriculum) {
		return CurriculumContent.builder()
			.curriculum(curriculum)
			.contentUUID(response.getUuid())
			.name(response.getName())
			.downloadPath(response.getDownloadPath())
			.properties(response.getProperties())
			.size(response.getSize())
			.thumbnailPath(response.getProfilePath())
			.targetPath(response.getTargetPath())
			.targetType(response.getTargetType())
			.isDeleted(false)
			.build();
	}

	public boolean equalsBy(String contentUUID) {
		return this.contentUUID.equals(contentUUID);
	}

	public void delete() {
		isDeleted = true;
	}
}
