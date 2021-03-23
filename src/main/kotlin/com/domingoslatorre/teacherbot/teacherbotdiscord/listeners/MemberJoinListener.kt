package com.domingoslatorre.teacherbot.teacherbotdiscord.listeners

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

object MemberJoinListener : ListenerAdapter() {
    override fun onGuildMemberJoin(event: GuildMemberJoinEvent) {
        event.user.openPrivateChannel().queue {
            it.sendMessage("Welcome ${event.user.name}").queue()
        }
    }
}
