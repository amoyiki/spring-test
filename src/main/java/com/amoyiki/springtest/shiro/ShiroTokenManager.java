package com.amoyiki.springtest.shiro;
import com.amoyiki.springtest.constant.ShiroConstant;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @author amoyiki
 * @since 2019/3/6
 */
public class ShiroTokenManager extends DefaultWebSessionManager {

    /**
     *
     * 获取token
     * @author amoyiki
     * @param request
     * @param response
     * @return java.io.Serializable
     */
//    @Override
//    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
//        String id = WebUtils.toHttp(request).getHeader(ShiroConstant.AUTHORIZATION);
//        if (!StringUtils.isEmpty(id)) {
//            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroConstant.REFERENCED_SESSION_ID_SOURCE);
//            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
//            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
//            return id;
//        } else {
//            return super.getSessionId(request, response);
//        }
//    }
}
