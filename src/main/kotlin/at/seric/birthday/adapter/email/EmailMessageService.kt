package at.seric.birthday.adapter.email

import at.seric.birthday.domain.MessageService
import at.seric.birthday.domain.entities.Employee
import at.seric.birthday.domain.entities.Message

class EmailMessageService : MessageService {

    override fun sendMessage(message: Message) {
        println("Send message: $message")
    }

    override fun createBirthdayMessage(employee: Employee): Message {
        return Message("Happy birthday!", "Happy birthday, dear ${employee.firstName}!")
    }

}