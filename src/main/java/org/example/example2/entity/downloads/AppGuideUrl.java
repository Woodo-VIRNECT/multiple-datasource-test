package com.virnect.download.domain;

import lombok.Getter;

@Getter
public enum AppGuideUrl {
	// REMOTE
	REMOTE_USER_GUIDE("https://file.virnect.com/Guide/remote_user_guide.pdf"),
	REMOTE_LINKFLOW_USER_GUIDE("https://file.virnect.com/Guide/remote_linkflow_user_guide.pdf"),
	REMOTE_HOLOLENS2_USER_GUIDE("https://file.virnect.com/Guide/remote_hololens2_user_guide.pdf"),
	REMOTE_MOBILE_USER_GUIDE("https://file.virnect.com/Guide/remote_mobile_user_guide.pdf"),
	REMOTE_MOZIWARE_USER_GUIDE("https://file.virnect.com/Guide/remote_moziware_user_guide.pdf"),
	REMOTE_REALWEAR_USER_GUIDE("https://file.virnect.com/Guide/remote_realwear_user_guide.pdf"),
	REMOTE_WEB_USER_GUIDE("https://file.virnect.com/Guide/remote_web_user_guide.pdf"),

	// MAKE
	MAKE_USER_GUIDE("https://file.virnect.com/Guide/make_user_guide.pdf"),

	// VIEW
	VIEW_PC_USER_GUIDE("https://file.virnect.com/Guide/view_PC_user_guide.pdf"),
	VIEW_MOBILE_USER_GUIDE("https://file.virnect.com/Guide/view_mobile_user_guide.pdf"),
	VIEW_REALWEAR_USER_GUIDE("https://file.virnect.com/Guide/view_realwear_user_guide.pdf"),
	VIEW_HOLOLENS2_USER_GUIDE("https://file.virnect.com/Guide/view_hololens2_user_guide.pdf"),

	// TRACK
	TRACK_USER_GUIDE("");

	private final String url;

	AppGuideUrl(String url) {
		this.url = url;
	}

}