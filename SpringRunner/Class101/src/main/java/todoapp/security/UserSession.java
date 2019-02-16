package todoapp.security;

import todoapp.core.user.domain.User;

import java.util.Objects;

/**
 * 사용자 세션 모델
 *
 * @author springrunner.kr@gmail.com
 */
public class UserSession {

    private final User user;

    public UserSession(User user) {
        this.user = Objects.requireNonNull(user, "user object must be not null");
    }

    public String getName() {
        return user.getUsername();
    }

    public User getUser() {
        return user;
    }

}
