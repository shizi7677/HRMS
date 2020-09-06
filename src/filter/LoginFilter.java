package filter;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "LoginFilter",urlPatterns = { "/verify/*" })
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        req.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String uri = request.getRequestURI();
        String op = req.getParameter("op");
        if(uri.contains("/account")){
            if("login".equals(op)||"login_mp".equals(op)||"register".equals(op)||"registerCandidate".equals(op)||"registerHR".equals(op)) {
                // 注册和登录，放行
                chain.doFilter(request, response);
                return;
            }
        }else if(uri.contains("/ad")){
            if("getNow".equals(op) || "getCarousels".equals(op)) {
                //获取广告，放行
                chain.doFilter(request, response);
                return;
            }
        }else if(uri.contains("/enterprise")){
            if("getRecommends".equals(op) || "getEnterprise".equals(op) || "getHr".equals(op)) {
                //获取推荐企业，放行
                chain.doFilter(request, response);
                return;
            }
        }else if(uri.contains("/job")){
            if("getJob".equals(op)) {
                //获取职位详情，放行
                chain.doFilter(request, response);
                return;
            }
        }else if(uri.contains("/roof")){
            if("getNow".equals(op)) {
                //获取置顶职位，放行
                chain.doFilter(request, response);
                return;
            }
        }

        Byte role = (Byte) request.getSession().getAttribute("role");
        if (role == null) {
            PrintWriter out = response.getWriter();
            JSONObject json = new JSONObject();
            json.put("success",false);
            json.put("code",0);
            json.put("msg","请先登录");
            out.print(json.toJSONString());
        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
