package at.seric.birthday.domain.entities

import java.time.LocalDate

data class Employee(val lastName: String, val firstName: String, val email: String, val dateOfBirth: LocalDate)
