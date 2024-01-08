package com.ader.commercebatchsystem.infrastructure.data.repository

import com.ader.commercebatchsystem.domain.ClaimCompleteExecutor
import com.ader.commercebatchsystem.domain.entity.claim.ClaimReceipt
import com.ader.commercebatchsystem.domain.enums.ClaimStatus
import org.springframework.stereotype.Service

@Service
class ClaimCompleteJpaExecutor(
  private val claimReceiptRepository: ClaimReceiptRepository
): ClaimCompleteExecutor {

  override fun updateClaim(claimId: Long) {
    val receipt = claimReceiptRepository.findById(claimId).get()
    receipt.updateCompletedAt()
    receipt.updateClaimStatus(ClaimStatus.COMPLETED)

    claimReceiptRepository.save(receipt)
  }
}
