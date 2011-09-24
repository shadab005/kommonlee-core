/**
 * 
 */
package org.elixirian.kommonlee.util.number;

/**
 * <pre>
 *     ___  _____                                              _____
 *    /   \/    / ______ __________________  ______ __ ______ /    /   ______  ______  
 *   /        / _/ __  // /  /   / /  /   /_/ __  // //     //    /   /  ___ \/  ___ \ 
 *  /        \ /  /_/ _/  _  _  /  _  _  //  /_/ _/   __   //    /___/  _____/  _____/
 * /____/\____\/_____//__//_//_/__//_//_/ /_____//___/ /__//________/\_____/ \_____/
 * </pre>
 * 
 * <pre>
 *     ___  _____  __________  ___________ _____  ____
 *    /   \/    / /      \   \/   /_    _//     \/   /
 *   /        /  /    ___/\      / /   / /          /
 *  /        \  /    ___/  \    /_/   /_/          /
 * /____/\____\/_______/    \__//______/___/\_____/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-11-14)
 */
public final class IntegersTotal
{
  private IntegersTotal()
  {
  }

  public static int sum(final int number1, final int number2)
  {
    return number1 + number2;
  }

  public static int sum(final int number1, final int number2, final int number3)
  {
    return number1 + number2 + number3;
  }

  public static int sum(final int number1, final int number2, final int number3, final int number4)
  {
    return number1 + number2 + number3 + number4;
  }

  public static int sum(final int number1, final int number2, final int number3, final int number4, final int number5)
  {
    return number1 + number2 + number3 + number4 + number5;
  }

  public static int sum(final int number1, final int number2, final int number3, final int number4, final int number5,
      final int... rest)
  {
    int total = sum(number1, number2, number3, number4, number5);
    for (final int each : rest)
    {
      total += each;
    }
    return total;
  }

  public static int sum(final int[] numbers)
  {
    int total = 0;
    for (final int each : numbers)
    {
      total += each;
    }
    return total;
  }

  public static int total(final Integer number1, final Integer number2)
  {
    return number1.intValue() + number2.intValue();
  }

  public static int total(final Integer number1, final Integer number2, final Integer number3)
  {
    return number1.intValue() + number2.intValue() + number3.intValue();
  }

  public static int total(final Integer number1, final Integer number2, final Integer number3, final Integer number4)
  {
    return number1.intValue() + number2.intValue() + number3.intValue() + number4.intValue();
  }

  public static int total(final Integer number1, final Integer number2, final Integer number3, final Integer number4,
      final Integer number5)
  {
    return number1.intValue() + number2.intValue() + number3.intValue() + number4.intValue() + number5.intValue();
  }

  public static int total(final Integer number1, final Integer number2, final Integer number3, final Integer number4,
      final Integer number5, final Integer... rest)
  {
    int total = total(number1, number2, number3, number4, number5);
    for (final Integer each : rest)
    {
      total += each.intValue();
    }
    return total;
  }

  public static int total(final Integer[] numbers)
  {
    int total = 0;
    for (final Integer each : numbers)
    {
      total += each.intValue();
    }
    return total;
  }

  public static int total(final Iterable<Integer> numbers)
  {
    int total = 0;
    for (final Integer each : numbers)
    {
      total += each.intValue();
    }
    return total;
  }
}