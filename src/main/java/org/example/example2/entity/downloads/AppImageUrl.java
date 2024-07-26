package com.virnect.download.domain;

import lombok.Getter;

@Getter
public enum AppImageUrl {
	REMOTE_PC("https://file.virnect.com/resource/remote_pc.png"),
	REMOTE_MOBILE("https://file.virnect.com/resource/remote_android.png"),
	REMOTE_REALWEAR("https://file.virnect.com/resource/view_realwear.png"),
	REMOTE_LINKFLOW("https://file.virnect.com/resource/remote_linkflow.png"),
	REMOTE_HOLOLENS2("https://file.virnect.com/resource/remote_hololens2.png"),
	REMOTE_MOZIWARE("https://file.virnect.com/resource/remote_moziwear.png"),

	VIEW_PC("https://file.virnect.com/resource/view_pc.png"),
	VIEW_REALWEAR("https://file.virnect.com/resource/view_realwear.png"),
	VIEW_MOBILE("https://file.virnect.com/resource/view_android.png"),

	MAKE_PC("https://file.virnect.com/resource/make_pc.png");

	private final String url;

	AppImageUrl(String url) {
		this.url = url;
	}

}
