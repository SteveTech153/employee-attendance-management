package com.example.employeeattendancemanagement.system.entity.support

import java.time.LocalDate

data class LeaveRequest(
    val emp_id: Int,
    val fromDate: LocalDate,
    val toDate: LocalDate,
    val reason: String,
    var approval: Status
) {
    override fun toString(): String {
        return "Employee Id : $emp_id, From date: $fromDate, To date: $toDate, reason: $reason, approval: $approval"
    }
}