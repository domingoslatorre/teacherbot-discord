package com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question

import java.util.UUID

class ShortQuestion(
    override val id: UUID = UUID.randomUUID(),
    override val text: String,
    override val answers: List<Answer>,
    override val successFeedback: String? = null,
    override val failureFeedback: String? = null,
) : Question {
    override val validationErrorFeedback = "The answer cannot be empty"
    override fun validAnswer(answer: String) = answer.trim().isNotBlank()
}

class ShortAnswer(
    override val value: Set<String>,
    override val feedback: String?,
    override val correct: Boolean,
) : Answer {
    override fun isCorrectAnswer(answer: String) = correct && value.contains(answer.trim().toLowerCase())
}
