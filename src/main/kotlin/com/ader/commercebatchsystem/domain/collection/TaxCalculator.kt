package com.ader.commercebatchsystem.domain.collection

import com.ader.commercebatchsystem.domain.TaxTypeItem
import com.ader.commercebatchsystem.domain.entity.order.OrderItemSnapshot
import com.ader.commercebatchsystem.domain.enums.TaxType
import java.math.BigDecimal

class TaxCalculator(
    private val orderItemSnapshot: OrderItemSnapshot
) {

    private val TAX_RATE =  0.03

    fun getTaxTypeItem(): TaxTypeItem =
        when (orderItemSnapshot.taxType) {
            TaxType.TAX -> TaxTypeItem.TaxItem(orderItemSnapshot.sellPrice)
            TaxType.ZERO -> TaxTypeItem.ZeroTaxItem(orderItemSnapshot.sellPrice)
            TaxType.FREE -> TaxTypeItem.TaxFreeItem(orderItemSnapshot.sellPrice)
            null -> TaxTypeItem.TaxItem(orderItemSnapshot.sellPrice)
        }

    fun getTaxAmount(): BigDecimal {
        val taxTypeItem = getTaxTypeItem()

        return when(taxTypeItem) {
            is TaxTypeItem.TaxItem -> getCalculateTaxForTaxItem(taxTypeItem)
            is TaxTypeItem.ZeroTaxItem -> taxTypeItem.price ?: BigDecimal.ZERO
            is TaxTypeItem.TaxFreeItem -> taxTypeItem.price ?: BigDecimal.ZERO
        }
    }

    private fun getCalculateTaxForTaxItem(taxItem: TaxTypeItem.TaxItem): BigDecimal {
        val price = taxItem.price ?: BigDecimal.ZERO
        val rate = BigDecimal.valueOf(TAX_RATE)

        return price.multiply(rate)
    }

}
