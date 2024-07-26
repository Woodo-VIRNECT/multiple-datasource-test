package org.example.example2.entity.messages;

import lombok.Getter;

/**
 * Project: PF-Message
 * DATE: 2020-06-15
 * AUTHOR: jkleee (Jukyoung Lee)
 * EMAIL: ljk@virnect.com
 * DESCRIPTION:
 */
@Getter
public enum MessageType {
    EMAIL("email"),
    PUSH("push"),
    EVENT("event");

    private String value;
    MessageType(String value) {
        this.value = value;
    }

}
