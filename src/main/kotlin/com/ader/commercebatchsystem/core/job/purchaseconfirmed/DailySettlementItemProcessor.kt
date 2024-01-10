package com.ader.commercebatchsystem.core.job.purchaseconfirmed

import com.ader.commercebatchsystem.domain.entity.order.OrderItem
import com.ader.commercebatchsystem.domain.entity.settlement.SettlementDaily
import org.springframework.batch.item.ItemProcessor
import java.math.BigDecimal
import java.time.LocalDate

class DailySettlementItemProcessor: ItemProcessor<OrderItem, SettlementDaily> {

    override fun process(item: OrderItem): SettlementDaily {
        val orderItemSnapShot = item.orderItemSnapshot
        val count = item.orderCount
        val seller = orderItemSnapShot.seller

        // 세금 계산
        val tax = BigDecimal.ZERO

        // + 정산 금액에 필요한 데이터 만들기
        val pgSalesAmount = BigDecimal.ZERO
        val commissionAmount = BigDecimal.ZERO

        return SettlementDaily(
            settlementDate = LocalDate.now(),
            orderNo = item.orderNo,
            orderCount = count,
            orderItemNo = item.orderItemSnapshotNo,
            sellerNo = orderItemSnapShot.sellerNo,
            sellerBusinessNumber = seller.businessNo,
            sellerName = seller.sellerName,
            sellType = seller.sellType,
            taxType = orderItemSnapShot.taxType,
            taxAmount = tax,
            commissionAmount = commissionAmount,
            pgSalesAmount = pgSalesAmount,
            couponDiscountAmount = orderItemSnapShot.promotionAmount,
            mileageUsageAmount = orderItemSnapShot.mileageUsageAmount,
            shippingFeeAmount = orderItemSnapShot.defaultDeliveryAmount
        )

    }
}