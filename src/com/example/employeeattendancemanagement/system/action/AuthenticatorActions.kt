package com.example.employeeattendancemanagement.system.action

import com.example.employeeattendancemanagement.system.entity.core.Employee

interface AuthenticatorActions {
    fun getEmployee(id: Int): Employee?
}