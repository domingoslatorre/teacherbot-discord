//package com.domingoslatorre.teacherbot.teacherbotdiscord.commands
//
//import com.domingoslatorre.teacherbot.teacherbotdiscord.core.command.Command
//import com.domingoslatorre.teacherbot.teacherbotdiscord.core.command.CommandContext
//import com.domingoslatorre.teacherbot.teacherbotdiscord.core.command.CommandScope
//import com.domingoslatorre.teacherbot.teacherbotdiscord.forms.Form
//
//class RegisterCommand(
//    override val name: String = "Register",
//    override val triggers: Set<String> = setOf("register", "registro"),
//    override val description: String = "Associa seu e-mail acadêmico a sua conta discord",
//    override val scope: CommandScope = CommandScope.PRIVATE,
//    override val usage: List<String> = listOf("register"),
//    private val inMemoryForms: MutableMap<String, Form>
//) : Command {
//    override fun execute(context: CommandContext): Boolean {
//        return false
//    }
////    override fun execute(event: GenericEvent, channel: MessageChannel, user: User, message: Message, args: List<String>) {
////        val messageReceivedEvent = event as MessageReceivedEvent
////        val userId = messageReceivedEvent.author.id
////        if (!inMemoryForms.containsKey(userId)) {
////            val form = FormFactory.registerForm(userId)
////            inMemoryForms[userId] = form
////            form.initiate(messageReceivedEvent)
////        }
////    }
//}
