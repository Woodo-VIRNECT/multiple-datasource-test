package com.virnect.data.domain.session;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.virnect.data.domain.BaseTimeEntity;
import com.virnect.data.domain.room.Room;
import com.virnect.data.dto.request.reservation.ReservationCreateRequest;

@Entity
@Getter
@Setter
@Table(name = "session_property")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SessionProperty extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "session_property_id", nullable = false)
	private Long id;

	@Column(name = "media_mode", nullable = false)
	private String mediaMode;

	@Column(name = "recording_mode", nullable = false)
	private String recordingMode;

	@Column(name = "default_output_mode", nullable = false)
	private String defaultOutputMode;

	@Column(name = "default_recording_layout", nullable = false)
	private String defaultRecordingLayout;

    /*@Column(name = "custom_session_id", unique = true)
    private String customSessionId;*/

	@Column(name = "recording", nullable = false)
	private boolean recording;

	@Column(name = "keepalive", nullable = false)
	private boolean keepalive;

	@Column(name = "session_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private SessionType sessionType;

	@Column(name = "publisher_id", nullable = false)
	private String publisherId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private Room room;

	@Builder
	public SessionProperty(
		String mediaMode,
		String recordingMode,
		String defaultOutputMode,
		String defaultRecordingLayout,
		boolean recording,
		boolean keepalive,
		SessionType sessionType,
		String publisherId,
		Room room

	) {
		this.mediaMode = mediaMode;
		this.recordingMode = recordingMode;
		this.defaultOutputMode = defaultOutputMode;
		this.defaultRecordingLayout = defaultRecordingLayout;
		this.recording = recording;
		this.keepalive = keepalive;
		this.sessionType = sessionType;
		this.publisherId = publisherId;
		this.room = room;
	}

	public static SessionProperty createReservedSessionProperty(ReservationCreateRequest createRequest) {
		//mediaMode, recordingMode, DefaultOutputMode, defaultRecordingLayout, recording, keepalive 는 전부 오픈비두에서 사용하는 값들로 현재 리모트에서는
		// 모두 고정된 값으로 사용하고 있음.
		return SessionProperty.builder()
			.mediaMode("ROUTED")
			.recordingMode("MANUAL")
			.defaultOutputMode("COMPOSED")
			.defaultRecordingLayout("BEST_FIT")
			.recording(true)
			.keepalive(false)
			.sessionType(SessionType.PRIVATE)
			.publisherId(createRequest.getLeaderId())
			.build();
	}

	@Override
	public String toString() {
		return "SessionProperty{" +
			"id=" + id +
			", mediaMode='" + mediaMode + '\'' +
			", recordingMode='" + recordingMode + '\'' +
			", defaultOutputMode='" + defaultOutputMode + '\'' +
			", defaultRecordingLayout='" + defaultRecordingLayout + '\'' +
			", recording='" + recording + '\'' +
			", keepalive='" + keepalive + '\'' +
			", sessionType='" + sessionType + '\'' +
			", publisherId='" + publisherId + '\'' +
			'}';
	}

	public void updateRoom(Room room) {
		this.room = room;
	}
}
