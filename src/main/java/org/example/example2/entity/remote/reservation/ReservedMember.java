package org.example.example2.entity.remote.reservation;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.example.example2.entity.remote.BaseTimeEntity;
import org.example.example2.entity.remote.member.MemberType;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@Table(name = "reserved_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservedMember extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reserved_member_id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "remote_reservation_id")
	private RemoteReservation remoteReservation;

	@Column(name = "member_email", nullable = false)
	private String memberEmail;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "member_type", nullable = false)
	private MemberType memberType;

	@Builder.Default
	@Enumerated(value = EnumType.STRING)
	@Column(name = "reserved_member_status", nullable = false)
	private ReservedMemberStatus memberStatus = ReservedMemberStatus.INVITED;

	@Column(name = "member_uuid")
	private String memberUuid;

	public static ReservedMember of(String userUuid, MemberType memberType, String email) {
		return ReservedMember.builder()
			.memberType(memberType)
			.memberUuid(userUuid)
			.memberEmail(email)
			.build();
	}

	public void updateRemoteReservation(RemoteReservation remoteReservation) {
		this.remoteReservation = remoteReservation;
	}

	public boolean isDeleted() {
		return this.memberStatus == ReservedMemberStatus.DELETED;
	}

	public boolean isNotDeleted() {
		return !isDeleted();
	}

	public boolean isSecessionMember() {
		return this.memberType == MemberType.SECESSION;
	}

	public boolean isNotSecessionMember() {
		return !isSecessionMember();
	}

	public boolean isGuest() {
		return this.memberType == MemberType.GUEST;
	}

	public boolean isNotGuest() {
		return !isGuest();
	}

	public void updateReservedMemberStatus(ReservedMemberStatus reservedMemberStatus) {
		this.memberStatus = reservedMemberStatus;
	}

	public boolean isLeader() {
		return this.memberType == MemberType.LEADER;
	}

	public boolean isNotLeader() {
		return !isLeader();
	}
}
