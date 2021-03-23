package com.domingoslatorre.teacherbot.teacherbotdiscord.forms

const val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
const val IFSP_CODE_REGEX = """[a-zA-Z]{2}\d{6}(\d{1}|[a-zA-Z]{1})"""

fun isEmailValid(email: String) = EMAIL_REGEX.toRegex().matches(email)

fun isIfspCodeValid(ifspCode: String) = IFSP_CODE_REGEX.toRegex().matches(ifspCode)
