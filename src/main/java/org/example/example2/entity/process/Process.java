package org.example.example2.entity.process;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Project: PF-ProcessManagement
 * DATE: 2020-02-04
 * AUTHOR: JohnMark (Chang Jeong Hyeon)
 * EMAIL: practice1356@gmail.com
 * DESCRIPTION:
 */
@Slf4j
@Getter
@Setter
@Entity
@Table(name = "process")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Process extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "process_id")
	private Long id;

	@Column(name = "process_name", length = 50, nullable = false)
	private String name;

	@Column(name = "position", length = 100)
	private String position;

	@Column(name = "start_date")
	private LocalDateTime startDate;

	@Column(name = "end_date")
	private LocalDateTime endDate;

	@Column(name = "reported_date")
	private LocalDateTime reportedDate;

	@Transient
	@Getter(AccessLevel.PROTECTED)
	@Enumerated(value = EnumType.STRING)
	private Conditions conditions;

	@Transient
	@Getter(AccessLevel.PROTECTED)
	private Integer progressRate;

	@Column(name = "state")
	@Enumerated(value = EnumType.STRING)
	private State state;

	@Column(name = "content_uuid", unique = true)
	private String contentUUID;

	@Column(name = "content_manager_uuid")
	private String contentManagerUUID;

	@Column(name = "workspace_uuid")
	private String workspaceUUID;

	@Column(name = "worker_uuid")
	private String workerUUID;

	@OneToMany(mappedBy = "process")
	private List<Target> targetList = new ArrayList<>();

	@OneToMany(mappedBy = "process")
	private List<SubProcess> subProcessList = new ArrayList<>();

	public void addTarget(Target target) {
		target.setProcess(this);
		log.info("CREATE TARGET ---> {}", target.toString());
		this.targetList.add(target);
	}

	public void addSubProcess(SubProcess subProcess) {
		subProcess.setProcess(this);
		log.info("CREATE SUB PROCESS ---> {}", subProcess.toString());
		this.subProcessList.add(subProcess);
	}

	@Builder
	public Process(
		String name, String position, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime reportedDate,
		Conditions conditions, Integer progressRate, State state, String contentUUID, String contentManagerUUID,
		String workspaceUUID, String workerUUID, List<Target> targetList, List<SubProcess> subProcessList
	) {
		this.name = name;
		this.position = position;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reportedDate = reportedDate;
		this.conditions = conditions;
		this.progressRate = progressRate;
		this.state = state;
		this.contentUUID = contentUUID;
		this.contentManagerUUID = contentManagerUUID;
		this.workspaceUUID = workspaceUUID;
		this.workerUUID = workerUUID;
		this.targetList = new ArrayList<>();
		this.subProcessList = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Process{" +
			"id=" + id +
			", name='" + name + '\'' +
			", position='" + position + '\'' +
			", startDate=" + startDate +
			", endDate=" + endDate +
			", reportedDate=" + reportedDate +
			", conditions=" + conditions +
			", progressRate=" + progressRate +
			", state=" + state +
			", contentUUID='" + contentUUID + '\'' +
			", contentManagerUUID='" + contentManagerUUID + '\'' +
			", workspaceUUID='" + workspaceUUID + '\'' +
			", workerUUID='" + workerUUID + '\'' +
			", targetList=" + targetList +
			", subProcessList=" + subProcessList +
			'}';
	}
}
