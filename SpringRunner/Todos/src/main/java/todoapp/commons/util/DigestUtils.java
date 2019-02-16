package todoapp.commons.util;

import todoapp.commons.SystemException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ?��?�� ?��?��(hash function) ?��?��리티
 *
 * @author springrunner.kr@gmail.com
 */
public interface DigestUtils {

  /**
   * SHA-256 ?��고리즘으�? ?��?��?�� 문자?��?�� ?��?�� 값을 ?��?��?��?��?��.
   *
   * @param value ???�� 문자?��
   * @return
   */
  static String sha256(String value) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      return new String(digest.digest(value.getBytes(StandardCharsets.UTF_8)));
    } catch (NoSuchAlgorithmException error) {
      throw new SystemException("SHA-256 ?��고리즘이 ?��?��?��?��.", error);
    }
  }

}
