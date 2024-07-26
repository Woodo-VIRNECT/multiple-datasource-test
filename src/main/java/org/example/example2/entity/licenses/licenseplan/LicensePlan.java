package org.example.example2.entity.licenses.licenseplan;

import static java.time.LocalDate.*;
import static org.example.example2.entity.licenses.licenseplan.PlanStatus.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.example.example2.entity.licenses.BaseTimeEntity;
import org.springframework.data.annotation.LastModifiedBy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author jeonghyeon.chang (johnmark)
 * @project PF-License
 * @email practice1356@gmail.com
 * @description
 * @since 2020.04.09
 */
@Entity
@Getter
@Setter
@Table(name = "license_plan", uniqueConstraints = {
	@UniqueConstraint(columnNames = {"workspace_id", "status", "created_at"}),
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LicensePlan extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "license_plan_id")
	private Long id;

	@Column(name = "start_at")
	private LocalDateTime startDate;

	@Column(name = "expired_at")
	private LocalDateTime endDate;

	@Column(name = "user_id", nullable = false)
	private String userId;

	@Column(name = "max_user_amount")
	private Long maxUserAmount;

	@Column(name = "max_storage_size", nullable = false)
	private Long maxStorageSize;

	@Column(name = "max_download_hit", nullable = false)
	private Long maxDownloadHit;

	@Column(name = "max_call_time", nullable = false)
	private Long maxCallTime;

	@Column(name = "workspace_id", nullable = false)
	private String workspaceId;

	@Column(name = "modified_user", nullable = false)
	@LastModifiedBy
	private String modifiedUser;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private PlanStatus planStatus = PlanStatus.ACTIVE;

	@Column(name = "country_code")
	private String countryCode;

	@Column(name = "payment_id")
	private String paymentId;

	@Column(name = "is_event_plan")
	private boolean isEventPlan;

	@Column(name = "is_term_plan")
	private boolean isTermPlan;

	@Column(name = "inactive_date")
	private LocalDateTime inactiveDate;

	@Column(name = "terminate_date")
	private LocalDateTime terminateDate;

	@Builder
	public LicensePlan(
		String userId, String workspaceId, LocalDateTime startDate, LocalDateTime endDate, PlanStatus planStatus,
		Long maxDownloadHit, Long maxStorageSize, Long maxCallTime, String paymentId, Long maxUserAmount,
		String countryCode, boolean isEventPlan, boolean isTermPlan
	) {
		this.userId = userId;
		this.workspaceId = workspaceId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.planStatus = planStatus;
		this.maxDownloadHit = maxDownloadHit;
		this.maxCallTime = maxCallTime;
		this.maxStorageSize = maxStorageSize;
		this.modifiedUser = userId;
		this.paymentId = paymentId;
		this.maxUserAmount = maxUserAmount;
		this.countryCode = countryCode;
		this.isEventPlan = isEventPlan;
		this.isTermPlan = isTermPlan;
	}

	@Override
	public String toString() {
		return "LicensePlan{" +
			"id=" + id +
			", startDate=" + startDate +
			", endDate=" + endDate +
			", userId='" + userId + '\'' +
			", maxUserAmount=" + maxUserAmount +
			", maxStorageSize=" + maxStorageSize +
			", maxDownloadHit=" + maxDownloadHit +
			", maxCallTime=" + maxCallTime +
			", workspaceId='" + workspaceId + '\'' +
			", modifiedUser='" + modifiedUser + '\'' +
			", planStatus=" + planStatus +
			", countryCode='" + countryCode + '\'' +
			", paymentId='" + paymentId + '\'' +
			", isEventPlan=" + isEventPlan +
			", isTermPlan=" + isTermPlan +
			", inactiveDate=" + inactiveDate +
			", terminateDate=" + terminateDate +
			'}';
	}

	public PlanStatus updateStatusByDate(LocalDate startDate, LocalDate endDate) {
		return planStatus = !startDate.isAfter(now()) && !endDate.isBefore(now()) ? ACTIVE : INACTIVE;
	}

	public void update(
		long maxCallTime, long maxStorageSize, long maxDownloadHit, LocalDateTime startDate, LocalDateTime endDate
	) {
		this.maxCallTime = maxCallTime;
		this.maxStorageSize = maxStorageSize;
		this.maxDownloadHit = maxDownloadHit;
		this.startDate = startDate;
		this.endDate = endDate;
		this.inactiveDate = planStatus.isActive() ? null : this.inactiveDate;
		this.terminateDate = planStatus.isActive() ? null : this.terminateDate;
	}

	public boolean isActive() {
		return planStatus.isActive();
	}

	public boolean isInactive() {
		return planStatus.isInactive();
	}
}
