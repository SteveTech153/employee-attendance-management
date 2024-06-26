package com.example.employeeattendancemanagement.ui

object UI {
    fun display() {
        println("_______________________________Welcome to Employee Attendance Management System_______________________________")

        var choice = ""
        while (choice != "0") {
            println("\n----------Main Menu----------")
            println("1. Login as Admin")
            println("2. Login as Employee")
            println("0. Exit")
            print("Enter your choice : ")
            choice = readln()
            when (choice) {
                "1" -> AdminUI.display()
                "2" -> EmployeeUI.display()
                "0" -> {}
                else -> println("Invalid choice!\n")
            }
        }

        println("\n___________________________Thank you for using Employee Attendance Management System___________________________\n")
    }
}