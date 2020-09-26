package com.thoughtworks.capability.gtb.basicquiz.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    String timestamp;
    Integer status;
    String error;
    String message;
}
