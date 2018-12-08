package mailprogramming;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 2018년 17주차 문제
 * 피보나치 배열은 0과 1로 시작하며, 다음 피보나치 수는 바로 앞의 두 피보나치 수의 합이 된다.
 * 정수 N이 주어지면, N보다 작은 모든 짝수 피보나치 수의 합을 구하여라.
 *
 * 예제)
 * Input: N = 12
 * Output: 10 // 0, 1, 2, 3, 5, 8 중 짝수인 2 + 8 = 10.
 */
public class Coding1817 {

  /**
   * 문제가 좀 이상함 피보나치가 0,1,1,2,3,5 .... 이거 아닌가...
   * 짝수의 합을 구하는것이니깐 상관 없지만... 먼가 좀 이상함...
   * @param input
   * @return
   */
  public int mySolution(int input) {
    int sum = 0, before = 1, beforebefore = 0;
    for (int pibo=1;pibo<input;pibo=(before+beforebefore)) {
//      System.out.printf("p:%s, b:%s, bb:%s\n",pibo,before,beforebefore);
      if (pibo < input && 0 == (pibo%2)) {
        sum += pibo;
      }
      beforebefore = before;
      before = pibo;
    }
    return sum;
  }

  public int bestSolution(int N) {
    int sum = 0;
    int x = 1;
    int y = 2;
    while (x <= N) {
      if (x % 2 == 0) {
        sum += x;
      }
      int z = x + y;
      x = y;
      y = z;
    }
    return sum;
  }
}
