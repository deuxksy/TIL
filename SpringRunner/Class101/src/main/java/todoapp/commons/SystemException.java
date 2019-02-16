package todoapp.commons;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.util.ClassUtils;

/**
 * ?��?��?�� ?��?�� �? 발생 �??��?�� 최상?�� ?��?�� ?��?��?��
 *
 * @author springrunner.kr@gmail.com
 */
@SuppressWarnings("serial")
public class SystemException extends RuntimeException implements MessageSourceResolvable {

  public SystemException(String format, Object... args) {
    super(String.format(format, args));
  }

  public SystemException(Throwable cause) {
    super(cause);
  }

  public SystemException(String message, Throwable cause) {
    super(message, cause);
  }

  @Override
  public String[] getCodes() {
    return new String[]{"Exception." + ClassUtils.getShortName(getClass())};
  }

  @Override
  public Object[] getArguments() {
    return new Object[0];
  }

  @Override
  public String getDefaultMessage() {
    return getMessage();
  }

}
