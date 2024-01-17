package com.ader.commercebatchsystem.core.listener

import net.gpedro.integrations.slack.SlackApi
import net.gpedro.integrations.slack.SlackMessage
import org.springframework.batch.core.ChunkListener
import org.springframework.batch.core.scope.context.ChunkContext

class PurchaseConfirmedChunkListener: ChunkListener {

    private val slackWebhookUrl = ""

    override fun beforeChunk(context: ChunkContext) {
        sendMessage(SlackMessage("Chunk START :: $context"))
        super.beforeChunk(context)
    }

    override fun afterChunk(context: ChunkContext) {
        sendMessage(SlackMessage("Chunk END :: $context"))
        super.afterChunk(context)
    }

    override fun afterChunkError(context: ChunkContext) {
        val slackMessage = SlackMessage(context.toString())
        slackMessage.setIcon(":bug:")
        slackMessage.setUsername("Park")

        sendMessage(slackMessage)
        super.afterChunkError(context)
    }

    private fun sendMessage(message: SlackMessage) {
        val api = SlackApi(this.slackWebhookUrl)
        api.call(message)
    }

}