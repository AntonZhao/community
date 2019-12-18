package com.example.community.advice;

import com.example.community.Exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable ex, Model model) {
//        HttpStatus status = getStatus(request);

        if (ex instanceof CustomizeException) {
            model.addAttribute("message", ex.getMessage());
        }else {
            model.addAttribute("message", "阿连和老赵不在家哦");

        }
        return new ModelAndView("error");
    }
}
