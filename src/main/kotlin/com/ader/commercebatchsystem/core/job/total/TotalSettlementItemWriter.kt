package com.ader.commercebatchsystem.core.job.total

import com.ader.commercebatchsystem.domain.entity.settlement.SettlementTotal
import com.ader.commercebatchsystem.infrastructure.data.repository.SettlementTotalRepository
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter

class TotalSettlementItemWriter(
  private val settlementTotalRepository: SettlementTotalRepository
): ItemWriter<SettlementTotal> {

  override fun write(chunk: Chunk<out SettlementTotal>) {
    settlementTotalRepository.saveAll(chunk.toList())
  }

}
