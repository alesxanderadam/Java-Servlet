package Controllers;

import Config.MysqlConfig;
import Models.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "loginController", urlPatterns = ("/login"))
public class LoginController extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // Demo lưu cookie
//        // Cookie cookie  = new Cookie("username","ltqhuy.th0112@gmail.com");
//        // resp.addCookie(cookie);
//        //Duyệt cookie
////        Cookie[] cookies = req.getCookies();
////        if(cookies.length > 0){
////            for (Cookie item: cookies) {
////                if(item.getName().equals("username")){
////                    System.out.println("Check cookie: " + item.getValue());
////                }
////            }
////        }
//
//
////        // Session
////        HttpSession session = req.getSession();
////        session.setAttribute("password", "123456");
////
////        System.out.println("Session : " + session.getAttribute("password"));
//
//        Cookie[] cookies = req.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("email")) {
//                    req.setAttribute("email", cookie.getValue());
//                }
//                if (cookie.getName().equals("password")) {
//                    req.setAttribute("password", cookie.getValue());
//                }
//            }
//        }
//
//        req.getRequestDispatcher("login.jsp").forward(req,resp);
//    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Connection connection = null;
//        PrintWriter writer = resp.getWriter();
//
//        //B1: Lấy tham số parameter
//        String email = req.getParameter("username");
//        String password = req.getParameter("password");
//        String rememberMe = req.getParameter("rememberMe");
//        //B2: Viết câu query
//        // ? : Tham số được truyền ở JDBC trong query
//        String sql_query_user = "SELECT * FROM users u WHERE u.email = ? AND u.password = ?";
//        //B3: Đưa câu query vào Statement để chuẩn bị thực thi
//        try{
//            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql_query_user); //Mở chuỗi kết nối
//            // Truyền tham số cho dấu chấm hỏi ( ? ) tính parameterIndex dấu chấm hỏi lần luwtj từ trái qua phải
//            // * Nếu không có tham số thì không cần truyền statement
//            statement.setString(1,email);
//            statement.setString(2,password);
//
//        // B4: Thực thi câu query
//            // +: excuteQuery : ( Get )
//            // + excuteUpdate : (Insert, Update, Delete)
//           ResultSet resultSet = statement.executeQuery();
//           List<UserModel> listUser = new ArrayList<>();
//
//        //B5: Duyệt từng dữ liệu trong resultSet và lưu vào list UserModel
//            while (resultSet.next()){
//                UserModel userModel = new UserModel();
//                //Lấy giá trị của cột chỉ định tham chiếu table trong database
//                userModel.setId (resultSet.getInt("id"));
//                userModel.setEmail(resultSet.getString("email"));
//                userModel.setPassword(resultSet.getString("password"));
//                userModel.setFullname(resultSet.getString("fullname"));
//                userModel.setAvatar(resultSet.getString("avatar"));
//                userModel.setRoleId(resultSet.getInt("role_id"));
//
//                listUser.add(userModel);
//            }
//            boolean isAddSuccess = listUser.size() > 0;
//            if(rememberMe != null && rememberMe.equals("on") && isAddSuccess){
//                Cookie cookieEmail = new Cookie("email", email);
//                Cookie cookiePassword = new Cookie("password", password);
//                cookieEmail.setMaxAge(30 * 24 * 60 * 60);
//                cookiePassword.setMaxAge(30 * 24 * 60 * 60);
//                resp.addCookie(cookieEmail);
//                resp.addCookie(cookiePassword);
//            }
//            writer.printf(isAddSuccess ? "Login success" : "Login faild");
//        }catch (Exception error){
//            System.out.println("Error connecttion database: " + error.getMessage());
//        }finally {
//            if(connection != null){
//                try{
//                    connection.close();
//                }catch (Exception e){
//
//                }
//            }
//
//        }
//    }

    // Use Session login
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession();
        String email = req.getParameter("username");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("rememberMe");
        List<UserModel> listUser = new ArrayList<>();

        String sql_query_user = "SELECT * FROM users u WHERE u.email = ? AND u.password = ?";
        try {
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql_query_user);
            statement.setString(1,email);
            statement.setString(2,password);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                UserModel userModel = new UserModel();

                userModel.setId (resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setPassword(resultSet.getString("password"));
                userModel.setFullname(resultSet.getString("fullname"));
                userModel.setAvatar(resultSet.getString("avatar"));
                userModel.setRoleId(resultSet.getInt("role_id"));

                listUser.add(userModel);
                for (UserModel user: listUser) {
                    Cookie name = new Cookie("name", URLEncoder.encode(user.getFullname(), "UTF-8"));

                    Cookie avatar = new Cookie("avatar", URLEncoder.encode(user.getAvatar(), "UTF-8"));

                    Cookie role = new Cookie("role", URLEncoder.encode(Integer.toString(user.getRoleId()), "UTF-8"));

                    resp.addCookie(name);
                    name.setMaxAge(1 * 24 * 60 * 60);

                    resp.addCookie(avatar);
                    avatar.setMaxAge(1 * 24 * 60 * 60);

                    resp.addCookie(role);
                    role.setMaxAge(1 * 24 * 60 * 60);
                }
            }
            boolean isAddSuccess = listUser.size() > 0;
            if(rememberMe != null && rememberMe.equals("on") && isAddSuccess){
                session.setAttribute("email", email);
                session.setAttribute("password", password);
            }
            req.getRequestDispatcher(isAddSuccess ? "information-user.jsp" : "login.jsp").forward(req,resp);
        }catch (Exception error){
            System.out.println("Error connecttion database: " + error.getMessage());
        }finally {
            if(connection != null){
                try{
                    connection.close();
                }catch (Exception e){
                    System.out.println("Eerror finaly " + e.getMessage());
                }
            }
        }
    }
}
