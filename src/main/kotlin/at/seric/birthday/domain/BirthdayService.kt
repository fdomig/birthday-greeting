package at.seric.birthday.domain

import at.seric.birthday.domain.commands.SendGreetingsCommand
import at.seric.birthday.domain.events.GreetingSentEvent
import java.time.LocalDate
import java.time.Month

class BirthdayService(private val employeeRepository: EmployeeRepository, private val messageService: MessageService) {

    fun apply(sendGreetingsCommand: SendGreetingsCommand): GreetingSentEvent {

        with(sendGreetingsCommand.date) {
            val employees = when (isLastOfFebruaryInNonLeapYear(this)) {
                true -> employeeRepository.findAllBornOn(this.month, this.dayOfMonth) + employeeRepository.findAllBornOn(Month.FEBRUARY, 29)
                false -> employeeRepository.findAllBornOn(this.month, this.dayOfMonth)
            }

            employees.map { messageService.createBirthdayMessage(it) }
                    .forEach { messageService.sendMessage(it) }

            return GreetingSentEvent(employees)
        }
    }

    private fun isLastOfFebruaryInNonLeapYear(date: LocalDate) =
            date.month == Month.FEBRUARY && date.dayOfMonth == 28 && !date.isLeapYear

}