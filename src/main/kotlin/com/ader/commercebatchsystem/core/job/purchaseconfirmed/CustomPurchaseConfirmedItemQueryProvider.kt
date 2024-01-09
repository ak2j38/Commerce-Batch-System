package com.ader.commercebatchsystem.core.job.purchaseconfirmed

import com.ader.commercebatchsystem.domain.entity.order.OrderItem
import jakarta.persistence.Query
import jakarta.persistence.TypedQuery
import org.springframework.batch.item.database.orm.AbstractJpaQueryProvider
import java.time.ZonedDateTime

class CustomPurchaseConfirmedItemQueryProvider(
    private val startDateTime: ZonedDateTime,
    private val endDateTime: ZonedDateTime,
): AbstractJpaQueryProvider() {
    override fun createQuery(): Query {
        val query: TypedQuery<OrderItem> = this.entityManager.createQuery(
            """
                SELECT oi
                FROM OrderItem oi 
                WHERE purchaseConfirmedAt BETWEEN :startDateTime AND :endDateTime
            """.trimIndent(),
            OrderItem::class.java
        )

        query.setParameter("startDateTime", startDateTime)
        query.setParameter("endDateTime", endDateTime)

        return query
    }

    override fun afterPropertiesSet() {
        TODO("Not yet implemented")
    }
}
