package com.example.employeeattendancemanagement.util

object Validator {
    fun validateName(name: String): Boolean{
        return name.length in 3..20 && name.all { it.isLetter() }
    }
    fun validatePassword(password: String): Boolean {
        if (password.length !in 6..15) return false
        var hasDigit = false
        var hasLetter = false
        var hasSpecialChar = false
        var hasUpperCase = false
        password.forEach { char ->
            when {
                char.isDigit() -> hasDigit = true
                char.isLetter() -> hasLetter = true
                char.isUpperCase() -> hasUpperCase = true
                !char.isLetterOrDigit() -> hasSpecialChar = true
            }
        }
        return hasDigit && hasLetter && hasSpecialChar && hasUpperCase
    }
}