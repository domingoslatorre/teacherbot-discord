package com.domingoslatorre.teacherbot.teacherbotdiscord.core.command

import net.dv8tion.jda.api.EmbedBuilder

abstract class Command (
    val name: String,
    val triggers: Set<String>,
    val description: String,
    val scope: CommandScope = CommandScope.BOTH,
    val permission: CommandPermission = CommandPermission.MEMBER,
    val usage: List<String> = listOf()
) {
    abstract fun execute(context: CommandContext): Boolean
    fun isPrivateScope() = scope == CommandScope.PRIVATE
    fun isChannelScope() = scope == CommandScope.CHANNEL

    fun sendHelp(context: CommandContext) = context
        .reply(
            EmbedBuilder()
                .setTitle("$name Command")
                .setDescription(description)
                .addField("usage", usage.joinToString("\n"), false)
                .build()
        ).run { true }
}
