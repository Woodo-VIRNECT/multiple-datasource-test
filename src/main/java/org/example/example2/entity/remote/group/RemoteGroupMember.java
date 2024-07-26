package org.example.example2.entity.remote.group;

import org.example.example2.entity.remote.BaseTimeEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "remote_group_members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RemoteGroupMember extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "remote_group_member_id", nullable = false)
	private Long id;

	@Column(name = "uuid", nullable = false)
	private String uuid;

	@Column(name = "deleted", nullable = false)
	private boolean deleted;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "remote_group_id")
	private RemoteGroup remoteGroup;

	@Builder
	public RemoteGroupMember(
		RemoteGroup remoteGroup,
		String uuid
	) {
		this.remoteGroup = remoteGroup;
		this.uuid = uuid;
	}

}
