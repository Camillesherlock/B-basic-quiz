package com.thoughtworks.capability.gtb.basicquiz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    private long userId;
    @Min(1900)
    @Max(2100)
    @NotNull
    private Integer year;


    @Size(min = 1, max = 256)
    @NotNull
    private String title;


    @Size(min = 1, max = 4096)
    @NotNull
    private String description;
}
