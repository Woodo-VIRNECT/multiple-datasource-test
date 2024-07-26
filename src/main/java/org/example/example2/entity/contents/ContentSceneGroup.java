package org.example.example2.entity.contents;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Project        : PF-ContentManagement
 * DATE           : 2020-01-22
 * AUTHOR         : JohnMark (Chang Jeong Hyeon)
 * EMAIL          : practice1356@gmail.com
 * DESCRIPTION    : Scene Group Data Domain Class
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "scene_group")
public class ContentSceneGroup extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "scene_group_id")
	private Long id;

	@Column(name = "uuid")
	private String uuid;

	@Column(name = "priority")
	private Integer priority;

	@Column(name = "name")
	private String name;

	@Column(name = "job_total")
	private Integer jobTotal;

	@OneToMany(mappedBy = "contentSceneGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ContentScene> contentScenes = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "content_id")
	private Content content;

	@Builder(access = AccessLevel.PRIVATE)
	private ContentSceneGroup(String uuid, int priority, String name, int jobTotal) {
		this.uuid = uuid;
		this.priority = priority;
		this.name = name;
		this.jobTotal = jobTotal;
	}

	public static ContentSceneGroup of(String uuid, int priority, String name, int jobTotal){
		return ContentSceneGroup.builder()
			.uuid(uuid)
			.priority(priority)
			.name(name)
			.jobTotal(jobTotal)
			.build();
	}

	public void updateContent(Content content) {
		this.content = content;
	}

	public void addContentScene(ContentScene contentScene) {
		contentScene.updateContentSceneGroup(this);
		this.contentScenes.add(contentScene);
	}
}
