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
 * ?Š¤?”„ë§ë??Š¸?— ê¸°ë³¸ êµ¬í˜„ì²´ì¸ {@link DefaultErrorAttributes}?— message ?†?„±?„ ?®?–´?“°ê¸? ?•  ëª©ì ?œ¼ë¡? ?‘?„±?•œ ì»´í¬?„Œ?Š¸?´?‹¤.
 *
 * DefaultErrorAttributes?Š” message ?†?„±?„ ?˜ˆ?™¸ ê°ì²´?˜ ê°’ì„ ?‚¬?š©?•˜ê¸? ?•Œë¬¸ì— ?‚¬?š©?ê°? ?½ê¸°ì— ì¢‹ì? ë¬¸êµ¬ê°? ?•„?‹ˆ?‹¤.
 * ?•´?‹¹ ë©”ì‹œì§?ë¥? ë³´ë‹¤ ?½ê¸? ì¢‹ì? ë¬¸êµ¬ë¡? ê°?ê³µí•´?„œ ? œê³µí•œ?‹¤.
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

        // TODO attributes, error ?„ ?‚¬?š©?•´?„œ message ?†?„±?„ ?½ê¸? ì¢‹ì? ë¬¸êµ¬ë¡? ê°?ê³µí•œ?‹¤.
        // TODO ex) attributes.put("message", "ë¬¸êµ¬");

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
