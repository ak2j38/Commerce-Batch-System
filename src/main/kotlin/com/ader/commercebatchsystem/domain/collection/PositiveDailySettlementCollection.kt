package com.ader.commercebatchsystem.domain.collection

import com.ader.commercebatchsystem.domain.entity.order.OrderItem
import com.ader.commercebatchsystem.domain.entity.settlement.SettlementDaily
import java.time.LocalDate

class PositiveDailySettlementCollection(
    private val orderItem: OrderItem
) {
    fun getSettlementDaily(): SettlementDaily {
        val orderItemSnapShot = orderItem.orderItemSnapshot
        val count = orderItem.orderCount ?: 1
        val countToDecimal = count.toBigDecimal()
        val seller = orderItemSnapShot.seller
        val taxCalculator = TaxCalculator(orderItemSnapShot)
        val tax = taxCalculator.getTaxAmount().multiply(countToDecimal)

        // + 정산 금액에 필요한 데이터 만들기
        val pgCalculator = PgSalesAmountCalculator(orderItemSnapShot)
        val pgSalesAmount = pgCalculator.getPgSaleAmount().multiply(countToDecimal)
        val commissionCalculator = CommissionCalculator(orderItemSnapShot)
        val commissionAmount = commissionCalculator.getCommissionAmount()

        return SettlementDaily(
            settlementDate = LocalDate.now(),
            orderNo = orderItem.orderNo,
            orderCount = count,
            orderItemNo = orderItem.orderItemSnapshotNo,
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
