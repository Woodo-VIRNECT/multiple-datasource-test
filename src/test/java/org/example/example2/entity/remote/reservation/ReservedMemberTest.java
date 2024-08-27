package org.example.example2.entity.remote.reservation;

import static org.junit.jupiter.api.Assertions.*;

import org.example.example2.entity.remote.member.MemberType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReservedMemberTest {

	@Test
	@DisplayName("탈퇴 멤버인지 확인하는 테스트")
	public void testIsSecessionMember() {
		// memberType이 SECESSION인 경우 테스트
		ReservedMember secessionMember = ReservedMember.builder()
			.memberType(MemberType.SECESSION)
			.build();
		assertTrue(secessionMember.isSecessionMember(), "이 멤버는 탈퇴 멤버로 식별되어야 합니다.");

		// memberType이 SECESSION이 아닌 경우 테스트
		ReservedMember guestMember = ReservedMember.builder()
			.memberType(MemberType.GUEST)
			.build();
		assertFalse(guestMember.isSecessionMember(), "이 멤버는 탈퇴 멤버가 아니어야 합니다.");
	}

	@Test
	@DisplayName("게스트 멤버인지 확인하는 테스트")
	public void testIsGuest() {
		// memberType이 GUEST인 경우 테스트
		ReservedMember guestMember = ReservedMember.builder()
			.memberType(MemberType.GUEST)
			.build();
		assertTrue(guestMember.isGuest(), "이 멤버는 게스트 멤버로 식별되어야 합니다.");

		// memberType이 GUEST가 아닌 경우 테스트
		ReservedMember leaderMember = ReservedMember.builder()
			.memberType(MemberType.LEADER)
			.build();
		assertFalse(leaderMember.isGuest(), "이 멤버는 게스트 멤버가 아니어야 합니다.");
	}

	@Test
	@DisplayName("리더 멤버인지 확인하는 테스트")
	public void testIsLeader() {
		// memberType이 LEADER인 경우 테스트
		ReservedMember leaderMember = ReservedMember.builder()
			.memberType(MemberType.LEADER)
			.build();
		assertTrue(leaderMember.isLeader(), "이 멤버는 리더 멤버로 식별되어야 합니다.");

		// memberType이 LEADER가 아닌 경우 테스트
		ReservedMember guestMember = ReservedMember.builder()
			.memberType(MemberType.GUEST)
			.build();
		assertFalse(guestMember.isLeader(), "이 멤버는 리더 멤버가 아니어야 합니다.");
	}

	@Test
	@DisplayName("삭제된 멤버인지 확인하는 테스트")
	public void testIsDeleted() {
		// memberStatus가 DELETED인 경우 테스트
		ReservedMember deletedMember = ReservedMember.builder()
			.memberStatus(ReservedMemberStatus.DELETED)
			.build();
		assertTrue(deletedMember.isDeleted(), "이 멤버는 삭제된 멤버로 식별되어야 합니다.");

		// memberStatus가 DELETED가 아닌 경우 테스트
		ReservedMember invitedMember = ReservedMember.builder()
			.memberStatus(ReservedMemberStatus.INVITED)
			.build();
		assertFalse(invitedMember.isDeleted(), "이 멤버는 삭제된 멤버가 아니어야 합니다.");
	}

	@Test
	@DisplayName("탈퇴 멤버가 아닌지 확인하는 테스트")
	public void testIsNotSecessionMember() {
		// memberType이 SECESSION이 아닌 경우 테스트
		ReservedMember guestMember = ReservedMember.builder()
			.memberType(MemberType.GUEST)
			.build();
		assertTrue(guestMember.isNotSecessionMember(), "이 멤버는 탈퇴 멤버가 아니어야 합니다.");

		// memberType이 SECESSION인 경우 테스트
		ReservedMember secessionMember = ReservedMember.builder()
			.memberType(MemberType.SECESSION)
			.build();
		assertFalse(secessionMember.isNotSecessionMember(), "이 멤버는 탈퇴 멤버여야 합니다.");
	}

	@Test
	@DisplayName("게스트 멤버가 아닌지 확인하는 테스트")
	public void testIsNotGuest() {
		// memberType이 GUEST가 아닌 경우 테스트
		ReservedMember leaderMember = ReservedMember.builder()
			.memberType(MemberType.LEADER)
			.build();
		assertTrue(leaderMember.isNotGuest(), "이 멤버는 게스트 멤버가 아니어야 합니다.");

		// memberType이 GUEST인 경우 테스트
		ReservedMember guestMember = ReservedMember.builder()
			.memberType(MemberType.GUEST)
			.build();
		assertFalse(guestMember.isNotGuest(), "이 멤버는 게스트 멤버여야 합니다.");
	}

	@Test
	@DisplayName("리더가 아닌 멤버인지 확인하는 테스트")
	public void testIsNotLeader() {
		// memberType이 LEADER가 아닌 경우 테스트
		ReservedMember guestMember = ReservedMember.builder()
			.memberType(MemberType.GUEST)
			.build();
		assertTrue(guestMember.isNotLeader(), "이 멤버는 리더가 아니어야 합니다.");

		// memberType이 LEADER인 경우 테스트
		ReservedMember leaderMember = ReservedMember.builder()
			.memberType(MemberType.LEADER)
			.build();
		assertFalse(leaderMember.isNotLeader(), "이 멤버는 리더여야 합니다.");
	}

}
