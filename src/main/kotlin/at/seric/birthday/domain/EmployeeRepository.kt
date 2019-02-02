package at.seric.birthday.domain

import at.seric.birthday.domain.entities.Employee
import java.time.Month

interface EmployeeRepository {
    fun findAllBornOn(month: Month, dayOfMonth: Int): List<Employee>
}