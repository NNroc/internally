package com.cumt.internally.interceptor;

import com.cumt.internally.annotation.AdministratorToken;
import com.cumt.internally.annotation.PassToken;
import com.cumt.internally.annotation.UserToken;
import com.cumt.internally.model.Result;
import com.cumt.internally.model.Staff;
import com.cumt.internally.service.StaffService;
import com.cumt.internally.utils.JwtUtil;
import com.cumt.internally.utils.SendMsgUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * token 验证
 *
 * @author NNroc
 * @date 2020/5/14 17:31
 */
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
    private static final String encry = "salt";
    @Autowired
    StaffService staffService;
    @Autowired
    SendMsgUtil sendMsgUtil;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        log.info("______开始处理______");
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        log.info(token);
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        // 检查是否有PassToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        // 检查有没有需要普通员工权限的注解
        if (method.isAnnotationPresent(UserToken.class)) {
            UserToken userLoginToken = method.getAnnotation(UserToken.class);
            if (userLoginToken.required()) {
                //  --------------------------------------------------------------------------------------------
                // 执行认证
                if (token == null) {
                    log.info("没有token进行操作");
                    Result<String> result = new Result<>();
                    result.setCode(404);
                    result.setMsg("未找到token");
                    result.setData("get token null");
                    sendMsgUtil.sendJsonMessage(httpServletResponse, result);
                    return false;
                }
                // 获取 token 中的 staffId 并核对
                Staff staff = staffService.getStaffFromToken(token);
                if (StringUtils.isBlank(staff.getStaffId())) {
                    log.info("未找到该员工");
                    Result<String> result = new Result<>();
                    result.setCode(404);
                    result.setMsg("未找到该员工");
                    result.setData("get staff null");
                    sendMsgUtil.sendJsonMessage(httpServletResponse, result);
                    return false;
                }
                //  --------------------------------------------------------------------------------------------
                return true;
            }
        }
        // 检查有没有需要管理员权限的注解
        if (method.isAnnotationPresent(AdministratorToken.class)) {
            AdministratorToken administratorToken = method.getAnnotation(AdministratorToken.class);
            if (administratorToken.required()) {
                // --------------------------------------------------------------------------------------------
                // 执行认证
                if (token == null) {
                    log.info("没有token进行操作");
                    Result<String> result = new Result<>();
                    result.setCode(404);
                    result.setMsg("未找到token");
                    result.setData("get token null");
                    sendMsgUtil.sendJsonMessage(httpServletResponse, result);
                    return false;
                }
                // 获取 token 中的 staffId 并核对
                Staff staff = staffService.getStaffFromToken(token);
                if (staff.getStaffId()==null) {
                    log.info("未找到该员工");
                    Result<String> result = new Result<>();
                    result.setCode(404);
                    result.setMsg("未找到该员工");
                    result.setData("get staff null");
                    sendMsgUtil.sendJsonMessage(httpServletResponse, result);
                    return false;
                }
                //  --------------------------------------------------------------------------------------------
                // 获取 token 中的 权重
                double weight = staff.getStaffWeight();
                if (weight != 4.0) {
                    log.info("权限不足");
                    Result<String> result = new Result<>();
                    result.setCode(404);
                    result.setMsg("权限不足");
                    result.setData("stop");
                    sendMsgUtil.sendJsonMessage(httpServletResponse, result);
                    return false;
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
