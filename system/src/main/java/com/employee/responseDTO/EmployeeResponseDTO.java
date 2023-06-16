package com.employee.responseDTO;

import lombok.Data;

@Data
public class EmployeeResponseDTO {
    private long employeeId;
    private String employeeName;
    private String employeeSalary;
    private String employeeDesignation;
    private String employeeAddress;
}
