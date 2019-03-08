package com.amoyiki.springtest.exception;

import com.amoyiki.springtest.enums.BindStatusEnum;
import com.amoyiki.springtest.utils.ResultVOUtil;
import com.amoyiki.springtest.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/** Controller 层异常处理
 * @author amoyiki
 * @since 2019/3/8
 */

@ControllerAdvice
@Slf4j
public class ControllerException {
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public <T> ResultVO<?> handleBindException(BindException ex) {
        log.info("├ [参数异常处理]: handleBindException");
        BindException exception = (BindException) ex;
        Map<String, String > issus = new HashMap<>();
        exception.getBindingResult().getFieldErrors().stream().forEach(f -> {
            issus.put(f.getField(), f.getDefaultMessage());
        });
        log.info(issus.toString());
        return ResultVOUtil.error(BindStatusEnum.PARAM_ERROR.getCode(), BindStatusEnum.PARAM_ERROR.getMsg(), issus);
    }
}
