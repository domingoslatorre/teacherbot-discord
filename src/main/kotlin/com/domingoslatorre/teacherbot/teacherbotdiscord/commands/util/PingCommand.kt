package com.domingoslatorre.teacherbot.teacherbotdiscord.commands.util

import com.domingoslatorre.teacherbot.teacherbotdiscord.core.command.Command
import com.domingoslatorre.teacherbot.teacherbotdiscord.core.command.CommandContext

class PingCommand : Command(
    name = "Ping",
    triggers = setOf("ping"),
    description = "Test if the bot is working",
    usage = listOf(
        "ping",
    ),
) {
    override fun execute(context: CommandContext) : Boolean {
        context.replyAndDelete("pong ✅")
        return true
    }
}
