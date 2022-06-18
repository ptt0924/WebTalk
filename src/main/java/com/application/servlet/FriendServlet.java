package com.application.servlet;



import com.alibaba.fastjson.JSONObject;
import com.application.entity.Friends;
import com.application.service.FriendsService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author chenLiang
 * @Date 2022/5/30 20:08
 */
@WebServlet("/friends")
public class FriendServlet extends HttpServlet {

    FriendsService friendsService = new FriendsService();

    /**
     * 得到好友列表
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        int account = Integer.parseInt(req.getParameter("account"));
        List<Friends> friends = friendsService.getAllFriends(account);
        String friendsJson = JSONObject.toJSONString(friends);
        resp.getWriter().write(friendsJson);
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    /**
     * 修改好友信息
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
        Integer fromAccount = Integer.parseInt(req.getParameter("fromAccount"));
        Integer toAccount = Integer.parseInt(req.getParameter("toAccount"));
        String remark = req.getParameter("remark");
        int update = friendsService.updateFriend(fromAccount,toAccount,remark);
        if(update>0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code","0");
            resp.getWriter().write(jsonObject.toJSONString());
        }else{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code","1");
            resp.getWriter().write(jsonObject.toJSONString());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
