package com.virnect.data.domain.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.virnect.data.domain.Permission;

@Getter
@Setter
@Audited
@Entity
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
	private Permission permission;

	@Builder
	public MemberPermission(
		Member member,
		Permission permission
	) {
		//this.member = member;
		this.permission = permission;
	}

}
