package euler;

/**
 * 어떤 수를 소수의 곱으로만 나타내는 것을 소인수분해라 하고, 이 소수들을 그 수의 소인수라고 합니다.
 * 예를 들면 13195의 소인수는 5, 7, 13, 29 입니다.
 * <p>
 * 600851475143의 소인수 중에서 가장 큰 수를 구하세요.
 */
public class Problem0003 {

  boolean isPrimeNumber(long input) {
    if (2 > input) {
      return false;
    }
    for (long i = 2; i <= (input / 2); i++) {
      if (input % i == 0) {
        return false;
      }
    }
    return true;
  }

  public long mySolution(long input) {
    long value = 0;
    for (long i = 2; i < Math.sqrt(input) + 1; i++) {
      if (input % i == 0 && isPrimeNumber(i)) {
        value = i;
      }
    }
    return value;
  }
}
