package com.example.employeeattendancemanagement.util

object Validator {
    fun validateName(name: String): Boolean{
        return name.length>=3 && name.length<=20 && name.all { it.isLetter() }
    }

}