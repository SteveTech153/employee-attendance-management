package com.example.employeeattendancemanagement.util

import com.example.employeeattendancemanagement.db.EmployeeDB
import com.example.employeeattendancemanagement.system.action.AuthenticatorActions
import com.example.employeeattendancemanagement.system.entity.core.Admin
import com.example.employeeattendancemanagement.system.entity.core.Employee
import com.example.employeeattendancemanagement.system.entity.core.PortalManager

object Authenticator: AuthenticatorActions by PortalManager {
    fun authenticateAndGetAdmin(password: String): Admin?{
        val admin: Admin
        try{
            admin = Admin(password)
            return admin
        }catch (e: Exception){
            println(e.message)
        }
        return null
    }
    fun authenticateAndGetEmployee(id: Int): Employee?{
         val employee = getEmployee(id)
        return if(employee==null){
            println("Id is wrong !")
            null
        }else{
            employee
        }
    }
}