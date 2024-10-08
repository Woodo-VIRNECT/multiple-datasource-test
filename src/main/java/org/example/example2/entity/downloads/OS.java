package org.example.example2.entity.downloads;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
 * Project: PF-Download
 * DATE: 2020-05-04
 * AUTHOR: jkleee (Jukyoung Lee)
 * EMAIL: ljk@virnect.com
 * DESCRIPTION:
 */
@Entity
@Getter
@Setter
@Table(name = "os")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OS extends TimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "os_id")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Builder(access = AccessLevel.PRIVATE)
	public OS(
		String name,
		String description
	) {
		this.name = name;
		this.description = description;
	}

	public static OS of(
		String name,
		String description
	) {
		return OS.builder()
			.name(name)
			.description(description)
			.build();
	}
}
