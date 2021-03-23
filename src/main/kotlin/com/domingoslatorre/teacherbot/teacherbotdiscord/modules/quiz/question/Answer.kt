package com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question

sealed interface Answer {
    val value: Any
    val feedback: String?
    val correct: Boolean
    fun isCorrectAnswer(answer: String): Boolean
}
