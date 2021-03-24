package com.domingoslatorre.teacherbot.teacherbotdiscord.core.command

import java.time.Duration
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.EmbedType
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageChannel
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

data class CommandContext (
    val event: MessageReceivedEvent,
    val channel: MessageChannel,
    val user: User,
    val message: Message,
    val args: List<String>
) {
    fun reply(message: String) {
        channel.sendMessage(message).queue()
    }

    fun reply(message: MessageEmbed) {
        channel.sendMessage(message).queue()
    }

    fun replyAndDelete(message: String, duration: Duration = Duration.ofSeconds(3)) {
        channel.sendMessage(message).queue {
            Thread.sleep(duration.toMillis())
            it.delete().queue()
            this.message.delete().queue()
        }
    }
}