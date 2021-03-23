package com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question

import java.util.UUID

sealed interface Question {
    val id: UUID
    val text: String
    val answers: List<Answer>
    val successFeedback: String?
    val failureFeedback: String?
    val validationErrorFeedback: String
    fun validAnswer(answer: String): Boolean
    fun isCorrectAnswer(answer: String) = answers.any { it.isCorrectAnswer(answer.trim().toLowerCase()) }
}
