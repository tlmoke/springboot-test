package com.tl.springboottest.base.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {
        // // If the exception is annotated with @ResponseStatus rethrow it and let
        // // the framework handle it - like the OrderNotFoundException example
        // // at the start of this post.
        // // AnnotationUtils is a Spring Framework utility class.
        // if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) !=
        // null)
        // throw e;
        //
        // // Otherwise setup and send the user to a default error-view.
        // ModelAndView mav = new ModelAndView();
        // mav.addObject("exception", e);

        // mav.addObject("url", req.getRequestURL());
        // mav.setViewName(DEFAULT_ERROR_VIEW);
        // return mav;
        
        // 打印异常信息：
        e.printStackTrace();
        System.out.println("GlobalDefaultExceptionHandler.defaultErrorHandler()");

        /*
         * 返回 json 数据或者 String 数据：
         * 那么需要在方法上加上注解：@ResponseBody
         * 添加 return 即可。
         */

        /*
         * 返回视图：
         * 定义一个 ModelAndView 即可，
         * 然后 return;
         * 定义视图文件(比如：error.html,error.ftl,error.jsp);
         *
         */
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
}
