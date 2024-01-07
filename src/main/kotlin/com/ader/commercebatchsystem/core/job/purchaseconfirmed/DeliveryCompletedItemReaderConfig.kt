package com.ader.commercebatchsystem.core.job.purchaseconfirmed

import com.ader.commercebatchsystem.domain.entity.order.OrderItem
import com.ader.commercebatchsystem.infrastructure.data.repository.OrderItemRepository
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.ZonedDateTime

@Configuration
class DeliveryCompletedItemReaderConfig {
    val chunkSize = 500

    @Bean
    fun deliveryCompletedJpaItemReader(orderItemRepository: OrderItemRepository): JpaPagingItemReader<OrderItem> {
        val queryProvider = DeliveryCompletedJpaQueryProvider(
            startDateTime = ZonedDateTime.now().minusDays(1),
            endDateTime = ZonedDateTime.now(),
        )

        return JpaPagingItemReaderBuilder<OrderItem>()
            .name("deliveryCompletedItemReader")
            .pageSize(chunkSize)
            .queryProvider(queryProvider)
            .build()
    }
}
