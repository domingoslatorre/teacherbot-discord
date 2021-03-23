package com.domingoslatorre.teacherbot.teacherbotdiscord.forms

data class Question(
    val text: String,
    val validation: (String) -> Boolean,
    val erroMessage: String,
    var answer: String? = null
) {
    fun answer(answer: String): Boolean {
        if (validation.invoke(answer)) {
            this.answer = answer
            return true
        }
        return false
    }
}
