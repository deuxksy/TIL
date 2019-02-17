package todoapp.commons.web.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.ClassUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import todoapp.commons.util.ThrowableUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 스프링부트에 기본 구현체인 {@link DefaultErrorAttributes}에 message 속성을 덮어쓰기 할 목적으로 작성한 컴포넌트이다.
 *
 * DefaultErrorAttributes는 message 속성을 예외 객체의 값을 사용하기 때문에 사용자가 읽기에 좋은 문구가 아니다.
 * 해당 메시지를 보다 읽기 좋은 문구로 가공해서 제공한다.
 *
 * @author springrunner.kr@gmail.com
 */
public class ReadableErrorAttributes implements ErrorAttributes, HandlerExceptionResolver, Ordered {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final DefaultErrorAttributes delegate;
    
    private MessageSource messageSource;
    
    public ReadableErrorAttributes(MessageSource messageSource) {
        this(false);
        this.messageSource = messageSource;
    }

    public ReadableErrorAttributes(boolean includeException) {
        this.delegate = new DefaultErrorAttributes(includeException);
    }

    /**
     * Spring MVC 내부에서 오류가 발생하면, 스프링 부트가 등록한 BasicErrorController 가 동작한다.
     * BasciErrorController는 ErrorAttributes 컴포넌트를 사용해서 오류 모델을 구성한다.
     * 오류 모델을 구성할 때 이 메소드(getErrorAttributes)가 호출된다.
     */
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> attributes = delegate.getErrorAttributes(webRequest, includeStackTrace);
        Throwable error = getError(webRequest);
        Locale locale = webRequest.getLocale();
        
        if (Objects.nonNull(error)) {
        	String message;
        	if (ClassUtils.isAssignableValue(MessageSourceResolvable.class, error)) {
        		message = messageSource.getMessage((MessageSourceResolvable) error, locale);            	
        	} else {
        		// code = Exception.{CalssName}
            	String code = "Exception." + error.getClass().getSimpleName();
            	String defaultMessage = error.getMessage();
            	
            	message = messageSource.getMessage(code, null, defaultMessage, locale);        		
        	}
        	attributes.put("message", message);
        	
        	/*
        	if (error instanceof MethodArgumentNotValidException) {
        		
        	} else if (error instanceof xxx) {
        		
        	} else if (error instanceof yyy) {
        		
        	} else if (error instanceof zzz) {
        		
        	}
        	*/
        }
        
        BindingResult bindingResult = ThrowableUtils.extractBindingResult(error);
        if (Objects.nonNull(bindingResult)) {
        	List<String> errors = bindingResult.getAllErrors()
        			.stream()
        			.map(code -> messageSource.getMessage(code, locale))
        			.collect(Collectors.toList());        		
        	attributes.put("errors", errors);
        }
        
        // TODO attributes, error 을 사용해서 message 속성을 읽기 좋은 문구로 가공한다.
        // TODO ex) attributes.put("message", "문구");
        
        

        return attributes;
    }

    @Override
    public Throwable getError(WebRequest webRequest) {
        return delegate.getError(webRequest);
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception error) {
        return delegate.resolveException(request, response, handler, error);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
