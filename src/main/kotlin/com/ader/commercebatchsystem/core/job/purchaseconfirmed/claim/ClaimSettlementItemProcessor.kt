package com.ader.commercebatchsystem.core.job.purchaseconfirmed.claim

import com.ader.commercebatchsystem.domain.collection.NegativeDailySettlementCollection
import com.ader.commercebatchsystem.domain.entity.claim.ClaimItem
import com.ader.commercebatchsystem.domain.entity.settlement.SettlementDaily
import org.springframework.batch.item.ItemProcessor

class ClaimSettlementItemProcessor: ItemProcessor<ClaimItem, SettlementDaily> {
    override fun process(item: ClaimItem): SettlementDaily {
        return NegativeDailySettlementCollection(item).getSettlementDaily()
    }
}
