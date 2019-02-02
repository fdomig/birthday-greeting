package at.seric.birthday.domain.events

import at.seric.birthday.domain.entities.Employee

data class GreetingSentEvent(val employees: List<Employee>)