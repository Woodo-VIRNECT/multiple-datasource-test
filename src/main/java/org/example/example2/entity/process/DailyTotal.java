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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "daily_total")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyTotal extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "total_rate", nullable = false)
    private int totalRate;

    @Column(name = "total_count_processes", nullable = false)
    private int totalCountProcesses;

    @OneToMany(mappedBy = "dailyTotal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DailyTotalWorkspace> dailyTotalWorkspaceList = new ArrayList<>();

    public void addDailyTotalWorkspace(DailyTotalWorkspace dailyTotalWorkspace) {
        dailyTotalWorkspace.setDailyTotal(this);
        dailyTotalWorkspaceList.add(dailyTotalWorkspace);
    }

    @Builder
    public DailyTotal(int totalRate, int totalCountProcesses, List<DailyTotalWorkspace> dailyTotalWorkspaceList) {
        this.totalRate = totalRate;
        this.totalCountProcesses = totalCountProcesses;
        this.dailyTotalWorkspaceList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "DailyTotal{" +
                "id=" + id +
                ", totalRate=" + totalRate +
                ", totalCountProcesses=" + totalCountProcesses +
                ", dailyTotalWorkspaceList=" + dailyTotalWorkspaceList +
                '}';
    }
}
