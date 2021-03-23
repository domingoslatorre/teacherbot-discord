package com.domingoslatorre.teacherbot.teacherbotdiscord.forms

object FormFactory {
    fun registerForm(userId: String) = Form(
        title = "Formulario de Registro inicial",
        finalMessage = "Obrigado por se registrar. Confirme no seu e-mail.",
        timeoutMessage = "Tempo esgotado para responder o formulário. Digite .regiter para iniciar novamente.",
        userId = userId,
        questions = listOf(
            Question(
                text = "Digite seu prontuário?",
                validation = { isIfspCodeValid(it) },
                erroMessage = "Prontuário inválido"
            ),
            Question(
                text = "Digite seu e-mail?",
                validation = { isEmailValid(it) },
                erroMessage = "E-mail inválido"
            ),
        )
    )
}
