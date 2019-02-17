package todoapp.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import todoapp.commons.web.error.ReadableErrorAttributes;
import todoapp.commons.web.servlet.ExecutionTimeHandlerInterceptor;
import todoapp.commons.web.servlet.LoggingHandlerInterceptor;
import todoapp.commons.web.view.CommaSeparatedValuesView;
import todoapp.security.UserSessionRepository;
import todoapp.security.web.method.UserSessionHandlerMethodArgumentResolver;
import todoapp.security.web.servlet.RolesVerifyHandlerInterceptor;

/**
 * Spring Web MVC 설정
 *
 * @author springrunner.kr@gmail.com
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	private UserSessionRepository userSessionRepository;
	
	public WebMvcConfiguration(UserSessionRepository userSessionRepository) {
		this.userSessionRepository = userSessionRepository;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// http://localhost:8080/assets/servlet.css
		// servlet.css 파일을 웹 브라우저에게 제공해야 한다
		
		// registry.addResourceHandler("/assets/**")
		//		   .addResourceLocations("assets/", "file:../files/assets/", "classpath:assets/");		
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new UserSessionHandlerMethodArgumentResolver(userSessionRepository));
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggingHandlerInterceptor());
		registry.addInterceptor(new ExecutionTimeHandlerInterceptor());
		registry.addInterceptor(new RolesVerifyHandlerInterceptor());
	}	
	
	@Bean
	public ErrorAttributes errorAttributes(MessageSource messageSource) {
		return new ReadableErrorAttributes(messageSource);
	}
	
	
	
	@Configuration
	public static class ContentNegotiationCustomizer {
	
		/**
		 * 스프링 부트가 구성한 ContentNegotiatingViewResolver 컴포넌트에
		 * DefaultViews 속성을 덮어쓰는 역할을 수행하는 메소드이다.
		 */
		@Autowired
	    public void configurer(ContentNegotiatingViewResolver viewResolver) {
	        List<View> views = new ArrayList<>(viewResolver.getDefaultViews());
	    	views.add(new CommaSeparatedValuesView());
	    	
	    	viewResolver.setDefaultViews(views);
	    }
	
	}

}
