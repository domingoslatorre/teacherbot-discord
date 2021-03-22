package com.domingoslatorre.teacherbot.teacherbotdiscord

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.ChannelType
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent

fun getBotToken() = when (val token = System.getenv("DISCORD_BOT_TOKEN")) {
    null -> throw RuntimeException("The bot token is not defined")
    else -> token
}

fun MessageReceivedEvent.isFromMember() = this.isFromType(ChannelType.TEXT) && ! this.author.isBot
fun MessageReceivedEvent.reply(message: String) = this.channel.sendMessage(message).queue()

object MessageReceiveListener : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        if(event.isFromMember() && event.message.contentRaw == ".hi") {
            event.reply("Hello")
        }
    }
}

object MemberJoinListener : ListenerAdapter() {
    override fun onGuildMemberJoin(event: GuildMemberJoinEvent) {
        event.user.openPrivateChannel().queue() {
            it.sendMessage("Welcome ${event.user.name}").queue()
        }
    }
}

fun main() {
    val jda = JDABuilder.createDefault(getBotToken())
        .enableIntents(GatewayIntent.GUILD_MEMBERS)
        .build()

    jda.addEventListener(
        MessageReceiveListener,
        MemberJoinListener,
    )

}