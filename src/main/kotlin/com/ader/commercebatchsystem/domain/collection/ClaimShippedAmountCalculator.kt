package com.ader.commercebatchsystem.domain.collection

import com.ader.commercebatchsystem.domain.entity.claim.ClaimItem
import com.ader.commercebatchsystem.domain.enums.ClaimType
import com.ader.commercebatchsystem.domain.enums.ExtraFeePayer
import java.math.BigDecimal

class ClaimShippedAmountCalculator(
    private val claimItem: ClaimItem
) {
    private val SHIPPING_AMOUNT = BigDecimal.valueOf(3000)

    fun getClaimShippedAmount(): BigDecimal {
        val claimReceipt = claimItem.claimReceipt
        // 취소는 0, 교환은 2, 반품은 1
        val shippingCount = getShippingCount(claimReceipt.requestType, claimReceipt.extraFeePayer)

        return SHIPPING_AMOUNT.multiply(shippingCount.toBigDecimal())
    }

    private fun getShippingCount(requestType: ClaimType, extraFeePayer: ExtraFeePayer): Int {
        if (extraFeePayer == ExtraFeePayer.BUYER) {
            return 0
        }

        return when(requestType) {
            ClaimType.CANCELED -> 0
            ClaimType.RETURN -> 1
            ClaimType.EXCHANGE -> 2
        }
    }
}
