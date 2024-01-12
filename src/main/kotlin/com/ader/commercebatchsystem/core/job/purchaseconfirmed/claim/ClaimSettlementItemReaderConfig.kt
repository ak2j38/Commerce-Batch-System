package com.ader.commercebatchsystem.core.job.purchaseconfirmed.claim

import com.ader.commercebatchsystem.domain.entity.claim.ClaimItem
import jakarta.persistence.EntityManager
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ClaimSettlementItemReaderConfig(
    private val entityManager: EntityManager
) {
    val chunkSize = 500

    @Bean
    fun claimSettlementItemReader(): JpaPagingItemReader<ClaimItem> {
        val queryProvider = CustomClaimItemQueryProvider()

        return JpaPagingItemReaderBuilder<ClaimItem>()
            .name("claimSettlementItemReader")
            .entityManagerFactory(entityManager.entityManagerFactory)
            .pageSize(chunkSize)
            .queryProvider(queryProvider)
            .build()
    }
}
