package com.thoughtworks.capability.gtb.basicquiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EducationDTO {
    @NotNull
    @Min(1900)
    @Max(2020)
    private Long year;
    @NotEmpty
    @Size(min = 1, max = 256)
    private String title;
    @NotEmpty
    @Size(min = 1, max = 4096)
    private String description;
}
