package com.domingoslatorre.teacherbot.teacherbotdiscord.core.command

import net.dv8tion.jda.api.entities.ChannelType
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class CommandHandler(
    private val commandPrefix: String = "!"
)  {
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

    private fun parseMessage(event: MessageReceivedEvent): Pair<String, List<String>> =
        event.message.contentRaw.trim().split(" ").let { it[0] to it.drop(1) }

    fun findAndExecute(event: MessageReceivedEvent): Boolean {
        // Check if is a bot
        if(event.author.isBot) return false

        // Get command and args from messageRaw
        val (trigger, args) = parseMessage(event)

        // Check command prefix
        if(!trigger.startsWith(commandPrefix)) return false

        val triggerWithouPrefix = trigger.drop(1)
        find(triggerWithouPrefix).fold(
            { command ->
                // Check scopes of messages
                if(command.isPrivateScope() && !event.isFromType(ChannelType.PRIVATE)) return false
                if(command.isChannelScope() && event.isFromType(ChannelType.PRIVATE)) return false

                // Check permission
                if(command.permission == CommandPermission.OWNER && !event.member!!.isOwner) return false

                // Create a commandEvent object from event properties
                val commandEvent = CommandContext(event, event.channel, event.author, event.message, args)

                // Execute a command passing the command event
                return command.execute(commandEvent)
            },
            { return false }
        )
    }

}