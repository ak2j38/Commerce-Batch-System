package com.ader.commercebatchsystem.core.job.purchaseconfirmed.claim

import com.ader.commercebatchsystem.domain.entity.settlement.SettlementDaily
import com.ader.commercebatchsystem.infrastructure.data.repository.SettlementDailyRepository
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.context.annotation.Configuration

@Configuration
class ClaimSettlementItemWriter(
    private val dailyRepository: SettlementDailyRepository
): ItemWriter<SettlementDaily> {
    override fun write(chunk: Chunk<out SettlementDaily>) {
        dailyRepository.saveAll(chunk)
    }
}