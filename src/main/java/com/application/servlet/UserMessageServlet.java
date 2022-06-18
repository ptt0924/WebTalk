package com.application.servlet;

import com.alibaba.fastjson.JSONObject;
import com.application.dto.MessageList;
import com.application.service.UserMessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @Author chenLiang
 * @Date 2022/5/30 20:13
 */
@WebServlet("/userMessage")
public class UserMessageServlet extends HttpServlet {

    private UserMessageService  userMessageService = new UserMessageService();


    /**
     * 登录获取未读和已读的消息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String account = req.getParameter("account");
        System.out.println("UserMsg => Get: " + account);
        //todo
        HashMap<String,MessageList> list = userMessageService.getMessage(account,0);
        resp.getWriter().write(JSONObject.toJSONString(list));
        resp.getWriter().flush();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
