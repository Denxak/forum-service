package ait.cohort46.security.filter;

import ait.cohort46.accounting.dao.UserAccountRepository;
import ait.cohort46.accounting.model.Role;
import ait.cohort46.accounting.model.UserAccount;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Order(15)
public class OwnerOrAdminFilter implements Filter {
    private final UserAccountRepository repository;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if (checkEndpoint(request.getMethod(), request.getServletPath())) {
            String login = extractLoginFromPath(request.getServletPath());
            UserAccount user = repository.findById(request.getUserPrincipal().getName()).get();
            if (HttpMethod.DELETE.matches(request.getMethod())) {
                if (!user.getLogin().equals(login) && !user.getRoles().contains(Role.ADMINISTRATOR)) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
            } else if (HttpMethod.PATCH.matches(request.getMethod())) {
                if (!user.getLogin().equals(login)) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

    private boolean checkEndpoint(String method, String path) {
        return (HttpMethod.PATCH.matches(method) || HttpMethod.DELETE.matches(method)) &&
                path.matches("/account/user/\\w+");
    }

    private String extractLoginFromPath(String path) {
        return path.split("/")[3];
    }
}

