package com.domingoslatorre.teacherbot.teacherbotdiscord.commands.util

import com.domingoslatorre.teacherbot.teacherbotdiscord.core.command.Command
import com.domingoslatorre.teacherbot.teacherbotdiscord.core.command.CommandContext
import com.domingoslatorre.teacherbot.teacherbotdiscord.core.command.CommandHandler
import com.domingoslatorre.teacherbot.teacherbotdiscord.core.command.CommandPermission
import net.dv8tion.jda.api.EmbedBuilder

class HelpCommand(val commandHandler: CommandHandler) : Command(
    name = "Help",
    triggers = setOf("help", "ajuda"),
    description = "Show bot commands and help",
    usage = listOf(
        "help",
    )
) {
    override fun execute(context: CommandContext) : Boolean {
        val builder = EmbedBuilder().setTitle("$name Command").setDescription(description)

        // Show owner permission commands only for owner
        if(context.event.member!!.isOwner) {
            commandHandler.commands
                .forEach { builder.addField(it.name, it.usage.joinToString("\n"), false) }
        } else {
            commandHandler.commands
                .filter { it.permission != CommandPermission.OWNER }
                .forEach { builder.addField(it.name, it.usage.joinToString("\n"), false) }
        }

        context.reply(builder.build())
        return true
    }

}
