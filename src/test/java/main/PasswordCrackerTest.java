// All Rights Reserved, Copyright © Paysafe Holdings UK Limited 2017. For more information see LICENSE

package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PasswordCrackerTest {

  /*
   * [[p, P], - 2
   * [a, A, @], - 3
   * [s, S, $], - 3
   * [s, S, $], - 3
   * [w, W], - 2
   * [o, O, 0], - 3
   * [r, R], - 2
   * [d, D]] - 2
   *
   * 2 * 3 * 3 * 3 * 2 * 3 * 2 * 2 = 1296
   * */
  @Test
  public void test() {
    final List<String> result = new PasswordCracker().crack("password");

    assertEquals(1296, result.size());
    assertTrue(result.contains("p@sSw0rd"));
    assertTrue(result.contains("PASsworD"));
    assertTrue(result.contains("PaSsWORD"));
    assertTrue(result.contains("PassW0Rd"));
    assertTrue(result.contains("pA$$w0RD"));
  }

}
