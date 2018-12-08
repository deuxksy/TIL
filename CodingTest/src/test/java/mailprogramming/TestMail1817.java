package mailprogramming;

import org.junit.Assert;
import org.junit.Test;

public class TestMail1817 {
  @Test
  public void testMail() throws Exception {
    Coding1817 test = new Coding1817();
    Assert.assertEquals(10, test.mySolution(12));
  }
}
