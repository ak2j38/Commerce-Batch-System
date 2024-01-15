package com.ader.commercebatchsystem.core.job.total

import com.ader.commercebatchsystem.domain.entity.settlement.SettlementTotal
import com.ader.commercebatchsystem.domain.projection.SummingSettlementResponse
import org.springframework.batch.item.ItemProcessor
import java.time.LocalDate

class TotalSettlementItemProcessor: ItemProcessor<SummingSettlementResponse, SettlementTotal> {
  override fun process(item: SummingSettlementResponse): SettlementTotal {
    return SettlementTotal(
      settlementDate = LocalDate.now(),
      sellerNo = item.sellerNo,
      sellerName = item.sellerName,
      sellerBusinessNumber = item.sellerBusinessNumber,
      taxType = item.taxType,
      sellType = item.sellType,
      pgSalesAmount = item.sumPgSalesAmount,
      couponDiscountAmount = item.sumCouponDiscountAmount,
      mileageUsageAmount = item.sumMileageUsageAmount,
      shippingFeeAmount = item.sumShippingFeeAmount,
      claimShippingFeeAmount = item.sumClaimShippingFeeAmount,
      commissionAmount = item.sumCommissionAmount,
      taxAmount = item.sumTaxAmount
    )
  }
}
