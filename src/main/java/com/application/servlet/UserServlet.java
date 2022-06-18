package com.application.servlet;

import com.alibaba.fastjson.JSONObject;
import com.application.entity.User;
import com.application.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @Author chenLiang
 * @Date 2022/5/30 20:06
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
    UserService userService = new UserService();

    /**
     * 查看用户信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        Integer account = Integer.parseInt(req.getParameter("account"));
        User user = userService.searchUser(account);
        resp.getWriter().write(JSONObject.toJSONString(user));
    }

    /**
     * 注册用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        Integer account = Integer.parseInt(req.getParameter("account"));
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        boolean isExitAccount = userService.isExistsAccount(account);
        if(isExitAccount){
            resp.getWriter().write("账户已经注册");
        }else{
            User user = new User();
            user.setAccount(account);
            user.setPassword(password);
            user.setName(name);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            userService.insertUser(user);
            resp.getWriter().write("注册成功");
        }

    }


    /**
     * 修改用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String userInfo =  req.getParameter("user");
        System.out.println(userInfo);
        User user = JSONObject.parseObject(userInfo,User.class);
        User user1 = userService.selectByPrimaryKey(user);
        user.setCreateTime(user1.getCreateTime());
        user.setUpdateTime(new Date());
        int count = userService.updateUser(user);
        JSONObject jsonObject = new JSONObject();
        if(count>0){
            jsonObject.put("code","0");
        }else {
            jsonObject.put("code","1");
        }
        resp.getWriter().write(jsonObject.toJSONString());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
