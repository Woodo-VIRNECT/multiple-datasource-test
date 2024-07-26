package com.virnect.data.domain.reservation;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.virnect.data.domain.BaseTimeEntity;
import com.virnect.data.dto.request.reservation.ReservationCreateRequest;

/**
 * Project        : RM-Service
 * DATE           : 2023-11-16
 * AUTHOR         : VIRNECT (John Yoo)
 * EMAIL          : jonghyun.yoo@virnect.com
 * DESCRIPTION    :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-16      VIRNECT          최초 생성
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@Table(name = "remote_reservations",
	indexes = @Index(name = "idx_remote_reservations_start_time", columnList = "start_time"),
	uniqueConstraints = {@UniqueConstraint(name = "uk_remote_workspace_id_and_session_id", columnNames = {"session_id", "workspace_id"})}
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RemoteReservation extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "remote_reservation_id", nullable = false)
	private Long id;

	@Builder.Default
	@OneToMany(mappedBy = "remoteReservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<ReservedMember> reservedMembers = new ArrayList<>();

	@Column(name = "timezone", nullable = false)
	@Convert(converter = Jsr310JpaConverters.ZoneIdConverter.class)
	private ZoneId zoneId;

	@Column(name = "start_time", nullable = false, columnDefinition = "TIMESTAMP")
	private OffsetDateTime startTime;

	@Column(name = "duration_in_minutes", nullable = false)
	private Integer durationInMinutes;

	@Column(name = "end_time", nullable = false, columnDefinition = "TIMESTAMP")
	private OffsetDateTime endTime;

	@Enumerated(EnumType.STRING)
	@Column(name = "reservation_status", nullable = false)
	private ReservationStatus reservationStatus;

	@Column(name = "workspace_id", nullable = false)
	private String workspaceUuid;

	@Column(name = "leader_id", nullable = false)
	private String leaderUuid;

	// 예약 대상이 되는 협업의 고유 식별자
	@Column(name = "session_id", nullable = false)
	private String sessionId;

	@Column(name = "is_notified", nullable = false)
	private boolean isNotified;

	public static RemoteReservation of(ReservationCreateRequest createRequest, String sessionId, List<ReservedMember> members) {
		ZoneId zoneId = createRequest.getTimezone();
		OffsetDateTime startOffsetDateTime = createStartTime(createRequest, zoneId);
		Integer durationInMinutes = createRequest.getDurationInMinutes();

		RemoteReservation remoteReservation = RemoteReservation.builder()
			.zoneId(zoneId)
			.durationInMinutes(durationInMinutes)
			.startTime(startOffsetDateTime)
			.endTime(startOffsetDateTime.plusMinutes(durationInMinutes))
			.workspaceUuid(createRequest.getWorkspaceId())
			.leaderUuid(createRequest.getLeaderId())
			.reservationStatus(ReservationStatus.WAITING)
			.sessionId(sessionId)
			.isNotified(false)
			.build();

		members.forEach(remoteReservation::addMember);
		return remoteReservation;
	}

	private static OffsetDateTime createStartTime(ReservationCreateRequest createRequest, ZoneId zoneId) {
		LocalDateTime startDateTime = createRequest.getStartDateTime();
		ZonedDateTime zonedDateTime = ZonedDateTime.of(startDateTime, zoneId);
		return zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).toOffsetDateTime();
	}

	public void addMember(ReservedMember reservedMember) {
		this.reservedMembers.add(reservedMember);
		reservedMember.updateRemoteReservation(this);
	}

	public void updateReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public void updateTimezone(ZoneId requestTimezone) {
		this.zoneId = requestTimezone;
	}

	public void updateStartTime(OffsetDateTime updatedStartTime) {
		this.startTime = updatedStartTime;
		this.updateEndTime(this.startTime.plusMinutes(durationInMinutes));
	}

	public void updateDuration(Integer durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
		this.updateEndTime(this.startTime.plusMinutes(durationInMinutes));
	}

	public void updateEndTime(OffsetDateTime updatedEndTime) {
		this.endTime = updatedEndTime;
	}
}
