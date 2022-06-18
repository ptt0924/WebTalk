package com.application.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.application.entity.UserMessage;
import com.application.service.UserMessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author chenLiang
 * @Date 2022/6/18 14:41
 */
@WebServlet("/message")
public class MessageServlet extends HttpServlet {
    UserMessageService userMessageService = new UserMessageService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String fromAccount = req.getParameter("fromAccount");
        String toAccount = req.getParameter("toAccount");
        Integer count = Integer.parseInt(req.getParameter("count"));
        List<UserMessage> list = userMessageService.get20Message(Integer.parseInt(fromAccount),Integer.parseInt(toAccount),count);
        resp.getWriter().write(JSON.toJSONString(list));
    }
}
