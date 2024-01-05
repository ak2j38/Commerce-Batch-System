package com.ader.commercebatchsystem.domain.entity.claim

import com.ader.commercebatchsystem.domain.entity.order.OrderItem
import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
class ClaimItem(
    @Id
    @Column(name = "claim_item_no")
    val id : Long,
    @Column(name = "claim_receipt_no")
    val claimReceiptNo: Long,
    val createdAt: ZonedDateTime? = ZonedDateTime.now(),
    val updatedAt: ZonedDateTime? = ZonedDateTime.now(),
    val deletedAt: ZonedDateTime? = null,
    @Column(name="order_item_no")
    val orderItemNo: Long,
    val claimCount: Int? = 1,
    @OneToOne
    @JoinColumn(name = "order_item_no", insertable = false, updatable = false)
    val orderItem: OrderItem,
    @ManyToOne
    @JoinColumn(name = "claim_receipt_no", insertable = false, updatable = false)
    val claimReceipt: ClaimReceipt,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ClaimItem

        if (id != other.id) return false
        if (claimReceiptNo != other.claimReceiptNo) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false
        if (deletedAt != other.deletedAt) return false
        if (orderItemNo != other.orderItemNo) return false
        if (claimCount != other.claimCount) return false
        if (orderItem != other.orderItem) return false
        if (claimReceipt != other.claimReceipt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + claimReceiptNo.hashCode()
        result = 31 * result + (createdAt?.hashCode() ?: 0)
        result = 31 * result + (updatedAt?.hashCode() ?: 0)
        result = 31 * result + (deletedAt?.hashCode() ?: 0)
        result = 31 * result + orderItemNo.hashCode()
        result = 31 * result + (claimCount ?: 0)
        result = 31 * result + orderItem.hashCode()
        result = 31 * result + claimReceipt.hashCode()
        return result
    }
}
