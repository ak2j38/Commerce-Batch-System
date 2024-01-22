package com.ader.commercebatchsystem.`interface`.controller

import com.ader.commercebatchsystem.core.launcher.PurchaseConfirmedRunner
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class JobTestController(
    private val purchaseConfirmedRunner: PurchaseConfirmedRunner
) {

    @GetMapping("/settlement/daily")
    @Throws(Exception::class)
    fun runJob() {
        purchaseConfirmedRunner.runJob()
    }
}
