package Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(urlPatterns = {"/login"})
public class CustomFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;
        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("email") != null && session.getAttribute("password") != null);
        String informationPage = httpRequest.getContextPath() + "/information-user";

        if(isLoggedIn){
            httpResponse.sendRedirect(informationPage);
        }else{
            filterChain.doFilter(req, resp);
            System.out.println("Filter login active");
        }
    }

}
