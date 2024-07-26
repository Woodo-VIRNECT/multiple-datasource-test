package org.example.example2.entity.workspace.lms.enums;

public enum LearningStatus {
	UNSTARTED,
	IN_PROGRESS,
	COMPLETED,
	INCOMPLETE;

	public boolean isCompleted() {
		return this.equals(COMPLETED);
	}

	public boolean isUnstarted() {
		return this.equals(UNSTARTED);
	}

	public boolean isInProgress() {
		return this.equals(IN_PROGRESS);
	}
}
