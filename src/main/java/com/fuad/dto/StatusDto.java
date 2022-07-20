package com.fuad.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class StatusDto {
    String title;

    @NotBlank(message = "Enter your status")
    String statusText;

//    @NotBlank(message = "Enter your location")
    Long locationId;

//    @NotBlank(message = "Select privacy")
    String privacy;

    String attachmentPath;
}
