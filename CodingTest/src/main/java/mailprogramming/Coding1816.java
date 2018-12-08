package mailprogramming;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 2018년 16주차 문제
 * 정수 배열(int array)가 주어지면 가장 큰 이어지는 원소들의 합을 구하시오.
 * 단, 시간복잡도는 O(n).
 * 예제}
 *
 * Input: [-1, 3, -1, 5]
 * Output: 7 // 3 + (-1) + 5
 *
 * Input: [-5, -3, -1]
 * Output: -1 // -1
 *
 * Input: [2, 4, -2, -3, 8]
 * Output: 9 // 2 + 4 + (-2) + (-3) + 8
 */
public class Coding1816 {

  /**
   * for 두개 쓴거 아나 ㅠ.ㅠ;
   * @param input
   * @return
   */
  public int mySolution(int[] input) {
    Integer maxSum = null;
    for (int i = 0; i < input.length; i++) {
      for (int j = i + 1; j <= input.length; j++) {
        int sum = IntStream.of(Arrays.copyOfRange(input, i, j)).sum();
        if (null != maxSum) {
          if (maxSum < sum) {
            maxSum = sum;
          }
        } else {
          maxSum = sum;
        }
      }
    }
    return maxSum;
  }

  public int bestSolution(int[] arr) {
    int maxSum = arr[0];
    int currentSum = arr[0];
    for(int i = 1; i < arr.length; i++) {
      currentSum = Math.max(currentSum + arr[i], arr[i]);
      maxSum = Math.max(currentSum, maxSum);
    }
    return maxSum;
  }
}
