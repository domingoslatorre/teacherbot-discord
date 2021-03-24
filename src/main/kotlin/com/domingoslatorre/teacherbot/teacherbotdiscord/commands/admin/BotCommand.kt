package com.domingoslatorre.teacherbot.teacherbotdiscord.commands.admin

import com.domingoslatorre.teacherbot.teacherbotdiscord.core.command.Command
import com.domingoslatorre.teacherbot.teacherbotdiscord.core.command.CommandContext
import com.domingoslatorre.teacherbot.teacherbotdiscord.core.command.CommandPermission
import com.domingoslatorre.teacherbot.teacherbotdiscord.core.command.CommandScope
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity

class BotCommand : Command(
    name = "Bot",
    triggers = setOf("bot"),
    description = "Update Bot Status",
    usage = listOf(
        "bot presence <text>",
        "bot name <text>",
    ),
    scope = CommandScope.PRIVATE,
    permission = CommandPermission.OWNER,
) {
    override fun execute(context: CommandContext) : Boolean {
        if(context.args.isEmpty()) {
            sendHelp(context)
            return false
        }


        return when(context.args[0]) {
            "presence" -> changeStatus(context)
            "name" -> changeName(context)
            else -> false
        }
    }

    private fun changeStatus(context: CommandContext)  =
        when(context.args.getOrNull(1)) {
            null -> false
            else -> {
                val presence = context.args.drop(1).joinToString(" ")
                context.event.jda.presence.setPresence(OnlineStatus.ONLINE, Activity.playing(presence))
                true
            }
        }

    private fun changeName(context: CommandContext) =
        when(context.args.getOrNull(1)) {
            null -> false
            else -> {
                val name = context.args.drop(1).joinToString(" ")

                if(name.length > 32) {
                    context.replyAndDelete("Name must be 32 or fewer in length.")
                    false
                }

                val bot = context.event.jda.selfUser
                context.event.guild.getMember(bot)!!.modifyNickname(name).queue()
                true
            }
        }

}
