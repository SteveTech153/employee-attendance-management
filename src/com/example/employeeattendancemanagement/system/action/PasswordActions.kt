package com.example.employeeattendancemanagement.system.action

interface PasswordActions {
    fun hashPassword(password: String): String
    fun checkPassword(password: String, hashed: String): Boolean
}