package com.port.transitPort.util.requests;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseRequest {

    @NotNull(message = "Capacity is required")
    @Positive(message = "Capacity must be greater than zero")
    private Integer capacity;

    @NotNull(message = "Occupancy is required")
    @PositiveOrZero(message = "Occupancy must be zero or greater")
    @Max(value = Integer.MAX_VALUE, message = "Occupancy cannot exceed capacity")
    private Integer occupancy;

    @NotNull(message = "Terminal ID is required")
    private Long terminalId;
}
