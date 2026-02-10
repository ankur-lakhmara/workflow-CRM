package org.developerport.workflowcrmbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/{a}/{b}")
    public int hello(@PathVariable int a, @PathVariable int  b){
        return (a+b);
    }
}
