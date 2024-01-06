package com.ader.commercebatchsystem.core.job.purchaseconfirmed

import com.ader.commercebatchsystem.domain.entity.order.OrderItem
import com.ader.commercebatchsystem.infrastructure.data.repository.OrderItemRepository
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.lang.NonNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class PurchaseConfirmedWriter(
    private val orderItemRepository: OrderItemRepository
): ItemWriter<OrderItem> {
    override fun write(@NonNull chunk: Chunk<out OrderItem>) {
        orderItemRepository.saveAll(chunk)
    }
}
