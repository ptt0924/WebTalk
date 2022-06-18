package com.application.servlet;

import com.alibaba.fastjson.JSONObject;
import com.application.service.AddFriendService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author chenLiang
 * @Date 2022/6/17 2:05
 */
@WebServlet("/isRequestAddFriend")
public class IsRequestedAddServlet extends HttpServlet {
    AddFriendService addFriendService = new AddFriendService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        Integer fromAccount = Integer.parseInt(req.getParameter("fromAccount"));
        Integer toAccount = Integer.parseInt(req.getParameter("toAccount"));
        boolean isRequestAdd = addFriendService.isAddRequest(fromAccount,toAccount);
        JSONObject jsonObject = new JSONObject();
        if(isRequestAdd){
            jsonObject.put("code","0");
        }else{
            jsonObject.put("code","1");
        }
        resp.getWriter().write(jsonObject.toJSONString());
//        super.doGet(req, resp);
    }
}
