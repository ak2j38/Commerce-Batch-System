package com.ader.commercebatchsystem.domain.collection

import com.ader.commercebatchsystem.domain.entity.order.OrderItemSnapshot
import java.math.BigDecimal

class PgSalesAmountCalculator(
    private val orderItemSnapshot: OrderItemSnapshot
) {

    // pg -> payment gateway
    fun getPgSaleAmount(): BigDecimal {
        return orderItemSnapshot.sellPrice
            ?.subtract(orderItemSnapshot.promotionAmount)
            ?.subtract(orderItemSnapshot.mileageUsageAmount)
            ?: BigDecimal.ZERO

    }
}
