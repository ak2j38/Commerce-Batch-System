package com.ader.commercebatchsystem.infrastructure.data.repository

import com.ader.commercebatchsystem.domain.entity.claim.ClaimReceipt
import org.springframework.data.jpa.repository.JpaRepository

interface ClaimReceiptRepository: JpaRepository<ClaimReceipt, Long>
