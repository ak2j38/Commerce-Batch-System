package com.ader.commercebatchsystem.core.job.total

import com.ader.commercebatchsystem.domain.projection.SummingSettlementResponse
import com.ader.commercebatchsystem.infrastructure.data.repository.OrderItemRepository
import jakarta.persistence.EntityManager
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate

@Configuration
class TotalSettlementItemReaderConfig(
    private val entityManager: EntityManager
) {

    private val chunkSize = 500
    private val currentDate: LocalDate = LocalDate.now()

    @Bean
    fun totalSettlementItemReader(orderItemRepository: OrderItemRepository): JpaPagingItemReader<SummingSettlementResponse> {
        val queryProvider = SummingSettlementDailyQueryProvider(getFirstDate(), getLastDate())

        return JpaPagingItemReaderBuilder<SummingSettlementResponse>()
            .name("totalSettlementItemReader")
            .entityManagerFactory(entityManager.entityManagerFactory)
            .pageSize(chunkSize)
            .queryProvider(queryProvider)
            .build()
    }

    private fun getFirstDate(): LocalDate {
        val year = currentDate.year
        val month = currentDate.month

        return when (month.value) {
            1 -> return LocalDate.of(year - 1, 12, 1)
            else -> return LocalDate.of(year, month.minus(1), 1)
        }
    }

    private fun getLastDate(): LocalDate {
        return currentDate.withDayOfMonth(1)
    }

}
