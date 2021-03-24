package com.domingoslatorre.teacherbot.teacherbotdiscord.core.command

interface Command {
    val name: String
    val triggers: Set<String>
    val description: String
    val scope: CommandScope
    val usage: List<String>
    fun execute(event: CommandEvent): Boolean
}
