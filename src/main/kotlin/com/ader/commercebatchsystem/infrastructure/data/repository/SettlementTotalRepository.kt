package com.ader.commercebatchsystem.infrastructure.data.repository

import com.ader.commercebatchsystem.domain.entity.settlement.SettlementTotal
import org.springframework.data.jpa.repository.JpaRepository

interface SettlementTotalRepository: JpaRepository<SettlementTotal, Long> {
}
