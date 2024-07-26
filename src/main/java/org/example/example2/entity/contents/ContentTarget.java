package org.example.example2.entity.contents;

import org.hibernate.tool.schema.TargetType;

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
import lombok.extern.slf4j.Slf4j;

/**
 * Project        : PF-ContentManagement
 * DATE           : 2020.04.10
 * AUTHOR         : hangkee.min (henry)
 * EMAIL          : hkmin@virnect.com
 * DESCRIPTION    : Content Target Domain
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 */
@Slf4j
@Getter
@Entity
@Table(name = "target")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContentTarget extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "target_id")
	private Long id;

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private TargetType type;

	@Column(name = "data", unique = true)
	private String data;

	@Column(name = "img_path")
	private String imgPath;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "content_id")
	private Content content;

	@Column(name = "target_size")
	private Float size;

	@Builder(access = AccessLevel.PRIVATE)
	private ContentTarget(TargetType type, String data, String imgPath, Content content, Float size) {
		this.type = type;
		this.data = data;
		this.imgPath = imgPath;
		this.content = content;
		this.size = size;
	}

	public static ContentTarget of(
		TargetType type, String data, String imgPath, Content content, Float size
	) {
        return ContentTarget.builder()
            .type(type)
            .data(data)
            .imgPath(imgPath)
            .content(content)
            .size(size)
            .build();
	}

	@Override
	public String toString() {
		return "ContentTarget{" +
			"id=" + id +
			", type=" + type +
			", data='" + data + '\'' +
			", imgPath=" + imgPath + '\'' +
			", size=" + size + '\'' +
			//                ", content=" + content +
			'}';
	}

	public void updateContent(Content content) {
		this.content = content;
	}

	public void updateContentType(TargetType type, String data, String imgPath, Float size) {
		this.type = type;
		this.data = data;
		this.imgPath = imgPath;
		this.size = size;
	}

}
