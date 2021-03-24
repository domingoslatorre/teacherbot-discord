package com.domingoslatorre.teacherbot.teacherbotdiscord.command.admin

import com.domingoslatorre.teacherbot.teacherbotdiscord.core.command.Command
import com.domingoslatorre.teacherbot.teacherbotdiscord.core.command.CommandEvent
import com.domingoslatorre.teacherbot.teacherbotdiscord.core.command.CommandScope

class PingCommand : Command {
    override val name: String = "Ping Command"
    override val triggers: Set<String> = setOf("ping")
    override val description: String = "Test if the bot is working"
    override val scope: CommandScope = CommandScope.CHANNEL
    override val usage: List<String> = listOf()

    override fun execute(event: CommandEvent) : Boolean {
        event.reply("Pong!")
        return true
    }
}