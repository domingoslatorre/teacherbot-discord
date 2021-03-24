package com.domingoslatorre.teacherbot.teacherbotdiscord.listeners

import com.domingoslatorre.teacherbot.teacherbotdiscord.QuizData
import com.domingoslatorre.teacherbot.teacherbotdiscord.core.command.CommandHandler
import com.domingoslatorre.teacherbot.teacherbotdiscord.forms.Form
import net.dv8tion.jda.api.entities.ChannelType
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

fun MessageReceivedEvent.isFromChannel() = this.isFromType(ChannelType.TEXT) && ! this.author.isBot
fun MessageReceivedEvent.isFromPrivate() = this.isFromType(ChannelType.PRIVATE) && ! this.author.isBot
fun MessageReceivedEvent.messageRaw() = this.message.contentRaw.trim().toLowerCase()
fun MessageReceivedEvent.reply(message: String) = this.channel.sendMessage(message).queue()
fun MessageReceivedEvent.replyPrivate(message: String) = this.author.openPrivateChannel().queue {
    it.sendMessage(message).queue()
}

class MessageReceiveListener(
    private val inMemoryForms: MutableMap<String, Form>,
    private val quizData: QuizData,
    private val commandHandler: CommandHandler
) : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        commandHandler.findAndExecute(event)


//        val messageList = event.messageRaw().split(" ")
//        val command = messageList[0]
//        val args = messageList.drop(1)

//        if (event.isFromChannel() && command.startsWith("profile")) {
//            return ProfileCommand().execute(event, event.channel, event.author, event.message, args)
//        }
//
//        if (event.isFromChannel() && command.startsWith("register")) {
//            return RegisterCommand(inMemoryForms = inMemoryForms).execute(event, event.channel, event.author, event.message, args)
//        }

//        // Checar se o membro está em algum processo
//        if (event.isFromPrivate()) {
//            val userId = event.author.id
//            if (inMemoryForms.containsKey(userId)) {
//                inMemoryForms[userId]!!.process(event, inMemoryForms)
//            }
//        }
//
//
//        val quizCommand = ".quiz"
//        val quizSubCommands = listOf("help", "start", "stop", "list")
//        if (event.isFromPrivate() && event.messageRaw().startsWith(quizCommand)) {
//            val split = event.messageRaw().split(" ")
//
//            if (split.size != 2) {
//                return event.replyPrivate("Como usar o $quizCommand")
//            }
//
//            val (command, subCommand) = split
//
//
//            if (!quizSubCommands.contains(subCommand)) {
//                return event.replyPrivate("'$subCommand' is not a $command command")
//            }
//
//            if (subCommand == "help") {
//                event.replyPrivate("help ...")
//            }
//
//            if (subCommand == "start") {
//                event.replyPrivate("start ...")
//            }
//
//            if (subCommand == "stop") {
//                event.replyPrivate("stop ...")
//            }
//
//            if (subCommand == "list") {
//                event.replyPrivate("list ...")
//            }
//
//        }
    }
}
