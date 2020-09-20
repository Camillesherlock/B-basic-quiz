package com.thoughtworks.capability.gtb.basicquiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Education {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonIgnore
    private long userId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="user_id")
    private User user;

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
