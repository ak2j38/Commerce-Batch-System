package com.ader.commercebatchsystem.core.job.purchaseconfirmed.claim

import com.ader.commercebatchsystem.domain.entity.claim.ClaimItem
import jakarta.persistence.Query
import org.springframework.batch.item.database.orm.AbstractJpaQueryProvider

class CustomClaimItemQueryProvider: AbstractJpaQueryProvider() {

    override fun createQuery(): Query {
        val query = this.entityManager.createQuery(
            """
                SELECT ci
                FROM ClaimItem ci
                    LEFT OUTER JOIN SettlementDaily sd ON ci.claimReceiptNo = sd.claimReceiptNo
                    JOIN ClaimReceipt cr ON ci.claimReceiptNo = cr.id
                WHERE cr.completedAt IS NOT NULL
                    AND sd.id IS NULL
            """.trimIndent()
            , ClaimItem::class.java
        )

        return query
    }

    override fun afterPropertiesSet() {
        TODO("Not yet implemented")
    }
}
