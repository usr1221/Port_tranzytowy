package com.port.transitPort.util.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShipRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Home Port is required")
    private String homePort;

    @NotNull(message = "Draft is required")
    private Integer draft;

    @NotNull(message = "Length is required")
    private Integer length;

    @NotBlank(message = "Call Sign is required")
    private String callSign;

    @NotBlank(message = "Type is required")
    private String type;

    private Long wharf; // Optional wharf ID
}

