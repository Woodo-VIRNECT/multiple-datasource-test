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
 * 2024/04/12      dnejdzlr2          최초 생성
 */
@Getter
public enum ProductType {
	REMOTE("remote"),
	MAKE("make"),
	VIEW("view"),
	PLATFORM("platform"),
	TRACK("track");

	private final String description;

	ProductType(String description) {
		this.description = description;
	}

}
