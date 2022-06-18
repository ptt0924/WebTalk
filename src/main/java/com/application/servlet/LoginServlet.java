package com.application.servlet;





import com.alibaba.fastjson.JSONObject;
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
 * @Date 2022/5/30 20:13
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        Integer account = Integer.parseInt(req.getParameter("account"));
        String password = req.getParameter("password");
        System.out.println("account:" + account+"password:"+password);
        boolean existsAccount = userService.isExistsAccount(account);
        boolean existsUser = userService.isExistsUser(account, password);
        JSONObject jsonObject = new JSONObject();
        Result result = new Result();
        if(!existsAccount){
            result.setCode("0");
            result.setData("没有账号");
        }else if(!existsUser) {
            result.setCode("1");
            result.setData("密码错误");
        }else {
            result.setCode("2");
            result.setData("登录成功");
        }
        resp.getWriter().write(JSONObject.toJSONString(result));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
