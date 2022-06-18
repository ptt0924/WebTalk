package com.application.servlet;

import com.alibaba.fastjson.JSON;
import com.application.entity.User;
import com.application.service.FriendsService;
import com.application.service.UserService;
import com.application.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author chenLiang
 * @Date 2022/6/15 10:35
 */
@WebServlet("/searchFriend")
public class SearchFriendServlet extends HttpServlet {
    UserService userService = new UserService();
    FriendsService friendsService = new FriendsService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        Integer toAccount = Integer.parseInt(req.getParameter("toAccount"));
        User user = userService.searchUser(toAccount);
        Result result = new Result();
        if(user==null){
           result.setCode("0");
           result.setData("没有该用户");
        }else{
            result.setCode("1");
            result.setData(JSON.toJSONString(user));
        }
        resp.getWriter().write(JSON.toJSONString(JSON.toJSONString(result)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
