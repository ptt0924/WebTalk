package com.application.servlet;

import com.alibaba.fastjson.JSON;
import com.application.dto.AddFriendDto;
import com.application.service.AddFriendService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author chenLiang
 * @Date 2022/6/15 16:24
 */
@WebServlet("/getAddFriendMessage")
public class RequestAddServlet extends HttpServlet {

    AddFriendService addFriendService = new AddFriendService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        Integer account  = Integer.parseInt(req.getParameter("account"));
        List<AddFriendDto> addFriends = addFriendService.getAddFriendMessage(account);
        resp.getWriter().write(JSON.toJSONString(addFriends));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
