package org.developerport.workflowcrmbackend.dto.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String messages;
    private int status;
    private long timeStamp;
}
