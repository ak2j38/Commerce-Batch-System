package com.ader.commercebatchsystem.core.job.purchaseconfirmed

import com.ader.commercebatchsystem.domain.collection.PositiveDailySettlementCollection
import com.ader.commercebatchsystem.domain.entity.order.OrderItem
import com.ader.commercebatchsystem.domain.entity.settlement.SettlementDaily
import org.springframework.batch.item.ItemProcessor

class DailySettlementItemProcessor: ItemProcessor<OrderItem, SettlementDaily> {

    override fun process(item: OrderItem): SettlementDaily {
        val positiveDailySettlementCollection = PositiveDailySettlementCollection(item)
        return positiveDailySettlementCollection.getSettlementDaily()
    }
}
