package com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question

import java.util.UUID

class ShortQuestion(
    override val id: UUID = UUID.randomUUID(),
    override val text: String,
    override val answers: ShortAnswer,
    override val successFeedback: String? = null,
    override val failureFeedback: String? = null,
) : Question {
    override val validationErrorFeedback = "The answer cannot be empty"
    override fun isValidAnswer(answer: String) = answer.trim().isNotBlank()
    override fun isCorrectAnswer(answer: String) = answers.value.any { it == answer.trim().toLowerCase() }
}

class ShortAnswer(
    override val value: Set<String>,
    override val feedback: String?,
    override val correct: Boolean,
) : Answer
