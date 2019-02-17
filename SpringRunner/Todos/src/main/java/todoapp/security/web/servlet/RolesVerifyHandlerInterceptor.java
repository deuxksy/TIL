package todoapp.security.web.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import todoapp.security.AccessDeniedException;
import todoapp.security.UnauthorizedAccessException;
import todoapp.security.UserSession;
import todoapp.security.support.RolesAllowedSupport;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Role(역할) 기반으로 사용자가 사용 권한을 확인하는 인터셉터 구현체
 */
public class RolesVerifyHandlerInterceptor implements HandlerInterceptor, RolesAllowedSupport {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public final boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RolesAllowed allowed = getRolesAllowed(handler);
        if (Objects.nonNull(allowed)) {
            Principal principal = request.getUserPrincipal();
            if (Objects.isNull(principal)) {
                throw new UnauthorizedAccessException();
            }
            if (decide(request.getUserPrincipal(), allowed)) {
                return true;
            }
            throw new AccessDeniedException();
        }
        return true;
    }

    protected boolean decide(Principal principal, RolesAllowed allowed) {
        if (!ClassUtils.isAssignableValue(UserSession.class, principal)) {
            log.warn("unsupported userSession: {}", principal);
            return false;
        }

        Set<String> matchedRoles = Stream.of(allowed.value()).filter(((UserSession) principal)::hasRole).collect(Collectors.toSet());
        log.debug("matched roles: {}", matchedRoles);

        return matchedRoles.size() > 0;
    }

}
