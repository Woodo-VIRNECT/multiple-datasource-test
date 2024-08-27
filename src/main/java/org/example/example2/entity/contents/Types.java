package org.example.example2.entity.contents;

import lombok.Getter;

/**
 * Project        : PF-ContentManagement
 * DATE           : 2020-04-10
 * AUTHOR         : hangkee.min (henry)
 * EMAIL          : hkmin@virnect.com
 * DESCRIPTION    : Contents Type Domain
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 */
@Getter
public enum Types {
	AUGMENTED_REALITY("증강현실"),
	ASSISTED_REALITY("보조현실"),
	CROSS_PLATFORM("크로스플랫폼"),
	MIXED_REALITY("혼합현실"),
	NOT_DEFINED("정의되지 않음");

	private final String message;

	Types(String message) {
		this.message = message;
	}

}
