package org.example.example2.entity.remote.member;

import org.example.example2.entity.remote.RemotePermission;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Audited
@Entity
@AuditTable(value = "member_permission_aud")
@Table(name = "member_permission")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberPermission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_permission_id")
	private Long id;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;*/

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "permission_id")
	private RemotePermission remotePermission;

	@Builder
	public MemberPermission(
		Member member,
		RemotePermission remotePermission
	) {
		//this.member = member;
		this.remotePermission = remotePermission;
	}

}
