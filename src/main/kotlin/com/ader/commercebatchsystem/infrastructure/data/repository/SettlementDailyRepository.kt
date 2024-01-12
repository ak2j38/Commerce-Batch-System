package com.ader.commercebatchsystem.infrastructure.data.repository

import com.ader.commercebatchsystem.domain.entity.settlement.SettlementDaily
import org.springframework.data.jpa.repository.JpaRepository

interface SettlementDailyRepository: JpaRepository<SettlementDaily, Long>
