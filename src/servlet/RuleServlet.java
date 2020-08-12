package servlet;

import database.*;
import service.rule.RuleFundService;
import service.rule.RuleMedicareService;
import service.rule.RuleSocialService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(urlPatterns = "/rule")
public class RuleServlet extends HttpServlet {
    private RuleSocialService socialService = new RuleSocialService();
    private RuleMedicareService medicalService = new RuleMedicareService();
    private RuleFundService ruleFundService = new RuleFundService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String result = "";
        Connection conn = ConnUtil.getConnection();

        String op = request.getParameter("op");

        switch (op) {
            case "insert"://插入公积金清单
                result = insert(conn,request);
                break;
            case "delete"://删除公积金清单
                result = delete(conn,request);
                break;
            case "update"://修改公积金清单
                result = update(conn,request);
                break;
            case "getList"://获取所有公积金清单
                result = getList(conn,request);
                break;
            case "get"://获取一个公积金清单
                result = get(conn,request);
                break;



        }
        ConnUtil.closeConnection(conn);
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }

    private String insert(Connection conn, HttpServletRequest request) {
      return null;
    }

    private String delete(Connection conn, HttpServletRequest request) {
        return null;
    }

    private String update(Connection conn, HttpServletRequest request) {
      return null;
    }

    private String getList(Connection conn, HttpServletRequest request) {

        return null;
    }

    private String get(Connection conn, HttpServletRequest request) {
      return null;
    }





}
