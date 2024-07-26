package org.example.example2.entity.workspace.lms.enums;

public enum CurriculumProgressStatus {
	BEFORE_DEPLOYMENT,
	STAND_BY,
	IN_PROGRESS,
	DEADLINE,
	END;

	public boolean isEnd() {
		return this.equals(END);
	}

	public boolean isDeployed() {
		return !this.equals(BEFORE_DEPLOYMENT);
	}

	public boolean isInProgress() {
		return this.equals(IN_PROGRESS);
	}

	public boolean isBeforeDeployment() {
		return this.equals(BEFORE_DEPLOYMENT);
	}

	public boolean isDeadline() {
		return this.equals(DEADLINE);
	}

	public boolean isBeforeDeadline() {
		return this.equals(BEFORE_DEPLOYMENT) || this.equals(STAND_BY) || this.equals(IN_PROGRESS);
	}
}
