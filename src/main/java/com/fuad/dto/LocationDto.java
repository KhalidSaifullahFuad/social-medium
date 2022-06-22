package com.fuad.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LocationDto {

    @NotBlank(message = "Enter location name")
    private String locationName;
}
