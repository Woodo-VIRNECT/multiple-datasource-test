package org.example.example2.entity.remote.room;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.example.example2.entity.remote.BaseTimeEntity;
import org.example.example2.entity.remote.member.Member;
import org.example.example2.entity.remote.session.SessionProperty;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Room Domain Model Class
 * DATE:
 * AUTHOR:
 * EMAIL:
 * DESCRIPTION:
 *
 */
@Entity
@Getter
@Setter
@Table(name = "rooms")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id", nullable = false)
	private Long id;

	@Column(name = "session_id", unique = true)
	private String sessionId;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "profile")
	private String profile;

	@Column(name = "leader_id", nullable = false)
	private String leaderId;

	@Column(name = "workspace_id", nullable = false)
	private String workspaceId;

	@Column(name = "maxUserCount", nullable = false)
	private int maxUserCount;

	@Column(name = "room_status", nullable = false)
	private RoomStatus roomStatus;

	@Column(name = "license_name", nullable = false)
	private String licenseName;

	@Column(name = "active_at")
	private LocalDateTime activeDate;

	@Column(name = "video_restricted_mode")
	private boolean videoRestrictedMode;

	@Column(name = "audio_restricted_mode")
	private boolean audioRestrictedMode;

	//add active or un-active type later
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Member> members = new ArrayList<>();

	@OneToOne(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private SessionProperty sessionProperty;

	@Builder
	public Room(
		String sessionId,
		String title,
		String description,
		String leaderId,
		String workspaceId,
		String licenseName,
		int maxUserCount,
		boolean videoRestrictedMode,
		boolean audioRestrictedMode,
		SessionProperty sessionProperty
	) {
		this.sessionId = sessionId;
		this.title = title;
		this.description = description;
		this.leaderId = leaderId;
		this.workspaceId = workspaceId;
		this.licenseName = licenseName;
		this.maxUserCount = maxUserCount;
		this.sessionProperty = sessionProperty;
		this.videoRestrictedMode = videoRestrictedMode;
		this.audioRestrictedMode = audioRestrictedMode;

		//default setting
		this.profile = "default";
		this.roomStatus = RoomStatus.UNACTIVE;
		this.activeDate = LocalDateTime.now();
	}
}