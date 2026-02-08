//package org.developerport.workflowcrmbackend.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
////package org.developerport.workflowcrmbackend.dto.exception;
//
//import org.developerport.workflowcrmbackend.dto.exception.ErrorResponse;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ErrorResponse> handleRunTimeException(RuntimeException ex){
//        ErrorResponse error = new ErrorResponse(
//                ex.getMessage(),
//                HttpStatus.BAD_REQUEST.value(),
//                System.currentTimeMillis()
//        );
//        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
//    }
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
//
//        ErrorResponse error = new ErrorResponse(
//                "Something went wrong",
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                System.currentTimeMillis()
//        );
//
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
