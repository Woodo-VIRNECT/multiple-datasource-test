package org.example.example2.entity.process;
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
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "priority")
    private Integer priority;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private ItemType type;

    @Column(name = "title")
    private String title;

    @Column(name = "photo_file_path")
    private String path;

    @Column(name = "answer")
    private String answer;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "result")
    private Result result;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Report report;

    @Builder
    public Item(Integer priority, ItemType type, String title, String path, String answer, Result result, Report report) {
        this.priority = priority;
        this.type = type;
        this.title = title;
        this.path = path;
        this.answer = answer;
        this.result = result;
        this.report = report;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", priority=" + priority +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", answer='" + answer + '\'' +
                ", result=" + result +
//                ", report=" + report +      // 무한 toString 방지
                '}';
    }
}
