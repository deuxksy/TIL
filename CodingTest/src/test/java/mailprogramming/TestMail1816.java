package mailprogramming;
import org.junit.Assert;
import org.junit.Test;

public class TestMail1816 {
  @Test
  public void testMail() throws Exception {
    Coding1816 test = new Coding1816();
    Assert.assertEquals(7, test.mySolution(new int[]{-1, 3, -1, 5}));
    Assert.assertEquals(-1, test.mySolution(new int[]{-5, -3, -1}));
    Assert.assertEquals(9, test.mySolution(new int[]{2, 4, -2, -3, 8}));
  }
}