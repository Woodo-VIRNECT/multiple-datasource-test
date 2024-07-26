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
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "daily_total_workspace")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyTotalWorkspace extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "total_rate", nullable = false)
    private int totalRate;

    @Column(name = "total_count_processes", nullable = false)
    private int totalCountProcesses;

    @Column(name = "workspace_uuid", nullable = false)
    private String workspaceUUID;

    @ManyToOne
    @JoinColumn(name = "daily_total_id")
    private DailyTotal dailyTotal;

    @Builder
    public DailyTotalWorkspace(int totalRate, int totalCountProcesses, String workspaceUUID) {
        this.totalRate = totalRate;
        this.totalCountProcesses = totalCountProcesses;
        this.workspaceUUID = workspaceUUID;
    }

    @Override
    public String toString() {
        return "DailyTotalWorkspace{" +
                "id=" + id +
                ", totalRate=" + totalRate +
                ", totalCountProcesses=" + totalCountProcesses +
                ", workspaceUUID='" + workspaceUUID + '\'' +
                '}';
    }
}
