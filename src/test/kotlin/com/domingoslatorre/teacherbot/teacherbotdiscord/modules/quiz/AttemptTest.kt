package com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz

import com.domingoslatorre.teacherbot.teacherbotdiscord.modules.quiz.attempt.Attempt
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
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class AttemptTest : StringSpec({
    "Create a quiz attempt" {
        val member1 = MemberFactory.member1()

        val question1 = ShortQuestion(
            text = "One plus one equals to ____",
            answers = ShortAnswer(setOf("dois", "2", "two"), "Correct!", true)
        )

        val question2 = TrueFalseQuestion(
            text = "One plus one equals to two",
            answers = listOf(
                TrueFalseAnswer(true, "Correct!", true),
                TrueFalseAnswer(false, "False!", false)
            )
        )

        val question3 = MultipleChoiceQuestion(
            text = "Which city in Brazil is known as the Marvelous City?",
            answers = listOf(
                ChoiceAnswer("São Paulo", false),
                ChoiceAnswer("Curitiba", false),
                ChoiceAnswer("Rio de Janeiro", true),
                ChoiceAnswer("Santos", false),
                ChoiceAnswer("Guarulhos", false)
            )
        )

        val quiz1 = Quiz(title = "Quiz 1", questions = listOf(question1, question2, question3))
        val attempt = Attempt(quiz = quiz1, member = member1)

        with(attempt) {
            id.shouldNotBeNull()
            start.shouldNotBeNull()
            end.shouldBeNull()
            currentQuestion shouldBe question1
            quiz shouldBe quiz1
            member shouldBe member1
            attemptAnswers shouldHaveSize 0
        }
    }

    "Answer a quiz attempt" {
        val member1 = MemberFactory.member1()

        val question1 = ShortQuestion(
            text = "One plus one equals to ____",
            answers = ShortAnswer(setOf("dois", "2", "two"), "Correct!", true)
        )

        val question2 = TrueFalseQuestion(
            text = "One plus one equals to two",
            answers = listOf(
                TrueFalseAnswer(true, "Correct!", true),
                TrueFalseAnswer(false, "False!", false)
            )
        )

        val question3 = MultipleChoiceQuestion(
            text = "Which city in Brazil is known as the Marvelous City?",
            answers = listOf(
                ChoiceAnswer("São Paulo", false),
                ChoiceAnswer("Curitiba", false),
                ChoiceAnswer("Rio de Janeiro", true),
                ChoiceAnswer("Santos", false),
                ChoiceAnswer("Guarulhos", false)
            )
        )

        val quiz1 = Quiz(title = "Quiz 1", questions = listOf(question1, question2, question3))
        val attempt = Attempt(quiz = quiz1, member = member1)

        attempt.answer("dois")
        attempt.attemptAnswers shouldHaveSize 1
        attempt.currentQuestion shouldBe question2
        attempt.isOpen().shouldBeTrue()

        attempt.answer("true")
        attempt.attemptAnswers shouldHaveSize 2
        attempt.currentQuestion shouldBe question3
        attempt.isOpen().shouldBeTrue()

        attempt.answer("3")
        attempt.attemptAnswers shouldHaveSize 3
        attempt.currentQuestion shouldBe null
        attempt.isOpen().shouldBeFalse()
        attempt.numberCorrectAnswers() shouldBe 3
        attempt.numberIncorrectAnswers() shouldBe 0
    }

    "Answer a quiz attempt with a invalid value" {
        val member1 = MemberFactory.member1()

        val question1 = ShortQuestion(
            text = "One plus one equals to ____",
            answers = ShortAnswer(setOf("dois", "2", "two"), "Correct!", true)
        )

        val question2 = TrueFalseQuestion(
            text = "One plus one equals to two",
            answers = listOf(
                TrueFalseAnswer(true, "Correct!", true),
                TrueFalseAnswer(false, "False!", false)
            )
        )

        val question3 = MultipleChoiceQuestion(
            text = "Which city in Brazil is known as the Marvelous City?",
            answers = listOf(
                ChoiceAnswer("São Paulo", false),
                ChoiceAnswer("Curitiba", false),
                ChoiceAnswer("Rio de Janeiro", true),
                ChoiceAnswer("Santos", false),
                ChoiceAnswer("Guarulhos", false)
            )
        )

        val quiz1 = Quiz(title = "Quiz 1", questions = listOf(question1, question2, question3))
        val attempt = Attempt(quiz = quiz1, member = member1)

        attempt.answer(" ")
        attempt.attemptAnswers shouldHaveSize 0
        attempt.currentQuestion shouldBe question1
        attempt.isOpen().shouldBeTrue()

        attempt.answer("true")
        attempt.attemptAnswers shouldHaveSize 2
        attempt.currentQuestion shouldBe question3
        attempt.isOpen().shouldBeTrue()

        attempt.answer("3")
        attempt.attemptAnswers shouldHaveSize 3
        attempt.currentQuestion shouldBe null
        attempt.isOpen().shouldBeFalse()
        attempt.numberCorrectAnswers() shouldBe 3
        attempt.numberIncorrectAnswers() shouldBe 0
    }

})