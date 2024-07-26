package org.example.example2.entity.remote.group;

import java.util.ArrayList;
import java.util.List;

import org.example.example2.entity.remote.BaseTimeEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "favorite_groups")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FavoriteGroup extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "favorite_group_id", nullable = false)
	private Long id;

	@Column(name = "workspace_id", nullable = false)
	private String workspaceId;

	@Column(name = "group_id", nullable = false)
	private String groupId;

	@Column(name = "group_name", nullable = false)
	private String groupName;

	@Column(name = "uuid", nullable = false)
	private String uuid;

	@OneToMany(mappedBy = "favoriteGroup", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<FavoriteGroupMember> favoriteGroupMembers = new ArrayList<>();

	@Builder
	public FavoriteGroup(
		String workspaceId,
		String groupId,
		String groupName,
		String uuid
	) {
		this.workspaceId = workspaceId;
		this.groupId = groupId;
		this.groupName = groupName;
		this.uuid = uuid;
	}

}
