package com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz

import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question.ShortAnswer
import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question.ShortQuestion
import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question.TrueFalseAnswer
import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question.TrueFalseQuestion
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class QuestionTest : StringSpec({
    "Create a short question" {
        val question1 = ShortQuestion(
            text = "One plus one equals to ____",
            answers = listOf(
                ShortAnswer(setOf("dois", "2", "two"), "Correct!", true)
            )
        )
        question1.text shouldBe "One plus one equals to ____"
        question1.answers shouldHaveSize 1
    }

    "Check if a answer for a simple question is valid" {
        val question1 = ShortQuestion(
            text = "One plus one equals to ____",
            answers = listOf(
                ShortAnswer(setOf("dois", "2", "two"), "Correct!", true)
            )
        )
        question1.validAnswer("two").shouldBeTrue()
        question1.validAnswer("two two").shouldBeTrue()
        question1.validAnswer("2").shouldBeTrue()
        question1.validAnswer("1").shouldBeTrue()
        question1.validAnswer("").shouldBeFalse()
        question1.validAnswer("   ").shouldBeFalse()
    }

    "Check if a answer for a simple question is correct" {
        val question1 = ShortQuestion(
            text = "One plus one equals to ____",
            answers = listOf(
                ShortAnswer(setOf("dois", "2", "two"), "Correct!", true)
            )
        )
        question1.isCorrectAnswer("dois").shouldBeTrue()
        question1.isCorrectAnswer("dois ").shouldBeTrue()
        question1.isCorrectAnswer("Dois").shouldBeTrue()
        question1.isCorrectAnswer(" DOIS ").shouldBeTrue()
        question1.isCorrectAnswer("2").shouldBeTrue()
        question1.isCorrectAnswer(" 2 ").shouldBeTrue()
        question1.isCorrectAnswer("two").shouldBeTrue()
        question1.isCorrectAnswer(" two").shouldBeTrue()
        question1.isCorrectAnswer("3").shouldBeFalse()
        question1.isCorrectAnswer("two two").shouldBeFalse()
    }

    "Create a true or false question" {
        val question1 = TrueFalseQuestion(
            text = "One plus one equals to two",
            answers = listOf(
                TrueFalseAnswer(true, "Correct!", true),
                TrueFalseAnswer(false, "False!", false)
            )
        )
        question1.text shouldBe "One plus one equals to two"
        question1.answers shouldHaveSize 2
    }

    "Check if a answer for a true or false question is valid" {
        val question1 = TrueFalseQuestion(
            text = "One plus one equals to two",
            answers = listOf(
                TrueFalseAnswer(true, "Correct!", true),
                TrueFalseAnswer(false, "False!", false)
            )
        )
        question1.validAnswer("verdadeiro").shouldBeTrue()
        question1.validAnswer("Verdadeiro").shouldBeTrue()
        question1.validAnswer("VERDADEIRO").shouldBeTrue()
        question1.validAnswer("v").shouldBeTrue()
        question1.validAnswer("V").shouldBeTrue()
        question1.validAnswer("true").shouldBeTrue()
        question1.validAnswer("TRUE").shouldBeTrue()
        question1.validAnswer("t").shouldBeTrue()
        question1.validAnswer("T").shouldBeTrue()
        question1.validAnswer("1").shouldBeTrue()
        question1.validAnswer(" verdadeiro").shouldBeTrue()
        question1.validAnswer("Verdadeiro  ").shouldBeTrue()
        question1.validAnswer(" 1 ").shouldBeTrue()

        question1.validAnswer("falso").shouldBeTrue()
        question1.validAnswer("Falso").shouldBeTrue()
        question1.validAnswer("FALSO").shouldBeTrue()
        question1.validAnswer("f").shouldBeTrue()
        question1.validAnswer("F").shouldBeTrue()
        question1.validAnswer("false").shouldBeTrue()
        question1.validAnswer("FaLSE").shouldBeTrue()
        question1.validAnswer("0").shouldBeTrue()
        question1.validAnswer(" verdadeiro").shouldBeTrue()
        question1.validAnswer("Verdadeiro  ").shouldBeTrue()
        question1.validAnswer(" 0 ").shouldBeTrue()

        question1.validAnswer("X").shouldBeFalse()
        question1.validAnswer("FALLLSO").shouldBeFalse()
        question1.validAnswer("").shouldBeFalse()
        question1.validAnswer("   ").shouldBeFalse()
    }

    "Check if a answer for a true or false question is correct" {
        val question1 = TrueFalseQuestion(
            text = "One plus one equals to two",
            answers = listOf(
                TrueFalseAnswer(true, "Correct!", true),
                TrueFalseAnswer(false, "False!", false)
            )
        )

        question1.isCorrectAnswer("v").shouldBeTrue()
        question1.isCorrectAnswer("Verdadeiro").shouldBeTrue()
        question1.isCorrectAnswer("1").shouldBeTrue()
        question1.isCorrectAnswer("TRUE").shouldBeTrue()

        question1.isCorrectAnswer("F").shouldBeFalse()
        question1.isCorrectAnswer("False").shouldBeFalse()
        question1.isCorrectAnswer("falso").shouldBeFalse()
        question1.isCorrectAnswer("0").shouldBeFalse()

    }
})