package com.virnect.license.domain.licenseplan;

import static com.virnect.license.domain.licenseplan.PlanStatus.*;
import static java.time.LocalDate.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedBy;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.virnect.license.domain.BaseTimeEntity;
import com.virnect.license.domain.product.LicenseProduct;

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
@Audited
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

	@OneToMany(mappedBy = "licensePlan", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LicenseProduct> licenseProductList = new HashSet<>();

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

	public void addLicenseProduct(LicenseProduct licenseProduct) {
		licenseProductList.add(licenseProduct);
		licenseProduct.setLicensePlan(this);
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
