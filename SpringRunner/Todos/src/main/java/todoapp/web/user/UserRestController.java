package todoapp.web.user;

import java.net.URI;
import java.util.Objects;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import todoapp.core.user.application.ProfilePictureChanger;
import todoapp.core.user.domain.ProfilePicture;
import todoapp.core.user.domain.ProfilePictureStorage;
import todoapp.core.user.domain.User;
import todoapp.security.UserSession;
import todoapp.web.model.UserProfile;

@RestController
@RolesAllowed("ROLE_USER")
public class UserRestController {
	
	private ProfilePictureChanger profilePictureChanger;
	private ProfilePictureStorage profilePictureStorage;
	
	public UserRestController(ProfilePictureChanger profilePictureChanger, ProfilePictureStorage profilePictureStorage) {
		this.profilePictureChanger = profilePictureChanger;
		this.profilePictureStorage = profilePictureStorage;
	}

	@GetMapping("/api/user/profile")
	public ResponseEntity<UserProfile> userProfile(UserSession session) {
		if (Objects.nonNull(session)) {
			// 로그인된 사용자 프로필 제공
			return ResponseEntity.ok(new UserProfile(session.getUser()));
		}
		
		// 인증되지 않았음, 401 상태 반환
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
							 .build();
	}

	@PostMapping("/api/user/profile-picture")
	public UserProfile profilePicture(HttpSession httpSession, UserSession session, MultipartFile profilePicture) {
		User logindUser = session.getUser();
		URI uri = profilePictureStorage.save(profilePicture.getResource());
		
		User savedUser = profilePictureChanger.change(logindUser.getUsername(), new ProfilePicture(uri));
		httpSession.setAttribute(User.class.getName(), savedUser);
		
		return new UserProfile(savedUser);
	}
	
}
