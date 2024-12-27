package com.port.transitPort.controller;

import com.port.transitPort.model.Employee;
import com.port.transitPort.service.EmployeeService;
import com.port.transitPort.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody Employee employee) {
        employeeService.registerEmployee(employee);
        return "Employee registered successfully";
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        Employee employee = employeeService.authenticate(email, password);
        String token = jwtUtil.generateToken(employee.getEmail(), employee.getPosition());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
}
