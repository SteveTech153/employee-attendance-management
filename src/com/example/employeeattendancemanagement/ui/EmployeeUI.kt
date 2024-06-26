package com.example.employeeattendancemanagement.ui

import com.example.employeeattendancemanagement.system.entity.core.Employee
import com.example.employeeattendancemanagement.util.Authenticator
import com.example.employeeattendancemanagement.util.Validator
import java.time.LocalDate

object EmployeeUI {

    fun display() {
        println("\n----------Employee Login----------")
        var employee: Employee? = null
        while (employee == null) {
            print("Enter employee ID : ")
            val id: Int = readln().toInt()
            employee = Authenticator.authenticateAndGetEmployee(id)
            if (employee == null) {
                println("Do you want to try again? (y/n)")
                if (readln() != "y") {
                    return
                }
            }
        }
        println("Login successful!\n")
        println("\n----------Employee Menu----------")
        var employeeChoice: String
        do {
            printMenu()
            employeeChoice = readLine() ?: ""
            when (employeeChoice) {
                "1" -> checkIn(employee)
                "2" -> checkOut(employee)
                "3" -> markAway(employee)
                "4" -> applyLeave(employee)
                "5" -> checkMyStatus(employee)
                "6" -> checkMyAttendanceOnDate(employee)
                "7" -> viewSubordinates(employee)
                "8" -> checkStatusOfASubordinate(employee)
                "9" -> checkAttendanceOfASubordinateOnDate(employee)
                "10" -> approveLeaveRequest(employee)
                "11" -> checkStatusOfMyLeaveRequests(employee)
                "12" -> viewLeaveRequestsOfSubordinates(employee)
                "0" -> println("Logging out...")
                else -> println("Invalid choice!\n")
            }
        } while (employeeChoice != "0")
    }

    private fun printMenu() {
        println("1. Check in")
        println("2. Check out")
        println("3. Mark away")
        println("4. Apply leave")
        println("5. Check my status")
        println("6. Check my attendance on a date")
        println("7. View subordinates")
        println("8. Check status of a subordinate")
        println("9. Check attendance of a subordinate on a date")
        println("10. Approve a leave request")
        println("11. Check status of my leave requests")
        println("12. View leave requests of subordinates")
        println("0. Logout")
        print("Enter your choice: ")
    }

    private fun checkIn(employee: Employee) {
        if (employee.checkIn(employee)) {
            println("Checked in successfully!")
        } else {
            println("Check in failed")
        }
        println()
    }

    private fun checkOut(employee: Employee) {
        if (employee.checkOut(employee)) {
            println("Checked out successfully!")
        } else {
            println("Check out failed")
        }
        println()
    }

    private fun markAway(employee: Employee) {
        if (employee.markAway(employee)) {
            println("Status is set as away successfully!")
        } else {
            println("Status change failed")
        }
        println()
    }

    private fun applyLeave(employee: Employee) {
        var validFromDate = false
        var fromDate: LocalDate = LocalDate.now()
        var toDate: LocalDate = LocalDate.now()

        while (!validFromDate) {
            println("Enter leave from date (yyyy-mm-dd): ")
            val fromDateString = readLine() ?: ""
            try {
                fromDate = LocalDate.parse(fromDateString)
                if (fromDate.isBefore(LocalDate.now())) {
                    throw IllegalArgumentException("Date must be today or in the future.")
                }
                validFromDate = true
            } catch (e: Exception) {
                println("Invalid date format or date must be today or in the future. Enter again.")
            }
        }

        var validToDate = false
        while (!validToDate) {
            println("Enter leave to date (yyyy-mm-dd): ")
            val toDateString = readLine() ?: ""
            try {
                toDate = LocalDate.parse(toDateString)
                if (toDate.isBefore(LocalDate.now()) || toDate.isBefore(fromDate)) {
                    throw IllegalArgumentException("Date must be today or later than from date.")
                }
                validToDate = true
            } catch (e: Exception) {
                println("Invalid date format or date must be today or later than from date. Enter again.")
            }
        }

        println("Enter reason: ")
        val reason = readLine() ?: ""

        if (employee.applyLeave(employee, fromDate, toDate, reason)) {
            println("Leave applied successfully!")
        } else {
            println("Leave application failed")
        }
        println()
    }

    private fun checkMyStatus(employee: Employee) {
        val status = employee.checkStatus(employee)
        println("Your current status: $status")
        println()
    }

    private fun checkMyAttendanceOnDate(employee: Employee) {
        println("Enter date to check attendance (yyyy-mm-dd): ")
        val dateString = readLine() ?: ""
        try {
            val date = LocalDate.parse(dateString)
            val attendanceStatus = employee.checkAttendanceOnDate(employee, date)
            println("Attendance status on $date: $attendanceStatus")
        } catch (e: Exception) {
            println("Invalid date format. Please enter date in yyyy-mm-dd format.")
        }
        println()
    }

    private fun viewSubordinates(employee: Employee) {
        println("Subordinates:")
        employee.viewSubOrdinates(employee)
        println()
    }

    private fun checkStatusOfASubordinate(employee: Employee) {
        viewSubordinates(employee)
        println("Enter subordinate ID: ")
        val subordinateId = readLine()?.toIntOrNull() ?: return
        val status = employee.checkStatusOfASubOrdinate(employee, subordinateId)
        println("Subordinate's status: $status")
        println()
    }

    private fun checkAttendanceOfASubordinateOnDate(employee: Employee) {
        viewSubordinates(employee)
        println("Enter subordinate ID: ")
        val subordinateId = readLine()?.toIntOrNull() ?: return
        println("Enter date to check attendance (yyyy-mm-dd): ")
        val dateString = readLine() ?: ""
        try {
            val date = LocalDate.parse(dateString)
            val attendanceStatus = employee.checkAttendanceOfASubOrdinateOnDate(employee, subordinateId, date)
            println("Subordinate's attendance status on $date: $attendanceStatus")
        } catch (e: Exception) {
            println("Invalid date format. Please enter date in yyyy-mm-dd format.")
        }
        println()
    }

    private fun approveLeaveRequest(employee: Employee) {
        viewLeaveRequestsOfSubordinates(employee)

        println("Enter index of leave request to approve: ")
        val index = readLine()?.toIntOrNull() ?: return
        println("Approve leave request? (y/n): ")
        val approval = readLine()?.toLowerCase() == "y"
        if (employee.approveLeaveRequest(employee, index, approval)) {
            println("Leave request approved successfully!")
        } else {
            println("Leave request approval failed")
        }
        println()
    }

    private fun checkStatusOfMyLeaveRequests(employee: Employee) {
        println("Your leave requests status:")
        val status = employee.checkStatusOfLeaveRequests(employee)
        println(status)
        println()
    }

    private fun viewLeaveRequestsOfSubordinates(employee: Employee) {
        println("Leave requests of your subordinates:")
        employee.viewLeaveRequestsOfSubordinates(employee)
        println()
    }

}
