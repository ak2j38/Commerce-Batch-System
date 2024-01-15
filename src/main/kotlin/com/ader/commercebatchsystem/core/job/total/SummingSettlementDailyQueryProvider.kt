package com.ader.commercebatchsystem.core.job.total

import com.ader.commercebatchsystem.domain.projection.SummingSettlementResponse
import jakarta.persistence.Query
import jakarta.persistence.TypedQuery
import org.springframework.batch.item.database.orm.AbstractJpaQueryProvider
import java.time.LocalDate

class SummingSettlementDailyQueryProvider(
    private val startDate: LocalDate,
    private val endDate: LocalDate
): AbstractJpaQueryProvider() {

    override fun createQuery(): Query {
        val query: TypedQuery<SummingSettlementResponse> = this.entityManager.createQuery(
            """
                SELECT sd.sellerNo,
                    sd.sellerName,
                    sd.sellerBusinessNumber,
                    sd.taxType,
                    sd.sellType,
                    sum(sd.pgSalesAmount) as sumPgSalesAmount,
                    sum(sd.couponDiscountAmount) as sumCouponDiscountAmount,
                    sum(sd.mileageUsageAmount) as sumMileageUsageAmount,
                    sum(sd.mileageUsageAmount) as sumShippingFeeAmount,
                    sum(sd.claimShippingFeeAmount) as sumClaimShippingFeeAmount,
                    sum(sd.commissionAmount) as sumCommissionAmount
                FROM SettlementDaily sd
                WHERE sd.settlementDate BETWEEN :startDate AND :endDate
                GROUP BY sellerNo
            """.trimIndent(),
            SummingSettlementResponse::class.java
        )

        query.setParameter("startDate", startDate)
        query.setParameter("endDate", endDate)

        return query
    }

    override fun afterPropertiesSet() {
        TODO("Not yet implemented")
    }
}
