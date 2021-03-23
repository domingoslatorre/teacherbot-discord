package com.domingoslatorre.teacherbot.teacherbotdiscord.listeners

import com.domingoslatorre.teacherbot.teacherbotdiscord.forms.Form
import com.domingoslatorre.teacherbot.teacherbotdiscord.forms.FormFactory
import net.dv8tion.jda.api.entities.ChannelType
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

fun MessageReceivedEvent.isFromChannel() = this.isFromType(ChannelType.TEXT) && ! this.author.isBot
fun MessageReceivedEvent.isFromPrivate() = this.isFromType(ChannelType.PRIVATE) && ! this.author.isBot
fun MessageReceivedEvent.messageRaw() = this.message.contentRaw
fun MessageReceivedEvent.reply(message: String) = this.channel.sendMessage(message).queue()

class MessageReceiveListener(private val inMemoryForms: MutableMap<String, Form>) : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.isFromChannel() && event.messageRaw() == ".hi") {
            event.reply("Hello")
        }

        if (event.isFromChannel() && event.messageRaw() == ".register") {
            val userId = event.author.id
            if (!inMemoryForms.containsKey(userId)) {
                val form = FormFactory.registerForm(userId)
                inMemoryForms[userId] = form
                form.initiate(event)
            }
        }

        if (event.isFromPrivate()) {
            val userId = event.author.id
            if (inMemoryForms.containsKey(userId)) {
                inMemoryForms[userId]!!.process(event, inMemoryForms)
            }
        }
    }
}
