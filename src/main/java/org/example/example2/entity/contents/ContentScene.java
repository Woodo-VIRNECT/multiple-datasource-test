package org.example.example2.entity.contents;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Project        : PF-ContentManagement
 * DATE           : 6/13/24
 * AUTHOR         : dnejdzlr2 (Woodo Lee)
 * EMAIL          : dnejdzlr2@virnect.com
 * DESCRIPTION    :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/13/24      dnejdzlr2          최초 생성
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "scene")
public class ContentScene {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "scene_id")
	private Long id;

	@Column(name = "uuid")
	private String uuid;

	@Column(name = "title")
	private String title;

	@Column(name = "detail")
	private String detail;

	@Column(name = "component_name")
	private String componentName;

	@Column(name = "component_type")
	private Integer componentType;

	@Column(name = "priority")
	private Integer priority;

	@Column(name = "thumbnail_image_path")
	private String thumbnailImagePath;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scene_group_id")
	private ContentSceneGroup contentSceneGroup;

	@Builder(access = AccessLevel.PRIVATE)
	private ContentScene(
		String uuid, String title, String detail, String componentName, Integer componentType, Integer priority, String thumbnailImagePath
	) {
		this.uuid = uuid;
		this.title = title;
		this.detail = detail;
		this.componentName = componentName;
		this.componentType = componentType;
		this.priority = priority;
		this.thumbnailImagePath = thumbnailImagePath;
	}

	public static ContentScene of(
		String uuid, String title, String detail, String componentName, Integer componentType, Integer priority, String thumbnailImagePath
	) {
		return ContentScene.builder()
			.uuid(uuid)
			.title(title)
			.detail(detail)
			.componentName(componentName)
			.componentType(componentType)
			.priority(priority)
			.thumbnailImagePath(thumbnailImagePath)
			.build();
	}

	public void updateContentSceneGroup(ContentSceneGroup contentSceneGroup) {
		this.contentSceneGroup = contentSceneGroup;
	}
}
