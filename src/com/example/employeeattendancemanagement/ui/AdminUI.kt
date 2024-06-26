package com.example.employeeattendancemanagement.ui

import com.example.employeeattendancemanagement.system.entity.core.Admin
import com.example.employeeattendancemanagement.system.entity.core.PortalManager
import com.example.employeeattendancemanagement.util.Authenticator
import com.example.employeeattendancemanagement.util.Validator

object AdminUI {
    fun display() {
        println("\n----------Admin Login----------")
        var admin: Admin? = null

        while (admin == null) {
            print("Enter Admin password : ")
            val password: String = readln()
            admin = Authenticator.authenticateAndGetAdmin(password)
            if (admin == null) {
                println("Do you want to try again? (y/n)")
                if (readln() != "y") {
                    return
                }
            }
        }

        println("Login successful!\n")

        var adminChoice: String
        do {
            println("1. Add an employee")
            println("2. Add a subordinate for an employee")
            println("3. Set superior for an employee")
            println("4. view employees")
            println("0. Logout")
            adminChoice = readln()

            when (adminChoice) {
                "1" -> addEmployee(admin)
                "2" -> addSubordinate(admin)
                "3" -> setSuperior(admin)
                "4" -> viewEmployees(admin)
                "0" -> println("Logging out...")
                else -> println("Invalid choice!\n")
            }
        } while (adminChoice != "0")
    }

    private fun viewEmployees(admin: Admin) {
        admin.viewEmployees(admin)
    }

    private fun addEmployee(admin: Admin) {
        print("Enter name : ")
        var name: String = readln()
        while (!Validator.validateName(name)) {
            println("Invalid Name! (name should be 3 to 20 characters long and it must contain only alphabets)")
            print("Enter name : ")
            name = readln()
        }

        println("Enter superior ID: ")
        val superiorId: Int = readln().toInt()
        if(admin.addEmployee(admin, name, superiorId, PortalManager)){
            println("Employee added successfully!\n")
        }else
            println("something went wrong! Superior Id might not exist\n")
    }

    private fun addSubordinate(admin: Admin) {
        println("Enter Employee Id: ")
        val employeeId = readln().toInt()
        println("Enter Subordinate Id: ")
        val subordinateId = readln().toInt()
        if (admin.addASubOrdinateForAnEmployee(admin, employeeId, subordinateId)) {
            println("Subordinate added successfully!\n")
        } else {
            println("Couldn't add subordinate\n")
        }
    }

    private fun setSuperior(admin: Admin) {
        println("Enter Employee Id: ")
        val employeeId = readln().toInt()
        println("Enter superior Id: ")
        val superiorId = readln().toInt()
        admin.setSuperiorForAnEmployee(admin, employeeId, superiorId)
        println("Superior set successfully!\n")
    }
}
