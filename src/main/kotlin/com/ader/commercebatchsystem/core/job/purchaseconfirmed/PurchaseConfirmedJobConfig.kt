package com.ader.commercebatchsystem.core.job.purchaseconfirmed

import com.ader.commercebatchsystem.core.job.purchaseconfirmed.claim.ClaimSettlementItemProcessor
import com.ader.commercebatchsystem.domain.entity.claim.ClaimItem
import com.ader.commercebatchsystem.domain.entity.order.OrderItem
import com.ader.commercebatchsystem.domain.entity.settlement.SettlementDaily
import com.ader.commercebatchsystem.infrastructure.data.repository.OrderItemRepository
import com.ader.commercebatchsystem.infrastructure.data.repository.SettlementDailyRepository
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.data.RepositoryItemReader
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
@EnableBatchProcessing
class PurchaseConfirmedJobConfig(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager,
    private val orderItemRepository: OrderItemRepository,
    private val settlementDailyRepository: SettlementDailyRepository,
    @Qualifier("deliveryCompletedJpaItemReader") private val deliveryCompletedJpaItemReader: JpaPagingItemReader<OrderItem>,
    @Qualifier("dailySettlementItemReader") private val dailySettlementItemReader: JpaPagingItemReader<OrderItem>,
    @Qualifier("claimSettlementItemReader") private val claimSettlementItemReader: JpaPagingItemReader<ClaimItem>,
) {
    val JOB_NAME = "purchaseConfirmedJob"
    val CHUNK_SIZE = 500

    @Bean
    fun purchaseConfirmedJob(): Job {
        return JobBuilder(JOB_NAME, jobRepository)
            .start(purchaseConfirmedStep())
            .next(dailySettlementStep())
            .next(claimSettlementJobStep())
            .build()
    }

    @Bean
    @JobScope
    fun purchaseConfirmedStep(): Step {
        return StepBuilder(JOB_NAME + "_step", jobRepository)
            .chunk<OrderItem, OrderItem>(CHUNK_SIZE, transactionManager)
            .reader(deliveryCompletedJpaItemReader)
            .writer(purchaseConfirmedItemWriter())
            .build()
    }

    @Bean
    fun purchaseConfirmedItemWriter(): PurchaseConfirmedWriter {
        return PurchaseConfirmedWriter(orderItemRepository = orderItemRepository)
    }

    @Bean
    @JobScope
    fun dailySettlementStep(): Step {
        return StepBuilder(JOB_NAME + "_dailySettlement_step", jobRepository)
            .chunk<OrderItem, SettlementDaily>(CHUNK_SIZE, transactionManager)
            .reader(dailySettlementItemReader)
            .processor(dailySettlementItemProcessor())
            .writer(dailySettlementItemWriter())
            .build()
    }

    @Bean
    fun dailySettlementItemProcessor(): DailySettlementItemProcessor {
        return DailySettlementItemProcessor()
    }

    @Bean
    fun dailySettlementItemWriter(): DailySettlementItemWriter {
        return DailySettlementItemWriter(settlementDailyRepository)
    }

    @Bean
    @JobScope
    fun claimSettlementJobStep(): Step {
        return StepBuilder(JOB_NAME + "_claimSettlement_step", jobRepository)
            .chunk<ClaimItem, SettlementDaily>(CHUNK_SIZE, transactionManager)
            .reader(claimSettlementItemReader)
            .processor(claimSettlementItemProcessor())
            .build()
    }

    @Bean
    @JobScope
    fun claimSettlementItemProcessor(): ClaimSettlementItemProcessor {
        return ClaimSettlementItemProcessor()
    }
}
