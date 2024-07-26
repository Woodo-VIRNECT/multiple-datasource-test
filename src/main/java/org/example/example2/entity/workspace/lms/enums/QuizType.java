package com.virnect.workspace.domain.lms.enums;

public enum QuizType {
	OX,
	MULTIPLE_CHOICE;

	public boolean isOX() {
		return OX.equals(this);
	}
}
