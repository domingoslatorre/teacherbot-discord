package com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.attempt

import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question.Question

data class AttemptAnswer(
    val question: Question,
    val answer: String,
) {
    val success: Boolean
        get() = question.isCorrectAnswer(answer)
}