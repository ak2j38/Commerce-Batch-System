package com.ader.commercebatchsystem.core.job.purchaseconfirmed

import com.ader.commercebatchsystem.domain.entity.order.OrderItem
import org.springframework.batch.item.ItemProcessor
import java.time.ZonedDateTime

class PurchaseConfirmedProcessor: ItemProcessor<OrderItem, OrderItem> {
    override fun process(item: OrderItem): OrderItem {
        return OrderItem(
            id = item.id,
            orderNo = item.orderNo,
            createdAt = item.createdAt,
            updatedAt = item.updatedAt,
            deletedAt = item.deletedAt,
            orderItemSnapshotNo = item.orderItemSnapshotNo,
            orderCount = item.orderCount,
            itemDeliveryStatus = item.itemDeliveryStatus,
            purchaseConfirmedAt = ZonedDateTime.now(),
            shippedCompleteAt = item.shippedCompleteAt,
            orderItemSnapshot = item.orderItemSnapshot
        )
    }
}
