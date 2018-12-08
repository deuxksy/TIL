package mailprogramming;

import org.junit.Assert;
import org.junit.Test;

public class TestMail1818 {
  @Test
  public void testMail() throws Exception {
    Coding1818 test = new Coding1818();
    Assert.assertEquals(10, test.mySolution(12));
  }
}
