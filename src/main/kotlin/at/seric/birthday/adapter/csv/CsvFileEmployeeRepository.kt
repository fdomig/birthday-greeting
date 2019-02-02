package at.seric.birthday.adapter.csv

import at.seric.birthday.domain.EmployeeRepository
import at.seric.birthday.domain.entities.Employee
import java.io.BufferedReader
import java.io.FileReader
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

class CsvFileEmployeeRepository(private val fileName: String) : EmployeeRepository {


    private val employees = readFromFile()

    override fun findAllBornOn(month: Month, dayOfMonth: Int): List<Employee> {
        return employees.filter { it.dateOfBirth.month == month && it.dateOfBirth.dayOfMonth == dayOfMonth }
    }

    private fun readFromFile(): List<Employee> {

        val employees = mutableListOf<Employee>()
        var line: String?

        val fileReader = BufferedReader(FileReader(fileName))

        // Read CSV header
        fileReader.readLine()

        // Read the file line by line starting from the second line
        line = fileReader.readLine()
        while (line != null) {
            val tokens = line.split(",")
            if (tokens.isNotEmpty()) {
                employees.add(Employee(
                        tokens[EMPLOYEE_LAST_NAME_IDX].trim(),
                        tokens[EMPLOYEE_FIRST_NAME_IDX].trim(),
                        tokens[EMPLOYEE_EMAIL_IDX].trim(),
                        LocalDate.parse(tokens[EMPLOYEE_BIRTHDAY_IDX].trim(), DATE_FORMAT)))
            }

            line = fileReader.readLine()
        }

        return employees
    }

    companion object {
        const val EMPLOYEE_LAST_NAME_IDX = 0
        const val EMPLOYEE_FIRST_NAME_IDX = 1
        const val EMPLOYEE_BIRTHDAY_IDX = 2
        const val EMPLOYEE_EMAIL_IDX = 3

        val DATE_FORMAT = DateTimeFormatterBuilder()
                .appendValue(ChronoField.YEAR, 4)
                .appendLiteral('/')
                .appendValue(ChronoField.DAY_OF_MONTH, 2)
                .appendLiteral('/')
                .appendValue(ChronoField.MONTH_OF_YEAR, 2)
                .toFormatter()
    }

}