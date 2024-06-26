package com.example.employeeattendancemanagement.system.service

import com.example.employeeattendancemanagement.system.action.PasswordActions
import org.mindrot.jbcrypt.BCrypt

object PasswordService:
    PasswordActions
{
    val salt = """${'$'}2a${'$'}10${'$'}HMupEn79IHbztCo5lRAqjO"""
    override fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, salt)
    }
    override fun checkPassword(password: String, hashed: String): Boolean {
        return BCrypt.hashpw(password, salt).equals(hashed)
    }
}