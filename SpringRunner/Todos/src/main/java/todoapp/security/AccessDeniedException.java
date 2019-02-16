package todoapp.security;

import todoapp.commons.SystemException;

/**
 * 권한?�� ?��?�� ?���? 불�? ?��?��?�� 발생 �??��?�� ?��?�� ?��?��?��
 *
 * @author springrunner.kr@gmail.com
 */
public class AccessDeniedException extends SystemException {

  public AccessDeniedException() {
    super("?��근을 거�??��?��?��.");
  }

}
