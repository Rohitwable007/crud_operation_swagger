package com.employee.controller;

import com.employee.reqeuestDTO.EmployeeRequestDTO;
import com.employee.responseDTO.EmployeeResponseDTO;
import com.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.POST, path = "add/addemployee")
    public EmployeeResponseDTO addNewEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.addEmployee(employeeRequestDTO);
        return employeeResponseDTO;
    }

    @RequestMapping(method = RequestMethod.POST, path = "add/updateemployee/{empId}")
    public EmployeeResponseDTO updateEmployee(@RequestParam(name = "empId") long empId, @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.updateEmployee(empId,employeeRequestDTO);
        return employeeResponseDTO;
    }

    @RequestMapping(method = RequestMethod.GET, path = "add/getemployee/")
    public List<EmployeeResponseDTO> getEmployee() {
        List<EmployeeResponseDTO> employeeResponseDTO = employeeService.getAllEmployee();
        return employeeResponseDTO;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "add/deleteemployee/(empId}")
    public String deleteEmployee(long empId) {
       String employeeResponseDTO =  employeeService.deleteEmployee(empId);
       return employeeResponseDTO;
    }
}
