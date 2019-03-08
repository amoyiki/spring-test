//package com.amoyiki.springtest.exception;
//
//import com.alibaba.fastjson.support.spring.FastJsonJsonView;
//import com.amoyiki.springtest.enums.ShiroStatusEnum;
//import org.apache.shiro.authz.UnauthenticatedException;
//import org.apache.shiro.authz.UnauthorizedException;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author amoyiki
// * @since 2019/3/6
// */
//public class MyExceptionHandler implements HandlerExceptionResolver {
//    @Override
//    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
//                                         Object handler, Exception ex) {
//        ModelAndView modelAndView = new ModelAndView();
//        FastJsonJsonView view = new FastJsonJsonView();
//        Map<String, Object> attributes = new HashMap<>();
//        if (ex instanceof UnauthorizedException) {
//            attributes.put("code", ShiroStatusEnum.TOKEN_ERROR.getCode());
//            attributes.put("msg", ShiroStatusEnum.TOKEN_ERROR.getMsg());
//        }else if (ex instanceof UnauthenticatedException) {
//            attributes.put("code", ShiroStatusEnum.UNAUTHOR_ERROR.getCode());
//            attributes.put("msg", ShiroStatusEnum.UNAUTHOR_ERROR.getMsg());
//        }else{
//            attributes.put("code", ShiroStatusEnum.OTHER_ERROR.getMsg());
//            attributes.put("msg", ex.getMessage());
//        }
//        view.setAttributesMap(attributes);
//        modelAndView.setView(view);
//        return modelAndView;
//    }
//}
