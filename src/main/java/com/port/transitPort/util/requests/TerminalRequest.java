package com.port.transitPort.util.requests;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TerminalRequest {

    @NotNull(message = "Name is required")
    @Size(max = 20, message = "Name cannot exceed 20 characters")
    private String name;

    @NotNull(message = "Wharves count is required")
    @Min(value = 1, message = "Wharves count must be at least 1")
    private Integer wharvesCount;

    @NotNull(message = "Port ID is required")
    private Long portId;

    private List<Long> wharfIds; // IDs of associated wharves (optional)

    private List<Long> warehouseIds; // IDs of associated warehouses (optional)
}
