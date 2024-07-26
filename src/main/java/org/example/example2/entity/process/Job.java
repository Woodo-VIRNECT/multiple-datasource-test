package com.virnect.process.domain;

import com.virnect.process.application.ProgressManager;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
@Audited
@Table(name = "job")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Job extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long id;

    @Column(name = "priority", nullable = false)
    private Integer priority;

    @Column(name = "job_name", nullable = false)
    private String name;

    @Transient
    @Getter(AccessLevel.PROTECTED)
    private Integer progressRate;

    @Transient
    @Getter(AccessLevel.PROTECTED)
    @Enumerated(EnumType.STRING)
    private Conditions conditions;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "is_reported")
    private YesOrNo isReported;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "result")
    private Result result;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_process_id")
    private SubProcess subProcess;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "job")
    private List<Report> reportList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "job")
    private List<Issue> issueList = new ArrayList<>();


    public void addReport(Report report) {
        report.setJob(this);
        log.info("CREATE REPORT ---> {}", report.toString());
        this.reportList.add(report);
    }

    public void addIssue(Issue issue) {
        issue.setJob(this);
        log.info("CREATE ISSUE ---> {}", issue.toString());
        this.issueList.add(issue);
    }

    // 공정 상태 조회
    public Conditions getConditions() {
        return ProgressManager.getJobConditions(this);
    }

    // 공정률 조회
    public Integer getProgressRate() {
        return ProgressManager.getJobProgressRate(this);
    }

    @Builder
    public Job(Integer priority, String name, Integer progressRate, Conditions conditions, YesOrNo isReported, Result result, SubProcess subProcess, List<Report> reportList, List<Issue> issueList) {
        this.priority = priority;
        this.name = name;
        this.progressRate = progressRate;
        this.conditions = conditions;
        this.isReported = isReported;
        this.result = result;
        this.subProcess = subProcess;
        this.reportList = new ArrayList<>();
        this.issueList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", priority=" + priority +
                ", name='" + name + '\'' +
                ", progressRate=" + progressRate +
                ", conditions=" + conditions +
                ", isReported=" + isReported +
                ", result=" + result +
//                ", subProcess=" + subProcess +      // 무한 toString 방지
                ", reportList=" + reportList +
                ", issueList=" + issueList +
                '}';
    }
}
