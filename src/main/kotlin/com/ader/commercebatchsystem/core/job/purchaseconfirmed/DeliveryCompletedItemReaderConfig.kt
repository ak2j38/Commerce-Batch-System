package com.ader.commercebatchsystem.core.job.purchaseconfirmed

import com.ader.commercebatchsystem.domain.entity.order.OrderItem
import com.ader.commercebatchsystem.infrastructure.data.repository.OrderItemRepository
import org.springframework.batch.item.data.RepositoryItemReader
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Sort

@Configuration
class DeliveryCompletedItemReaderConfig {
    val chunkSize = 500

    @Bean
    fun deliveryCompletedJpaItemReader(orderItemRepository: OrderItemRepository): RepositoryItemReader<OrderItem> {
        return RepositoryItemReaderBuilder<OrderItem>()
            .name("deliveryCompletedItemReader")
            .repository(orderItemRepository)
            .methodName("findAll")
            .pageSize(chunkSize)
            .sorts(mapOf("shippedCompleteAt" to Sort.Direction.ASC))
            .build()
    }
}
