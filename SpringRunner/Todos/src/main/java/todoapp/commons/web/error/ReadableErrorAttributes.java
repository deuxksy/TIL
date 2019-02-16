package todoapp.commons.web.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * ?��?��링�??��?�� 기본 구현체인 {@link DefaultErrorAttributes}?�� message ?��?��?�� ?��?��?���? ?�� 목적?���? ?��?��?�� 컴포?��?��?��?��.
 * <p>
 * DefaultErrorAttributes?�� message ?��?��?�� ?��?�� 객체?�� 값을 ?��?��?���? ?��문에 ?��?��?���? ?��기에 좋�? 문구�? ?��?��?��.
 * ?��?�� 메시�?�? 보다 ?���? 좋�? 문구�? �?공해?�� ?��공한?��.
 *
 * @author springrunner.kr@gmail.com
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ReadableErrorAttributes implements ErrorAttributes, HandlerExceptionResolver, Ordered {

  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final DefaultErrorAttributes delegate;

  public ReadableErrorAttributes() {
    this(false);
  }

  public ReadableErrorAttributes(boolean includeException) {
    this.delegate = new DefaultErrorAttributes(includeException);
  }

  @Override
  public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
    Map<String, Object> attributes = delegate.getErrorAttributes(webRequest, includeStackTrace);
    Throwable error = getError(webRequest);

    // TODO attributes, error ?�� ?��?��?��?�� message ?��?��?�� ?���? 좋�? 문구�? �?공한?��.
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
