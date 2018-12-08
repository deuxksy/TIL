package mailprogramming;

import org.junit.Assert;
import org.junit.Test;

public class TestMail1819 {
  @Test
  public void testMail() throws Exception {
    Coding1819 test = new Coding1819();
    Assert.assertEquals(true, test.mySolution(123321));
  }
}
