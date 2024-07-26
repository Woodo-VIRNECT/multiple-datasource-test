package org.example.example2.entity.messages;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Project: PF-Message
 * DATE: 2020-04-03
 * AUTHOR: jkleee (Jukyoung Lee)
 * EMAIL: ljk@virnect.com
 * DESCRIPTION:
 */
@Entity
@Table(name = "mail_history")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MailHistory extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mail_history_id")
    private Long id;

    @Column(name = "receiver", nullable = false)
    private String receiver;

    @Column(name = "sender", nullable = false)
    private String sender;

    @Column(name = "subject", nullable = false)
    private String subject;

    // @Column(name = "contents", nullable = false, columnDefinition = "LONGTEXT")
    // private String contents;

    @Lob // oracle 에서는 CLOB 데이터로 정상적으로 저장되는데 mysql 에서는 tinyText or text 로 저장되는 이슈가 있음 -> 이 둘은 용량이 작으므로 오류가발생할 수 있음 -> mediumtext(16MB) or LONGTEXT(4GB.. 너무 큼) 로 변경해야함
    @Column(name = "contents", nullable = false)
    private String contents;

    @Column(name = "result_code", nullable = false)
    private Integer resultCode;

    @Builder
    public MailHistory(String receiver, String sender, String subject, String contents, Integer resultCode) {
        this.receiver = receiver;
        this.sender = sender;
        this.subject = subject;
        this.contents = contents;
        this.resultCode = resultCode;
    }
}

