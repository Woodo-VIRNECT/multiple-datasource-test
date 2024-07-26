package org.example.example2.entity.process;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

/**
 * Project: PF-ProcessManagement
 * DATE: 2020-02-04
 * AUTHOR: JohnMark (Chang Jeong Hyeon)
 * EMAIL: practice1356@gmail.com
 * DESCRIPTION:
 */
@Getter
@Setter
@Entity
@Table(name = "report")
@NoArgsConstructor
public class Report extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @Transient
    @Getter(AccessLevel.PROTECTED)
    private Integer progressRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "report")
    private List<Item> itemList = new ArrayList<>();


    @Builder
    public Report(Integer progressRate, Job job, List<Item> itemList) {
        this.progressRate = progressRate;
        this.job = job;
        this.itemList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", progressRate=" + progressRate +
//                ", job=" + job +      // 무한 toString 방지
                ", itemList=" + itemList +
                '}';
    }
}
