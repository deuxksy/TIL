package todoapp.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import todoapp.web.model.FeatureTogglesProperties;

/*
 * 여러분, 미션 있습니다.
 * 1시 20분까지 애플리케이션 기능 토글 조회 API를 구현하세요.
 * API 정의서를 보시면 보다 정확한 요구사항이 나와있습니다.
 * 
 * URI: /api/feature-toggles
 * RESPONSE: { "auth": true, "onlineUsersCounter": false }
 * 
 * todoapp.web.model.FeatureTogglesProperties.java 클래스는 모델로 사용가능합니다.
 * SiteProperties.java 클래스를 참고해서 외부 설정파일(application.yml)로 구성해보세요.
 * 
 */

// 힌트1: 이곳에 컨트롤러임을 알려주는 애노테이션이 선언되어야 합니다.
@RestController
public class FeatureTogglesRestController {

	// 힌트2:  스프링이 관리하는 FeatureTogglesProperties 컴포넌트를 받아와야 합니다.
	private FeatureTogglesProperties properties;
	
	public FeatureTogglesRestController(FeatureTogglesProperties properties) {
		this.properties = properties;
	}

	// 힌트3: 이곳에 핸들러(메소드)와 URI를 연결하는 애노테이션이 선언되어야 합니다.
	@GetMapping("/api/feature-toggles")
	public FeatureTogglesProperties featureTogglesProperties() {
		return properties;
	}
	
}
