package com.domingoslatorre.teacherbot.teacherbotdiscord.core.command

import com.domingoslatorre.teacherbot.teacherbotdiscord.listeners.messageRaw
import net.dv8tion.jda.api.entities.ChannelType
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class CommandHandler {
    var commands: MutableSet<Command> = mutableSetOf()

    fun register(command: Command): Result<Command> =
        if(command.triggers.intersect(commands.flatMap { it.triggers }).isNotEmpty()) {
            Result.failure(RuntimeException("Command with triggers already exists"))
        } else {
            commands.add(command)
            Result.success(command)
        }

    fun find(trigger: String): Result<Command> =
        when(val command = commands.firstOrNull { command -> command.triggers.any { it == trigger } }) {
            null -> Result.failure(RuntimeException("Command not found"))
            else -> Result.success(command)
        }

    fun findAndExecute(event: MessageReceivedEvent): Boolean {
        // Check if is a bot
        if(event.author.isBot) return false

        // Get command and args from messageRaw
        val commandAndArgs = event.messageRaw().split(" ")
        val command = commandAndArgs[0]
        val args = commandAndArgs.drop(1)

        find(command).fold(
            {
                // Check scopes of messages
                if(it.scope == CommandScope.PRIVATE && !event.isFromType(ChannelType.PRIVATE)) return false
                if(it.scope == CommandScope.CHANNEL && event.isFromType(ChannelType.PRIVATE)) return false

                // Create a commandEvent object from event properties
                val commandEvent = CommandEvent(event, event.channel, event.author, event.message, args)

                // Execute a command passing the command event
                return it.execute(commandEvent)
            },
            { return false }
        )
    }

}