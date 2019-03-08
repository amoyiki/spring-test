package com.amoyiki.springtest.utils;

import com.amoyiki.springtest.vo.ResultVO;

/**
 * @description:
 * @author: amoyiki
 * @date: 2019/1/20
 */
public class ResultVOUtil {
    public static ResultVO success(Object o){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode("0");
        resultVO.setMsg("成功");
        resultVO.setData(o);
        return resultVO;
    }

    public static ResultVO erro(String code, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(message);
        return resultVO;
    }
}
