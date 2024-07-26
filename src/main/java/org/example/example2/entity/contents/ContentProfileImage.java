package org.example.example2.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "content_profile_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(
	name = "id", column = @Column(name = "content_profile_image_id")
)
public class ContentProfileImage extends BaseProfileImage {

	@ManyToOne
	@JoinColumn(name = "content_uuid")
	private Content content;

	@Builder(access = AccessLevel.PRIVATE)
	private ContentProfileImage(
		ProfileImageType type,
		String path,
		Long size,
		Content content
	) {
		this.type = type;
		this.path = path;
		this.size = size;
		this.content = content;
	}

	public static ContentProfileImage of(
		ProfileImageType type,
		String path,
		Long size,
		Content content
	) {
		return ContentProfileImage.builder()
			.type(type)
			.path(path)
			.size(size)
			.content(content)
			.build();
	}

	public static ContentProfileImage withoutImageFileOf(
		ProfileImageType type,
		Content content
	) {
		return ContentProfileImage.builder()
			.type(type)
			.content(content)
			.build();
	}

	public void updateContent(Content content) {
		this.content = content;
	}
}
