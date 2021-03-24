package com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class QuizTest : StringSpec({
    "Create a quiz" {
        val quiz1 = Quiz(
            title = "Quiz 1",
            questions = listOf(
                QuestionFactory.short(),
                QuestionFactory.trueOrFalse(),
                QuestionFactory.multipleChoice()
            )
        )

        quiz1.title shouldBe "Quiz 1"
        quiz1.questions shouldHaveSize 3
    }
})