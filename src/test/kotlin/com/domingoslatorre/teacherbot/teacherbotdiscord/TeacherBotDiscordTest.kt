package com.domingoslatorre.teacherbot.teacherbotdiscord

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class TeacherBotDiscordTest : StringSpec({
    "strings.length should return size of string" {
        "hello".length shouldBe 5
    }
})
