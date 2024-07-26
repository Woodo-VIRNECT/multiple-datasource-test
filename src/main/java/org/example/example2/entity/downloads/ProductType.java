package com.virnect.download.domain;

import lombok.Getter;

import com.virnect.download.exception.DownloadException;
import com.virnect.download.global.error.ErrorCode;

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

	public static ProductType getProductTypeByDescription(String description) {
		for (ProductType productType : ProductType.values()) {
			if (productType.getDescription().equals(description)) {
				return productType;
			}
		}
		throw new DownloadException(ErrorCode.ERR_APP_UPLOAD_FAIL_PRODUCT_INFO_NOT_FOUND);
	}

}
