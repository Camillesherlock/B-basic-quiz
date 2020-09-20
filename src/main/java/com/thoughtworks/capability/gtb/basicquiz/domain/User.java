package com.thoughtworks.capability.gtb.basicquiz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1, max = 128)
    @NotNull
    private String name;

    @Min(16)
    @NotNull
    private long age;

    @Size(min = 8, max = 512)
    @NotNull
    private String avatar;

    @Size(max = 1024)
    private String description;
}
