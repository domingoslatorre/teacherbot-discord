package com.domingoslatorre.teacherbot.teacherbotdiscord

import com.domingoslatorre.teacherbot.teacherbotdiscord.forms.Form
import com.domingoslatorre.teacherbot.teacherbotdiscord.listeners.MemberJoinListener
import com.domingoslatorre.teacherbot.teacherbotdiscord.listeners.MessageReceiveListener
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent

fun getBotToken() = when (val token = System.getenv("DISCORD_BOT_TOKEN")) {
    null -> throw RuntimeException("The bot token is not defined")
    else -> token
}

fun main() {
    val inMemoryForms = mutableMapOf<String, Form>()

    val jda = JDABuilder.createDefault(getBotToken())
        .enableIntents(GatewayIntent.GUILD_MEMBERS)
        .build()

    jda.addEventListener(
        MessageReceiveListener(inMemoryForms),
        MemberJoinListener,
    )
}
