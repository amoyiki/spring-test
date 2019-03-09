package com.amoyiki.springtest.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.amoyiki.springtest.enums.BindStatusEnum;
import com.amoyiki.springtest.enums.ShiroStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author amoyiki
 * @since 2019/3/6
 */
@Slf4j
public class MyExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {
        ex.printStackTrace();
        log.info("├ [全局异常处理]: resolveException");
        ModelAndView modelAndView = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        Map attributes;
        if (ex instanceof MyException) {
            attributes = resolveMyException(request, response, handler, ex);
        }else if (ex instanceof BindException) {
            attributes = resolveBindException(request, response, handler, ex);

        }else{
            attributes = new HashMap<String, Object>();
            attributes.put("code", ShiroStatusEnum.OTHER_ERROR.getCode());
            attributes.put("msg", ex.getMessage());
        }
        view.setAttributesMap(attributes);
        modelAndView.setView(view);
        return modelAndView;
    }

    /**
     * 自定义异常处理
     *
     * @author amoyiki
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return java.util.Map
     */
    private Map resolveMyException(HttpServletRequest request, HttpServletResponse response,
                                    Object handler, Exception ex){
        log.info("├ [自定义异常处理]: resolveMyException");
        MyException exception = (MyException)ex;
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("code", exception.getCode());
        attributes.put("msg", exception.getMessage());
        return attributes;
    }

    /**
     * 参数绑定异常处理
     *
     * @author amoyiki
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return java.util.Map
     */
    private Map resolveBindException(HttpServletRequest request, HttpServletResponse response,
                                      Object handler, Exception ex) {
        log.info("├ [参数异常处理]: resolveMyException");
        BindException exception = (BindException) ex;
        Map<String, String > issus = new HashMap<>();
        exception.getBindingResult().getFieldErrors().stream().map(f -> {
            issus.put(f.getField(), f.getDefaultMessage());
            return null;
        });
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("code", 422);
        attributes.put("msg", BindStatusEnum.PARAM_ERROR.getMsg());
        attributes.put("issus", issus);
        return attributes;
    }
}
