package com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz

import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question.Question
import java.util.UUID

data class Quiz(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val questions: List<Question>,
)






