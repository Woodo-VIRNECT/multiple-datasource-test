package org.example.example2.entity.remote.chat;

import java.util.ArrayList;
import java.util.List;

import org.example.example2.entity.remote.BaseTimeEntity;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import com.vladmihalcea.hibernate.type.json.JsonType;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



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
// @TypeDef(name = "json", typeClass = JsonStringType.class)
// @Convert(attributeName = "entityAttrName", converter = JsonStringType.class)
public class Chat extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chat_id", nullable = false)
	private Long id;

	@Column(name = "workspace_id", nullable = false)
	private String workspaceId;

	@Column(name = "session_id", nullable = false)
	private String sessionId;

	@Builder
	public Chat(Long id, String workspaceId, String sessionId, List<ChatRedis> chatRedisList) {
		this.id = id;
		this.workspaceId = workspaceId;
		this.sessionId = sessionId;
		this.chatRedisList = chatRedisList;
	}

	// @Type(JsonType.class)
	@JdbcTypeCode(SqlTypes.JSON)
	@Lob
	@Column(name = "chat_redis_list")
	private List<ChatRedis> chatRedisList = new ArrayList<>();

	public static Chat createChat(String workspaceId, String sessionId, List<ChatRedis> chatRedisList) {
		return Chat.builder()
			.workspaceId(workspaceId)
			.sessionId(sessionId)
			.chatRedisList(chatRedisList)
			.build();
	}
}
