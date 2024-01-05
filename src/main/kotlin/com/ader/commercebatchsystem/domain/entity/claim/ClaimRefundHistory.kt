package com.ader.commercebatchsystem.domain.entity.claim

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.math.BigDecimal
import java.time.ZonedDateTime

@Entity
class ClaimRefundHistory(
    @Id
    @Column(name = "claim_refund_no")
    val id : Long,
    val claimReceiptNo: Long,
    val createdAt: ZonedDateTime? = ZonedDateTime.now(),
    val updatedAt: ZonedDateTime? = ZonedDateTime.now(),
    val deletedAt: ZonedDateTime? = null,
    val refundedAt: ZonedDateTime? = null, //환불시간
    val sellerNo: Long,
    val refundCashAmount: BigDecimal = BigDecimal.ZERO,
    val couponSaleAmount: BigDecimal = BigDecimal.ZERO,
    val refundMileageAmount: BigDecimal = BigDecimal.ZERO,
    val subtractDeliveryAmount: BigDecimal = BigDecimal.ZERO,
    val refundDeliveryAmount: BigDecimal = BigDecimal.ZERO,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ClaimRefundHistory

        if (id != other.id) return false
        if (claimReceiptNo != other.claimReceiptNo) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false
        if (deletedAt != other.deletedAt) return false
        if (refundedAt != other.refundedAt) return false
        if (sellerNo != other.sellerNo) return false
        if (refundCashAmount != other.refundCashAmount) return false
        if (couponSaleAmount != other.couponSaleAmount) return false
        if (refundMileageAmount != other.refundMileageAmount) return false
        if (subtractDeliveryAmount != other.subtractDeliveryAmount) return false
        if (refundDeliveryAmount != other.refundDeliveryAmount) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + claimReceiptNo.hashCode()
        result = 31 * result + (createdAt?.hashCode() ?: 0)
        result = 31 * result + (updatedAt?.hashCode() ?: 0)
        result = 31 * result + (deletedAt?.hashCode() ?: 0)
        result = 31 * result + (refundedAt?.hashCode() ?: 0)
        result = 31 * result + sellerNo.hashCode()
        result = 31 * result + refundCashAmount.hashCode()
        result = 31 * result + couponSaleAmount.hashCode()
        result = 31 * result + refundMileageAmount.hashCode()
        result = 31 * result + subtractDeliveryAmount.hashCode()
        result = 31 * result + refundDeliveryAmount.hashCode()
        return result
    }
}
