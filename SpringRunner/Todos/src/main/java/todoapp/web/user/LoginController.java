package todoapp.web.user;

import java.util.Locale;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import todoapp.commons.context.ExceptionMessageTranslator;
import todoapp.core.user.application.UserJoinder;
import todoapp.core.user.application.UserPasswordVerifier;
import todoapp.core.user.domain.User;
import todoapp.core.user.domain.UserEntityNotFoundException;
import todoapp.core.user.domain.UserPasswordNotMatchedException;
import todoapp.security.UserSession;
import todoapp.security.UserSessionRepository;

@Controller
public class LoginController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserPasswordVerifier userPasswordVerifier;
	
	@Autowired
	private UserJoinder userJoinder;
	
	@Autowired
	private UserSessionRepository userSessionRepository;
	
	@Autowired
	private ExceptionMessageTranslator exceptionMessageTranslator;
	
	@GetMapping("/login")
	public String loginForm() {
		if (Objects.nonNull(userSessionRepository.get())) {
            return "redirect:/todos";
        }
        return "login";
	}

	@PostMapping("/login")
	public String loginProcess(@Valid LoginCommand command, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("bindingResult", bindingResult);
			model.addAttribute("message", "사용자 입력 값이 올바르지 않습니다.");
			return "login";
		}
		
		User logindUser;
		try {
			// 1. 사용자 저장소에 해당 사용자가 있으면 비밀번호를 확인합니다.
			logindUser = userPasswordVerifier.verify(command.getUsername(), command.getPassword());						
		} catch (UserEntityNotFoundException error) {
			// 2. 사장자 저장소에 사용자가 없으면, 신규 가입 처리합니다.
			logindUser = userJoinder.join(command.getUsername(), command.getPassword());
		}
		userSessionRepository.set(new UserSession(logindUser));
			
		// 비밀번호가 일치시 /todos 로 리다이렉트 처리 
		return "redirect:/todos";
	}
	
	@ExceptionHandler(UserPasswordNotMatchedException.class)
	public String handleUserPasswordNotMatchedException(UserPasswordNotMatchedException error, Locale locale, Model model) {
		model.addAttribute("message", exceptionMessageTranslator.getMessage(error, locale));
		return "login";
	}	
	
	@RequestMapping("/logout")
	public String logout() {
		userSessionRepository.clear();
		
		return "redirect:/todos";
	}
	
	
	public static class LoginCommand {
		
		@Size(min = 4, max = 20)
		private String username;
		private String password;
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		@Override
		public String toString() {
			return "LoginCommand [username=" + username + ", password=" + password + "]";
		}
		
	}

}

