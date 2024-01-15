package com.ader.commercebatchsystem.core.job.total

import com.ader.commercebatchsystem.domain.entity.settlement.SettlementTotal
import com.ader.commercebatchsystem.domain.projection.SummingSettlementResponse
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
class TotalSettlementJobConfig(
    private val jobRepository: JobRepository,
    private val transactionManagement: PlatformTransactionManager,
    @Qualifier("totalSettlementItemReader") private val totalSettlementItemReader: JpaPagingItemReader<SummingSettlementResponse>,
) {
    private val JOB_NAME = "totalSettlementJob"
    private val chunkSize = 500

    @Bean
    fun totalSettlementJob(): Job {
        return JobBuilder(JOB_NAME, jobRepository)
            .start(totalSettlementStep())
            .build()
    }

    @Bean
    @JobScope
    fun totalSettlementStep(): Step {
        return StepBuilder(JOB_NAME + "_step", jobRepository)
            .chunk<SummingSettlementResponse, SettlementTotal>(chunkSize, transactionManagement)
            .reader(totalSettlementItemReader)
            .processor(totalSettlementItemProcessor())
            .build()
    }

    @Bean
    fun totalSettlementItemProcessor(): TotalSettlementItemProcessor {
        return TotalSettlementItemProcessor()
    }
}
