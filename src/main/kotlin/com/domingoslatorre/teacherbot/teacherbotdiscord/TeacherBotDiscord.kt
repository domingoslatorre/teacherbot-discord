package com.domingoslatorre.teacherbot.teacherbotdiscord

import com.domingoslatorre.teacherbot.teacherbotdiscord.commands.util.PingCommand
import com.domingoslatorre.teacherbot.teacherbotdiscord.commands.admin.BotCommand
import com.domingoslatorre.teacherbot.teacherbotdiscord.commands.util.HelpCommand
import com.domingoslatorre.teacherbot.teacherbotdiscord.core.command.CommandHandler
import com.domingoslatorre.teacherbot.teacherbotdiscord.forms.Form
import com.domingoslatorre.teacherbot.teacherbotdiscord.listeners.MemberJoinListener
import com.domingoslatorre.teacherbot.teacherbotdiscord.listeners.MessageReceiveListener
import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.Quiz
import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.attempt.Attempt
import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.attempt.Member
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent

fun getBotToken() = when (val token = System.getenv("DISCORD_BOT_TOKEN")) {
    null -> throw RuntimeException("The bot token is not defined")
    else -> token
}

interface QuizData {
    fun getListQuizzes(): MutableSet<Quiz>
    fun getWeekQuiz(): Quiz
    fun getOpenAttempt(member: Member): Attempt?
}

class InMemoryQuizData : QuizData {
    val quizzes: MutableSet<Quiz> = mutableSetOf()
    val attempts: Set<Attempt> = mutableSetOf()

    override fun getListQuizzes() = quizzes
    override fun getWeekQuiz() = quizzes.first()
    override fun getOpenAttempt(member: Member): Attempt? =
        attempts.filter { it.member == member && it.isOpen() }.firstOrNull()

}

fun main() {
    val inMemoryForms = mutableMapOf<String, Form>()
    val inMemoryQuizData = InMemoryQuizData()

    val commandHandler = CommandHandler()
    commandHandler.register(PingCommand())
    commandHandler.register(BotCommand())
    commandHandler.register(HelpCommand(commandHandler))

    val jda = JDABuilder.createDefault(getBotToken())
        .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES)
        .build()

    jda.addEventListener(
        MessageReceiveListener(inMemoryForms, inMemoryQuizData, commandHandler),
        MemberJoinListener,
    )
}
