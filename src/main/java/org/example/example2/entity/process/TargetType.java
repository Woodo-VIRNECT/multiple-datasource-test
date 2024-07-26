package org.example.example2.entity.process;

import lombok.ToString;

/**
 * @author hangkee.min (henry)
 * @project PF-ProcessManagement
 * @email hkmin@virnect.com
 * @description
 * @since 2020.04.08
 */
@ToString
public enum TargetType {
    QR("QR"),
    VTarget("VTarget"),
    VR("VR"),
    Image("Image");

    private String message;

    TargetType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
