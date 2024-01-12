package com.ader.commercebatchsystem.core.job.purchaseconfirmed

import com.ader.commercebatchsystem.domain.entity.order.OrderItem
import jakarta.persistence.EntityManager
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

@Configuration
class DailySettlementItemReaderConfig(
    private val entityManager: EntityManager
) {

    val chunkSize = 500
    val zoneId = ZoneId.of("Asia/Seoul")
    val startDateTime = ZonedDateTime.of(
        LocalDate.now(),
        LocalTime.MIN,
        zoneId
    )
    val endDateTime = ZonedDateTime.of(
        LocalDate.now(),
        LocalTime.MAX,
        zoneId
    )

    @Bean
    fun dailySettlementItemReader(): JpaPagingItemReader<OrderItem> {
        val customQueryProvider = CustomPurchaseConfirmedItemQueryProvider(
            startDateTime = startDateTime,
            endDateTime = endDateTime
        )

        return JpaPagingItemReaderBuilder<OrderItem>()
            .name("dailySettlementItemReader")
            .entityManagerFactory(entityManager.entityManagerFactory)
            .pageSize(chunkSize)
            .queryProvider(customQueryProvider)
            .build()
    }
}
