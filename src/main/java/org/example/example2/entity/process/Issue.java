package org.example.example2.entity.process;

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
import lombok.Setter;

/**
 * Project: PF-ProcessManagement
 * DATE: 2020-02-04
 * AUTHOR: JohnMark (Chang Jeong Hyeon)
 * EMAIL: practice1356@gmail.com
 * DESCRIPTION:
 */
@Getter
@Setter
@Entity
@Table(name = "issue")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Issue extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "issue_id")
	private Long id;

	@Column(name = "content")
	private String content;

	@Column(name = "photo_file_path")
	private String path;

	@Column(name = "worker_uuid")
	private String workerUUID;

	@Column(name = "workspace_uuid")
	private String workspaceUUID;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_id")
	private Job job;

	@Builder
	public Issue(String content, String path, String workerUUID, String workspaceUUID, Job job) {
		this.content = content;
		this.path = path;
		this.workerUUID = workerUUID;
		this.workspaceUUID = workspaceUUID;
		this.job = job;
	}

	@Override
	public String toString() {
		return "Issue{" +
			"id=" + id +
			", content='" + content + '\'' +
			", path='" + path + '\'' +
			", workerUUID='" + workerUUID + '\'' +
			", workspaceUUID='" + workspaceUUID + '\'' +
			'}';
	}
}
