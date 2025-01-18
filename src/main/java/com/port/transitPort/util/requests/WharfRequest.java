package com.port.transitPort.util.requests;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WharfRequest {

    @NotNull(message = "Length is required")
    @Positive(message = "Length must be a positive number")
    private Integer length;

    @NotNull(message = "Depth is required")
    @Positive(message = "Depth must be a positive number")
    private Integer depth;

    @NotNull(message = "Terminal ID is required")
    private Long terminalId;
}
