package com.virnect.data.domain.chat;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Builder;
import lombok.Getter;

import com.virnect.data.dto.response.rpc.ClientMetaData;

/**
 * Project        : RM-Service
 * DATE           : 2023-05-16
 * AUTHOR         : VIRNECT-JINTAE (Jintae Kim)
 * EMAIL          : jtkim@virnect.com
 * DESCRIPTION    :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-16      VIRNECT-JINTAE          최초 생성
 */
@Getter
@RedisHash("chats")
public class ChatRedis {
	@Id
	private String id;
	@Indexed
	private String sessionId;
	private String userId;
	private String nickname;
	private String message;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime createdAt;

	@TimeToLive(unit = TimeUnit.HOURS)
	private Long expired;

	@Builder
	@JsonCreator
	public ChatRedis(
		String id, String sessionId, String userId, String nickname, String message, LocalDateTime createdAt, Long expired
	) {
		this.id = id;
		this.sessionId = sessionId;
		this.userId = userId;
		this.nickname = nickname;
		this.message = message;
		this.createdAt = createdAt;
		this.expired = expired;
	}

	public static ChatRedis create(String sessionId, ClientMetaData clientMetaData, String chatMessage) {
		return ChatRedis.builder()
			.sessionId(sessionId)
			.userId(clientMetaData.getClientData())
			.nickname(clientMetaData.getNickname())
			.message(chatMessage)
			.createdAt(LocalDateTime.now())
			.expired(24L)
			.build();
	}
}