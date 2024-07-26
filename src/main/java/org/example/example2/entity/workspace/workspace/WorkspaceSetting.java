package org.example.example2.entity.workspace.workspace;


import java.time.LocalDateTime;
import java.util.List;

import org.example.example2.entity.workspace.TimeEntity;
import org.example.example2.entity.workspace.lms.enums.CurriculumProgressStatus;
import org.example.example2.entity.workspace.lms.enums.LearningStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Project: PF-Workspace
 * DATE: 2020-10-16
 * AUTHOR: jkleee (Jukyoung Lee)
 * EMAIL: ljk@virnect.com
 * DESCRIPTION:
 */
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "workspace_setting")
public class WorkspaceSetting extends TimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "workspace_setting_id")
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "default_logo")
	private String defaultLogo;

	@Column(name = "grey_logo")
	private String greyLogo;

	@Column(name = "white_logo")
	private String whiteLogo;

	@Column(name = "favicon")
	private String favicon;

	@Column(name = "remote_android_splash_logo")
	private String remoteAndroidSplashLogo;

	@Column(name = "remote_android_login_logo")
	private String remoteAndroidLoginLogo;

	@Column(name = "remote_hololens2_common_logo")
	private String remoteHololens2CommonLogo;

	@Column(name = "workspace_id")
	private String workspaceId;

	@Column(name = "lms_enabled", nullable = false)
	private Boolean lmsEnabled = true;

	@Builder(builderClassName = "WorkspaceSettingInitBuilder", builderMethodName = "workspaceSettingInitBuilder")
	public WorkspaceSetting(String workspaceId) {
		this.workspaceId = workspaceId;
	}

	public void updateLmsEnabled(Boolean lmsEnabled) {
		this.lmsEnabled = lmsEnabled;
	}
}
