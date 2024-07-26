package org.example.example2.entity.remote.roomhistory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.example.example2.entity.remote.BaseTimeEntity;
import org.example.example2.entity.remote.member.MemberHistory;
import org.example.example2.entity.remote.room.Room;
import org.example.example2.entity.remote.session.SessionPropertyHistory;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
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

@Entity
@Getter
@Setter
@Audited
@AuditTable(value = "rooms_history_aud")
@Table(name = "rooms_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomHistory extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_history_id", nullable = false)
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

	@Column(name = "license_name", nullable = false)
	private String licenseName;

	@Column(name = "active_at", nullable = false)
	private LocalDateTime activeDate;

	@Column(name = "unactive_at", nullable = false)
	private LocalDateTime unactiveDate;

	@Column(name = "duration_sec", nullable = false)
	private Long durationSec;

	@OneToMany(mappedBy = "roomHistory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<MemberHistory> memberHistories = new ArrayList<>();

	@OneToOne(mappedBy = "roomHistory", cascade = CascadeType.ALL, orphanRemoval = true)
	private SessionPropertyHistory sessionPropertyHistory;

	@Builder
	public RoomHistory(
		String sessionId,
		String title,
		String description,
		String profile,
		String leaderId,
		String workspaceId,
		String licenseName,
		int maxUserCount,
		SessionPropertyHistory sessionPropertyHistory,
		LocalDateTime activeDate,
		LocalDateTime unactiveDate,
		Long durationSec
	) {
		this.sessionId = sessionId;
		this.title = title;
		this.description = description;
		this.profile = profile;
		this.leaderId = leaderId;
		this.workspaceId = workspaceId;
		this.licenseName = licenseName;
		this.maxUserCount = maxUserCount;
		this.sessionPropertyHistory = sessionPropertyHistory;
		this.activeDate = activeDate;
		this.unactiveDate = unactiveDate;
		this.durationSec = durationSec;
	}

	public static RoomHistory from(Room room) {
		LocalDateTime endTime = LocalDateTime.now();
		return RoomHistory.builder()
			.sessionId(room.getSessionId())
			.title(room.getTitle())
			.description(room.getDescription())
			.profile(room.getProfile())
			.leaderId(room.getLeaderId())
			.workspaceId(room.getWorkspaceId())
			.maxUserCount(room.getMaxUserCount())
			.licenseName(room.getLicenseName())
			.activeDate(room.getActiveDate())
			.unactiveDate(endTime)
			.durationSec(Duration.between(room.getActiveDate(), endTime).getSeconds())
			.build();
	}

	public void updateSessionPropertyHistory(SessionPropertyHistory sessionPropertyHistory) {
		this.sessionPropertyHistory = sessionPropertyHistory;
	}

	@Override
	public String toString() {
		return "RoomHistory{" +
			"id=" + id +
			", sessionId='" + sessionId + '\'' +
			", title='" + title + '\'' +
			", description='" + description + '\'' +
			", leaderId='" + leaderId + '\'' +
			", workspaceId='" + workspaceId + '\'' +
			", profile='" + profile + '\'' +
			", durationTime='" + durationSec + '\'' +
			", memberHistories='" + memberHistories.toString() + '\'' +
			'}';
	}

	public void addMemberHistory(MemberHistory memberHistory) {
		this.memberHistories.add(memberHistory);
		memberHistory.updateRoomHistory(this);
	}
}
