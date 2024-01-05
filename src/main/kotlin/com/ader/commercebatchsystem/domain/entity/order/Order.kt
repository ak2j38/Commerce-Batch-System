package com.ader.commercebatchsystem.domain.entity.order

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.ZonedDateTime

@Entity
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_no")
    val id: Long? = null,
    val createdAt: ZonedDateTime? = ZonedDateTime.now(),
    val updatedAt: ZonedDateTime? = ZonedDateTime.now(),
    val deletedAt: ZonedDateTime? = null,
    val paidConfirmedAt: ZonedDateTime? = null,
    val paidPgAmount: BigDecimal? = BigDecimal.ZERO,
    val usedMileageAmount: BigDecimal? = BigDecimal.ZERO,
    val couponDiscountAmount: BigDecimal? = BigDecimal.ZERO,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Order

        if (id != other.id) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false
        if (deletedAt != other.deletedAt) return false
        if (paidConfirmedAt != other.paidConfirmedAt) return false
        if (paidPgAmount != other.paidPgAmount) return false
        if (usedMileageAmount != other.usedMileageAmount) return false
        if (couponDiscountAmount != other.couponDiscountAmount) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (createdAt?.hashCode() ?: 0)
        result = 31 * result + (updatedAt?.hashCode() ?: 0)
        result = 31 * result + (deletedAt?.hashCode() ?: 0)
        result = 31 * result + (paidConfirmedAt?.hashCode() ?: 0)
        result = 31 * result + (paidPgAmount?.hashCode() ?: 0)
        result = 31 * result + (usedMileageAmount?.hashCode() ?: 0)
        result = 31 * result + (couponDiscountAmount?.hashCode() ?: 0)
        return result
    }
}
