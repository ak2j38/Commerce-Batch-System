package com.ader.commercebatchsystem.core.job.purchaseconfirmed

import com.ader.commercebatchsystem.domain.entity.order.OrderItem
import com.ader.commercebatchsystem.infrastructure.data.repository.OrderItemRepository
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.data.RepositoryItemReader
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
    @Qualifier("deliveryCompletedJpaItemReader") private val deliveryCompletedJpaItemReader: RepositoryItemReader<OrderItem>,
) {
    val JOB_NAME = "purchaseConfirmedJob"
    val CHUNK_SIZE = 500

    @Bean
    fun purchaseConfirmedJob(): Job {
        return JobBuilder(JOB_NAME, jobRepository)
            .start(purchaseConfirmedStep())
            .build()
    }

    @Bean
    @JobScope
    fun purchaseConfirmedStep(): Step {
        return StepBuilder(JOB_NAME + "_step", jobRepository)
            .chunk<OrderItem, OrderItem>(CHUNK_SIZE, transactionManager)
            .reader(deliveryCompletedJpaItemReader)
            .processor(purchaseConfirmedProcessor())
            .writer(purchaseConfirmedItemWriter())
            .build()
    }

    @Bean
    fun purchaseConfirmedProcessor(): PurchaseConfirmedProcessor {
        return PurchaseConfirmedProcessor()
    }

    @Bean
    fun purchaseConfirmedItemWriter(): PurchaseConfirmedWriter {
        return PurchaseConfirmedWriter(orderItemRepository = orderItemRepository)
    }
}
