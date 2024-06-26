package com.example.employeeattendancemanagement.system.entity.support

enum class Status (val statusName: String, val message: String) {
    CHECK_IN("check-in", "User has checked in"),
    CHECK_OUT("check-out", "User has checked out"),
    AWAY("away", "User is away"),
    LEAVE("leave", "User is on leave today"),
    APPROVED("approved", "the request is approved"),
    REJECTED("rejected", "the request is rejected"),
    UNSEEN("unseen", "superior hasn't viewed the request yet")
}