package org.example.example2.entity.downloads;

import lombok.Getter;

/**
 * Project        : PF-Download
 * DATE           : 2024/04/12
 * AUTHOR         : dnejdzlr2 (Woodo Lee)
 * EMAIL          : dnejdzlr2@virnect.com
 * DESCRIPTION    :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/12      dnejdzlr2          기존 DeviceType.java 파일은 생성되어 있었지만 사용하고 있지않았음, 따라서 사용하도록 변경작업 잔행
 */
@Getter
public enum DeviceType {
	PC("PC"),
	MOBILE("Mobile"),
	REALWEAR("Realwear"),
	LINKFLOW("Linkflow"),
	HOLOLENS("Hololens"),
	METAQUEST("Metaquest"),
	MOZIWARE("Moziware");

	private final String description;

	DeviceType(String description) {
		this.description = description;
	}

}
