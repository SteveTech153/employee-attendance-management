package com.example.employeeattendancemanagement.db

import com.example.employeeattendancemanagement.system.entity.core.Employee
import com.example.employeeattendancemanagement.system.entity.support.Status
import java.time.LocalDate

interface EmployeeDB {
    val employees: MutableMap<Int, Employee>
    val statuses: MutableMap<Int, MutableMap<LocalDate, MutableList<Status>>>
}