import Models.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int a = 12;
//        int b = 12;
//        int result = (int) a + b;
//        req.setAttribute("Result",result);
        req.getRequestDispatcher("login.jsp").forward(req,resp);
        PrintWriter writer = resp.getWriter();
        writer.println("Hello");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users users = new Users();
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        users.setUsername("ltqhuy.th0112@gmail.com");
        users.setPassword(password);

        //Cách add thẳng arraylist khi tạo , không cần phải list.add !
        List<String> list = Arrays.asList("Quang Huy","Trà My","Lam Tuyền");

        String message = "";
        if(username != null && username.equals(users.getUsername())){
            message = "Hello " + users.getUsername();
        }else {
            message = "Khong tim thay user";
        }
        req.setAttribute("user",users);
        req.setAttribute("mess",message);
        req.setAttribute("list",list);

        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }
}
