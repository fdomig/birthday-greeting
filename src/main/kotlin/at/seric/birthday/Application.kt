package at.seric.birthday

import at.seric.birthday.adapter.csv.CsvFileEmployeeRepository
import at.seric.birthday.adapter.email.EmailMessageService
import at.seric.birthday.domain.BirthdayService
import at.seric.birthday.domain.commands.SendGreetingsCommand
import java.time.LocalDate

fun main(args: Array<String>) {

    val employeeRepository = CsvFileEmployeeRepository("src/main/resources/employees.csv")
    val emailService = EmailMessageService()
    val birthdayService = BirthdayService(employeeRepository, emailService)

    val event = birthdayService.apply(SendGreetingsCommand(today()))

    print("Sent greetings to ${event.employees.size} employee(s)")
}

fun today(): LocalDate {
    return LocalDate.now()
}

