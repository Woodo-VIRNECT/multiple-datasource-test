package org.example.example2.entity.remote.reservation;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.example.example2.entity.remote.BaseTimeEntity;
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
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
