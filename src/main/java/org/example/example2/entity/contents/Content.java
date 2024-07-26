package org.example.example2.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



/**
 * Project        : PF-ContentManagement
 * DATE           : 2020-01-21
 * AUTHOR         : JohnMark (Chang Jeong Hyeon)
 * EMAIL          : practice1356@gmail.com
 * DESCRIPTION    : Content Data Domain Entity Class
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "content", indexes = {
	@Index(name = "idx_content_uuid", columnList = "uuid"),
	@Index(name = "idx_content_user_uuid", columnList = "user_uuid"),
	@Index(name = "idx_content_workspace_uuid", columnList = "workspace_uuid")
})
public class Content extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "content_id")
	private Long id;

	@Column(name = "uuid", nullable = false, unique = true)
	private String uuid;

	// oracle 에서는 name 사용할 수 없음 예약어 라고 알고있는데 왜 동작되지?
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "path", nullable = false, unique = true)
	private String path;

	// oracle 에서는 size 사용할 수 없음 예약어
	// @Column(name = "size", nullable = false)
	@Column(name = "content_size", nullable = false)
	private Long size;

	@Column(name = "shared", nullable = false)
	@Enumerated(EnumType.STRING)
	private YesOrNo shared;

	@Column(name = "user_uuid", nullable = false)
	private String userUUID;

	@Column(name = "workspace_uuid", nullable = false)
	private String workspaceUUID;

	@Lob
	@Column(name = "metadata")
	private String metadata;

	@Lob
	@Column(name = "properties", nullable = false)
	private String properties;

	@Column(name = "deleted", nullable = false)
	@Enumerated(EnumType.STRING)
	private YesOrNo deleted = YesOrNo.NO;

	// 컨텐츠 변환여부 : 콘텐츠의 복제 여부로, 작업 생성 시 복제된 콘텐츠의 상태 (NO: 원본 콘텐츠, YES : 복제된 콘텐츠)
	@Column(name = "converted", nullable = false)
	@Enumerated(EnumType.STRING)
	private YesOrNo converted = YesOrNo.NO;

	// mysql
	// @Column(name = "download_hits")
	// private Long downloadHits;

	// oracle
	@Column(name = "download_hits")
	private Long downloadHits = 0L;

	@Column(name = "version")
	private String version;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id")
	private ContentType contentType;

	@OneToMany(mappedBy = "content", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ContentTarget> contentTargetList = new ArrayList<>();

	@OneToMany(mappedBy = "content", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ContentSceneGroup> contentSceneGroupList = new ArrayList<>();

	@OneToMany(mappedBy = "content", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ContentProfileImage> profileImageList = new ArrayList<>();

	@Builder(access = AccessLevel.PRIVATE)
	private Content(
		String uuid, String name, String path, Long size, YesOrNo shared, String userUUID, String workspaceUUID,
		String metadata, String properties, YesOrNo deleted, YesOrNo converted, ContentType contentType, String version
	) {
		this.uuid = uuid;
		this.name = name;
		this.path = path;
		this.size = size;
		this.shared = shared;
		this.userUUID = userUUID;
		this.workspaceUUID = workspaceUUID;
		this.metadata = metadata;
		this.properties = properties;
		this.deleted = deleted;
		this.converted = converted;
		this.contentType = contentType;
		this.downloadHits = 0L;
		this.version = version;
	}

	public static Content of(
		String uuid, String name, String path, Long size, YesOrNo shared, String userUUID, String workspaceUUID,
		String metadata, String properties, YesOrNo deleted, YesOrNo converted, String version
	){
		return Content.builder()
			.uuid(uuid)
			.name(name)
			.path(path)
			.size(size)
			.shared(shared)
			.userUUID(userUUID)
			.workspaceUUID(workspaceUUID)
			.metadata(metadata)
			.properties(properties)
			.deleted(deleted)
			.converted(converted)
			.version(version)
			.build();
	}

	public void addTarget(ContentTarget contentTarget) {
		contentTarget.updateContent(this);
		this.contentTargetList.add(contentTarget);
	}

	public void addSceneGroup(ContentSceneGroup contentSceneGroup) {
		contentSceneGroup.updateContent(this);
		this.contentSceneGroupList.add(contentSceneGroup);
	}

	public void addProfileImage(ContentProfileImage contentProfileImage) {
		contentProfileImage.updateContent(this);
		this.profileImageList.add(contentProfileImage);
	}

	public void updateContent(String name, String path, Long size, String userUUID, String properties, String version) {
		this.name = name;
		this.path = path;
		this.size = size;
		this.userUUID = userUUID;
		this.properties = properties;
		this.version = version;
	}

	public void updateContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	public void updateShared(YesOrNo shared) {
		this.shared = shared;
	}

	public void updateConverted(YesOrNo converted){
		this.converted = converted;
	}

	public boolean isOwnedBy(String workspaceUUID) {
		return this.workspaceUUID.equals(workspaceUUID);
	}

	public boolean hasContentTarget() {
		return !this.contentTargetList.isEmpty();
	}
}
