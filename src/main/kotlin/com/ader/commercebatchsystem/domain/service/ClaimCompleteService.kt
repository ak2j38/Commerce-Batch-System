package com.ader.commercebatchsystem.domain.service

import com.ader.commercebatchsystem.domain.ClaimCompleteExecutor
import org.springframework.stereotype.Service

@Service
class ClaimCompleteService(
  private val executor: ClaimCompleteExecutor
) {

  fun complete(claimId: Long) = executor.updateClaim(claimId)
}
