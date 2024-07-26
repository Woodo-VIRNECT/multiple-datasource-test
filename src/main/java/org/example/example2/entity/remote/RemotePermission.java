package org.example.example2.entity.remote;

import org.example.example2.entity.remote.BaseTimeEntity;
import org.example.example2.entity.remote.roomhistory.RoomHistory;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Audited
@AuditTable(value = "permission_aud")
@Entity
@Table(name = "permission")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Permission extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "permission_id")
	private Long id;

	@Column(name = "permission")
	private String permission;
}
