package com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question

sealed interface Answer {
    val value: Any
    val correct: Boolean
    val feedback: String?
}
