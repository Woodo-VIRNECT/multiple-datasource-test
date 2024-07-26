package org.example.example2.entity.contents;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Project        : PF-ContentManagement
 * DATE           : 2020-04-10
 * AUTHOR         : hangkee.min (henry)
 * EMAIL          : hkmin@virnect.com
 * DESCRIPTION    : Content Type Domain
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 */
@Getter
@Entity
@Table(name = "type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContentType extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "type_id")
	private Long id;

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private Types type;

	@OneToMany(mappedBy = "contentType", cascade = CascadeType.ALL)
	private final List<Content> contentList = new ArrayList<>();

	@OneToMany(mappedBy = "contentType", cascade = CascadeType.ALL)
	private final List<ContentTypeDevice> contentTypeDeviceList = new ArrayList<>();

	@Builder(access = AccessLevel.PRIVATE)
	private ContentType(Types type) {
		this.type = type;
	}

	public static ContentType of(Types type) {
		return ContentType.builder()
			.type(type)
			.build();
	}
}
