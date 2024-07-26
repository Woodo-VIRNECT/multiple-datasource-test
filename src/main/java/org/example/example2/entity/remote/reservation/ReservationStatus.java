package org.example.example2.entity.remote.reservation;

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
public enum ReservationStatus {
	WAITING, // 예약이 생성되었고, 예약한 날짜가 되기를 기다리는 상태
	QUEUED, // 예약한 날짜에 Message Queue에 들어갔고, 예약한 시간을 기다리는 상태
	STARTED, // 예약의 협업이 시작된 상태
	FINISHED, // 예약의 협업이 진행 후 종료된 상태
	DELETED // 예약이 삭제된 상태
}
