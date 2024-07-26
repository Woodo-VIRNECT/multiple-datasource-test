package com.virnect.data.domain.member;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.virnect.data.domain.BaseTimeEntity;
import com.virnect.data.domain.DeviceType;
import com.virnect.data.domain.roomhistory.RoomHistory;

@Entity
@Getter
@Setter
@Audited
@Table(name = "members_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberHistory extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_history_id", nullable = false)
	private Long id;

	@Column(name = "workspace_id", nullable = false)
	private String workspaceId;

	@Column(name = "uuid", nullable = false)
	private String uuid;

   /* @Column(name = "email", nullable = false)
    private String email;*/

	@Column(name = "member_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private MemberType memberType;

	@Column(name = "device_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private DeviceType deviceType;

	@Column(name = "session_id", nullable = false)
	private String sessionId;

	@Column(name = "start_at", nullable = false)
	private LocalDateTime startDate;

	@Column(name = "end_at", nullable = false)
	private LocalDateTime endDate;

	@Column(name = "duration_sec", nullable = false)
	private Long durationSec;

	@Column(name = "history_deleted", nullable = false)
	private boolean historyDeleted;

	@ManyToOne(targetEntity = RoomHistory.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "room_history_id")
	private RoomHistory roomHistory;

	@Builder
	public MemberHistory(
		RoomHistory roomHistory,
		MemberType memberType,
		DeviceType deviceType,
		String workspaceId,
		String uuid,
		String sessionId,
		LocalDateTime startDate,
		LocalDateTime endDate,
		Long durationSec
	) {
		this.roomHistory = roomHistory;
		this.memberType = memberType;
		this.deviceType = deviceType;
		this.workspaceId = workspaceId;
		this.uuid = uuid;
		//this.email = email;
		this.sessionId = sessionId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.durationSec = durationSec;
		this.historyDeleted = false;
	}

	public static MemberHistory from(Member roomMember) {
		return MemberHistory.builder()
			.workspaceId(roomMember.getWorkspaceId())
			.uuid(roomMember.getUuid())
			.memberType(roomMember.getMemberType())
			.deviceType(roomMember.getDeviceType())
			.sessionId(roomMember.getSessionId())
			.startDate(roomMember.getStartDate())
			.endDate(roomMember.getEndDate())
			.durationSec(roomMember.getDurationSec())
			.build();
	}

	@Override
	public String toString() {
		return "MemberHistory{" +
			"id=" + id +
			", memberType='" + memberType + '\'' +
			", deviceType='" + deviceType + '\'' +
			", uuid='" + uuid + '\'' +
			//", email='" + email + '\'' +
			", sessionId='" + sessionId + '\'' +
			'}';
	}

	public void updateRoomHistory(RoomHistory roomHistory) {
		this.roomHistory = roomHistory;
	}
}
