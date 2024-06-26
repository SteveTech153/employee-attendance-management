package com.example.employeeattendancemanagement.system.entity.core

import com.example.employeeattendancemanagement.system.action.EmployeeActions
import com.example.employeeattendancemanagement.system.action.PasswordActions
import com.example.employeeattendancemanagement.system.assistance.EmployeeAssistance
import com.example.employeeattendancemanagement.system.entity.support.LeaveRequest
import com.example.employeeattendancemanagement.system.service.PasswordService


data class Employee(
    val name: String,
    val id: Int,
    var superiorId: Int,
    val assistance: EmployeeAssistance
): EmployeeActions by assistance,
    PasswordActions by assistance {

    val subordinatesIds = HashSet<Int>()
    val subordinatesLeaveRequests = HashSet<LeaveRequest>()

}