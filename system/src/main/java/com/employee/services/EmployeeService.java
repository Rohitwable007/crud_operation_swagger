package com.employee.services;

import com.employee.models.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.reqeuestDTO.EmployeeRequestDTO;
import com.employee.responseDTO.EmployeeResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO employeeRequestDTO){
         EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
         Employee employee = new Employee();
         employee = modelMapper.map(employeeRequestDTO,Employee.class);
         employee = employeeRepository.save(employee);
         responseDTO = modelMapper.map(employee,EmployeeResponseDTO.class);
         return responseDTO;
    }

    public EmployeeResponseDTO updateEmployee(long id, EmployeeRequestDTO employeeRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
        if (Objects.nonNull(id)) {
            Employee employee = employeeRepository.findById(id);
            if (Objects.nonNull(employee)) {
                employeeRepository.delete(employee);
                employee.setEmployeeId(id);
                employee.setEmployeeName(employeeRequestDTO.getEmployeeName());
                employee.setEmployeeDesignation(employeeRequestDTO.getEmployeeDesignation());
                employee.setEmployeeAddress(employeeRequestDTO.getEmployeeAddress());
                employee = employeeRepository.save(employee);
                employeeResponseDTO = modelMapper.map(employee, EmployeeResponseDTO.class);
            }
        }
        return employeeResponseDTO;
    }

    public List<EmployeeResponseDTO> getAllEmployee(){
        List<EmployeeResponseDTO> responseDTOS = new ArrayList<>();
        List<Employee> employeeList = employeeRepository.findAll();
        if(Objects.nonNull(employeeList)){

            for(Employee employee : employeeList){
                EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
                employeeResponseDTO = modelMapper.map(employee,EmployeeResponseDTO.class);
                responseDTOS.add(employeeResponseDTO);
            }
        }
        return responseDTOS;
    }

    public String  deleteEmployee(long empId){
        String msg="";
        if(Objects.nonNull(empId)){
            Employee employee = employeeRepository.findById(empId);
            if(Objects.nonNull(employee)){
                employeeRepository.delete(employee);
                msg = "Employee Deleted Successfully";
            }else {
                msg = "Employee Deletion Failed ID=" + empId;
            }
        }else {
            msg = "Employee Id Not Found";
        }

        return msg;
    }
}
