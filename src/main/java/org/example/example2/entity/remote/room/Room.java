package com.virnect.data.domain.room;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.virnect.data.domain.BaseTimeEntity;
import com.virnect.data.domain.member.Member;
import com.virnect.data.domain.session.SessionProperty;
import com.virnect.data.dto.constraint.LicenseItem;
import com.virnect.data.dto.request.reservation.ReservationCreateRequest;

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

	public static Room createReservedRoom(
		String customSessionId, ReservationCreateRequest createRequest, SessionProperty reservedSessionProperty
	) {
		Room reservedRoom = Room.builder()
			.sessionId(customSessionId)
			.workspaceId(createRequest.getWorkspaceId())
			.leaderId(createRequest.getLeaderId())
			.title(createRequest.getTitle())
			.description(createRequest.getDescription())
			.licenseName(LicenseItem.ITEM_PRODUCT.name())
			.maxUserCount(LicenseItem.ITEM_PRODUCT.getUserCapacity())
			.videoRestrictedMode(createRequest.isVideoRestrictedMode())
			.build();
		reservedRoom.updateSessionProperty(reservedSessionProperty);
		return reservedRoom;
	}

	public void updateSessionProperty(SessionProperty reservedSessionProperty) {
		this.sessionProperty = reservedSessionProperty;
		sessionProperty.updateRoom(this);
	}

	public void addMember(Member member) {
		this.members.add(member);
	}

	@Override
	public String toString() {
		return "Room{" +
			"id=" + id +
			", sessionId='" + sessionId + '\'' +
			", title='" + title + '\'' +
			", description='" + description + '\'' +
			", leaderId='" + leaderId + '\'' +
			", workspaceId='" + workspaceId + '\'' +
			", profile='" + profile + '\'' +
			", members='" + members.toString() + '\'' +
			'}';
	}

	public void updateTitle(String title) {
		this.title = title;
	}

	public void updateDescription(String description) {
		this.description = description;
	}
}