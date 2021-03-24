package com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.attempt

import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.Quiz
import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question.Question
import java.time.Instant
import java.util.UUID

data class Attempt(
    val id: UUID = UUID.randomUUID(),
    var start: Instant = Instant.now(),
    var end: Instant? = null,
    var currentQuestion: Question? = null,
    val quiz: Quiz,
    val member: Member,
    val attemptAnswers: MutableList<AttemptAnswer> = mutableListOf()
) {
    init {
        setNextQuestion()
    }

    private fun getRemainingQuestions() =
        quiz.questions.subtract(attemptAnswers.map { it.question })

    private fun setNextQuestion() {
        currentQuestion = getRemainingQuestions().firstOrNull()
        if(currentQuestion == null) close()
    }

    private fun close() = run { end = Instant.now() }

    fun isOpen() = end == null && currentQuestion != null

    fun answer(answer: String) {
        if(isOpen()) {
            if (currentQuestion!!.isValidAnswer(answer)) {
                AttemptAnswer(currentQuestion!!, answer).let {
                    attemptAnswers.add(it)
                    setNextQuestion()
                }
            } else {

            }
        }
    }

    fun numberCorrectAnswers() = attemptAnswers.count { it.success }

    fun numberIncorrectAnswers() = attemptAnswers.size - numberCorrectAnswers()

}