package com.springbook.view.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("com.springbook.view")
public class CommonExceptionHandler {
      @ExceptionHandler(ArithmeticException.class)
      public ModelAndView handlerArithmeticException(Exception e) {
    	  ModelAndView mav = new ModelAndView();
    	  mav.addObject("exception",e);
    	  mav.setViewName("/commons/arithmeticError.jsp");
    	  return mav;
      }
      
      @ExceptionHandler(NullPointerException.class)
      public ModelAndView handleNullPointException(Exception e) {
    	  ModelAndView mav = new ModelAndView();
    	  mav.addObject("exception",e);
    	  mav.setViewName("/commons/nullPointError.jsp");
    	  return mav;
      }
      
      @ExceptionHandler(Exception.class)
      public ModelAndView handleException(Exception e) {
    	  ModelAndView mav = new ModelAndView();
    	  mav.addObject("exception",e);
    	  mav.setViewName("/commons/errors.jsp");
    	  return mav;
      }
}
