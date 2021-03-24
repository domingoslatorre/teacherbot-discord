package com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question

import java.util.UUID

class MultipleChoiceQuestion(
    override val id: UUID = UUID.randomUUID(),
    override val text: String,
    override val answers: List<ChoiceAnswer>,
    override val successFeedback: String? = null,
    override val failureFeedback: String? = null,
) : Question {
    override val validationErrorFeedback = "The answer must be the choice number"
    override fun isValidAnswer(answer: String) =
        answer.trim().toIntOrNull().let {
            if (it == null) false
            else (it - 1 >= 0)  && (it <= answers.size)
        }

    override fun isCorrectAnswer(answer: String) =
        if(isValidAnswer(answer)) answers.indexOfFirst { it.correct } == (answer.trim().toInt() - 1)
        else false
}

class ChoiceAnswer(
    override val value: String,
    override val correct: Boolean,
    override val feedback: String? = null,
) : Answer