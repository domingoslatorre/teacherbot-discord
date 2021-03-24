//package com.domingoslatorre.teacherbot.teacherbotdiscord.commands
//
//import com.domingoslatorre.teacherbot.teacherbotdiscord.core.command.Command
//import com.domingoslatorre.teacherbot.teacherbotdiscord.core.command.CommandContext
//import com.domingoslatorre.teacherbot.teacherbotdiscord.core.command.CommandScope
//
//class ProfileCommand(
//    override val name: String = "Profile",
//    override val triggers: Set<String> = setOf("profile", "perfil", "conta"),
//    override val description: String = "Apresenta informações do seu perfil",
//    override val scope: CommandScope = CommandScope.BOTH,
//    override val usage: List<String> = listOf(
//        "profile",
//        "profile <@username> //mostra o perfil de um usuário"
//    )
//) : Command {
//    override fun execute(context: CommandContext): Boolean {
//        return true
//    }
////    override fun execute(event: GenericEvent, channel: MessageChannel, user: User, message: Message, args: List<String>) {
////        val messageReceivedEvent = event as MessageReceivedEvent
////        if(args.isEmpty()) {
////            messageReceivedEvent.reply("Informações do seu perfil aqui: ${user.name}")
////        } else {
////            val userName = args[0]
////            val userFounded = channel.jda.getUsersByName(userName, true)
////            return if (userFounded.isEmpty()) {
////                messageReceivedEvent.reply("Usuário não encontrado com username $userName")
////            } else {
////                messageReceivedEvent.reply("Dados do usuário $userName: ${userFounded.first().name}")
////            }
////        }
////    }
//}