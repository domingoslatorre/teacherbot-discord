package com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz

import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.attempt.Member
import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question.ChoiceAnswer
import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question.MultipleChoiceQuestion
import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question.ShortAnswer
import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question.ShortQuestion
import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question.TrueFalseAnswer
import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question.TrueFalseQuestion

object QuestionFactory {
    fun short() = ShortQuestion(
        text = "One plus one equals to ____",
        answers = ShortAnswer(setOf("dois", "2", "two"), "Correct!", true)
    )

    fun trueOrFalse() = TrueFalseQuestion(
        text = "One plus one equals to two",
        answers = listOf(
            TrueFalseAnswer(true, "Correct!", true),
            TrueFalseAnswer(false, "False!", false)
        )
    )

    fun multipleChoice() = MultipleChoiceQuestion(
        text = "Which city in Brazil is known as the Marvelous City?",
        answers = listOf(
            ChoiceAnswer("São Paulo", false),
            ChoiceAnswer("Curitiba", false),
            ChoiceAnswer("Rio de Janeiro", true),
            ChoiceAnswer("Santos", false),
            ChoiceAnswer("Guarulhos", false)
        )
    )
}

object QuizFactory {
    fun quiz1() = Quiz(
        title = "Quiz 1",
        questions = listOf(
            QuestionFactory.short(),
            QuestionFactory.trueOrFalse(),
            QuestionFactory.multipleChoice()
        )
    )
}

object MemberFactory {
    fun member1() = Member(
        discordId = "123"
    )
}