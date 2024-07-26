package com.virnect.data.domain.chat;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonStringType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.virnect.data.domain.BaseTimeEntity;

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
@Entity
@Getter
@Table(name = "chats")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Chat extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chat_id", nullable = false)
	private Long id;

	@Column(name = "workspace_id", nullable = false)
	private String workspaceId;

	@Column(name = "session_id", nullable = false)
	private String sessionId;

	@Type(type = "json")
	@Column(columnDefinition = "longtext")
	private List<ChatRedis> chatRedisList = new ArrayList<>();

	@Builder
	public Chat(Long id, String workspaceId, String sessionId, List<ChatRedis> chatRedisList) {
		this.id = id;
		this.workspaceId = workspaceId;
		this.sessionId = sessionId;
		this.chatRedisList = chatRedisList;
	}

	public static Chat createChat(String workspaceId, String sessionId, List<ChatRedis> chatRedisList) {
		return Chat.builder()
			.workspaceId(workspaceId)
			.sessionId(sessionId)
			.chatRedisList(chatRedisList)
			.build();
	}
}
