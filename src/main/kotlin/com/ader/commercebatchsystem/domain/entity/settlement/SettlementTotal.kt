package com.ader.commercebatchsystem.domain.entity.settlement

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.time.ZonedDateTime

@Entity
class SettlementTotal(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="settlement_total_id", nullable = false)
    val id: Long? = null,
    @Column(nullable = false)
    val settlementDate: LocalDate,
    @Column(nullable = false)
    val sellerNo: Long,
    @Column(nullable = false)
    val sellerName: String,
    val sellerBusinessNumber: String?,
    val taxType: String? = "TAX",
    val sellType: String? = "CONSIGNMENT",
    @Column(nullable = false)
    val pgSalesAmount: BigDecimal? = BigDecimal.ZERO,
    @Column(nullable = false)
    val couponDiscountAmount: BigDecimal? = BigDecimal.ZERO,
    @Column(nullable = false)
    val mileageUsageAmount: BigDecimal? = BigDecimal.ZERO,
    @Column(nullable = false)
    val shippingFeeAmount: BigDecimal? = BigDecimal.ZERO,
    @Column(nullable = false)
    val claimShippingFeeAmount: BigDecimal? = BigDecimal.ZERO,
    @Column(nullable = false)
    val commissionAmount: BigDecimal? = BigDecimal.ZERO,
    @Column(nullable = false)
    val taxAmount: BigDecimal? = BigDecimal.ZERO,
    val createdAt: ZonedDateTime? = ZonedDateTime.now(), //생성시간
    val updatedAt: ZonedDateTime? = ZonedDateTime.now(), //업데이트시간
    val deletedAt: ZonedDateTime? = null, //삭제시간
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SettlementTotal

        if (id != other.id) return false
        if (settlementDate != other.settlementDate) return false
        if (sellerNo != other.sellerNo) return false
        if (sellerName != other.sellerName) return false
        if (sellerBusinessNumber != other.sellerBusinessNumber) return false
        if (taxType != other.taxType) return false
        if (sellType != other.sellType) return false
        if (pgSalesAmount != other.pgSalesAmount) return false
        if (couponDiscountAmount != other.couponDiscountAmount) return false
        if (mileageUsageAmount != other.mileageUsageAmount) return false
        if (shippingFeeAmount != other.shippingFeeAmount) return false
        if (claimShippingFeeAmount != other.claimShippingFeeAmount) return false
        if (commissionAmount != other.commissionAmount) return false
        if (taxAmount != other.taxAmount) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false
        if (deletedAt != other.deletedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + settlementDate.hashCode()
        result = 31 * result + sellerNo.hashCode()
        result = 31 * result + sellerName.hashCode()
        result = 31 * result + (sellerBusinessNumber?.hashCode() ?: 0)
        result = 31 * result + (taxType?.hashCode() ?: 0)
        result = 31 * result + (sellType?.hashCode() ?: 0)
        result = 31 * result + (pgSalesAmount?.hashCode() ?: 0)
        result = 31 * result + (couponDiscountAmount?.hashCode() ?: 0)
        result = 31 * result + (mileageUsageAmount?.hashCode() ?: 0)
        result = 31 * result + (shippingFeeAmount?.hashCode() ?: 0)
        result = 31 * result + (claimShippingFeeAmount?.hashCode() ?: 0)
        result = 31 * result + (commissionAmount?.hashCode() ?: 0)
        result = 31 * result + (taxAmount?.hashCode() ?: 0)
        result = 31 * result + (createdAt?.hashCode() ?: 0)
        result = 31 * result + (updatedAt?.hashCode() ?: 0)
        result = 31 * result + (deletedAt?.hashCode() ?: 0)
        return result
    }
}
