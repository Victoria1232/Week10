package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author mfgperez
 */
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        // code that is executed before the servlet
        // to make sure  user is authenticated 
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
      
        User user = (User) session.getAttribute("user");

        if (user.getRole().getRoleId() == 1) {

            chain.doFilter(request, response); // execute the servlet
             HttpServletResponse httpResponse = (HttpServletResponse) response;
             httpResponse.sendRedirect("admin");
           
               return;
            // code that is executed after the servlet
        } else {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("login");
            return;
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
