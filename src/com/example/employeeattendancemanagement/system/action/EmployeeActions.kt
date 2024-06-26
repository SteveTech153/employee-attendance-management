package com.example.employeeattendancemanagement.system.action

import com.example.employeeattendancemanagement.system.entity.core.Employee
import com.example.employeeattendancemanagement.system.entity.support.LeaveRequest
import java.time.LocalDate

interface EmployeeActions {
    fun checkIn(employee: Employee): Boolean
    fun checkOut(employee: Employee): Boolean
    fun markAway(employee: Employee): Boolean
    fun applyLeave(employee: Employee, fromDate: LocalDate, toDate: LocalDate, reason: String): Boolean
    fun checkStatus(employee: Employee): String
    fun checkAttendanceOnDate(employee: Employee, date: LocalDate): String
    fun checkStatusOfASubOrdinate(employee: Employee, id: Int): String
    fun checkAttendanceOfASubOrdinateOnDate(employee: Employee, id: Int, date: LocalDate): String
    fun approveLeaveRequest(employee: Employee, indexOfRequest: Int, approval: Boolean): Boolean
    fun getLeaveRequests(employee: Employee): List<LeaveRequest>
    fun checkStatusOfLeaveRequests(employee: Employee): String
    fun viewLeaveRequestsOfSubordinates(employee: Employee)
    fun viewSubOrdinates(employee: Employee)
}