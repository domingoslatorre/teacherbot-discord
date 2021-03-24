package com.domingoslatorre.teacherbot.teacherbotdiscord.core.command

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import net.dv8tion.jda.api.entities.ChannelType
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class TestCommand1 : Command {
    override val name: String = "Test Command 1"
    override val triggers: Set<String> = setOf("teste1", "test1")
    override val description: String = "Test command for testing 1"
    override val scope: CommandScope = CommandScope.BOTH
    override val usage: List<String> = listOf()

    override fun execute(event: CommandEvent) = true
}

class TestCommand1Clone : Command {
    override val name: String = "Test Command 1"
    override val triggers: Set<String> = setOf("teste1")
    override val description: String = "Test command for testing 1"
    override val scope: CommandScope = CommandScope.BOTH
    override val usage: List<String> = listOf()

    override fun execute(event: CommandEvent) = true
}

class TestCommand2 : Command {
    override val name: String = "Test Command 2"
    override val triggers: Set<String> = setOf("teste2", "test2")
    override val description: String = "Test command for testing 2"
    override val scope: CommandScope = CommandScope.BOTH
    override val usage: List<String> = listOf()

    override fun execute(event: CommandEvent) = true
}

class ChannelCommand : Command {
    override val name: String = "Channel Command"
    override val triggers: Set<String> = setOf("channel", "canel")
    override val description: String = "Test command for channel private"
    override val scope: CommandScope = CommandScope.CHANNEL
    override val usage: List<String> = listOf()

    override fun execute(event: CommandEvent) = true
}

class PrivateCommand : Command {
    override val name: String = "Private Command"
    override val triggers: Set<String> = setOf("private", "privado")
    override val description: String = "Test command for testing private"
    override val scope: CommandScope = CommandScope.PRIVATE
    override val usage: List<String> = listOf()

    override fun execute(event: CommandEvent) = true
}

class CommandHandlerTest : StringSpec({
    "Should create a command" {
        val cm = CommandHandler()

        cm.commands.shouldBeEmpty()
    }

    "Should add a command" {
        val cm = CommandHandler()
        cm.register(TestCommand1())

        cm.commands.shouldNotBeEmpty()
    }

    "Should not add a command if a trigger word already exists" {
        val cm = CommandHandler()
        cm.register(TestCommand1())
        cm.register(TestCommand1Clone())

        cm.commands shouldHaveSize 1
    }

    "Should find a command by trigger word" {
        val cm = CommandHandler()
        val command1 = TestCommand1()
        val command2 = TestCommand2()
        cm.register(command1)
        cm.register(command2)

        val commandFound1 = cm.find("test1")
        val commandFound2 = cm.find("test2")

        commandFound1.isSuccess.shouldBeTrue()
        commandFound1.getOrThrow() shouldBe command1
        commandFound2.isSuccess.shouldBeTrue()
        commandFound2.getOrThrow() shouldBe command2

    }

    "Should execute a command" {
        val cm = CommandHandler()
        val command1 = TestCommand1()
        val command2 = TestCommand2()
        cm.register(command1)
        cm.register(command2)

        val mockMessage: Message = mockk()
        every { mockMessage.contentRaw } returns "test1"

        val mockAuthor: User = mockk()
        every { mockAuthor.isBot } returns false

        val mockMessageReceive: MessageReceivedEvent = mockk()
        every { mockMessageReceive.message } returns mockMessage
        every { mockMessageReceive.author } returns mockAuthor
        every { mockMessageReceive.channel } returns mockk()

        cm.findAndExecute(mockMessageReceive).shouldBeTrue()


    }

    "Should not execute a command if is a bot message" {
        val cm = CommandHandler()
        val command1 = TestCommand1()
        val command2 = TestCommand2()
        cm.register(command1)
        cm.register(command2)

        val mockMessage: Message = mockk()
        every { mockMessage.contentRaw } returns "test1"

        val mockAuthor: User = mockk()
        every { mockAuthor.isBot } returns true

        val mockMessageReceive: MessageReceivedEvent = mockk()
        every { mockMessageReceive.message } returns mockMessage
        every { mockMessageReceive.author } returns mockAuthor
        every { mockMessageReceive.channel } returns mockk()

        cm.findAndExecute(mockMessageReceive).shouldBeFalse()


    }

    "Should execute a command scope private in a private channel" {
        val cm = CommandHandler()
        cm.register(PrivateCommand())

        val mockMessage: Message = mockk()
        every { mockMessage.contentRaw } returns "private"

        val mockAuthor: User = mockk()
        every { mockAuthor.isBot } returns false

        val mockMessageReceive: MessageReceivedEvent = mockk()
        every { mockMessageReceive.message } returns mockMessage
        every { mockMessageReceive.author } returns mockAuthor
        every { mockMessageReceive.channel } returns mockk()
        every { mockMessageReceive.isFromType(ChannelType.PRIVATE) } returns true

        cm.findAndExecute(mockMessageReceive).shouldBeTrue()


    }

    "Should not execute a command scope private in a text channel" {
        val cm = CommandHandler()
        cm.register(PrivateCommand())

        val mockMessage: Message = mockk()
        every { mockMessage.contentRaw } returns "private"

        val mockAuthor: User = mockk()
        every { mockAuthor.isBot } returns false

        val mockMessageReceive: MessageReceivedEvent = mockk()
        every { mockMessageReceive.message } returns mockMessage
        every { mockMessageReceive.author } returns mockAuthor
        every { mockMessageReceive.channel } returns mockk()
        every { mockMessageReceive.isFromType(ChannelType.PRIVATE) } returns false

        cm.findAndExecute(mockMessageReceive).shouldBeFalse()
    }

    "Should not execute a command scope channel in a private channel" {
        val cm = CommandHandler()
        cm.register(ChannelCommand())

        val mockMessage: Message = mockk()
        every { mockMessage.contentRaw } returns "channel"

        val mockAuthor: User = mockk()
        every { mockAuthor.isBot } returns false

        val mockMessageReceive: MessageReceivedEvent = mockk()
        every { mockMessageReceive.message } returns mockMessage
        every { mockMessageReceive.author } returns mockAuthor
        every { mockMessageReceive.channel } returns mockk()
        every { mockMessageReceive.isFromType(ChannelType.PRIVATE) } returns true

        cm.findAndExecute(mockMessageReceive).shouldBeFalse()
    }

    "Should execute a command scope channel in a text channel" {
        val cm = CommandHandler()
        cm.register(ChannelCommand())

        val mockMessage: Message = mockk()
        every { mockMessage.contentRaw } returns "channel"

        val mockAuthor: User = mockk()
        every { mockAuthor.isBot } returns false

        val mockMessageReceive: MessageReceivedEvent = mockk()
        every { mockMessageReceive.message } returns mockMessage
        every { mockMessageReceive.author } returns mockAuthor
        every { mockMessageReceive.channel } returns mockk()
        every { mockMessageReceive.isFromType(ChannelType.PRIVATE) } returns false

        cm.findAndExecute(mockMessageReceive).shouldBeTrue()
    }

})