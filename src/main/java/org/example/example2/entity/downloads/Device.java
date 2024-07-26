package org.example.example2.entity.downloads;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
 * Project: PF-Download
 * DATE: 2020-05-04
 * AUTHOR: jkleee (Jukyoung Lee)
 * EMAIL: ljk@virnect.com
 * DESCRIPTION:
 */
@Entity
@Getter
@Setter
@Table(name = "device")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Device extends TimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "device_id")
	private Long id;

	@Column(name = "type", nullable = false)
	private String type; //MOBILE

	@Column(name = "type_description", nullable = false)
	private String typeDescription; //Google Play

	@Column(name = "model", nullable = false)
	private String model; //스마트폰/태블릿 PC

	@Column(name = "model_description", nullable = false)
	private String modelDescription; //<span style="color: #1468e2">스마트폰/타블릿</span>

	@Column(name = "model_description_eng", nullable = true)
	private String modelDescriptionEng;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Column(name = "device_support_update_status")
	@Enumerated(EnumType.STRING)
	private DeviceSupportUpdateStatus deviceSupportUpdateStatus;

	@Builder(access = AccessLevel.PRIVATE)
	public Device(
		String type, String typeDescription, String model, String modelDescription, String modelDescriptionEng,
		DeviceSupportUpdateStatus deviceSupportUpdateStatus
	) {
		this.type = type;
		this.typeDescription = typeDescription;
		this.model = model;
		this.modelDescription = modelDescription;
		this.modelDescriptionEng = modelDescriptionEng;
		this.deviceSupportUpdateStatus = deviceSupportUpdateStatus;
	}

	public static Device of(
		String type, String typeDescription, String model, String modelDescription, String modelDescriptionEng, DeviceSupportUpdateStatus status
	) {
		return Device.builder()
			.type(type)
			.typeDescription(typeDescription)
			.model(model)
			.modelDescription(modelDescription)
			.modelDescriptionEng(modelDescriptionEng)
			.deviceSupportUpdateStatus(status)
			.build();
	}
}
