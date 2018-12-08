package euler;

import org.junit.Test;

public class TestEuler0003 {
  @Test
  public void testEuler() throws Exception{
    long before = System.currentTimeMillis();
    Problem0003 test = new Problem0003();
//    System.out.println(test.mySolution(13195));
    System.out.println(test.mySolution(600851475143L));
    System.out.format("time: %s ms", (System.currentTimeMillis() - before));
  }
}
