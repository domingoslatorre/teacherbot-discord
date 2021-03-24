package com.domingoslatorre.teacherbot.teacherbotdiscord.core.command

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageChannel
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.events.GenericEvent

class CommandEvent (
    val event: GenericEvent,
    val channel: MessageChannel,
    val user: User,
    val message: Message,
    val args: List<String>
) {
    fun reply(message: String) {
        channel.sendMessage(message).queue()
    }
}