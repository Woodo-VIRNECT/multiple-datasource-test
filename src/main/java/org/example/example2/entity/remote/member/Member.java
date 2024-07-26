package org.example.example2.entity.remote.member;

import java.time.LocalDateTime;

import org.example.example2.entity.remote.BaseTimeEntity;
import org.example.example2.entity.remote.DeviceType;
import org.example.example2.entity.remote.room.Room;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id", nullable = false)
	private Long id;

	@Column(name = "workspace_id", nullable = false)
	private String workspaceId;

	@Column(name = "uuid", nullable = false)
	private String uuid;

	@Column(name = "connection_id", nullable = false)
	private String connectionId;

	@Column(name = "member_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private MemberType memberType;

	@Column(name = "device_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private DeviceType deviceType;

	@Column(name = "member_status", nullable = false)
	@Enumerated(EnumType.STRING)
	private MemberStatus memberStatus;

	@Column(name = "session_id", nullable = false)
	private String sessionId;

	@Column(name = "start_at", nullable = false)
	private LocalDateTime startDate;

	@Column(name = "end_at", nullable = false)
	private LocalDateTime endDate;

	@Column(name = "duration_sec", nullable = false)
	private Long durationSec;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private Room room;

	@Builder
	public Member(
		Room room,
		MemberType memberType,
		String workspaceId,
		String uuid,
		String sessionId
	) {
		this.room = room;
		this.memberType = memberType;
		this.workspaceId = workspaceId;
		this.uuid = uuid;
		this.sessionId = sessionId;

		// default set
		this.deviceType = DeviceType.UNKNOWN;
		this.memberStatus = MemberStatus.UNLOAD;
		this.connectionId = "";

		LocalDateTime now = LocalDateTime.now();
		this.startDate = now;
		this.endDate = now;
		this.durationSec = 0L;
	}

	public static Member create(Room room, String userId, MemberType memberType) {
		return Member.builder()
			.room(room)
			.memberType(memberType)
			.workspaceId(room.getWorkspaceId())
			.uuid(userId)
			.sessionId(room.getSessionId())
			.build();
	}

	public void resetStatusAndTypeForReInvitedMember() {
		this.memberStatus = MemberStatus.UNLOAD;
		this.memberType = MemberType.UNKNOWN;
	}

	@Override
	public String toString() {
		return "Member{" +
			"id=" + id +
			", memberType='" + memberType + '\'' +
			", deviceType='" + deviceType + '\'' +
			", memberStatus='" + memberStatus + '\'' +
			", uuid='" + uuid + '\'' +
			", sessionId='" + sessionId + '\'' +
			'}';
	}

	public void updateMemberStatus(MemberStatus memberStatus) {
		this.memberStatus = memberStatus;
	}

	public boolean isLeader() {
		return memberType.equals(MemberType.LEADER);
	}
}
