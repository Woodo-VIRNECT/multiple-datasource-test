package org.example.example2.entity.licenses.license;

import org.example.example2.entity.licenses.BaseTimeEntity;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author jeonghyeon.chang (johnmark)
 * @project PF-License
 * @email practice1356@gmail.com
 * @description
 * @since 2020.04.09
 */
@Entity
@Getter
@Setter
@Where(clause = "license_status !='TERMINATE'")
@Table(name = "license")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class License extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "license_id")
	private Long id;

	@Column(name = "serial_key")
	private String serialKey;

	@Column(name = "user_id")
	private String userId;

	@Enumerated(EnumType.STRING)
	@Column(name = "license_status")
	private LicenseStatus status = LicenseStatus.UNUSE;

	@Builder
	public License(String serialKey, LicenseStatus status) {
		this.serialKey = serialKey;
		this.status = status;
	}

	@Override
	public String toString() {
		return "License{" +
			"id=" + id +
			", serialKey='" + serialKey + '\'' +
			", userId='" + userId + '\'' +
			", status=" + status +
			'}';
	}

	public boolean isUnused() {
		return status.equals(LicenseStatus.UNUSE);
	}

	public boolean isUsed() {
		return status.equals(LicenseStatus.USE);
	}

	public void terminate() {
		status = LicenseStatus.TERMINATE;
	}
}
