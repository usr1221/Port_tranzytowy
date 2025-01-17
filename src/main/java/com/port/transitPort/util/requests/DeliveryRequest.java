package com.port.transitPort.util.requests;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRequest {

    @NotNull(message = "Status is required")
    @Size(max = 12, message = "Status cannot exceed 12 characters")
    private String status;

    @NotNull(message = "Arrival date is required")
    private Date arrivalDate;

    private Date completionDate;

    private Date departureDate;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than zero")
    private Integer quantity;

    @NotNull(message = "Ship ID is required")
    private Long shipId;
}