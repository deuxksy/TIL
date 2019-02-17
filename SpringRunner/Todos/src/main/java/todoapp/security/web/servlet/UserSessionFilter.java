package todoapp.security.web.servlet;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import todoapp.security.UserSession;
import todoapp.security.UserSessionRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

/**
 * HttpServletRequest가 로그인 사용자 세션({@link UserSession}을 사용 할 수 있도록 지원하는 필터 구현체
 */
@Component
public class UserSessionFilter extends OncePerRequestFilter {

    private UserSessionRepository userSessionRepository;

    public UserSessionFilter(UserSessionRepository userSessionRepository) {
        this.userSessionRepository = userSessionRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        UserSessionRequestWrapper requestWrapper = new UserSessionRequestWrapper(request, userSessionRepository.get());
        filterChain.doFilter(requestWrapper, response);
    }

    /**
     * 로그인 사용자 세션을 기반으로 인증 개체와 역할 확인 기능을 제공
     */
    final class UserSessionRequestWrapper extends HttpServletRequestWrapper {

        final Optional<UserSession> userSession;

        protected UserSessionRequestWrapper(HttpServletRequest request, UserSession userSession) {
            super(request);
            this.userSession = Optional.ofNullable(userSession);
        }

        @Override
        public boolean isUserInRole(String role) {
            return userSession.map(it -> it.hasRole(role)).orElse(false);
        }

        @Override
        public Principal getUserPrincipal() {
            return userSession.orElse(null);
        }

    }

}
