package org.example.example2.entity.contents;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Project        : PF-ContentManagement
 * DATE           : 2020-08-24
 * AUTHOR         : JohnMark (Chang Jeong Hyeon)
 * EMAIL          : practice1356@gmail.com
 * DESCRIPTION    : Content Download Log Domain
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 */
@Getter
@Entity
@Table(name = "content_download_log", indexes = {
	@Index(name = "idx_content_download_log_workspace_uuid", columnList = "workspace_uuid,created_at")
})
@NoArgsConstructor
public class ContentDownloadLog extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "content_download_log_id")
	private Long id;

	@Column(name = "workspace_uuid", nullable = false)
	private String workspaceUUID;

	@Column(name = "content_name", nullable = false)
	private String contentName;

	@Column(name = "content_size")
	private Long contentSize;

	@Column(name = "upload_user_uuid")
	private String uploadUserUUID;

	@Column(name = "download_user_UUID")
	private String downloadUserUUID;

	@Builder(access = AccessLevel.PRIVATE)
	private ContentDownloadLog(
		String workspaceUUID, String contentName, long contentSize, String contentUploader, String downloader
	) {
		this.workspaceUUID = workspaceUUID;
		this.contentName = contentName;
		this.contentSize = contentSize;
		this.uploadUserUUID = contentUploader;
		this.downloadUserUUID = downloader;
	}

	public static ContentDownloadLog of(
		String workspaceUUID, String contentName, long contentSize, String contentUploader, String downloader
	) {
		return ContentDownloadLog.builder()
			.workspaceUUID(workspaceUUID)
			.contentName(contentName)
			.contentSize(contentSize)
			.contentUploader(contentUploader)
			.downloader(downloader)
			.build();
	}
}
