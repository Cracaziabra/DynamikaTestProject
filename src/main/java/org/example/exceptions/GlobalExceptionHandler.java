package org.example.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            InvalidDataException.class
    })
    public ModelAndView invalidDataExceptions(RuntimeException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errorPage");
        modelAndView.addObject("errorMessage", e.getMessage());
        return modelAndView;
    }

}
