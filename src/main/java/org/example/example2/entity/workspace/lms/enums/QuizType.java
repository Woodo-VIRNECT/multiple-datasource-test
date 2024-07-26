package org.example.example2.entity.workspace.lms.enums;

public enum QuizType {
	OX,
	MULTIPLE_CHOICE;

	public boolean isOX() {
		return OX.equals(this);
	}
}
