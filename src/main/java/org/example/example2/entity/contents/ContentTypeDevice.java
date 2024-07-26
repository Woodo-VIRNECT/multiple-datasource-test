package org.example.example2.entity.contents;

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
 * Project        : PF-ContentManagement
 * DATE           : 2020-04-10
 * AUTHOR         : hangkee.min (henry)
 * EMAIL          : hkmin@virnect.com
 * DESCRIPTION    : Content Type Device Domain
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 */
@Getter
@Setter
@Entity
@Table(name = "type_device")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContentTypeDevice extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "type_device_id")
	private Long id;

	@Column(name = "name")
	@Enumerated(EnumType.STRING)
	private Devices device;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id")
	private ContentType contentType;

	@Builder(access = AccessLevel.PRIVATE)
	private ContentTypeDevice(Devices device) {
		this.device = device;
	}

	public static ContentTypeDevice of(Devices device) {
		return ContentTypeDevice.builder()
			.device(device)
			.build();
	}
}
