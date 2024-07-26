package org.example.example2.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

/**
 * Project        : PF-ContentManagement
 * DATE           : 2023-05-16
 * AUTHOR         : User (Changsuu Ha)
 * EMAIL          : soo@virnect.com
 * DESCRIPTION    : Embeddable Profile Image
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-16      User          최초 생성
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseProfileImage extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	protected ProfileImageType type;

	@Column(name = "path", nullable = false)
	protected String path;

	@Column(name = "profile_size", nullable = false)
	protected Long size;

	public void addImageFile(String path, Long size) {
		this.path = path;
		this.size = size;
	}
}
