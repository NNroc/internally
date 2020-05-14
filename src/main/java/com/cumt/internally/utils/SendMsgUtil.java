package com.cumt.internally.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author NNroc
 * @date 2020/5/14 18:30
 */
@Component
public class SendMsgUtil {
    /**
     * 发送消息 text/html;charset=utf-8
     *
     * @param response
     * @param str
     * @throws Exception
     */
    public void sendMessage(HttpServletResponse response, String str) throws Exception {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(str);
        writer.close();
        response.flushBuffer();
    }

    /**
     * 将某个对象转换成json格式并发送到客户端
     *
     * @param response
     * @param obj
     * @throws Exception
     */
    public void sendJsonMessage(HttpServletResponse response, Object obj) throws Exception {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        String res = mapper.writeValueAsString(obj);
        writer.print(res);
        writer.close();
        response.flushBuffer();
    }
}