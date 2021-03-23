package com.domingoslatorre.teacherbot.teacherbotdiscord.forms

import com.domingoslatorre.teacherbot.teacherbotdiscord.listeners.messageRaw
import com.domingoslatorre.teacherbot.teacherbotdiscord.listeners.reply
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

data class Form(
    val title: String,
    val finalMessage: String,
    val timeoutMessage: String,
    val questions: List<Question>,
    val userId: String,
    var currentQuestion: Question? = null,
) {
    private fun nextQuestion(): Boolean {
        currentQuestion = questions.firstOrNull { it.answer == null }
        return currentQuestion != null
    }

    fun initiate(event: MessageReceivedEvent) {
        if (nextQuestion()) {
            event.author.openPrivateChannel().queue {
                it.sendMessage("Bem vindo ao processo de $title. Responda as perguntas").queue()
                it.sendMessage(currentQuestion!!.text).queue()
            }
        }
    }

    fun process(event: MessageReceivedEvent, inMemoryForms: MutableMap<String, Form>) {
        if (nextQuestion()) {
            if (currentQuestion!!.answer(event.messageRaw())) {
                if (nextQuestion()) {
                    event.reply(currentQuestion!!.text)
                } else {
                    event.reply(finalMessage)
                    inMemoryForms.remove(userId)
                    println(userId)
                    println(this)
                }
            } else {
                event.reply(currentQuestion!!.erroMessage + ". Responda novamente a pergunta.")
                event.reply(currentQuestion!!.text)
            }
        }
    }
}
