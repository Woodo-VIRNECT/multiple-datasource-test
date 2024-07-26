package org.example.example2.entity.remote;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
	@Column(name = "created_at", nullable = false)
	@CreatedDate
	private LocalDateTime createdDate;

	@Column(name = "updated_at", nullable = false)
	@LastModifiedDate
	private LocalDateTime updatedDate;
}
