package com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question

import java.util.UUID

sealed interface Question {
    val id: UUID
    val text: String
    val answers: Any
    val successFeedback: String?
    val failureFeedback: String?
    val validationErrorFeedback: String
    fun isValidAnswer(answer: String): Boolean
    fun isCorrectAnswer(answer: String): Boolean
}
