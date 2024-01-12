package com.ader.commercebatchsystem.core.job.purchaseconfirmed

import com.ader.commercebatchsystem.domain.entity.settlement.SettlementDaily
import com.ader.commercebatchsystem.infrastructure.data.repository.SettlementDailyRepository
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.context.annotation.Configuration
import org.springframework.lang.NonNull

@Configuration
class DailySettlementItemWriter(
    private val settlementDailyRepository: SettlementDailyRepository
): ItemWriter<SettlementDaily> {
    override fun write(@NonNull chunk: Chunk<out SettlementDaily>) {
        settlementDailyRepository.saveAll(chunk)
    }
}
