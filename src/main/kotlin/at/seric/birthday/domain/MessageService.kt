package at.seric.birthday.domain

import at.seric.birthday.domain.entities.Employee
import at.seric.birthday.domain.entities.Message

interface MessageService {
    fun createBirthdayMessage(employee: Employee): Message

    fun sendMessage(message: Message)
}