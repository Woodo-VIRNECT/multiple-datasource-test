package org.example.example2.entity.downloads;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Project: PF-Download
 * DATE: 2020-04-20
 * AUTHOR: jkleee (Jukyoung Lee)
 * EMAIL: ljk@virnect.com
 * DESCRIPTION:
 */
@Entity
@Getter
@Setter
@Table(
	name = "app",
	indexes = {
		@Index(name = "idx_app_uuid", columnList = "uuid", unique = true),
		@Index(name = "idx_app_version_code", columnList = "version_code"),
		@Index(name = "idx_os_id", columnList = "os_id"),
		@Index(name = "idx_device_id", columnList = "device_id"),
		@Index(name = "idx_product_id", columnList = "product_id")
	}
)
@NoArgsConstructor
public class App extends TimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "app_id")
	private Long id;

	@Column(name = "uuid", nullable = false, unique = true)
	private String uuid;

	@Column(name = "app_url", nullable = false)
	private String appUrl;

	@Column(name = "guide_url")
	private String guideUrl;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "version_name", nullable = false)
	private String versionName;

	@Column(name = "version_code", nullable = false)
	private Long versionCode;

	@Column(name = "package_name")
	private String packageName;

	@Column(name = "signature")
	private String signature;

	@Column(name = "app_update_status")
	@Enumerated(EnumType.STRING)
	private AppUpdateStatus appUpdateStatus;

	@Column(name = "app_status")
	@Enumerated(EnumType.STRING)
	private AppStatus appStatus;

	@Column(name = "app_download_count", nullable = false)
	private Long appDownloadCount = 0L;

	@Column(name = "guide_download_count", nullable = false)
	private Long guideDownloadCount = 0L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "device_id", nullable = false)
	private Device device;

	@JoinColumn(name = "product_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "os_id", nullable = false)
	private OS os;

	@Builder(builderClassName = "AppBuilder", builderMethodName = "appBuilder")
	public App(
		String uuid, String appUrl, String guideUrl, String imageUrl, Device device, Product product, OS os,
		String versionName, Long versionCode,
		String packageName, String signature
	) {
		this.uuid = uuid;
		this.appUrl = appUrl;
		this.guideUrl = guideUrl;
		this.imageUrl = imageUrl;
		this.device = device;
		this.product = product;
		this.os = os;
		this.versionName = versionName;
		this.versionCode = versionCode;
		this.packageName = packageName;
		this.signature = signature;
		this.appUpdateStatus = AppUpdateStatus.OPTIONAL;
		this.appStatus = AppStatus.INACTIVE;
		this.appDownloadCount = 0L;
		this.guideDownloadCount = 0L;
	}

	@Builder(builderClassName = "AppByAdminBuilder", builderMethodName = "appByAdminBuilder")
	public App(
		String uuid, String appUrl, Device device, Product product, OS os,
		String versionName, Long versionCode, String signature
	) {
		this.uuid = uuid;
		this.appUrl = appUrl;
		this.guideUrl = null;
		this.imageUrl = null;
		this.device = device;
		this.product = product;
		this.os = os;
		this.versionName = versionName;
		this.versionCode = versionCode;
		this.packageName = null;
		this.signature = signature;
		this.appUpdateStatus = AppUpdateStatus.REQUIRED;
		this.appStatus = AppStatus.ACTIVE;
		this.appDownloadCount = 0L;
		this.guideDownloadCount = 0L;
	}
}