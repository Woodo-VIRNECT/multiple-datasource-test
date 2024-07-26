package org.example.example2.entity.licenses.applicense;

import java.time.LocalDateTime;

import org.example.example2.entity.licenses.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "app_license")
public class AppLicense extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "app_license_id")
	private Long id;

	@Column(name = "serial_key")
	private String serialKey;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private AppLicenseStatus status;

	@Column(name = "started_at")
	private LocalDateTime startDate;

	@Column(name = "expired_at")
	private LocalDateTime expiredDate;

	@Column(name = "hit")
	private Long hit;

	@Override
	public String toString() {
		return "AppLicense{" +
			"id=" + id +
			", serialKey='" + serialKey + '\'' +
			", status=" + status +
			", startDate=" + startDate +
			", expiredDate=" + expiredDate +
			", hit=" + hit +
			'}';
	}

	public void increaseHit() {
		this.hit += 1;
	}
}
