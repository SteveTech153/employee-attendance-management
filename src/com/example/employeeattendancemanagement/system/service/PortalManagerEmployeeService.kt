package com.example.employeeattendancemanagement.system.service

import com.example.employeeattendancemanagement.db.EmployeeDB
import com.example.employeeattendancemanagement.system.action.EmployeeActions
import com.example.employeeattendancemanagement.system.entity.core.Employee
import com.example.employeeattendancemanagement.system.entity.support.LeaveRequest
import com.example.employeeattendancemanagement.system.entity.support.Status
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class PortalManagerEmployeeService(
    val employeeDB: EmployeeDB
): EmployeeActions {
    private fun getStatusIdMap(id: Int): MutableMap<LocalDate, MutableList<Status>> {
        return employeeDB.statuses.getOrDefault(id, HashMap())
    }
    private fun getStatusListOfAnEmployee(id: Int): MutableList<Status>{
        val statusMapOfId = getStatusIdMap(id)
        val statusList = statusMapOfId.getOrDefault(LocalDate.now(), ArrayList())
        return statusList
    }
    private fun updateStatus(id: Int, statusList: MutableList<Status>){
        val statusMapOfId = getStatusIdMap(id)
        statusMapOfId[LocalDate.now()] = statusList
        employeeDB.statuses[id] = statusMapOfId
    }
    private fun updateStatusOnADate(id: Int, statusList: MutableList<Status>, date: LocalDate){
        val statusMapOfId = getStatusIdMap(id)
        statusMapOfId[date] = statusList
        employeeDB.statuses[id] = statusMapOfId
    }
    private fun checkStatus(id: Int): String {
        val statusList = getStatusListOfAnEmployee(id)
        return if(statusList.isEmpty()){
            "out"
        }else{
            statusList.last().message
        }
    }
    private fun checkAttendanceOnDate(id: Int, date: LocalDate): String {
        return if(employeeDB.statuses[id]?.get(date)?.isNotEmpty() == true){
            if( employeeDB.statuses[id]?.get(date)?.first()?.equals(Status.LEAVE) == true)
                "User on leave"
            else
                "User checked in"
        }else{
            "User didn't check in"
        }
    }
    private fun checkIfSubordinate(employee: Employee, id: Int): Boolean {
        return employee.subordinatesIds.contains(id)
    }

    override fun checkIn(employee: Employee): Boolean {
        val statusList = getStatusListOfAnEmployee(employee.id)
        return if( statusList.isNotEmpty() && statusList.last().equals(Status.CHECK_IN) ){
            false
        }else{
            statusList.add(Status.CHECK_IN)
            updateStatus(employee.id, statusList)
            true
        }
    }

    override fun checkOut(employee: Employee): Boolean {
        val statusList = getStatusListOfAnEmployee(employee.id)
        return if( statusList.isEmpty() || (statusList.last().equals(Status.CHECK_OUT) )){
            false
        }else{
            statusList.add(Status.CHECK_OUT)
            updateStatus(employee.id, statusList)
            true
        }
    }

    override fun markAway(employee: Employee): Boolean {
        val statusList = getStatusListOfAnEmployee(employee.id)
        return if( statusList.isEmpty() || !statusList.last().equals(Status.CHECK_IN) ){
            false
        }else{
            statusList.add(Status.AWAY)
            updateStatus(employee.id, statusList)
            true
        }
    }

    override fun applyLeave(employee: Employee, fromDate: LocalDate, toDate: LocalDate, reason: String): Boolean {
        val request = LeaveRequest(employee.id, fromDate, toDate, reason, Status.UNSEEN)
        return employeeDB.employees[employee.superiorId]?.subordinatesLeaveRequests?.add(request) == true
    }

    override fun checkStatus(employee: Employee): String {
        return checkStatus(employee.id)
    }


    override fun checkAttendanceOnDate(employee: Employee, date: LocalDate): String {
        return checkAttendanceOnDate(employee.id, date)
    }

    override fun checkStatusOfASubOrdinate(employee: Employee, id: Int): String {
        return if(checkIfSubordinate(employee, id))
            checkStatus(id)
        else
            "You are not authorised to do that !"
    }

    override fun checkAttendanceOfASubOrdinateOnDate(employee: Employee, id: Int, date: LocalDate): String {
        return if(checkIfSubordinate(employee, id))
            checkAttendanceOnDate(id, date)
        else
             "You are not authorised to do that !"
    }

    override fun approveLeaveRequest(employee: Employee, indexOfRequest: Int, approval: Boolean): Boolean {
        val list = employee.subordinatesLeaveRequests.toList()
        return list.getOrNull(indexOfRequest-1)?.let {
            it.approval =  if(approval) Status.APPROVED else Status.REJECTED
            if(approval) {
                val daysBetween = ChronoUnit.DAYS.between(it.fromDate, it.toDate)
                for (index in 0 .. daysBetween.toInt()) {
                    updateStatusOnADate(it.emp_id, arrayListOf(Status.LEAVE), it.fromDate.plusDays(index.toLong()))
                }
            }
            true
        } ?: false
    }

    override fun viewLeaveRequestsOfSubordinates(employee: Employee){
        employee.subordinatesLeaveRequests.filter { it -> it.approval.equals(Status.UNSEEN) }.forEachIndexed { index, leaveRequest -> println("${index+1} -> $leaveRequest") }
    }

    override fun viewSubOrdinates(employee: Employee) {
        employee.subordinatesIds.forEach { it -> println("ID: $it Name: ${employeeDB.employees.get(it)?.name}") }
    }

    override fun getLeaveRequests(employee: Employee): List<LeaveRequest> {
        return employee.subordinatesLeaveRequests.toList()
    }

    override fun checkStatusOfLeaveRequests(employee: Employee): String{
        val stringBuilder = StringBuilder("")
        val superior = employeeDB.employees.get(employee.superiorId)
        superior?.subordinatesLeaveRequests?.filter { it -> it.emp_id==employee.id }?.takeLast(5)?.forEach { it -> stringBuilder.append("$it\n") }
        return stringBuilder.toString()
    }



}