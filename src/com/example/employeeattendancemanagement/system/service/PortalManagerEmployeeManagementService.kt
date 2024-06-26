package com.example.employeeattendancemanagement.system.service

import com.example.employeeattendancemanagement.db.EmployeeDB
import com.example.employeeattendancemanagement.system.action.EmployeeManagementActions
import com.example.employeeattendancemanagement.system.assistance.EmployeeAssistance
import com.example.employeeattendancemanagement.system.entity.core.Employee
import com.example.employeeattendancemanagement.system.entity.core.PortalManager

class PortalManagerEmployeeManagementService(
    val employeeDB: EmployeeDB
)
    : EmployeeManagementActions
{
    private var emp_id_index = 0
    private fun generateId(): Int {
        return ++emp_id_index
    }
    override fun addEmployee(name: String, superiorId: Int, assistance: EmployeeAssistance): Boolean{
        return if(employeeDB.employees.contains(superiorId) || superiorId==0) {
            employeeDB.employees.put(generateId(), Employee(name, emp_id_index, superiorId, PortalManager))
            employeeDB.employees.get(superiorId)?.subordinatesIds?.add(emp_id_index)
            true
        }else
            false

    }
    override fun addASubOrdinateForAnEmployee(id: Int, subordinateId: Int): Boolean {
        return if(employeeDB.employees.contains(id) && employeeDB.employees.contains(subordinateId)){
            employeeDB.employees.get( employeeDB.employees.get(subordinateId)!!.superiorId )?.subordinatesIds?.remove(subordinateId)
            employeeDB.employees[id]!!.subordinatesIds.add(subordinateId)
            employeeDB.employees.get(subordinateId)!!.superiorId = id
            true
        }else
            false
    }
    override fun setSuperiorForAnEmployee(id: Int, superiorId: Int): Boolean {
        return if(employeeDB.employees.contains(superiorId) && employeeDB.employees.contains(id)) {
            employeeDB.employees.get( employeeDB.employees.get(id)!!.superiorId )?.subordinatesIds?.remove(id)
            employeeDB.employees[id]?.superiorId = superiorId
            employeeDB.employees.get(superiorId)!!.subordinatesIds.add(id)
            true
        }else
            false
    }

    override fun viewEmployees() {
        employeeDB.employees.forEach { (id, employee) ->
            val superiorName = employeeDB.employees[employee.superiorId]?.name ?: "None"
            val subordinatesDetails = employee.subordinatesIds.mapNotNull { subordinateId ->
                val subordinate = employeeDB.employees[subordinateId]
                subordinate?.let { "${it.name} (ID: $subordinateId)" }
            }.joinToString(", ")

            println("Id: $id Name: ${employee.name} Superior: $superiorName Subordinates: $subordinatesDetails")
        }
    }



}