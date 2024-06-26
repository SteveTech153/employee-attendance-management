package com.example.employeeattendancemanagement.system.action

import com.example.employeeattendancemanagement.system.assistance.EmployeeAssistance
import com.example.employeeattendancemanagement.system.entity.core.Admin

interface EmployeeManagementActions {
    fun addEmployee(admin: Admin, name: String, superiorId: Int, assistance: EmployeeAssistance): Boolean
    fun addASubOrdinateForAnEmployee(admin: Admin, id: Int, subordinateId: Int): Boolean
    fun setSuperiorForAnEmployee(admin: Admin, id: Int, superiorId: Int): Boolean
    fun viewEmployees(admin: Admin)
}