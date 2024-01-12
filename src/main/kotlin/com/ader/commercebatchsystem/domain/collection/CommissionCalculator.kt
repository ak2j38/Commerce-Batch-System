package com.ader.commercebatchsystem.domain.collection

import com.ader.commercebatchsystem.domain.entity.order.OrderItemSnapshot
import java.math.BigDecimal

class CommissionCalculator(
    private val orderItemSnapshot: OrderItemSnapshot
) {

    fun getCommissionAmount(): BigDecimal {
        val seller = orderItemSnapshot.seller
        val price = orderItemSnapshot.sellPrice ?: BigDecimal.ZERO

        val marginAmount = price.subtract(orderItemSnapshot.supplyPrice)

        return getCalculate(marginAmount, seller.commission ?: 0)
    }

    private fun getCalculate(marginAmount: BigDecimal, commission: Int): BigDecimal {
        if (commission == 0) {
            return BigDecimal.ZERO
        }

        val rate = commission.div(100).toBigDecimal()
        return marginAmount.multiply(rate)
    }
}
