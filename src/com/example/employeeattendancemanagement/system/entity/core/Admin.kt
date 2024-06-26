package com.example.employeeattendancemanagement.system.entity.core

import com.example.employeeattendancemanagement.system.action.EmployeeManagementActions
import com.example.employeeattendancemanagement.system.action.PasswordActions
import com.example.employeeattendancemanagement.system.service.PasswordService

class Admin private constructor (
    private val portalManager: PortalManager = PortalManager
): EmployeeManagementActions by portalManager,
    PasswordActions by PasswordService
{
    val password = "${'$'}2a${'$'}10${'$'}HMupEn79IHbztCo5lRAqjOyg5SCmFaQ/cqgyjnV6S8oY.wYVDzNXO"

    constructor(password: String) : this() {
        if(!checkPassword(password, this.password)){
            throw IllegalAccessException("Password is wrong !")
        }
    }
}