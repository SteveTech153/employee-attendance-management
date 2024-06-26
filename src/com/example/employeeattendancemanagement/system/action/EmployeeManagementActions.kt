package com.example.employeeattendancemanagement.system.action

import com.example.employeeattendancemanagement.system.assistance.EmployeeAssistance

interface EmployeeManagementActions {
    fun addEmployee(name: String, superiorId: Int, assistance: EmployeeAssistance): Boolean
    fun addASubOrdinateForAnEmployee(id: Int, subordinateId: Int): Boolean
    fun setSuperiorForAnEmployee(id: Int, superiorId: Int): Boolean
    fun viewEmployees()
}