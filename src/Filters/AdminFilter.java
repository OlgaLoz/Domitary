package Filters;

import Model.Role;
import Utils.Pages;
import Utils.RoleControl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter", urlPatterns = "/pages/admin/*")
public class AdminFilter implements Filter {

    private RoleControl roleControl = new RoleControl();
    private String CURRENT_ROLE_ATTRIBUTE = "currentRole";

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute(CURRENT_ROLE_ATTRIBUTE) == null){
            response.sendRedirect(Pages.HOME_GUEST.getPagePath());
            return;
        }

        Role currentRole = (Role) session.getAttribute(CURRENT_ROLE_ATTRIBUTE);

        if (!Role.Admin.equals(currentRole)) {
            response.sendRedirect(roleControl.getPagePathByRole(currentRole));
        }
        else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {

    }

}
