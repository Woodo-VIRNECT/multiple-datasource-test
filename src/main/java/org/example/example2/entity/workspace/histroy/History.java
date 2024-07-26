package org.example.example2.entity.workspace.histroy;


import org.example.example2.entity.workspace.TimeEntity;
import org.example.example2.entity.workspace.workspace.Workspace;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Project: PF-Workspace
 * DATE: 2020-05-12
 * AUTHOR: jkleee (Jukyoung Lee)
 * EMAIL: ljk@virnect.com
 * DESCRIPTION:
 */
@Entity
@Getter
@Setter
@Table(name = "history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class History extends TimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "history_id")
	private Long id;

	@ManyToOne(targetEntity = Workspace.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "workspace_id")
	private Workspace workspace;

	@Column(name = "user_id", nullable = false)
	private String userId;

	@Column(name = "message", nullable = false)
	private String message;

	@Builder
	public History(Workspace workspace, String userId, String message) {
		this.workspace = workspace;
		this.userId = userId;
		this.message = message;
	}
}
