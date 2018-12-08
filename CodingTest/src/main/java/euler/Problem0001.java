package euler;

/**
 * 10보다 작은 자연수 중에서 3 또는 5의 배수는 3, 5, 6, 9 이고, 이것을 모두 더하면 23입니다.
 * 1000보다 작은 자연수 중에서 3 또는 5의 배수를 모두 더하면 얼마일까요?
 */
public class Problem0001 {

  public int mySolution (int input) {
    int maxSum = 0;
    for (int i=3; i< input; i++) {
      if (0 == i%3 || 0 == i%5) {
        maxSum += i;
      }
    }
    return maxSum;
  }
}