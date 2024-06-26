package com.example.employeeattendancemanagement.system.entity.core

import com.example.employeeattendancemanagement.db.EmployeeDB
import com.example.employeeattendancemanagement.system.action.AuthenticatorActions
import com.example.employeeattendancemanagement.system.action.EmployeeActions
import com.example.employeeattendancemanagement.system.action.EmployeeManagementActions
import com.example.employeeattendancemanagement.system.action.PasswordActions
import com.example.employeeattendancemanagement.system.assistance.EmployeeAssistance
import com.example.employeeattendancemanagement.system.entity.support.Status
import com.example.employeeattendancemanagement.system.service.AuthenticatorService
import com.example.employeeattendancemanagement.system.service.PasswordService
import com.example.employeeattendancemanagement.system.service.PortalManagerEmployeeManagementService
import com.example.employeeattendancemanagement.system.service.PortalManagerEmployeeService
import java.time.LocalDate

object PortalManager:
    EmployeeAssistance(),
    EmployeeActions by PortalManagerEmployeeService(employeeDB),
    EmployeeManagementActions by PortalManagerEmployeeManagementService(employeeDB),
    AuthenticatorActions by AuthenticatorService(employeeDB),
    PasswordActions by PasswordService
{
    private object employeeDB : EmployeeDB {
        override val employees: MutableMap<Int, Employee> = mutableMapOf()
        override val statuses: MutableMap<Int, MutableMap<LocalDate, MutableList<Status>>> = mutableMapOf()
    }
}
