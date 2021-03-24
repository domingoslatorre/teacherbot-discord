package com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz

import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question.ChoiceAnswer
import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question.MultipleChoiceQuestion
import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question.ShortAnswer
import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question.ShortQuestion
import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question.TrueFalseAnswer
import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.question.TrueFalseQuestion
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class QuestionTest : StringSpec({
    "Create a short question" {
        val question1 = ShortQuestion(
            text = "One plus one equals to ____",
            answers = ShortAnswer(setOf("dois", "2", "two"), "Correct!", true)
        )
        question1.text shouldBe "One plus one equals to ____"
        question1.answers.shouldNotBeNull()
    }

    "Check if a answer for a simple question is valid" {
        val question1 = ShortQuestion(
            text = "One plus one equals to ____",
            answers = ShortAnswer(setOf("dois", "2", "two"), "Correct!", true)
        )
        question1.isValidAnswer("two").shouldBeTrue()
        question1.isValidAnswer("two two").shouldBeTrue()
        question1.isValidAnswer("2").shouldBeTrue()
        question1.isValidAnswer("1").shouldBeTrue()
        question1.isValidAnswer("").shouldBeFalse()
        question1.isValidAnswer("   ").shouldBeFalse()
    }

    "Check if a answer for a simple question is correct" {
        val question1 = ShortQuestion(
            text = "One plus one equals to ____",
            answers = ShortAnswer(setOf("dois", "2", "two"), "Correct!", true)
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
        question1.isValidAnswer("verdadeiro").shouldBeTrue()
        question1.isValidAnswer("Verdadeiro").shouldBeTrue()
        question1.isValidAnswer("VERDADEIRO").shouldBeTrue()
        question1.isValidAnswer("v").shouldBeTrue()
        question1.isValidAnswer("V").shouldBeTrue()
        question1.isValidAnswer("true").shouldBeTrue()
        question1.isValidAnswer("TRUE").shouldBeTrue()
        question1.isValidAnswer("t").shouldBeTrue()
        question1.isValidAnswer("T").shouldBeTrue()
        question1.isValidAnswer("1").shouldBeTrue()
        question1.isValidAnswer(" verdadeiro").shouldBeTrue()
        question1.isValidAnswer("Verdadeiro  ").shouldBeTrue()
        question1.isValidAnswer(" 1 ").shouldBeTrue()

        question1.isValidAnswer("falso").shouldBeTrue()
        question1.isValidAnswer("Falso").shouldBeTrue()
        question1.isValidAnswer("FALSO").shouldBeTrue()
        question1.isValidAnswer("f").shouldBeTrue()
        question1.isValidAnswer("F").shouldBeTrue()
        question1.isValidAnswer("false").shouldBeTrue()
        question1.isValidAnswer("FaLSE").shouldBeTrue()
        question1.isValidAnswer("0").shouldBeTrue()
        question1.isValidAnswer(" verdadeiro").shouldBeTrue()
        question1.isValidAnswer("Verdadeiro  ").shouldBeTrue()
        question1.isValidAnswer(" 0 ").shouldBeTrue()

        question1.isValidAnswer("X").shouldBeFalse()
        question1.isValidAnswer("FALLLSO").shouldBeFalse()
        question1.isValidAnswer("").shouldBeFalse()
        question1.isValidAnswer("   ").shouldBeFalse()
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

    "Check if a answer for a negation true or false question is correct" {
        val question1 = TrueFalseQuestion(
            text = "One plus one is not equals to two",
            answers = listOf(
                TrueFalseAnswer(false, "Correct!", true),
                TrueFalseAnswer(true, "False!", false)
            )
        )

        question1.isCorrectAnswer("v").shouldBeFalse()
        question1.isCorrectAnswer("Verdadeiro").shouldBeFalse()
        question1.isCorrectAnswer("1").shouldBeFalse()
        question1.isCorrectAnswer("TRUE").shouldBeFalse()

        question1.isCorrectAnswer("F").shouldBeTrue()
        question1.isCorrectAnswer("False").shouldBeTrue()
        question1.isCorrectAnswer("falso").shouldBeTrue()
        question1.isCorrectAnswer("0").shouldBeTrue()

    }

    "Create a multiple choice question" {
        val question1 = MultipleChoiceQuestion(
            text = "One plus one equals to?",
            answers = listOf(
                ChoiceAnswer("1", false),
                ChoiceAnswer("3", false),
                ChoiceAnswer("2", true),
                ChoiceAnswer("5", false),
                ChoiceAnswer("0", false)
            )
        )
        question1.text shouldBe "One plus one equals to?"
        question1.answers shouldHaveSize 5
    }

    "Check if a answer for a multiple choice question is valid" {
        val question1 = MultipleChoiceQuestion(
            text = "Which city in Brazil is known as the Marvelous City?",
            answers = listOf(
                ChoiceAnswer("São Paulo", false),
                ChoiceAnswer("Curitiba", false),
                ChoiceAnswer("Rio de Janeiro", true),
                ChoiceAnswer("Santos", false),
                ChoiceAnswer("Guarulhos", false)
            )
        )

        question1.isValidAnswer("1").shouldBeTrue()
        question1.isValidAnswer("2").shouldBeTrue()
        question1.isValidAnswer("3").shouldBeTrue()
        question1.isValidAnswer("4").shouldBeTrue()
        question1.isValidAnswer("5").shouldBeTrue()
        question1.isValidAnswer(" 3").shouldBeTrue()
        question1.isValidAnswer("3 ").shouldBeTrue()
        question1.isValidAnswer(" 3 ").shouldBeTrue()

        question1.isValidAnswer("6").shouldBeFalse()
        question1.isValidAnswer("6 ").shouldBeFalse()
        question1.isValidAnswer("20").shouldBeFalse()
        question1.isValidAnswer("0").shouldBeFalse()
        question1.isValidAnswer("-1").shouldBeFalse()
        question1.isValidAnswer("-100").shouldBeFalse()
    }

    "Check if a answer for a multiple choice question is correct" {
        val question1 = MultipleChoiceQuestion(
            text = "Which city in Brazil is known as the Marvelous City?",
            answers = listOf(
                ChoiceAnswer("São Paulo", false),
                ChoiceAnswer("Curitiba", false),
                ChoiceAnswer("Rio de Janeiro", true),
                ChoiceAnswer("Santos", false),
                ChoiceAnswer("Guarulhos", false)
            )
        )

        question1.isCorrectAnswer("3").shouldBeTrue()
        question1.isCorrectAnswer("3 ").shouldBeTrue()
        question1.isCorrectAnswer(" 3 ").shouldBeTrue()

        question1.isCorrectAnswer("1").shouldBeFalse()
        question1.isCorrectAnswer("2").shouldBeFalse()
        question1.isCorrectAnswer("4").shouldBeFalse()
        question1.isCorrectAnswer("5").shouldBeFalse()
        question1.isCorrectAnswer("6").shouldBeFalse()
        question1.isCorrectAnswer("X").shouldBeFalse()

    }

})