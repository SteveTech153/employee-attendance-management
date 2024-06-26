package com.example.employeeattendancemanagement.system.service

import com.example.employeeattendancemanagement.db.EmployeeDB
import com.example.employeeattendancemanagement.system.action.AuthenticatorActions
import com.example.employeeattendancemanagement.system.entity.core.Employee

class AuthenticatorService(
    val employeeDB: EmployeeDB
) : AuthenticatorActions {
    override fun getEmployee(id: Int): Employee? {
        return if(employeeDB.employees.contains(id))
            employeeDB.employees.get(id)
        else
            null
    }
}