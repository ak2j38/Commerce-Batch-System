package com.ader.commercebatchsystem.domain.entity.order

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
class OrderItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_no")
    val id: Long? = null,
    val orderNo: Long,
    @Column(name = "order_item_snapshot_no")
    val orderItemSnapshotNo: Long,
    val orderCount: Int? = 1,
    val itemDeliveryStatus: Int? = 0,
    val createdAt: ZonedDateTime? = ZonedDateTime.now(),
    val updatedAt: ZonedDateTime? = ZonedDateTime.now(),
    val deletedAt: ZonedDateTime? = null,
    val purchaseConfirmedAt: ZonedDateTime? = null,
    val shippedCompleteAt: ZonedDateTime? = null,
    @OneToOne
    @JoinColumn(name = "order_item_snapshot_no", insertable = false, updatable = false)
    val orderItemSnapshot: OrderItemSnapshot,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OrderItem

        if (id != other.id) return false
        if (orderNo != other.orderNo) return false
        if (orderItemSnapshotNo != other.orderItemSnapshotNo) return false
        if (orderCount != other.orderCount) return false
        if (itemDeliveryStatus != other.itemDeliveryStatus) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false
        if (deletedAt != other.deletedAt) return false
        if (purchaseConfirmedAt != other.purchaseConfirmedAt) return false
        if (shippedCompleteAt != other.shippedCompleteAt) return false
        if (orderItemSnapshot != other.orderItemSnapshot) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + orderNo.hashCode()
        result = 31 * result + orderItemSnapshotNo.hashCode()
        result = 31 * result + (orderCount ?: 0)
        result = 31 * result + (itemDeliveryStatus ?: 0)
        result = 31 * result + (createdAt?.hashCode() ?: 0)
        result = 31 * result + (updatedAt?.hashCode() ?: 0)
        result = 31 * result + (deletedAt?.hashCode() ?: 0)
        result = 31 * result + (purchaseConfirmedAt?.hashCode() ?: 0)
        result = 31 * result + (shippedCompleteAt?.hashCode() ?: 0)
        result = 31 * result + orderItemSnapshot.hashCode()
        return result
    }
}