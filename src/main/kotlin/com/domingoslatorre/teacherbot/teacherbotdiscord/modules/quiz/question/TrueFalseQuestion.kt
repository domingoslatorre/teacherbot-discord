package com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question

import java.util.UUID

class TrueFalseQuestion(
    override val id: UUID = UUID.randomUUID(),
    override val text: String,
    override val answers: List<TrueFalseAnswer>,
    override val successFeedback: String? = null,
    override val failureFeedback: String? = null,
) : Question {

    override val validationErrorFeedback =
        "the response must contain one of the following values ${trueValues.union(falseValues).joinToString()}"

    override fun validAnswer(answer: String) = trueValues.union(falseValues).contains(answer.trim().toLowerCase())

    companion object {
        val trueValues = listOf("verdadeiro", "v", "true", "t", "1")
        val falseValues = listOf("falso", "f", "false", "0")
    }
}

class TrueFalseAnswer(
    override val value: Boolean,
    override val feedback: String?,
    override val correct: Boolean,
) : Answer {
    override fun isCorrectAnswer(answer: String) =
        if(value && TrueFalseQuestion.trueValues.contains(answer) && correct) true
        else !value && TrueFalseQuestion.falseValues.contains(answer) && correct
}
