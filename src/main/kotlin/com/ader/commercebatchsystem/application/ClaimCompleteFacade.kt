package com.ader.commercebatchsystem.application

import com.ader.commercebatchsystem.domain.service.ClaimCompleteService
import org.springframework.stereotype.Service

@Service
class ClaimCompleteFacade(
  private val claimCompleteService: ClaimCompleteService
) {

  fun receiptComplete(claimId: Long) {
    claimCompleteService.complete(claimId)
  }
}
