package com.port.transitPort.controller;

import com.port.transitPort.model.Employee;
import com.port.transitPort.model.Port;
import com.port.transitPort.service.EmployeeService;
import com.port.transitPort.service.PortService;
import com.port.transitPort.util.JWTUtil;
import com.port.transitPort.util.requests.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private PortService portService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/role")
    public ResponseEntity<String> getRoleFromToken(@RequestHeader("Authorization") String token) {
        String role = jwtUtil.extractPosition(token.substring(7)); // Usunięcie "Bearer "
        return ResponseEntity.ok(role);
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest registerRequest) {
        Port port = portService.getPortById(registerRequest.getPort());
        if (port == null) {
            throw new RuntimeException("Port not found with ID: " + registerRequest.getPort());
        }
        Employee employee = Employee.builder()
                .email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .pesel(registerRequest.getPesel())
                .nationality(registerRequest.getNationality())
                .phoneNumber(registerRequest.getPhoneNumber())
                .position(registerRequest.getPosition())
                .port(port)
                .build();

        employeeService.registerEmployee(employee, registerRequest.getPassword());
        return "Employee registered successfully";
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        Employee employee = employeeService.authenticate(email, password);
        String token = jwtUtil.generateToken(employee.getEmail(), employee.getRole());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
}
