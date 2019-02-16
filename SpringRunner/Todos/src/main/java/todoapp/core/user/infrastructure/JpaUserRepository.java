package todoapp.core.user.infrastructure;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import todoapp.Constant;
import todoapp.core.user.domain.User;
import todoapp.core.user.domain.UserRepository;

/**
 * 데이터베이스 및 Spring Data JPA 기반 사용자 저장소 구현체
 *
 * @author springrunner.kr@gmail.com
 */
@Profile(Constant.PROFILE_PRODUCTION)
public interface JpaUserRepository extends UserRepository, JpaRepository<User, String> {

}
