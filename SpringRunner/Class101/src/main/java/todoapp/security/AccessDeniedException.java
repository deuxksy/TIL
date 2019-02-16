package todoapp.security;

import todoapp.commons.SystemException;

/**
 * ê¶Œí•œ?´ ?—†?–´ ? ‘ê·? ë¶ˆê? ?ƒ?™©?‹œ ë°œìƒ ê°??Š¥?•œ ?˜ˆ?™¸ ?´?˜?Š¤
 *
 * @author springrunner.kr@gmail.com
 */
public class AccessDeniedException extends SystemException {

    public AccessDeniedException() {
        super("? ‘ê·¼ì„ ê±°ë??•©?‹ˆ?‹¤.");
    }

}
