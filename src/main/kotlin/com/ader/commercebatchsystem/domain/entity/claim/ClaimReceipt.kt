package com.ader.commercebatchsystem.domain.entity.claim

import com.ader.commercebatchsystem.domain.enums.*
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.ZonedDateTime

@Entity
class ClaimReceipt(
    @Id
    @Column(name = "claim_receipt_no")
    val id : Long,
    val orderNo: Long,
    val createdAt: ZonedDateTime? = ZonedDateTime.now(),
    val updatedAt: ZonedDateTime? = ZonedDateTime.now(),
    val deletedAt: ZonedDateTime? = null,
    var completedAt: ZonedDateTime? = null,
    @Convert(converter = ClaimTypeConverter::class)
    val requestType: ClaimType,
    @Convert(converter = ClaimStatusConverter::class)
    var claimStatus: ClaimStatus,
    @Convert(converter = ExtraFeePayerConverter::class)
    val extraFeePayer: ExtraFeePayer,
    val claimReason: Int,
) {
    fun updateCompletedAt() {
        this.completedAt = ZonedDateTime.now()
    }

    fun updateClaimStatus(claimStatus: ClaimStatus) {
        this.claimStatus = claimStatus
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ClaimReceipt

        if (id != other.id) return false
        if (orderNo != other.orderNo) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false
        if (deletedAt != other.deletedAt) return false
        if (completedAt != other.completedAt) return false
        if (requestType != other.requestType) return false
        if (claimStatus != other.claimStatus) return false
        if (extraFeePayer != other.extraFeePayer) return false
        if (claimReason != other.claimReason) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + orderNo.hashCode()
        result = 31 * result + (createdAt?.hashCode() ?: 0)
        result = 31 * result + (updatedAt?.hashCode() ?: 0)
        result = 31 * result + (deletedAt?.hashCode() ?: 0)
        result = 31 * result + (completedAt?.hashCode() ?: 0)
        result = 31 * result + requestType.hashCode()
        result = 31 * result + claimStatus.hashCode()
        result = 31 * result + extraFeePayer.hashCode()
        result = 31 * result + claimReason
        return result
    }
}
