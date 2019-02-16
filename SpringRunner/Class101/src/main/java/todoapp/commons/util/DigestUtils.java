package todoapp.commons.util;

import todoapp.commons.SystemException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ?•´?‹œ ?•¨?ˆ˜(hash function) ?œ ?‹¸ë¦¬í‹°
 *
 * @author springrunner.kr@gmail.com
 */
public interface DigestUtils {

    /**
     * SHA-256 ?•Œê³ ë¦¬ì¦˜ìœ¼ë¡? ?…? ¥?œ ë¬¸ì?—´?„ ?•´?‹œ ê°’ì„ ?ƒ?„±?•©?‹ˆ?‹¤.
     *
     * @param value ???ƒ ë¬¸ì?—´
     * @return
     */
    static String sha256(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return new String(digest.digest(value.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException error) {
            throw new SystemException("SHA-256 ?•Œê³ ë¦¬ì¦˜ì´ ?—†?Šµ?‹ˆ?‹¤.", error);
        }
    }

}
