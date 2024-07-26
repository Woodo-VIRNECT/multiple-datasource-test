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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
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
@Table(name = "sub_process")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubProcess extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_process_id")
    private Long id;

    @Column(name = "priority", nullable = false)
    private Integer priority;

    @Column(name = "sub_process_name", nullable = false)
    private String name;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "reported_date")
    private LocalDateTime reportedDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "is_recent")
    private YesOrNo isRecent;

    @Transient
    @Getter(AccessLevel.PROTECTED)
    private Integer progressRate;

    @Transient
    @Getter(AccessLevel.PROTECTED)
    @Enumerated(value = EnumType.STRING)
    private Conditions conditions;

    @Column(name = "worker_uuid")
    private String workerUUID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_id")
    private Process process;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subProcess")
    private List<Job> jobList = new ArrayList<>();

    public void addJob(Job job) {
        job.setSubProcess(this);
        log.info("CREATE JOB ---> {}", job.toString());
        jobList.add(job);
    }


    @Builder
    public SubProcess(Integer priority, String name, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime reportedDate, YesOrNo isRecent, Integer progressRate, Conditions conditions, String workerUUID, Process process, List<Job> jobList) {
        this.priority = priority;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reportedDate = reportedDate;
        this.isRecent = isRecent;
        this.progressRate = progressRate;
        this.conditions = conditions;
        this.workerUUID = workerUUID;
        this.process = process;
        this.jobList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "SubProcess{" +
                "id=" + id +
                ", priority=" + priority +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", reportedDate=" + reportedDate +
                ", isRecent=" + isRecent +
                ", progressRate=" + progressRate +
                ", conditions=" + conditions +
                ", workerUUID='" + workerUUID + '\'' +
//                ", process=" + process +      // 무한 toString 방지
                ", jobList=" + jobList +
                '}';
    }
}
