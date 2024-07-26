package org.example.example2.entity.downloads;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Project: PF-Download
 * DATE: 2020-11-27
 * AUTHOR: jkleee (Jukyoung Lee)
 * EMAIL: ljk@virnect.com
 * DESCRIPTION:
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
	name = "product",
	indexes = @Index(name = "idx_product_name", columnList = "name", unique = true)
)
public class Product extends TimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;

	@Column(name = "name", length = 70)
	private String name;

	@Column(name = "description")
	private String description;

	@Builder(access = AccessLevel.PRIVATE)
	public Product(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public static Product of(String name, String description) {
		return Product.builder()
			.name(name)
			.description(description)
			.build();
	}
}