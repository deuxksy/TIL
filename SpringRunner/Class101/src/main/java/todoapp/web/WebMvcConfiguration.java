package todoapp.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import todoapp.commons.web.view.CommaSeparatedValuesView;

/**
 * Spring Web MVC 설정
 *
 * @author springrunner.kr@gmail.com
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// http://localhost:8080/assets/servlet.css
		// servlet.css 파일을 웹 브라우저에게 제공해야 한다
		
		// registry.addResourceHandler("/assets/**")
		//		   .addResourceLocations("assets/", "file:../files/assets/", "classpath:assets/");		
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
