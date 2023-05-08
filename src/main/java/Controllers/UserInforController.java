package Controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet(urlPatterns = ("/information-user"))
public class UserInforController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if(cookies.length > 0){
            for (Cookie item: cookies) {
                if(item.getName().equals("name")){
                    req.setAttribute("name", URLDecoder.decode(item.getValue(), "UTF-8"));
                }
                if(item.getName().equals("avatar")){
                    req.setAttribute("avatar", URLDecoder.decode(item.getValue(), "UTF-8"));
                }
                if(item.getName().equals("role")){
                    int role = Integer.parseInt(item.getValue());
                    String nameRole = null;
                    switch (role){
                        case 1:
                            nameRole = "Quản trị hệ thống";
                            break;
                        case 2:
                            nameRole = "Quản lý";
                            break;
                        case 3:
                            nameRole = "Nhân Viên";
                            break;
                        default:
                            break;
                    }
                    req.setAttribute("role", URLDecoder.decode(nameRole, "UTF-8"));
                }
            }
            req.getRequestDispatcher("information-user.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }
        resp.sendRedirect("login.jsp");

    }
}
