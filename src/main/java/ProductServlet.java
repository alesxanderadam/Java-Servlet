import Models.Products;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Định nghĩa đường dẫn
@WebServlet(name = "ProductServlet", urlPatterns = {"/products"})
public class ProductServlet extends HttpServlet {
    List<Products> productList = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("product.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("productName");
        String Quatity = req.getParameter("Quatity");
        String Price = req.getParameter("Price");

        // Thêm sản phẩm mới vào danh sách sản phẩm
        productList.add(new Products(productName, Integer.parseInt(Quatity) , Double.parseDouble(Price)));

        // Set attribute cho request
        req.setAttribute("productList", productList);

        // Forward request tới trang JSP hiển thị danh sách sản phẩm
        req.getRequestDispatcher("product.jsp").forward(req,resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy giá trị của tham số "item"
        String item = req.getParameter("item");
        System.out.println("Item" + item);

    }
}
