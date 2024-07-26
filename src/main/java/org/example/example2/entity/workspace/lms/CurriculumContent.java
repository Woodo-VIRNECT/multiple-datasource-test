package org.example.example2.entity.workspace.lms;


import java.time.LocalDateTime;
import java.util.List;

import org.example.example2.entity.workspace.TimeEntity;
import org.example.example2.entity.workspace.lms.enums.CurriculumProgressStatus;
import org.example.example2.entity.workspace.lms.enums.LearningStatus;
import org.example.example2.entity.workspace.lms.enums.TargetType;

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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "curriculum_content", indexes = @Index(name = "idx_curriculum_content_curriculum_id", columnList = "curriculum_id"))
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

	@Column(name = "content_size")
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

}
