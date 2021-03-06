/**
 * This project is licensed under the Apache License, Version 2.0
 * if the following condition is met:
 * (otherwise it cannot be used by anyone but the author, Kevin, only)
 *
 * The original KommonLee project is owned by Lee, Seong Hyun (Kevin).
 *
 * -What does it mean to you?
 * Nothing, unless you want to take the ownership of
 * "the original project" (not yours or forked & modified one).
 * You are free to use it for both non-commercial and commercial projects
 * and free to modify it as the Apache License allows.
 *
 * -So why is this condition necessary?
 * It is only to protect the original project (See the case of Java).
 *
 *
 * Copyright 2009 Lee, Seong Hyun (Kevin)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.elixirian.kommonlee.util.number;

import static java.util.Arrays.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.elixirian.kommonlee.type.functional.Condition1;
import org.elixirian.kommonlee.type.selector.Selector1;
import org.elixirian.kommonlee.util.number.Numbers.NumberIterableSelector;
import org.elixirian.kommonlee.util.number.Numbers.NumbersSelector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Matchers;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-11-15)
 */
public class NumbersTest
{
  private static final int RANGE = 20;

  private static final List<Byte> BYTE_LIST = Collections.unmodifiableList(toByteList(Byte.MIN_VALUE, -102, -101, -100,
      -12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 100, 101, 102,
      Byte.MAX_VALUE));

  private static final List<Short> SHORT_LIST = Collections.unmodifiableList(toShortList(Short.MIN_VALUE, -1002, -1001,
      -1000, -102, -101, -100, -12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
      12, 100, 101, 102, 1000, 1001, 1002, Short.MAX_VALUE));

  @SuppressWarnings("boxing")
  private static final List<Integer> INTEGER_LIST = Collections.unmodifiableList(Arrays.asList(Integer.MIN_VALUE,
      -1002, -1001, -1000, -102, -101, -100, -12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7,
      8, 9, 10, 11, 12, 100, 101, 102, 1000, 1001, 1002, Integer.MAX_VALUE));

  @SuppressWarnings("boxing")
  private static final List<Long> LONG_LIST = Collections.unmodifiableList(Arrays.asList(Long.MIN_VALUE, -1002L,
      -1001L, -1000L, -102L, -101L, -100L, -12L, -11L, -10L, -9L, -8L, -7L, -6L, -5L, -4L, -3L, -2L, -1L, 0L, 1L, 2L,
      3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 100L, 101L, 102L, 1000L, 1001L, 1002L, Long.MAX_VALUE));

  @SuppressWarnings("boxing")
  private static final List<Float> FLOAT_LIST = Collections.unmodifiableList(Arrays.asList(Float.MIN_VALUE, -102.10f,
      -101.11f, -100.12f, -12.13f, -11.14f, -10.15f, -9.16f, -8.17f, -7.18f, -6.19f, -5.20f, -4.21f, -3.22f, -2.23f,
      -1.24f, 0.0f, 1.10f, 2.11f, 3.12f, 4.13f, 5.14f, 6.15f, 7.16f, 8.17f, 9.18f, 10.19f, 11.20f, 12.21f, 100.22f,
      101.23f, 102.24f, Float.MAX_VALUE));

  @SuppressWarnings("boxing")
  private static final List<Double> DOUBLE_LIST = Collections.unmodifiableList(Arrays.asList(Double.MIN_VALUE,
      -1002.50D, -1001.51D, -1000.52D, -102.10D, -101.11D, -100.12D, -12.13D, -11.14D, -10.15D, -9.16D, -8.17D, -7.18D,
      -6.19D, -5.20D, -4.21D, -3.22D, -2.23D, -1.24D, 0.0D, 1.10D, 2.11D, 3.12D, 4.13D, 5.14D, 6.15D, 7.16D, 8.17D,
      9.18D, 10.19D, 11.20D, 12.21D, 100.22D, 101.23D, 102.24D, 1000.50D, 1001.51D, 1002.52D, Double.MAX_VALUE));

  private static final List<BigInteger> BIG_INTEGER_LIST =
    Collections.unmodifiableList(Arrays.asList(BigInteger.valueOf(Long.MIN_VALUE), BigInteger.valueOf(-1002),
        BigInteger.valueOf(-1001), BigInteger.valueOf(-1000), BigInteger.valueOf(-102), BigInteger.valueOf(-101),
        BigInteger.valueOf(-100), BigInteger.valueOf(-12), BigInteger.valueOf(-11), BigInteger.valueOf(-10),
        BigInteger.valueOf(-9), BigInteger.valueOf(-8), BigInteger.valueOf(-7), BigInteger.valueOf(-6),
        BigInteger.valueOf(-5), BigInteger.valueOf(-4), BigInteger.valueOf(-3), BigInteger.valueOf(-2),
        BigInteger.valueOf(-1), BigInteger.valueOf(0), BigInteger.valueOf(1), BigInteger.valueOf(2),
        BigInteger.valueOf(3), BigInteger.valueOf(4), BigInteger.valueOf(5), BigInteger.valueOf(6),
        BigInteger.valueOf(7), BigInteger.valueOf(8), BigInteger.valueOf(9), BigInteger.valueOf(10),
        BigInteger.valueOf(11), BigInteger.valueOf(12), BigInteger.valueOf(100), BigInteger.valueOf(101),
        BigInteger.valueOf(102), BigInteger.valueOf(1000), BigInteger.valueOf(1001), BigInteger.valueOf(1002),
        BigInteger.valueOf(Long.MAX_VALUE)));

  private static final List<BigDecimal> BIG_DECIMAL_LIST = Collections.unmodifiableList(Arrays.asList(
      BigDecimal.valueOf(Double.MIN_VALUE), BigDecimal.valueOf(-1002.50D), BigDecimal.valueOf(-1001.51D),
      BigDecimal.valueOf(-1000.52D), BigDecimal.valueOf(-102.10D), BigDecimal.valueOf(-101.11D),
      BigDecimal.valueOf(-100.12D), BigDecimal.valueOf(-12.13D), BigDecimal.valueOf(-11.14D),
      BigDecimal.valueOf(-10.15D), BigDecimal.valueOf(-9.16D), BigDecimal.valueOf(-8.17D), BigDecimal.valueOf(-7.18D),
      BigDecimal.valueOf(-6.19D), BigDecimal.valueOf(-5.20D), BigDecimal.valueOf(-4.21D), BigDecimal.valueOf(-3.22D),
      BigDecimal.valueOf(-2.23D), BigDecimal.valueOf(-1.24D), BigDecimal.valueOf(0.0D), BigDecimal.valueOf(1.10D),
      BigDecimal.valueOf(2.11D), BigDecimal.valueOf(3.12D), BigDecimal.valueOf(4.13D), BigDecimal.valueOf(5.14D),
      BigDecimal.valueOf(6.15D), BigDecimal.valueOf(7.16D), BigDecimal.valueOf(8.17D), BigDecimal.valueOf(9.18D),
      BigDecimal.valueOf(10.19D), BigDecimal.valueOf(11.20D), BigDecimal.valueOf(12.21D), BigDecimal.valueOf(100.22D),
      BigDecimal.valueOf(101.23D), BigDecimal.valueOf(102.24D), BigDecimal.valueOf(1000.50D),
      BigDecimal.valueOf(1001.51D), BigDecimal.valueOf(1002.52D), BigDecimal.valueOf(Double.MAX_VALUE)));

  @BeforeClass
  public static void setUpBeforeClass() throws Exception
  {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {
  }

  @Before
  public void setUp() throws Exception
  {
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  public final void testOddInteger()
  {
    @SuppressWarnings("boxing")
    final List<Integer> oddNumbers =
      Collections.unmodifiableList(Arrays.asList(-1001, -101, -11, -9, -7, -5, -3, -1, 1, 3, 5, 7, 9, 11, 101, 1001));

    @SuppressWarnings("boxing")
    final List<Integer> integerList =
      Arrays.asList(-1002, -1001, -1000, -102, -101, -100, -12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2,
          3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 100, 101, 102, 1000, 1001, 1002);

    final Selector1<List<Integer>, Condition1<Integer>, List<Integer>> numberFunction =
      new Selector1<List<Integer>, Condition1<Integer>, List<Integer>>() {
        @Override
        public List<Integer> select(final Condition1<Integer> condition, final List<Integer> source)
        {
          final List<Integer> result = new ArrayList<Integer>();
          for (final Integer integer : source)
          {
            if (condition.isMet(integer))
            {
              result.add(integer);
            }
          }
          return result;
        }
      };

    final List<Integer> oddNumberResult = numberFunction.select(Numbers.oddInteger(), integerList);
    assertThat(oddNumberResult, is(equalTo(oddNumbers)));

    assertThat(Numbers.integerIterableToArrayListSelector()
        .select(Numbers.oddInteger(), integerList), is(equalTo(oddNumbers)));
  }

  @Test
  public final void testEvenInteger()
  {
    @SuppressWarnings("boxing")
    final List<Integer> evenNumbers =
      Collections.unmodifiableList(Arrays.asList(-1002, -1000, -102, -100, -12, -10, -8, -6, -4, -2, 0, 2, 4, 6, 8, 10,
          12, 100, 102, 1000, 1002));

    @SuppressWarnings("boxing")
    final List<Integer> integerList =
      Arrays.asList(-1002, -1001, -1000, -102, -101, -100, -12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2,
          3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 100, 101, 102, 1000, 1001, 1002);

    final Selector1<List<Integer>, Condition1<Integer>, List<Integer>> numberFunction =
      new Selector1<List<Integer>, Condition1<Integer>, List<Integer>>() {
        @Override
        public List<Integer> select(final Condition1<Integer> condition, final List<Integer> source)
        {
          final List<Integer> result = new ArrayList<Integer>();
          for (final Integer integer : source)
          {
            if (condition.isMet(integer))
            {
              result.add(integer);
            }
          }
          return result;
        }
      };

    final List<Integer> evenNumberResult = numberFunction.select(Numbers.evenInteger(), integerList);
    assertThat(evenNumberResult, is(equalTo(evenNumbers)));

    assertThat(Numbers.integerIterableToArrayListSelector()
        .select(Numbers.evenInteger(), integerList), is(equalTo(evenNumbers)));
  }

  @Test
  public final void testOddLong()
  {
    @SuppressWarnings("boxing")
    final List<Long> oddNumbers =
      Collections.unmodifiableList(Arrays.asList(-1001L, -101L, -11L, -9L, -7L, -5L, -3L, -1L, 1L, 3L, 5L, 7L, 9L, 11L,
          101L, 1001L));

    @SuppressWarnings("boxing")
    final List<Long> numberList =
      Arrays.asList(-1002L, -1001L, -1000L, -102L, -101L, -100L, -12L, -11L, -10L, -9L, -8L, -7L, -6L, -5L, -4L, -3L,
          -2L, -1L, 0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 100L, 101L, 102L, 1000L, 1001L, 1002L);

    final Selector1<List<Long>, Condition1<Long>, List<Long>> numberFunction =
      new Selector1<List<Long>, Condition1<Long>, List<Long>>() {
        @Override
        public List<Long> select(final Condition1<Long> condition, final List<Long> source)
        {
          final List<Long> result = new ArrayList<Long>();
          for (final Long number : source)
          {
            if (condition.isMet(number))
            {
              result.add(number);
            }
          }
          return result;
        }
      };

    final List<Long> oddNumberResult = numberFunction.select(Numbers.oddLong(), numberList);
    assertThat(oddNumberResult, is(equalTo(oddNumbers)));

    assertThat(Numbers.longIterableToArrayListSelector()
        .select(Numbers.oddLong(), numberList), is(equalTo(oddNumbers)));
  }

  @Test
  public final void testEvenLong()
  {
    @SuppressWarnings("boxing")
    final List<Long> evenNumbers =
      Collections.unmodifiableList(Arrays.asList(-1002L, -1000L, -102L, -100L, -12L, -10L, -8L, -6L, -4L, -2L, 0L, 2L,
          4L, 6L, 8L, 10L, 12L, 100L, 102L, 1000L, 1002L));

    @SuppressWarnings("boxing")
    final List<Long> numberList =
      Arrays.asList(-1002L, -1001L, -1000L, -102L, -101L, -100L, -12L, -11L, -10L, -9L, -8L, -7L, -6L, -5L, -4L, -3L,
          -2L, -1L, 0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 100L, 101L, 102L, 1000L, 1001L, 1002L);

    final Selector1<List<Long>, Condition1<Long>, List<Long>> numberFunction =
      new Selector1<List<Long>, Condition1<Long>, List<Long>>() {
        @Override
        public List<Long> select(final Condition1<Long> condition, final List<Long> source)
        {
          final List<Long> result = new ArrayList<Long>();
          for (final Long number : source)
          {
            if (condition.isMet(number))
            {
              result.add(number);
            }
          }
          return result;
        }
      };

    final List<Long> evenNumberResult = numberFunction.select(Numbers.evenLong(), numberList);
    assertThat(evenNumberResult, is(equalTo(evenNumbers)));

    assertThat(Numbers.longIterableToArrayListSelector()
        .select(Numbers.evenLong(), numberList), is(equalTo(evenNumbers)));
  }

  @Test
    public final void testNumberIterableToArrayListSelector()
    {
      final NumberIterableSelector<Byte, ArrayList<Byte>> byteIterableToArrayListFilter =
        Numbers.numberIterableToArrayListSelector();
      assertThat(byteIterableToArrayListFilter, is(notNullValue()));
  
      final NumberIterableSelector<Short, ArrayList<Short>> shortIterableToArrayListFilter =
        Numbers.numberIterableToArrayListSelector();
      assertThat(shortIterableToArrayListFilter, is(notNullValue()));
  
      final NumberIterableSelector<Integer, ArrayList<Integer>> integerIterableToArrayListFilter =
        Numbers.numberIterableToArrayListSelector();
      assertThat(integerIterableToArrayListFilter, is(notNullValue()));
  
      final NumberIterableSelector<Long, ArrayList<Long>> longIterableToArrayListFilter =
        Numbers.numberIterableToArrayListSelector();
      assertThat(longIterableToArrayListFilter, is(notNullValue()));
  
      final NumberIterableSelector<Float, ArrayList<Float>> floatIterableToArrayListFilter =
        Numbers.numberIterableToArrayListSelector();
      assertThat(floatIterableToArrayListFilter, is(notNullValue()));
  
      final NumberIterableSelector<Double, ArrayList<Double>> doubleIterableToArrayListFilter =
        Numbers.numberIterableToArrayListSelector();
      assertThat(doubleIterableToArrayListFilter, is(notNullValue()));
  
      final NumberIterableSelector<BigInteger, ArrayList<BigInteger>> bigIntegerIterableToArrayListFilter =
        Numbers.numberIterableToArrayListSelector();
      assertThat(bigIntegerIterableToArrayListFilter, is(notNullValue()));
  
      final NumberIterableSelector<BigDecimal, ArrayList<BigDecimal>> bigDecimalIterableToArrayListFilter =
        Numbers.numberIterableToArrayListSelector();
      assertThat(bigDecimalIterableToArrayListFilter, is(notNullValue()));
    }

  @SuppressWarnings("boxing")
  private static List<Byte> toByteList(final int... ints)
  {
    final List<Byte> List = new ArrayList<Byte>(ints.length);
    for (int i = 0, size = ints.length; i < size; i++)
    {
      List.add((byte) ints[i]);
    }
    return List;
  }

  @SuppressWarnings({ "boxing", "unchecked" })
  @Test
  public final void testByteIterableToArrayListSelector()
  {
    final NumberIterableSelector<Byte, ArrayList<Byte>> numberIterableToArrayListFilter =
      Numbers.byteIterableToArrayListSelector();

    final Condition1<Byte> condition = mock(Condition1.class);
    when(condition.isMet(anyByte())).thenReturn(Boolean.TRUE);

    final List<Byte> expected1 = BYTE_LIST;
    final List<Byte> result1 = numberIterableToArrayListFilter.select(condition, expected1);
    assertEquals(expected1.size(), result1.size());
    assertThat(result1, is(equalTo(expected1)));

    reset(condition);
    when(condition.isMet(anyByte())).thenReturn(Boolean.FALSE);
    final List<Byte> result2 = numberIterableToArrayListFilter.select(condition, expected1);
    final List<Byte> expected2 = asList();
    assertEquals(expected2.size(), result2.size());
    assertThat(result2, is(equalTo(expected2)));

    final List<Byte> byteList =
      asList(Byte.valueOf((byte) -10), Byte.valueOf((byte) -9), Byte.valueOf((byte) -5), Byte.valueOf((byte) -7),
          Byte.valueOf((byte) -3), Byte.valueOf((byte) -1), Byte.valueOf((byte) 0), Byte.valueOf((byte) 1),
          Byte.valueOf((byte) 2), Byte.valueOf((byte) 5), Byte.valueOf((byte) 6), Byte.valueOf((byte) 10),
          Byte.valueOf((byte) 100));
    reset(condition);
    int i = 0;
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);

    final List<Byte> result3 = numberIterableToArrayListFilter.select(condition, byteList);
    final List<Byte> expected3 = byteList.subList(0, 6);
    assertEquals(expected3.size(), result3.size());
    assertThat(result3, is(equalTo(expected3)));

    reset(condition);
    i = 0;
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);

    final List<Byte> result4 = numberIterableToArrayListFilter.select(condition, byteList);
    final List<Byte> expected4 = byteList.subList(6, 13);
    assertEquals(expected4.size(), result4.size());
    assertThat(result4, is(equalTo(expected4)));
  }

  @SuppressWarnings("boxing")
  private static List<Short> toShortList(final int... ints)
  {
    final List<Short> list = new ArrayList<Short>(ints.length);
    for (int i = 0, size = ints.length; i < size; i++)
    {
      list.add((short) ints[i]);
    }
    return list;
  }

  @SuppressWarnings({ "boxing", "unchecked" })
  @Test
  public final void testShortIterableToArrayListSelector()
  {
    final NumberIterableSelector<Short, ArrayList<Short>> numberIterableToArrayListFilter =
      Numbers.shortIterableToArrayListSelector();

    final Condition1<Short> condition = mock(Condition1.class);
    when(condition.isMet(anyShort())).thenReturn(Boolean.TRUE);

    final List<Short> expected1 = SHORT_LIST;
    final List<Short> result1 = numberIterableToArrayListFilter.select(condition, expected1);
    assertEquals(expected1.size(), result1.size());
    assertThat(result1, is(equalTo(expected1)));

    reset(condition);
    when(condition.isMet(anyShort())).thenReturn(Boolean.FALSE);
    final List<Short> result2 = numberIterableToArrayListFilter.select(condition, expected1);
    final List<Short> expected2 = asList();
    assertEquals(expected2.size(), result2.size());
    assertThat(result2, is(equalTo(expected2)));

    final List<Short> shortList =
      asList(Short.valueOf((short) -10), Short.valueOf((short) -9), Short.valueOf((short) -5),
          Short.valueOf((short) -7), Short.valueOf((short) -3), Short.valueOf((short) -1), Short.valueOf((short) 0),
          Short.valueOf((short) 1), Short.valueOf((short) 2), Short.valueOf((short) 5), Short.valueOf((short) 6),
          Short.valueOf((short) 10), Short.valueOf((short) 100));
    reset(condition);
    int i = 0;
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);

    final List<Short> result3 = numberIterableToArrayListFilter.select(condition, shortList);
    final List<Short> expected3 = shortList.subList(0, 6);
    assertEquals(expected3.size(), result3.size());
    assertThat(result3, is(equalTo(expected3)));

    reset(condition);
    i = 0;
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);

    final List<Short> result4 = numberIterableToArrayListFilter.select(condition, shortList);
    final List<Short> expected4 = shortList.subList(6, 13);
    assertEquals(expected4.size(), result4.size());
    assertThat(result4, is(equalTo(expected4)));
  }

  @SuppressWarnings({ "boxing", "unchecked" })
  @Test
  public final void testIntegerIterableToArrayListSelector()
  {
    final NumberIterableSelector<Integer, ArrayList<Integer>> numberIterableToArrayListFilter =
      Numbers.integerIterableToArrayListSelector();

    final Condition1<Integer> condition = mock(Condition1.class);
    when(condition.isMet(anyInt())).thenReturn(Boolean.TRUE);

    final List<Integer> expected1 = INTEGER_LIST;
    final List<Integer> result1 = numberIterableToArrayListFilter.select(condition, expected1);
    assertEquals(expected1.size(), result1.size());
    assertThat(result1, is(equalTo(expected1)));

    reset(condition);
    when(condition.isMet(anyInt())).thenReturn(Boolean.FALSE);
    final List<Integer> result2 = numberIterableToArrayListFilter.select(condition, expected1);
    final List<Integer> expected2 = asList();
    assertEquals(expected2.size(), result2.size());
    assertThat(result2, is(equalTo(expected2)));

    final List<Integer> integerList =
      asList(Integer.valueOf(-10), Integer.valueOf(-9), Integer.valueOf(-5), Integer.valueOf(-7), Integer.valueOf(-3),
          Integer.valueOf(-1), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(5),
          Integer.valueOf(6), Integer.valueOf(10), Integer.valueOf(100));
    reset(condition);
    int i = 0;
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);

    final List<Integer> result3 = numberIterableToArrayListFilter.select(condition, integerList);
    final List<Integer> expected3 = integerList.subList(0, 6);
    assertEquals(expected3.size(), result3.size());
    assertThat(result3, is(equalTo(expected3)));

    reset(condition);
    i = 0;
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
    when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);

    final List<Integer> result4 = numberIterableToArrayListFilter.select(condition, integerList);
    final List<Integer> expected4 = integerList.subList(6, 13);
    assertEquals(expected4.size(), result4.size());
    assertThat(result4, is(equalTo(expected4)));
  }

  @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public final void testLongIterableToArrayListSelector()
    {
      final NumberIterableSelector<Long, ArrayList<Long>> numberIterableToArrayListFilter =
        Numbers.longIterableToArrayListSelector();
  
      final Condition1<Long> condition = mock(Condition1.class);
      when(condition.isMet(anyLong())).thenReturn(Boolean.TRUE);
  
      final List<Long> expected1 = LONG_LIST;
      final List<Long> result1 = numberIterableToArrayListFilter.select(condition, expected1);
      assertEquals(expected1.size(), result1.size());
      assertThat(result1, is(equalTo(expected1)));
  
      reset(condition);
      when(condition.isMet(anyLong())).thenReturn(Boolean.FALSE);
      final List<Long> result2 = numberIterableToArrayListFilter.select(condition, expected1);
      final List<Long> expected2 = asList();
      assertEquals(expected2.size(), result2.size());
      assertThat(result2, is(equalTo(expected2)));
  
      final List<Long> longList =
        asList(Long.valueOf(-10), Long.valueOf(-9), Long.valueOf(-5), Long.valueOf(-7), Long.valueOf(-3),
            Long.valueOf(-1), Long.valueOf(0), Long.valueOf(1), Long.valueOf(2), Long.valueOf(5), Long.valueOf(6),
            Long.valueOf(10), Long.valueOf(100));
      reset(condition);
      int i = 0;
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
  
      final List<Long> result3 = numberIterableToArrayListFilter.select(condition, longList);
      final List<Long> expected3 = longList.subList(0, 6);
      assertEquals(expected3.size(), result3.size());
      assertThat(result3, is(equalTo(expected3)));
  
      reset(condition);
      i = 0;
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
  
      final List<Long> result4 = numberIterableToArrayListFilter.select(condition, longList);
      final List<Long> expected4 = longList.subList(6, 13);
      assertEquals(expected4.size(), result4.size());
      assertThat(result4, is(equalTo(expected4)));
    }

  @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public final void testFloatIterableToArrayListSelector()
    {
      final NumberIterableSelector<Float, ArrayList<Float>> numberIterableToArrayListFilter =
        Numbers.floatIterableToArrayListSelector();
  
      final Condition1<Float> condition = mock(Condition1.class);
      when(condition.isMet(anyFloat())).thenReturn(Boolean.TRUE);
  
      final List<Float> expected1 = FLOAT_LIST;
      final List<Float> result1 = numberIterableToArrayListFilter.select(condition, expected1);
      assertEquals(expected1.size(), result1.size());
      assertThat(result1, is(equalTo(expected1)));
  
      reset(condition);
      when(condition.isMet(anyFloat())).thenReturn(Boolean.FALSE);
      final List<Float> result2 = numberIterableToArrayListFilter.select(condition, expected1);
      final List<Float> expected2 = asList();
      assertEquals(expected2.size(), result2.size());
      assertThat(result2, is(equalTo(expected2)));
  
      final List<Float> floatList =
        asList(-10F, -9.5F, -5.3F, -7.7F, -3.9F, -1.0F, 0F, 1.0F, 2.3F, 5.5F, 6.8F, 10.99F, 100F);
      reset(condition);
      int i = 0;
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
  
      final List<Float> result3 = numberIterableToArrayListFilter.select(condition, floatList);
      final List<Float> expected3 = floatList.subList(0, 6);
      assertEquals(expected3.size(), result3.size());
      assertThat(result3, is(equalTo(expected3)));
  
      reset(condition);
      i = 0;
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
  
      final List<Float> result4 = numberIterableToArrayListFilter.select(condition, floatList);
      final List<Float> expected4 = floatList.subList(6, 13);
      assertEquals(expected4.size(), result4.size());
      assertThat(result4, is(equalTo(expected4)));
    }

  @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public final void testDoubleIterableToArrayListSelector()
    {
      final NumberIterableSelector<Double, ArrayList<Double>> numberIterableToArrayListFilter =
        Numbers.doubleIterableToArrayListSelector();
  
      final Condition1<Double> condition = mock(Condition1.class);
      when(condition.isMet(anyDouble())).thenReturn(Boolean.TRUE);
  
      final List<Double> expected1 = DOUBLE_LIST;
      final List<Double> result1 = numberIterableToArrayListFilter.select(condition, expected1);
      assertEquals(expected1.size(), result1.size());
      assertThat(result1, is(equalTo(expected1)));
  
      reset(condition);
      when(condition.isMet(anyDouble())).thenReturn(Boolean.FALSE);
      final List<Double> result2 = numberIterableToArrayListFilter.select(condition, expected1);
      final List<Double> expected2 = asList();
      assertEquals(expected2.size(), result2.size());
      assertThat(result2, is(equalTo(expected2)));
  
      final List<Double> doubleList =
        asList(-10D, -9.5D, -5.3D, -7.7D, -3.9D, -1.0D, 0D, 1.0D, 2.3D, 5.5D, 6.8D, 10.99D, 100D);
      reset(condition);
      int i = 0;
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
  
      final List<Double> result3 = numberIterableToArrayListFilter.select(condition, doubleList);
      final List<Double> expected3 = doubleList.subList(0, 6);
      assertEquals(expected3.size(), result3.size());
      assertThat(result3, is(equalTo(expected3)));
  
      reset(condition);
      i = 0;
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
  
      final List<Double> result4 = numberIterableToArrayListFilter.select(condition, doubleList);
      final List<Double> expected4 = doubleList.subList(6, 13);
      assertEquals(expected4.size(), result4.size());
      assertThat(result4, is(equalTo(expected4)));
    }

  @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public final void testBigIntegerIterableToArrayListSelector()
    {
      final NumberIterableSelector<BigInteger, ArrayList<BigInteger>> numberIterableToArrayListFilter =
        Numbers.bigIntegerIterableToArrayListSelector();
  
      final Condition1<BigInteger> condition = mock(Condition1.class);
      when(condition.isMet(Matchers.any(BigInteger.class))).thenReturn(Boolean.TRUE);
  
      final List<BigInteger> expected1 = BIG_INTEGER_LIST;
      final List<BigInteger> result1 = numberIterableToArrayListFilter.select(condition, expected1);
      assertEquals(expected1.size(), result1.size());
      assertThat(result1, is(equalTo(expected1)));
  
      reset(condition);
      when(condition.isMet(Matchers.any(BigInteger.class))).thenReturn(Boolean.FALSE);
      final List<BigInteger> result2 = numberIterableToArrayListFilter.select(condition, expected1);
      final List<BigInteger> expected2 = asList();
      assertEquals(expected2.size(), result2.size());
      assertThat(result2, is(equalTo(expected2)));
  
      final List<BigInteger> bigIntegerList =
        asList(BigInteger.valueOf(-10), BigInteger.valueOf(-9), BigInteger.valueOf(-5), BigInteger.valueOf(-7),
            BigInteger.valueOf(-3), BigInteger.valueOf(-1), BigInteger.valueOf(0), BigInteger.valueOf(1),
            BigInteger.valueOf(2), BigInteger.valueOf(5), BigInteger.valueOf(6), BigInteger.valueOf(10),
            BigInteger.valueOf(100));
      reset(condition);
      int i = 0;
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
  
      final List<BigInteger> result3 = numberIterableToArrayListFilter.select(condition, bigIntegerList);
      final List<BigInteger> expected3 = bigIntegerList.subList(0, 6);
      assertEquals(expected3.size(), result3.size());
      assertThat(result3, is(equalTo(expected3)));
  
      reset(condition);
      i = 0;
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
  
      final List<BigInteger> result4 = numberIterableToArrayListFilter.select(condition, bigIntegerList);
      final List<BigInteger> expected4 = bigIntegerList.subList(6, 13);
      assertEquals(expected4.size(), result4.size());
      assertThat(result4, is(equalTo(expected4)));
    }

  @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public final void testBigDecimalIterableToArrayListSelector()
    {
      final NumberIterableSelector<BigDecimal, ArrayList<BigDecimal>> numberIterableToArrayListFilter =
        Numbers.bigDecimalIterableToArrayListSelector();
  
      final Condition1<BigDecimal> condition = mock(Condition1.class);
      when(condition.isMet(Matchers.any(BigDecimal.class))).thenReturn(Boolean.TRUE);
  
      final List<BigDecimal> expected1 = BIG_DECIMAL_LIST;
      final List<BigDecimal> result1 = numberIterableToArrayListFilter.select(condition, expected1);
      assertEquals(expected1.size(), result1.size());
      assertThat(result1, is(equalTo(expected1)));
  
      reset(condition);
      when(condition.isMet(Matchers.any(BigDecimal.class))).thenReturn(Boolean.FALSE);
      final List<BigDecimal> result2 = numberIterableToArrayListFilter.select(condition, expected1);
      final List<BigDecimal> expected2 = asList();
      assertEquals(expected2.size(), result2.size());
      assertThat(result2, is(equalTo(expected2)));
  
      final List<BigDecimal> bigDecimalList =
        asList(new BigDecimal("-10"), new BigDecimal("-9.5"), new BigDecimal("-5.3"), new BigDecimal("-7.7"),
            new BigDecimal("-3.9"), new BigDecimal("-1.0"), new BigDecimal("0"), new BigDecimal("1.0"), new BigDecimal(
                "2.3"), new BigDecimal("5.5"), new BigDecimal("6.8"), new BigDecimal("10.99"), new BigDecimal("100"));
      reset(condition);
      int i = 0;
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
  
      final List<BigDecimal> result3 = numberIterableToArrayListFilter.select(condition, bigDecimalList);
      final List<BigDecimal> expected3 = bigDecimalList.subList(0, 6);
      assertEquals(expected3.size(), result3.size());
      assertThat(result3, is(equalTo(expected3)));
  
      reset(condition);
      i = 0;
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
  
      final List<BigDecimal> result4 = numberIterableToArrayListFilter.select(condition, bigDecimalList);
      final List<BigDecimal> expected4 = bigDecimalList.subList(6, 13);
      assertEquals(expected4.size(), result4.size());
      assertThat(result4, is(equalTo(expected4)));
    }

  @Test
    public final void testNumberIterableToHashSetSelector()
    {
      final NumberIterableSelector<Byte, HashSet<Byte>> byteIterableToHashSetFilter =
        Numbers.numberIterableToHashSetSelector();
      assertThat(byteIterableToHashSetFilter, is(notNullValue()));
  
      final NumberIterableSelector<Short, HashSet<Short>> shortIterableToHashSetFilter =
        Numbers.numberIterableToHashSetSelector();
      assertThat(shortIterableToHashSetFilter, is(notNullValue()));
  
      final NumberIterableSelector<Integer, HashSet<Integer>> integerIterableToHashSetFilter =
        Numbers.numberIterableToHashSetSelector();
      assertThat(integerIterableToHashSetFilter, is(notNullValue()));
  
      final NumberIterableSelector<Long, HashSet<Long>> longIterableToHashSetFilter =
        Numbers.numberIterableToHashSetSelector();
      assertThat(longIterableToHashSetFilter, is(notNullValue()));
  
      final NumberIterableSelector<Float, HashSet<Float>> floatIterableToHashSetFilter =
        Numbers.numberIterableToHashSetSelector();
      assertThat(floatIterableToHashSetFilter, is(notNullValue()));
  
      final NumberIterableSelector<Double, HashSet<Double>> doubleIterableToHashSetFilter =
        Numbers.numberIterableToHashSetSelector();
      assertThat(doubleIterableToHashSetFilter, is(notNullValue()));
  
      final NumberIterableSelector<BigInteger, HashSet<BigInteger>> bigIntegerIterableToHashSetFilter =
        Numbers.numberIterableToHashSetSelector();
      assertThat(bigIntegerIterableToHashSetFilter, is(notNullValue()));
  
      final NumberIterableSelector<BigDecimal, HashSet<BigDecimal>> bigDecimalIterableToHashSetFilter =
        Numbers.numberIterableToHashSetSelector();
      assertThat(bigDecimalIterableToHashSetFilter, is(notNullValue()));
    }

  @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public final void testByteIterableToHashSetSelector()
    {
      final NumberIterableSelector<Byte, HashSet<Byte>> numberIterableToHashSetFilter =
        Numbers.byteIterableToHashSetSelector();
  
      final Condition1<Byte> condition = mock(Condition1.class);
      when(condition.isMet(anyByte())).thenReturn(Boolean.TRUE);
  
      final Set<Byte> expected1 = new HashSet<Byte>(BYTE_LIST);
  
      final Set<Byte> result1 = numberIterableToHashSetFilter.select(condition, expected1);
      assertEquals(expected1.size(), result1.size());
      assertThat(result1, is(equalTo(expected1)));
  
      reset(condition);
      when(condition.isMet(anyByte())).thenReturn(Boolean.FALSE);
      final Set<Byte> result2 = numberIterableToHashSetFilter.select(condition, expected1);
      final Set<Byte> expected2 = new HashSet<Byte>();
      assertEquals(expected2.size(), result2.size());
      assertThat(result2, is(equalTo(expected2)));
  
      final List<Byte> byteList =
        asList(Byte.valueOf((byte) -10), Byte.valueOf((byte) -9), Byte.valueOf((byte) -5), Byte.valueOf((byte) -7),
            Byte.valueOf((byte) -3), Byte.valueOf((byte) -1), Byte.valueOf((byte) 0), Byte.valueOf((byte) 1),
            Byte.valueOf((byte) 2), Byte.valueOf((byte) 5), Byte.valueOf((byte) 6), Byte.valueOf((byte) 10),
            Byte.valueOf((byte) 100));
      reset(condition);
      int i = 0;
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
  
      final Set<Byte> result3 = numberIterableToHashSetFilter.select(condition, byteList);
      final Set<Byte> expected3 = new HashSet<Byte>(byteList.subList(0, 6));
      assertEquals(expected3.size(), result3.size());
      assertThat(result3, is(equalTo(expected3)));
  
      reset(condition);
      i = 0;
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
  
      final Set<Byte> result4 = numberIterableToHashSetFilter.select(condition, byteList);
      final Set<Byte> expected4 = new HashSet<Byte>(byteList.subList(6, 13));
      assertEquals(expected4.size(), result4.size());
      assertThat(result4, is(equalTo(expected4)));
    }

  @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public final void testShortIterableToHashSetSelector()
    {
      final NumberIterableSelector<Short, HashSet<Short>> numberIterableToHashSetFilter =
        Numbers.shortIterableToHashSetSelector();
  
      final Condition1<Short> condition = mock(Condition1.class);
      when(condition.isMet(anyShort())).thenReturn(Boolean.TRUE);
  
      final Set<Short> expected1 = new HashSet<Short>(SHORT_LIST);
  
      final Set<Short> result1 = numberIterableToHashSetFilter.select(condition, expected1);
      assertEquals(expected1.size(), result1.size());
      assertThat(result1, is(equalTo(expected1)));
  
      reset(condition);
      when(condition.isMet(anyShort())).thenReturn(Boolean.FALSE);
      final Set<Short> result2 = numberIterableToHashSetFilter.select(condition, expected1);
      final Set<Short> expected2 = new HashSet<Short>();
      assertEquals(expected2.size(), result2.size());
      assertThat(result2, is(equalTo(expected2)));
  
      final List<Short> shortList =
        asList(Short.valueOf((short) -10), Short.valueOf((short) -9), Short.valueOf((short) -5),
            Short.valueOf((short) -7), Short.valueOf((short) -3), Short.valueOf((short) -1), Short.valueOf((short) 0),
            Short.valueOf((short) 1), Short.valueOf((short) 2), Short.valueOf((short) 5), Short.valueOf((short) 6),
            Short.valueOf((short) 10), Short.valueOf((short) 100));
      reset(condition);
      int i = 0;
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
  
      final Set<Short> result3 = numberIterableToHashSetFilter.select(condition, shortList);
      final Set<Short> expected3 = new HashSet<Short>(shortList.subList(0, 6));
      assertEquals(expected3.size(), result3.size());
      assertThat(result3, is(equalTo(expected3)));
  
      reset(condition);
      i = 0;
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
  
      final Set<Short> result4 = numberIterableToHashSetFilter.select(condition, shortList);
      final Set<Short> expected4 = new HashSet<Short>(shortList.subList(6, 13));
      assertEquals(expected4.size(), result4.size());
      assertThat(result4, is(equalTo(expected4)));
    }

  @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public final void testIntegerIterableToHashSetSelector()
    {
      final NumberIterableSelector<Integer, HashSet<Integer>> numberIterableToHashSetFilter =
        Numbers.integerIterableToHashSetSelector();
  
      final Condition1<Integer> condition = mock(Condition1.class);
      when(condition.isMet(anyInt())).thenReturn(Boolean.TRUE);
  
      final Set<Integer> expected1 = new HashSet<Integer>(INTEGER_LIST);
      final Set<Integer> result1 = numberIterableToHashSetFilter.select(condition, expected1);
      assertEquals(expected1.size(), result1.size());
      assertThat(result1, is(equalTo(expected1)));
  
      reset(condition);
      when(condition.isMet(anyInt())).thenReturn(Boolean.FALSE);
      final Set<Integer> result2 = numberIterableToHashSetFilter.select(condition, expected1);
      final Set<Integer> expected2 = new HashSet<Integer>();
      assertEquals(expected2.size(), result2.size());
      assertThat(result2, is(equalTo(expected2)));
  
      final List<Integer> integerList =
        asList(Integer.valueOf(-10), Integer.valueOf(-9), Integer.valueOf(-5), Integer.valueOf(-7), Integer.valueOf(-3),
            Integer.valueOf(-1), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(5),
            Integer.valueOf(6), Integer.valueOf(10), Integer.valueOf(100));
      reset(condition);
      int i = 0;
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
  
      final Set<Integer> result3 = numberIterableToHashSetFilter.select(condition, integerList);
      final Set<Integer> expected3 = new HashSet<Integer>(integerList.subList(0, 6));
      assertEquals(expected3.size(), result3.size());
      assertThat(result3, is(equalTo(expected3)));
  
      reset(condition);
      i = 0;
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
  
      final Set<Integer> result4 = numberIterableToHashSetFilter.select(condition, integerList);
      final Set<Integer> expected4 = new HashSet<Integer>(integerList.subList(6, 13));
      assertEquals(expected4.size(), result4.size());
      assertThat(result4, is(equalTo(expected4)));
    }

  @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public final void testLongIterableToHashSetSelector()
    {
      final NumberIterableSelector<Long, HashSet<Long>> numberIterableToHashSetFilter =
        Numbers.longIterableToHashSetSelector();
  
      final Condition1<Long> condition = mock(Condition1.class);
      when(condition.isMet(anyLong())).thenReturn(Boolean.TRUE);
  
      final Set<Long> expected1 = new HashSet<Long>(LONG_LIST);
      final Set<Long> result1 = numberIterableToHashSetFilter.select(condition, expected1);
      assertEquals(expected1.size(), result1.size());
      assertThat(result1, is(equalTo(expected1)));
  
      reset(condition);
      when(condition.isMet(anyLong())).thenReturn(Boolean.FALSE);
      final Set<Long> result2 = numberIterableToHashSetFilter.select(condition, expected1);
      final Set<Long> expected2 = new HashSet<Long>();
      assertEquals(expected2.size(), result2.size());
      assertThat(result2, is(equalTo(expected2)));
  
      final List<Long> longList =
        asList(Long.valueOf(-10), Long.valueOf(-9), Long.valueOf(-5), Long.valueOf(-7), Long.valueOf(-3),
            Long.valueOf(-1), Long.valueOf(0), Long.valueOf(1), Long.valueOf(2), Long.valueOf(5), Long.valueOf(6),
            Long.valueOf(10), Long.valueOf(100));
      reset(condition);
      int i = 0;
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
  
      final Set<Long> result3 = numberIterableToHashSetFilter.select(condition, longList);
      final Set<Long> expected3 = new HashSet<Long>(longList.subList(0, 6));
      assertEquals(expected3.size(), result3.size());
      assertThat(result3, is(equalTo(expected3)));
  
      reset(condition);
      i = 0;
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
  
      final Set<Long> result4 = numberIterableToHashSetFilter.select(condition, longList);
      final Set<Long> expected4 = new HashSet<Long>(longList.subList(6, 13));
      assertEquals(expected4.size(), result4.size());
      assertThat(result4, is(equalTo(expected4)));
    }

  @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public final void testFloatIterableToHashSetSelector()
    {
      final NumberIterableSelector<Float, HashSet<Float>> numberIterableToHashSetFilter =
        Numbers.floatIterableToHashSetSelector();
  
      final Condition1<Float> condition = mock(Condition1.class);
      when(condition.isMet(anyFloat())).thenReturn(Boolean.TRUE);
  
      final Set<Float> expected1 = new HashSet<Float>(FLOAT_LIST);
      final Set<Float> result1 = numberIterableToHashSetFilter.select(condition, expected1);
      assertEquals(expected1.size(), result1.size());
      assertThat(result1, is(equalTo(expected1)));
  
      reset(condition);
      when(condition.isMet(anyFloat())).thenReturn(Boolean.FALSE);
      final Set<Float> result2 = numberIterableToHashSetFilter.select(condition, expected1);
      final Set<Float> expected2 = new HashSet<Float>();
      assertEquals(expected2.size(), result2.size());
      assertThat(result2, is(equalTo(expected2)));
  
      final List<Float> floatList =
        asList(-10F, -9.5F, -5.3F, -7.7F, -3.9F, -1.0F, 0F, 1.0F, 2.3F, 5.5F, 6.8F, 10.99F, 100F);
      reset(condition);
      int i = 0;
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
  
      final Set<Float> result3 = numberIterableToHashSetFilter.select(condition, floatList);
      final Set<Float> expected3 = new HashSet<Float>(floatList.subList(0, 6));
      assertEquals(expected3.size(), result3.size());
      assertThat(result3, is(equalTo(expected3)));
  
      reset(condition);
      i = 0;
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
  
      final Set<Float> result4 = numberIterableToHashSetFilter.select(condition, floatList);
      final Set<Float> expected4 = new HashSet<Float>(floatList.subList(6, 13));
      assertEquals(expected4.size(), result4.size());
      assertThat(result4, is(equalTo(expected4)));
    }

  @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public final void testDoubleIterableToHashSetSelector()
    {
      final NumberIterableSelector<Double, HashSet<Double>> numberIterableToHashSetFilter =
        Numbers.doubleIterableToHashSetSelector();
  
      final Condition1<Double> condition = mock(Condition1.class);
      when(condition.isMet(anyDouble())).thenReturn(Boolean.TRUE);
  
      final Set<Double> expected1 = new HashSet<Double>(DOUBLE_LIST);
      final Set<Double> result1 = numberIterableToHashSetFilter.select(condition, expected1);
      assertEquals(expected1.size(), result1.size());
      assertThat(result1, is(equalTo(expected1)));
  
      reset(condition);
      when(condition.isMet(anyDouble())).thenReturn(Boolean.FALSE);
      final Set<Double> result2 = numberIterableToHashSetFilter.select(condition, expected1);
      final Set<Double> expected2 = new HashSet<Double>();
      assertEquals(expected2.size(), result2.size());
      assertThat(result2, is(equalTo(expected2)));
  
      final List<Double> doubleList =
        asList(-10D, -9.5D, -5.3D, -7.7D, -3.9D, -1.0D, 0D, 1.0D, 2.3D, 5.5D, 6.8D, 10.99D, 100D);
      reset(condition);
      int i = 0;
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
  
      final Set<Double> result3 = numberIterableToHashSetFilter.select(condition, doubleList);
      final Set<Double> expected3 = new HashSet<Double>(doubleList.subList(0, 6));
      assertEquals(expected3.size(), result3.size());
      assertThat(result3, is(equalTo(expected3)));
  
      reset(condition);
      i = 0;
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
  
      final Set<Double> result4 = numberIterableToHashSetFilter.select(condition, doubleList);
      final Set<Double> expected4 = new HashSet<Double>(doubleList.subList(6, 13));
      assertEquals(expected4.size(), result4.size());
      assertThat(result4, is(equalTo(expected4)));
    }

  @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public final void testBigIntegerIterableToHashSetSelector()
    {
      final NumberIterableSelector<BigInteger, HashSet<BigInteger>> numberIterableToHashSetFilter =
        Numbers.bigIntegerIterableToHashSetSelector();
  
      final Condition1<BigInteger> condition = mock(Condition1.class);
      when(condition.isMet(Matchers.any(BigInteger.class))).thenReturn(Boolean.TRUE);
  
      final Set<BigInteger> expected1 = new HashSet<BigInteger>(BIG_INTEGER_LIST);
      final Set<BigInteger> result1 = numberIterableToHashSetFilter.select(condition, expected1);
      assertEquals(expected1.size(), result1.size());
      assertThat(result1, is(equalTo(expected1)));
  
      reset(condition);
      when(condition.isMet(Matchers.any(BigInteger.class))).thenReturn(Boolean.FALSE);
      final Set<BigInteger> result2 = numberIterableToHashSetFilter.select(condition, expected1);
      final Set<BigInteger> expected2 = new HashSet<BigInteger>();
      assertEquals(expected2.size(), result2.size());
      assertThat(result2, is(equalTo(expected2)));
  
      final List<BigInteger> bigIntegerList =
        asList(BigInteger.valueOf(-10), BigInteger.valueOf(-9), BigInteger.valueOf(-5), BigInteger.valueOf(-7),
            BigInteger.valueOf(-3), BigInteger.valueOf(-1), BigInteger.valueOf(0), BigInteger.valueOf(1),
            BigInteger.valueOf(2), BigInteger.valueOf(5), BigInteger.valueOf(6), BigInteger.valueOf(10),
            BigInteger.valueOf(100));
      reset(condition);
      int i = 0;
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
  
      final Set<BigInteger> result3 = numberIterableToHashSetFilter.select(condition, bigIntegerList);
      final Set<BigInteger> expected3 = new HashSet<BigInteger>(bigIntegerList.subList(0, 6));
      assertEquals(expected3.size(), result3.size());
      assertThat(result3, is(equalTo(expected3)));
  
      reset(condition);
      i = 0;
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
  
      final Set<BigInteger> result4 = numberIterableToHashSetFilter.select(condition, bigIntegerList);
      final Set<BigInteger> expected4 = new HashSet<BigInteger>(bigIntegerList.subList(6, 13));
      assertEquals(expected4.size(), result4.size());
      assertThat(result4, is(equalTo(expected4)));
    }

  @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public final void testBigDecimalIterableToHashSetSelector()
    {
      final NumberIterableSelector<BigDecimal, HashSet<BigDecimal>> numberIterableToHashSetFilter =
        Numbers.bigDecimalIterableToHashSetSelector();
  
      final Condition1<BigDecimal> condition = mock(Condition1.class);
      when(condition.isMet(Matchers.any(BigDecimal.class))).thenReturn(Boolean.TRUE);
  
      final Set<BigDecimal> expected1 = new HashSet<BigDecimal>(BIG_DECIMAL_LIST);
      final Set<BigDecimal> result1 = numberIterableToHashSetFilter.select(condition, expected1);
      assertEquals(expected1.size(), result1.size());
      assertThat(result1, is(equalTo(expected1)));
  
      reset(condition);
      when(condition.isMet(Matchers.any(BigDecimal.class))).thenReturn(Boolean.FALSE);
      final Set<BigDecimal> result2 = numberIterableToHashSetFilter.select(condition, expected1);
      final Set<BigDecimal> expected2 = new HashSet<BigDecimal>();
      assertEquals(expected2.size(), result2.size());
      assertThat(result2, is(equalTo(expected2)));
  
      final List<BigDecimal> bigDecimalList =
        asList(new BigDecimal("-10"), new BigDecimal("-9.5"), new BigDecimal("-5.3"), new BigDecimal("-7.7"),
            new BigDecimal("-3.9"), new BigDecimal("-1.0"), new BigDecimal("0"), new BigDecimal("1.0"), new BigDecimal(
                "2.3"), new BigDecimal("5.5"), new BigDecimal("6.8"), new BigDecimal("10.99"), new BigDecimal("100"));
      reset(condition);
      int i = 0;
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
  
      final Set<BigDecimal> result3 = numberIterableToHashSetFilter.select(condition, bigDecimalList);
      final Set<BigDecimal> expected3 = new HashSet<BigDecimal>(bigDecimalList.subList(0, 6));
      assertEquals(expected3.size(), result3.size());
      assertThat(result3, is(equalTo(expected3)));
  
      reset(condition);
      i = 0;
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
  
      final Set<BigDecimal> result4 = numberIterableToHashSetFilter.select(condition, bigDecimalList);
      final Set<BigDecimal> expected4 = new HashSet<BigDecimal>(bigDecimalList.subList(6, 13));
      assertEquals(expected4.size(), result4.size());
      assertThat(result4, is(equalTo(expected4)));
    }

  @Test
    public final void testNumberIterableToLinkedHashSetSelector()
    {
      final NumberIterableSelector<Byte, LinkedHashSet<Byte>> byteIterableToLinkedHashSetFilter =
        Numbers.numberIterableToLinkedHashSetSelector();
      assertThat(byteIterableToLinkedHashSetFilter, is(notNullValue()));
  
      final NumberIterableSelector<Short, LinkedHashSet<Short>> shortIterableToLinkedHashSetFilter =
        Numbers.numberIterableToLinkedHashSetSelector();
      assertThat(shortIterableToLinkedHashSetFilter, is(notNullValue()));
  
      final NumberIterableSelector<Integer, LinkedHashSet<Integer>> integerIterableToLinkedHashSetFilter =
        Numbers.numberIterableToLinkedHashSetSelector();
      assertThat(integerIterableToLinkedHashSetFilter, is(notNullValue()));
  
      final NumberIterableSelector<Long, LinkedHashSet<Long>> longIterableToLinkedHashSetFilter =
        Numbers.numberIterableToLinkedHashSetSelector();
      assertThat(longIterableToLinkedHashSetFilter, is(notNullValue()));
  
      final NumberIterableSelector<Float, LinkedHashSet<Float>> floatIterableToLinkedHashSetFilter =
        Numbers.numberIterableToLinkedHashSetSelector();
      assertThat(floatIterableToLinkedHashSetFilter, is(notNullValue()));
  
      final NumberIterableSelector<Double, LinkedHashSet<Double>> doubleIterableToLinkedHashSetFilter =
        Numbers.numberIterableToLinkedHashSetSelector();
      assertThat(doubleIterableToLinkedHashSetFilter, is(notNullValue()));
  
      final NumberIterableSelector<BigInteger, LinkedHashSet<BigInteger>> bigIntegerIterableToLinkedHashSetFilter =
        Numbers.numberIterableToLinkedHashSetSelector();
      assertThat(bigIntegerIterableToLinkedHashSetFilter, is(notNullValue()));
  
      final NumberIterableSelector<BigDecimal, LinkedHashSet<BigDecimal>> bigDecimalIterableToLinkedHashSetFilter =
        Numbers.numberIterableToLinkedHashSetSelector();
      assertThat(bigDecimalIterableToLinkedHashSetFilter, is(notNullValue()));
    }

  @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public final void testByteIterableToLinkedHashSetSelector()
    {
      final NumberIterableSelector<Byte, LinkedHashSet<Byte>> numberIterableToLinkedHashSetFilter =
        Numbers.byteIterableToLinkedHashSetSelector();
  
      final Condition1<Byte> condition = mock(Condition1.class);
      when(condition.isMet(anyByte())).thenReturn(Boolean.TRUE);
  
      final Set<Byte> expected1 = new LinkedHashSet<Byte>(BYTE_LIST);
  
      final Set<Byte> result1 = numberIterableToLinkedHashSetFilter.select(condition, expected1);
      assertEquals(expected1.size(), result1.size());
      assertThat(result1, is(equalTo(expected1)));
      final Iterator<Byte> expectedIterator1 = expected1.iterator();
      for (final Byte each : result1)
      {
        assertThat(each, is(equalTo(expectedIterator1.next())));
      }
  
      reset(condition);
      when(condition.isMet(anyByte())).thenReturn(Boolean.FALSE);
      final Set<Byte> result2 = numberIterableToLinkedHashSetFilter.select(condition, expected1);
      final Set<Byte> expected2 = new LinkedHashSet<Byte>();
      assertEquals(expected2.size(), result2.size());
      assertThat(result2, is(equalTo(expected2)));
      final Iterator<Byte> expectedIterator2 = expected2.iterator();
      for (final Byte each : result2)
      {
        assertThat(each, is(equalTo(expectedIterator2.next())));
      }
  
      final List<Byte> byteList =
        asList(Byte.valueOf((byte) -10), Byte.valueOf((byte) -9), Byte.valueOf((byte) -5), Byte.valueOf((byte) -7),
            Byte.valueOf((byte) -3), Byte.valueOf((byte) -1), Byte.valueOf((byte) 0), Byte.valueOf((byte) 1),
            Byte.valueOf((byte) 2), Byte.valueOf((byte) 5), Byte.valueOf((byte) 6), Byte.valueOf((byte) 10),
            Byte.valueOf((byte) 100));
      reset(condition);
      int i = 0;
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
  
      final Set<Byte> result3 = numberIterableToLinkedHashSetFilter.select(condition, byteList);
      final Set<Byte> expected3 = new LinkedHashSet<Byte>(byteList.subList(0, 6));
      assertEquals(expected3.size(), result3.size());
      assertThat(result3, is(equalTo(expected3)));
      final Iterator<Byte> expectedIterator3 = expected3.iterator();
      for (final Byte each : result3)
      {
        assertThat(each, is(equalTo(expectedIterator3.next())));
      }
  
      reset(condition);
      i = 0;
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(byteList.get(i++))).thenReturn(Boolean.TRUE);
  
      final Set<Byte> result4 = numberIterableToLinkedHashSetFilter.select(condition, byteList);
      final Set<Byte> expected4 = new LinkedHashSet<Byte>(byteList.subList(6, 13));
      assertEquals(expected4.size(), result4.size());
      assertThat(result4, is(equalTo(expected4)));
      final Iterator<Byte> expectedIterator4 = expected4.iterator();
      for (final Byte each : result4)
      {
        assertThat(each, is(equalTo(expectedIterator4.next())));
      }
    }

  @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public final void testShortIterableToLinkedHashSetSelector()
    {
      final NumberIterableSelector<Short, LinkedHashSet<Short>> numberIterableToLinkedHashSetFilter =
        Numbers.shortIterableToLinkedHashSetSelector();
  
      final Condition1<Short> condition = mock(Condition1.class);
      when(condition.isMet(anyShort())).thenReturn(Boolean.TRUE);
  
      final Set<Short> expected1 = new LinkedHashSet<Short>(SHORT_LIST);
  
      final Set<Short> result1 = numberIterableToLinkedHashSetFilter.select(condition, expected1);
      assertEquals(expected1.size(), result1.size());
      assertThat(result1, is(equalTo(expected1)));
      final Iterator<Short> expectedIterator1 = expected1.iterator();
      for (final Short each : result1)
      {
        assertThat(each, is(equalTo(expectedIterator1.next())));
      }
  
      reset(condition);
      when(condition.isMet(anyShort())).thenReturn(Boolean.FALSE);
      final Set<Short> result2 = numberIterableToLinkedHashSetFilter.select(condition, expected1);
      final Set<Short> expected2 = new LinkedHashSet<Short>();
      assertEquals(expected2.size(), result2.size());
      assertThat(result2, is(equalTo(expected2)));
      final Iterator<Short> expectedIterator2 = expected2.iterator();
      for (final Short each : result2)
      {
        assertThat(each, is(equalTo(expectedIterator2.next())));
      }
  
      final List<Short> shortList =
        asList(Short.valueOf((short) -10), Short.valueOf((short) -9), Short.valueOf((short) -5),
            Short.valueOf((short) -7), Short.valueOf((short) -3), Short.valueOf((short) -1), Short.valueOf((short) 0),
            Short.valueOf((short) 1), Short.valueOf((short) 2), Short.valueOf((short) 5), Short.valueOf((short) 6),
            Short.valueOf((short) 10), Short.valueOf((short) 100));
      reset(condition);
      int i = 0;
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
  
      final Set<Short> result3 = numberIterableToLinkedHashSetFilter.select(condition, shortList);
      final Set<Short> expected3 = new LinkedHashSet<Short>(shortList.subList(0, 6));
      assertEquals(expected3.size(), result3.size());
      assertThat(result3, is(equalTo(expected3)));
      final Iterator<Short> expectedIterator3 = expected3.iterator();
      for (final Short each : result3)
      {
        assertThat(each, is(equalTo(expectedIterator3.next())));
      }
  
      reset(condition);
      i = 0;
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(shortList.get(i++))).thenReturn(Boolean.TRUE);
  
      final Set<Short> result4 = numberIterableToLinkedHashSetFilter.select(condition, shortList);
      final Set<Short> expected4 = new LinkedHashSet<Short>(shortList.subList(6, 13));
      assertEquals(expected4.size(), result4.size());
      assertThat(result4, is(equalTo(expected4)));
      final Iterator<Short> expectedIterator4 = expected4.iterator();
      for (final Short each : result4)
      {
        assertThat(each, is(equalTo(expectedIterator4.next())));
      }
    }

  @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public final void testIntegerIterableToLinkedHashSetSelector()
    {
      final NumberIterableSelector<Integer, LinkedHashSet<Integer>> numberIterableToLinkedHashSetFilter =
        Numbers.integerIterableToLinkedHashSetSelector();
  
      final Condition1<Integer> condition = mock(Condition1.class);
      when(condition.isMet(anyInt())).thenReturn(Boolean.TRUE);
  
      final Set<Integer> expected1 = new LinkedHashSet<Integer>(INTEGER_LIST);
      final Set<Integer> result1 = numberIterableToLinkedHashSetFilter.select(condition, expected1);
      assertEquals(expected1.size(), result1.size());
      assertThat(result1, is(equalTo(expected1)));
      final Iterator<Integer> iterator1 = expected1.iterator();
      for (final Integer each : result1)
      {
        assertThat(each, is(equalTo(iterator1.next())));
      }
  
      reset(condition);
      when(condition.isMet(anyInt())).thenReturn(Boolean.FALSE);
      final Set<Integer> result2 = numberIterableToLinkedHashSetFilter.select(condition, expected1);
      final Set<Integer> expected2 = new LinkedHashSet<Integer>();
      assertEquals(expected2.size(), result2.size());
      assertThat(result2, is(equalTo(expected2)));
      final Iterator<Integer> iterator2 = expected2.iterator();
      for (final Integer each : result2)
      {
        assertThat(each, is(equalTo(iterator2.next())));
      }
  
      final List<Integer> integerList =
        asList(Integer.valueOf(-10), Integer.valueOf(-9), Integer.valueOf(-5), Integer.valueOf(-7), Integer.valueOf(-3),
            Integer.valueOf(-1), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(5),
            Integer.valueOf(6), Integer.valueOf(10), Integer.valueOf(100));
      reset(condition);
      int i = 0;
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
  
      final Set<Integer> result3 = numberIterableToLinkedHashSetFilter.select(condition, integerList);
      final Set<Integer> expected3 = new LinkedHashSet<Integer>(integerList.subList(0, 6));
      assertEquals(expected3.size(), result3.size());
      assertThat(result3, is(equalTo(expected3)));
      final Iterator<Integer> iterator3 = expected3.iterator();
      for (final Integer each : result3)
      {
        assertThat(each, is(equalTo(iterator3.next())));
      }
  
      reset(condition);
      i = 0;
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(integerList.get(i++))).thenReturn(Boolean.TRUE);
  
      final Set<Integer> result4 = numberIterableToLinkedHashSetFilter.select(condition, integerList);
      final Set<Integer> expected4 = new LinkedHashSet<Integer>(integerList.subList(6, 13));
      assertEquals(expected4.size(), result4.size());
      assertThat(result4, is(equalTo(expected4)));
      final Iterator<Integer> iterator4 = expected4.iterator();
      for (final Integer each : result4)
      {
        assertThat(each, is(equalTo(iterator4.next())));
      }
    }

  @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public final void testLongIterableToLinkedHashSetSelector()
    {
      final NumberIterableSelector<Long, LinkedHashSet<Long>> numberIterableToLinkedHashSetFilter =
        Numbers.longIterableToLinkedHashSetSelector();
  
      final Condition1<Long> condition = mock(Condition1.class);
      when(condition.isMet(anyLong())).thenReturn(Boolean.TRUE);
  
      final Set<Long> expected1 = new LinkedHashSet<Long>(LONG_LIST);
      final Set<Long> result1 = numberIterableToLinkedHashSetFilter.select(condition, expected1);
      assertEquals(expected1.size(), result1.size());
      assertThat(result1, is(equalTo(expected1)));
      final Iterator<Long> iterator1 = expected1.iterator();
      for (final Long each : result1)
      {
        assertThat(each, is(equalTo(iterator1.next())));
      }
  
      reset(condition);
      when(condition.isMet(anyLong())).thenReturn(Boolean.FALSE);
      final Set<Long> result2 = numberIterableToLinkedHashSetFilter.select(condition, expected1);
      final Set<Long> expected2 = new LinkedHashSet<Long>();
      assertEquals(expected2.size(), result2.size());
      assertThat(result2, is(equalTo(expected2)));
      final Iterator<Long> iterator2 = expected2.iterator();
      for (final Long each : result2)
      {
        assertThat(each, is(equalTo(iterator2.next())));
      }
  
      final List<Long> longList =
        asList(Long.valueOf(-10), Long.valueOf(-9), Long.valueOf(-5), Long.valueOf(-7), Long.valueOf(-3),
            Long.valueOf(-1), Long.valueOf(0), Long.valueOf(1), Long.valueOf(2), Long.valueOf(5), Long.valueOf(6),
            Long.valueOf(10), Long.valueOf(100));
      reset(condition);
      int i = 0;
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
  
      final Set<Long> result3 = numberIterableToLinkedHashSetFilter.select(condition, longList);
      final Set<Long> expected3 = new LinkedHashSet<Long>(longList.subList(0, 6));
      assertEquals(expected3.size(), result3.size());
      assertThat(result3, is(equalTo(expected3)));
      final Iterator<Long> iterator3 = expected3.iterator();
      for (final Long each : result3)
      {
        assertThat(each, is(equalTo(iterator3.next())));
      }
  
      reset(condition);
      i = 0;
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(longList.get(i++))).thenReturn(Boolean.TRUE);
  
      final Set<Long> result4 = numberIterableToLinkedHashSetFilter.select(condition, longList);
      final Set<Long> expected4 = new LinkedHashSet<Long>(longList.subList(6, 13));
      assertEquals(expected4.size(), result4.size());
      assertThat(result4, is(equalTo(expected4)));
      final Iterator<Long> iterator4 = expected4.iterator();
      for (final Long each : result4)
      {
        assertThat(each, is(equalTo(iterator4.next())));
      }
    }

  @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public final void testFloatIterableToLinkedHashSetSelector()
    {
      final NumberIterableSelector<Float, LinkedHashSet<Float>> numberIterableToLinkedHashSetFilter =
        Numbers.floatIterableToLinkedHashSetSelector();
  
      final Condition1<Float> condition = mock(Condition1.class);
      when(condition.isMet(anyFloat())).thenReturn(Boolean.TRUE);
  
      final Set<Float> expected1 = new LinkedHashSet<Float>(FLOAT_LIST);
      final Set<Float> result1 = numberIterableToLinkedHashSetFilter.select(condition, expected1);
      assertEquals(expected1.size(), result1.size());
      assertThat(result1, is(equalTo(expected1)));
      final Iterator<Float> iterator1 = expected1.iterator();
      for (final Float each : result1)
      {
        assertThat(each, is(equalTo(iterator1.next())));
      }
  
      reset(condition);
      when(condition.isMet(anyFloat())).thenReturn(Boolean.FALSE);
      final Set<Float> result2 = numberIterableToLinkedHashSetFilter.select(condition, expected1);
      final Set<Float> expected2 = new LinkedHashSet<Float>();
      assertEquals(expected2.size(), result2.size());
      assertThat(result2, is(equalTo(expected2)));
      final Iterator<Float> iterator2 = expected2.iterator();
      for (final Float each : result2)
      {
        assertThat(each, is(equalTo(iterator2.next())));
      }
  
      final List<Float> floatList =
        asList(-10F, -9.5F, -5.3F, -7.7F, -3.9F, -1.0F, 0F, 1.0F, 2.3F, 5.5F, 6.8F, 10.99F, 100F);
      reset(condition);
      int i = 0;
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
  
      final Set<Float> result3 = numberIterableToLinkedHashSetFilter.select(condition, floatList);
      final Set<Float> expected3 = new LinkedHashSet<Float>(floatList.subList(0, 6));
      assertEquals(expected3.size(), result3.size());
      assertThat(result3, is(equalTo(expected3)));
      final Iterator<Float> iterator3 = expected3.iterator();
      for (final Float each : result3)
      {
        assertThat(each, is(equalTo(iterator3.next())));
      }
  
      reset(condition);
      i = 0;
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(floatList.get(i++))).thenReturn(Boolean.TRUE);
  
      final Set<Float> result4 = numberIterableToLinkedHashSetFilter.select(condition, floatList);
      final Set<Float> expected4 = new LinkedHashSet<Float>(floatList.subList(6, 13));
      assertEquals(expected4.size(), result4.size());
      assertThat(result4, is(equalTo(expected4)));
      final Iterator<Float> iterator4 = expected4.iterator();
      for (final Float each : result4)
      {
        assertThat(each, is(equalTo(iterator4.next())));
      }
    }

  @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public final void testDoubleIterableToLinkedHashSetSelector()
    {
      final NumberIterableSelector<Double, LinkedHashSet<Double>> numberIterableToLinkedHashSetFilter =
        Numbers.doubleIterableToLinkedHashSetSelector();
  
      final Condition1<Double> condition = mock(Condition1.class);
      when(condition.isMet(anyDouble())).thenReturn(Boolean.TRUE);
  
      final Set<Double> expected1 = new LinkedHashSet<Double>(DOUBLE_LIST);
      final Set<Double> result1 = numberIterableToLinkedHashSetFilter.select(condition, expected1);
      assertEquals(expected1.size(), result1.size());
      assertThat(result1, is(equalTo(expected1)));
      final Iterator<Double> iterator1 = expected1.iterator();
      for (final Double each : result1)
      {
        assertThat(each, is(equalTo(iterator1.next())));
      }
  
      reset(condition);
      when(condition.isMet(anyDouble())).thenReturn(Boolean.FALSE);
      final Set<Double> result2 = numberIterableToLinkedHashSetFilter.select(condition, expected1);
      final Set<Double> expected2 = new LinkedHashSet<Double>();
      assertEquals(expected2.size(), result2.size());
      assertThat(result2, is(equalTo(expected2)));
      final Iterator<Double> iterator2 = expected2.iterator();
      for (final Double each : result2)
      {
        assertThat(each, is(equalTo(iterator2.next())));
      }
  
      final List<Double> doubleList =
        asList(-10D, -9.5D, -5.3D, -7.7D, -3.9D, -1.0D, 0D, 1.0D, 2.3D, 5.5D, 6.8D, 10.99D, 100D);
      reset(condition);
      int i = 0;
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
  
      final Set<Double> result3 = numberIterableToLinkedHashSetFilter.select(condition, doubleList);
      final Set<Double> expected3 = new LinkedHashSet<Double>(doubleList.subList(0, 6));
      assertEquals(expected3.size(), result3.size());
      assertThat(result3, is(equalTo(expected3)));
      final Iterator<Double> iterator3 = expected3.iterator();
      for (final Double each : result3)
      {
        assertThat(each, is(equalTo(iterator3.next())));
      }
  
      reset(condition);
      i = 0;
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(doubleList.get(i++))).thenReturn(Boolean.TRUE);
  
      final Set<Double> result4 = numberIterableToLinkedHashSetFilter.select(condition, doubleList);
      final Set<Double> expected4 = new LinkedHashSet<Double>(doubleList.subList(6, 13));
      assertEquals(expected4.size(), result4.size());
      assertThat(result4, is(equalTo(expected4)));
      final Iterator<Double> iterator4 = expected4.iterator();
      for (final Double each : result4)
      {
        assertThat(each, is(equalTo(iterator4.next())));
      }
    }

  @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public final void testBigIntegerIterableToLinkedHashSetSelector()
    {
      final NumberIterableSelector<BigInteger, LinkedHashSet<BigInteger>> numberIterableToLinkedHashSetFilter =
        Numbers.bigIntegerIterableToLinkedHashSetSelector();
  
      final Condition1<BigInteger> condition = mock(Condition1.class);
      when(condition.isMet(Matchers.any(BigInteger.class))).thenReturn(Boolean.TRUE);
  
      final Set<BigInteger> expected1 = new LinkedHashSet<BigInteger>(BIG_INTEGER_LIST);
      final Set<BigInteger> result1 = numberIterableToLinkedHashSetFilter.select(condition, expected1);
      assertEquals(expected1.size(), result1.size());
      assertThat(result1, is(equalTo(expected1)));
      final Iterator<BigInteger> iterator1 = expected1.iterator();
      for (final BigInteger each : result1)
      {
        assertThat(each, is(equalTo(iterator1.next())));
      }
  
      reset(condition);
      when(condition.isMet(Matchers.any(BigInteger.class))).thenReturn(Boolean.FALSE);
      final Set<BigInteger> result2 = numberIterableToLinkedHashSetFilter.select(condition, expected1);
      final Set<BigInteger> expected2 = new LinkedHashSet<BigInteger>();
      assertEquals(expected2.size(), result2.size());
      assertThat(result2, is(equalTo(expected2)));
      final Iterator<BigInteger> iterator2 = expected2.iterator();
      for (final BigInteger each : result2)
      {
        assertThat(each, is(equalTo(iterator2.next())));
      }
  
      final List<BigInteger> bigIntegerList =
        asList(BigInteger.valueOf(-10), BigInteger.valueOf(-9), BigInteger.valueOf(-5), BigInteger.valueOf(-7),
            BigInteger.valueOf(-3), BigInteger.valueOf(-1), BigInteger.valueOf(0), BigInteger.valueOf(1),
            BigInteger.valueOf(2), BigInteger.valueOf(5), BigInteger.valueOf(6), BigInteger.valueOf(10),
            BigInteger.valueOf(100));
      reset(condition);
      int i = 0;
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
  
      final Set<BigInteger> result3 = numberIterableToLinkedHashSetFilter.select(condition, bigIntegerList);
      final Set<BigInteger> expected3 = new LinkedHashSet<BigInteger>(bigIntegerList.subList(0, 6));
      assertEquals(expected3.size(), result3.size());
      assertThat(result3, is(equalTo(expected3)));
      final Iterator<BigInteger> iterator3 = expected3.iterator();
      for (final BigInteger each : result3)
      {
        assertThat(each, is(equalTo(iterator3.next())));
      }
  
      reset(condition);
      i = 0;
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigIntegerList.get(i++))).thenReturn(Boolean.TRUE);
  
      final Set<BigInteger> result4 = numberIterableToLinkedHashSetFilter.select(condition, bigIntegerList);
      final Set<BigInteger> expected4 = new LinkedHashSet<BigInteger>(bigIntegerList.subList(6, 13));
      assertEquals(expected4.size(), result4.size());
      assertThat(result4, is(equalTo(expected4)));
      final Iterator<BigInteger> iterator4 = expected4.iterator();
      for (final BigInteger each : result4)
      {
        assertThat(each, is(equalTo(iterator4.next())));
      }
    }

  @SuppressWarnings({ "boxing", "unchecked" })
    @Test
    public final void testBigDecimalIterableToLinkedHashSetSelector()
    {
      final NumberIterableSelector<BigDecimal, LinkedHashSet<BigDecimal>> numberIterableToLinkedHashSetFilter =
        Numbers.bigDecimalIterableToLinkedHashSetSelector();
  
      final Condition1<BigDecimal> condition = mock(Condition1.class);
      when(condition.isMet(Matchers.any(BigDecimal.class))).thenReturn(Boolean.TRUE);
  
      final Set<BigDecimal> expected1 = new LinkedHashSet<BigDecimal>(BIG_DECIMAL_LIST);
      final Set<BigDecimal> result1 = numberIterableToLinkedHashSetFilter.select(condition, expected1);
      assertEquals(expected1.size(), result1.size());
      assertThat(result1, is(equalTo(expected1)));
      final Iterator<BigDecimal> iterator1 = expected1.iterator();
      for (final BigDecimal each : result1)
      {
        assertThat(each, is(equalTo(iterator1.next())));
      }
  
      reset(condition);
      when(condition.isMet(Matchers.any(BigDecimal.class))).thenReturn(Boolean.FALSE);
      final Set<BigDecimal> result2 = numberIterableToLinkedHashSetFilter.select(condition, expected1);
      final Set<BigDecimal> expected2 = new LinkedHashSet<BigDecimal>();
      assertEquals(expected2.size(), result2.size());
      assertThat(result2, is(equalTo(expected2)));
      final Iterator<BigDecimal> iterator2 = expected2.iterator();
      for (final BigDecimal each : result2)
      {
        assertThat(each, is(equalTo(iterator2.next())));
      }
  
      final List<BigDecimal> bigDecimalList =
        asList(new BigDecimal("-10"), new BigDecimal("-9.5"), new BigDecimal("-5.3"), new BigDecimal("-7.7"),
            new BigDecimal("-3.9"), new BigDecimal("-1.0"), new BigDecimal("0"), new BigDecimal("1.0"), new BigDecimal(
                "2.3"), new BigDecimal("5.5"), new BigDecimal("6.8"), new BigDecimal("10.99"), new BigDecimal("100"));
      reset(condition);
      int i = 0;
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
  
      final Set<BigDecimal> result3 = numberIterableToLinkedHashSetFilter.select(condition, bigDecimalList);
      final Set<BigDecimal> expected3 = new LinkedHashSet<BigDecimal>(bigDecimalList.subList(0, 6));
      assertEquals(expected3.size(), result3.size());
      assertThat(result3, is(equalTo(expected3)));
      final Iterator<BigDecimal> iterator3 = expected3.iterator();
      for (final BigDecimal each : result3)
      {
        assertThat(each, is(equalTo(iterator3.next())));
      }
  
      reset(condition);
      i = 0;
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.FALSE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
      when(condition.isMet(bigDecimalList.get(i++))).thenReturn(Boolean.TRUE);
  
      final Set<BigDecimal> result4 = numberIterableToLinkedHashSetFilter.select(condition, bigDecimalList);
      final Set<BigDecimal> expected4 = new LinkedHashSet<BigDecimal>(bigDecimalList.subList(6, 13));
      assertEquals(expected4.size(), result4.size());
      assertThat(result4, is(equalTo(expected4)));
      final Iterator<BigDecimal> iterator4 = expected4.iterator();
      for (final BigDecimal each : result4)
      {
        assertThat(each, is(equalTo(iterator4.next())));
      }
    }

  @Test
    public final void testNumbersToArrayListSelector()
    {
      final NumbersSelector<Byte, ArrayList<Byte>> bytesToArrayListFilter = Numbers.numbersToArrayListSelector();
      assertThat(bytesToArrayListFilter, is(notNullValue()));
  
      final NumbersSelector<Short, ArrayList<Short>> shortsToArrayListFilter = Numbers.numbersToArrayListSelector();
      assertThat(shortsToArrayListFilter, is(notNullValue()));
  
      final NumbersSelector<Integer, ArrayList<Integer>> integersToArrayListFilter = Numbers.numbersToArrayListSelector();
      assertThat(integersToArrayListFilter, is(notNullValue()));
  
      final NumbersSelector<Long, ArrayList<Long>> longsToArrayListFilter = Numbers.numbersToArrayListSelector();
      assertThat(longsToArrayListFilter, is(notNullValue()));
  
      final NumbersSelector<Float, ArrayList<Float>> floatsToArrayListFilter = Numbers.numbersToArrayListSelector();
      assertThat(floatsToArrayListFilter, is(notNullValue()));
  
      final NumbersSelector<Double, ArrayList<Double>> doublesToArrayListFilter = Numbers.numbersToArrayListSelector();
      assertThat(doublesToArrayListFilter, is(notNullValue()));
  
      final NumbersSelector<BigInteger, ArrayList<BigInteger>> bigIntegersToArrayListFilter =
        Numbers.numbersToArrayListSelector();
      assertThat(bigIntegersToArrayListFilter, is(notNullValue()));
  
      final NumbersSelector<BigDecimal, ArrayList<BigDecimal>> bigDecimalsToArrayListFilter =
        Numbers.numbersToArrayListSelector();
      assertThat(bigDecimalsToArrayListFilter, is(notNullValue()));
    }

  private interface CollectionCreater<T extends Number, C extends Collection<T>>
  {
    C collectionOf();

    C collectionOf(int... numbers);

    C collectionOf(long... numbers);

    C collectionOf(float... numbers);

    C collectionOf(double... numbers);

    C collectionOf(BigInteger... numbers);

    C collectionOf(BigDecimal... numbers);
  }

  private static abstract class ByteCollectionCreater<C extends Collection<Byte>> implements CollectionCreater<Byte, C>
  {
    @Override
    public abstract C collectionOf();

    @SuppressWarnings("boxing")
    @Override
    public C collectionOf(final int... numbers)
    {
      final C collection = collectionOf();
      for (final int number : numbers)
      {
        collection.add(((byte) number));
      }
      return collection;
    }

    /* @formatter:off */
		@SuppressWarnings("unused")
		@Override public C collectionOf(final long... numbers) { throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final float... numbers) { throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final double... numbers) 	{ throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final BigInteger... numbers) 	{ throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final BigDecimal... numbers) 	{ throw new UnsupportedOperationException(); }
		/* @formatter:on */
  }

  private static class ByteListCreater extends ByteCollectionCreater<List<Byte>>
  {
    @Override
    public List<Byte> collectionOf()
    {
      return new ArrayList<Byte>();
    }
  }

  private static class ByteHashSetCreater extends ByteCollectionCreater<Set<Byte>>
  {
    @Override
    public HashSet<Byte> collectionOf()
    {
      return new HashSet<Byte>();
    }
  }

  private static class ByteLinkedHashSetCreater extends ByteCollectionCreater<Set<Byte>>
  {
    @Override
    public LinkedHashSet<Byte> collectionOf()
    {
      return new LinkedHashSet<Byte>();
    }
  }

  private static abstract class ShortCollectionCreater<C extends Collection<Short>> implements
      CollectionCreater<Short, C>
  {
    @Override
    public abstract C collectionOf();

    @SuppressWarnings("boxing")
    @Override
    public C collectionOf(final int... numbers)
    {
      final C collection = collectionOf();
      for (final int number : numbers)
      {
        collection.add(((short) number));
      }
      return collection;
    }

    /* @formatter:off */
		@SuppressWarnings("unused")
		@Override public C collectionOf(final long... numbers) { throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final float... numbers) { throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final double... numbers) 	{ throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final BigInteger... numbers) 	{ throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final BigDecimal... numbers) 	{ throw new UnsupportedOperationException(); }
		/* @formatter:on */
  }

  private static class ShortListCreater extends ShortCollectionCreater<List<Short>>
  {
    @Override
    public List<Short> collectionOf()
    {
      return new ArrayList<Short>();
    }

  }

  private static class ShortHashSetCreater extends ShortCollectionCreater<Set<Short>>
  {
    @Override
    public HashSet<Short> collectionOf()
    {
      return new HashSet<Short>();
    }

  }

  private static class ShortLinkedHashSetCreater extends ShortCollectionCreater<Set<Short>>
  {
    @Override
    public LinkedHashSet<Short> collectionOf()
    {
      return new LinkedHashSet<Short>();
    }

  }

  private static abstract class IntegerCollectionCreater<C extends Collection<Integer>> implements
      CollectionCreater<Integer, C>
  {
    @Override
    public abstract C collectionOf();

    @SuppressWarnings("boxing")
    @Override
    public C collectionOf(final int... numbers)
    {
      final C collection = collectionOf();
      for (final int number : numbers)
      {
        collection.add(number);
      }
      return collection;
    }

    /* @formatter:off */
		@SuppressWarnings("unused")
		@Override public C collectionOf(final long... numbers) { throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final float... numbers) { throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final double... numbers) 	{ throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final BigInteger... numbers) 	{ throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final BigDecimal... numbers) 	{ throw new UnsupportedOperationException(); }
		/* @formatter:on */
  }

  private static class IntegerListCreater extends IntegerCollectionCreater<List<Integer>>
  {
    @Override
    public List<Integer> collectionOf()
    {
      return new ArrayList<Integer>();
    }
  }

  private static class IntegerHashSetCreater extends IntegerCollectionCreater<Set<Integer>>
  {
    @Override
    public HashSet<Integer> collectionOf()
    {
      return new HashSet<Integer>();
    }
  }

  private static class IntegerLinkedHashSetCreater extends IntegerCollectionCreater<Set<Integer>>
  {
    @Override
    public LinkedHashSet<Integer> collectionOf()
    {
      return new LinkedHashSet<Integer>();
    }
  }

  private static abstract class LongCollectionCreater<C extends Collection<Long>> implements CollectionCreater<Long, C>
  {
    @Override
    public abstract C collectionOf();

    @SuppressWarnings("boxing")
    @Override
    public C collectionOf(final long... numbers)
    {
      final C collection = collectionOf();
      for (final long number : numbers)
      {
        collection.add(number);
      }
      return collection;
    }

    /* @formatter:off */
		@SuppressWarnings("unused")
		@Override public C collectionOf(final int... numbers) { throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final float... numbers) { throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final double... numbers) 	{ throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final BigInteger... numbers) 	{ throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final BigDecimal... numbers) 	{ throw new UnsupportedOperationException(); }
		/* @formatter:on */
  }

  private static class LongListCreater extends LongCollectionCreater<List<Long>>
  {
    @Override
    public List<Long> collectionOf()
    {
      return new ArrayList<Long>();
    }
  }

  private static class LongHashSetCreater extends LongCollectionCreater<Set<Long>>
  {
    @Override
    public HashSet<Long> collectionOf()
    {
      return new HashSet<Long>();
    }
  }

  private static class LongLinkedHashSetCreater extends LongCollectionCreater<Set<Long>>
  {
    @Override
    public LinkedHashSet<Long> collectionOf()
    {
      return new LinkedHashSet<Long>();
    }
  }

  private static abstract class FloatCollectionCreater<C extends Collection<Float>> implements
      CollectionCreater<Float, C>
  {
    @Override
    public abstract C collectionOf();

    @SuppressWarnings("boxing")
    @Override
    public C collectionOf(final float... numbers)
    {
      final C collection = collectionOf();
      for (final float number : numbers)
      {
        collection.add(number);
      }
      return collection;
    }

    /* @formatter:off */
		@SuppressWarnings("unused")
		@Override public C collectionOf(final int... numbers) { throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final long... numbers) { throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final double... numbers) 	{ throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final BigInteger... numbers) 	{ throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final BigDecimal... numbers) 	{ throw new UnsupportedOperationException(); }
		/* @formatter:on */
  }

  private static class FloatListCreater extends FloatCollectionCreater<List<Float>>
  {
    @Override
    public List<Float> collectionOf()
    {
      return new ArrayList<Float>();
    }
  }

  private static class FloatHashSetCreater extends FloatCollectionCreater<Set<Float>>
  {
    @Override
    public HashSet<Float> collectionOf()
    {
      return new HashSet<Float>();
    }
  }

  private static class FloatLinkedHashSetCreater extends FloatCollectionCreater<Set<Float>>
  {
    @Override
    public LinkedHashSet<Float> collectionOf()
    {
      return new LinkedHashSet<Float>();
    }
  }

  private static abstract class DoubleCollectionCreater<C extends Collection<Double>> implements
      CollectionCreater<Double, C>
  {
    @Override
    public abstract C collectionOf();

    @SuppressWarnings("boxing")
    @Override
    public C collectionOf(final double... numbers)
    {
      final C collection = collectionOf();
      for (final double number : numbers)
      {
        collection.add(number);
      }
      return collection;
    }

    /* @formatter:off */
		@SuppressWarnings("unused")
		@Override public C collectionOf(final int... numbers) 	{ throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final long... numbers) { throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final float... numbers) { throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final BigInteger... numbers) 	{ throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final BigDecimal... numbers) 	{ throw new UnsupportedOperationException(); }
		/* @formatter:on */
  }

  private static class DoubleListCreater extends DoubleCollectionCreater<List<Double>>
  {
    @Override
    public List<Double> collectionOf()
    {
      return new ArrayList<Double>();
    }
  }

  private static class DoubleHashSetCreater extends DoubleCollectionCreater<Set<Double>>
  {
    @Override
    public HashSet<Double> collectionOf()
    {
      return new HashSet<Double>();
    }
  }

  private static class DoubleLinkedHashSetCreater extends DoubleCollectionCreater<Set<Double>>
  {
    @Override
    public LinkedHashSet<Double> collectionOf()
    {
      return new LinkedHashSet<Double>();
    }
  }

  private static abstract class BigIntegerCollectionCreater<C extends Collection<BigInteger>> implements
      CollectionCreater<BigInteger, C>
  {
    @Override
    public abstract C collectionOf();

    @Override
    public C collectionOf(final BigInteger... numbers)
    {
      final C list = collectionOf();
      for (final BigInteger number : numbers)
      {
        list.add(number);
      }
      return list;
    }

    /* @formatter:off */
		@SuppressWarnings("unused")
		@Override public C collectionOf(final int... numbers) { throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final long... numbers) 	{ throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final float... numbers) { throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final double... numbers) 	{ throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final BigDecimal... numbers) 	{ throw new UnsupportedOperationException(); }
		/* @formatter:on */
  }

  private static class BigIntegerListCreater extends BigIntegerCollectionCreater<List<BigInteger>>
  {
    @Override
    public List<BigInteger> collectionOf()
    {
      return new ArrayList<BigInteger>();
    }
  }

  private static class BigIntegerHashSetCreater extends BigIntegerCollectionCreater<Set<BigInteger>>
  {
    @Override
    public HashSet<BigInteger> collectionOf()
    {
      return new HashSet<BigInteger>();
    }
  }

  private static class BigIntegerLinkedHashSetCreater extends BigIntegerCollectionCreater<Set<BigInteger>>
  {
    @Override
    public LinkedHashSet<BigInteger> collectionOf()
    {
      return new LinkedHashSet<BigInteger>();
    }
  }

  private static abstract class BigDecimalCollectionCreater<C extends Collection<BigDecimal>> implements
      CollectionCreater<BigDecimal, C>
  {
    @Override
    public abstract C collectionOf();

    @Override
    public C collectionOf(final BigDecimal... numbers)
    {
      final C collection = collectionOf();
      for (final BigDecimal number : numbers)
      {
        collection.add(number);
      }
      return collection;
    }

    /* @formatter:off */
		@SuppressWarnings("unused")
		@Override public C collectionOf(final int... numbers) 	{ throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final long... numbers) { throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final float... numbers) { throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final double... numbers) 	{ throw new UnsupportedOperationException(); }
		@SuppressWarnings("unused")
		@Override public C collectionOf(final BigInteger... numbers) 	{ throw new UnsupportedOperationException(); }
		/* @formatter:on */
  }

  private static class BigDecimalListCreater extends BigDecimalCollectionCreater<List<BigDecimal>>
  {
    @Override
    public List<BigDecimal> collectionOf()
    {
      return new ArrayList<BigDecimal>();
    }
  }

  private static class BigDecimalHashSetCreater extends BigDecimalCollectionCreater<Set<BigDecimal>>
  {
    @Override
    public HashSet<BigDecimal> collectionOf()
    {
      return new HashSet<BigDecimal>();
    }
  }

  private static class BigDecimalLinkedHashSetCreater extends BigDecimalCollectionCreater<Set<BigDecimal>>
  {
    @Override
    public LinkedHashSet<BigDecimal> collectionOf()
    {
      return new LinkedHashSet<BigDecimal>();
    }
  }

  private static <T extends Number, C extends Collection<T>> C getListOfNegative(
      final CollectionCreater<T, C> collectionCreater, final int num, final int howMany)
  {
    if (0 > howMany)
    {
      return collectionCreater.collectionOf();
    }
    if (0 > (num + howMany - 1))
    {
      final int[] ints = new int[howMany];
      for (int i = 0; i < howMany; i++)
      {
        ints[i] = num + i;
      }
      return collectionCreater.collectionOf(ints);
    }
    return getListOfNegative(collectionCreater, num, howMany - 1);
  }

  private static <T extends Number, C extends Collection<T>> C getListOfNegative(
      final CollectionCreater<T, C> listCreater, final long num, final int howMany)
  {
    if (0 > howMany)
    {
      return listCreater.collectionOf();
    }
    if (0 > (num + howMany - 1))
    {
      final long[] longs = new long[howMany];
      for (int i = 0; i < howMany; i++)
      {
        longs[i] = num + i;
      }
      return listCreater.collectionOf(longs);
    }
    return getListOfNegative(listCreater, num, howMany - 1);
  }

  private static <T extends Number, C extends Collection<T>> C getListOfNegative(
      final CollectionCreater<T, C> listCreater, final float num, final int howMany)
  {
    if (0 > howMany)
    {
      return listCreater.collectionOf();
    }
    if (0 > (num + howMany - 1))
    {
      final float[] nums = new float[howMany];
      for (int i = 0; i < howMany; i++)
      {
        nums[i] = num + i;
      }
      return listCreater.collectionOf(nums);
    }
    return getListOfNegative(listCreater, num, howMany - 1);
  }

  private static <T extends Number, C extends Collection<T>> C getListOfNegative(
      final CollectionCreater<T, C> listCreater, final double num, final int howMany)
  {
    if (0 > howMany)
    {
      return listCreater.collectionOf();
    }
    if (0 > (num + howMany - 1))
    {
      final double[] nums = new double[howMany];
      for (int i = 0; i < howMany; i++)
      {
        nums[i] = num + i;
      }
      return listCreater.collectionOf(nums);
    }
    return getListOfNegative(listCreater, num, howMany - 1);
  }

  private static <T extends Number, C extends Collection<T>> C getListOfNegative(
      final CollectionCreater<T, C> listCreater, final BigInteger num, final int howMany)
  {
    if (0 > howMany)
    {
      return listCreater.collectionOf();
    }
    if (0 > (num.add(BigInteger.valueOf(howMany))
        .subtract(BigInteger.ONE).compareTo(BigInteger.ZERO)))
    {
      final BigInteger[] nums = new BigInteger[howMany];
      for (int i = 0; i < howMany; i++)
      {
        nums[i] = num.add(BigInteger.valueOf(i));
      }
      return listCreater.collectionOf(nums);
    }
    return getListOfNegative(listCreater, num, howMany - 1);
  }

  private static <T extends Number, C extends Collection<T>> C getListOfNegative(
      final CollectionCreater<T, C> listCreater, final BigDecimal num, final int howMany)
  {
    if (0 > howMany)
    {
      return listCreater.collectionOf();
    }
    if (0 > (num.add(BigDecimal.valueOf(howMany))
        .subtract(BigDecimal.ONE).compareTo(BigDecimal.ZERO)))
    {
      final BigDecimal[] nums = new BigDecimal[howMany];
      for (int i = 0; i < howMany; i++)
      {
        nums[i] = num.add(BigDecimal.valueOf(i));
      }
      return listCreater.collectionOf(nums);
    }
    return getListOfNegative(listCreater, num, howMany - 1);
  }

  private static <T extends Number, C extends Collection<T>> C getListOfPositive(
      final CollectionCreater<T, C> listCreater, final int num, final int howMany)
  {
    if (0 >= howMany)
    {
      return listCreater.collectionOf();
    }
    if (0 < num)
    {
      final int[] ints = new int[howMany];
      for (int i = 0; i < howMany; i++)
      {
        ints[i] = num + i;
      }
      return listCreater.collectionOf(ints);
    }
    return getListOfPositive(listCreater, num + 1, howMany - 1);
  }

  private static <T extends Number, C extends Collection<T>> C getListOfPositive(
      final CollectionCreater<T, C> listCreater, final long num, final int howMany)
  {
    if (0 >= howMany)
    {
      return listCreater.collectionOf();
    }
    if (0 < num)
    {
      final long[] longs = new long[howMany];
      for (int i = 0; i < howMany; i++)
      {
        longs[i] = num + i;
      }
      return listCreater.collectionOf(longs);
    }
    return getListOfPositive(listCreater, num + 1, howMany - 1);
  }

  private static <T extends Number, C extends Collection<T>> C getListOfPositive(
      final CollectionCreater<T, C> listCreater, final float num, final int howMany)
  {
    if (0 >= howMany)
    {
      return listCreater.collectionOf();
    }
    if (0 < num)
    {
      final float[] nums = new float[howMany];
      for (int i = 0; i < howMany; i++)
      {
        nums[i] = num + i;
      }
      return listCreater.collectionOf(nums);
    }
    return getListOfPositive(listCreater, num + 1, howMany - 1);
  }

  private static <T extends Number, C extends Collection<T>> C getListOfPositive(
      final CollectionCreater<T, C> listCreater, final double num, final int howMany)
  {
    if (0 >= howMany)
    {
      return listCreater.collectionOf();
    }
    if (0 < num)
    {
      final double[] nums = new double[howMany];
      for (int i = 0; i < howMany; i++)
      {
        nums[i] = num + i;
      }
      return listCreater.collectionOf(nums);
    }
    return getListOfPositive(listCreater, num + 1, howMany - 1);
  }

  private static <T extends Number, C extends Collection<T>> C getListOfPositive(
      final CollectionCreater<T, C> listCreater, final BigInteger num, final int howMany)
  {
    if (0 >= howMany)
    {
      return listCreater.collectionOf();
    }
    if (0 < num.compareTo(BigInteger.ZERO))
    {
      final BigInteger[] nums = new BigInteger[howMany];
      for (int i = 0; i < howMany; i++)
      {
        nums[i] = num.add(BigInteger.valueOf(i));
      }
      return listCreater.collectionOf(nums);
    }
    return getListOfPositive(listCreater, num.add(BigInteger.ONE), howMany - 1);
  }

  private static <T extends Number, C extends Collection<T>> C getListOfPositive(
      final CollectionCreater<T, C> listCreater, final BigDecimal num, final int howMany)
  {
    if (0 >= howMany)
    {
      return listCreater.collectionOf();
    }
    if (0 < num.compareTo(BigDecimal.ZERO))
    {
      final BigDecimal[] nums = new BigDecimal[howMany];
      for (int i = 0; i < howMany; i++)
      {
        nums[i] = num.add(BigDecimal.valueOf(i));
      }
      return listCreater.collectionOf(nums);
    }
    return getListOfPositive(listCreater, num.add(BigDecimal.ONE), howMany - 1);
  }

  private <T extends Number, C extends Collection<T>> C getListOfNegative(final C fromList, final C toList)
  {
    for (final T number : fromList)
    {
      if ((number instanceof Long && 0 > number.longValue())
          || (number instanceof Float && 0f > number.floatValue())
          || (number instanceof Double && 0L > number.doubleValue())
          || ((number instanceof Integer || number instanceof Short || number instanceof Byte) && 0 > number.intValue())
          || ((number instanceof BigInteger && 0 > ((BigInteger) number).compareTo(BigInteger.ZERO)))
          || ((number instanceof BigDecimal && 0 > ((BigDecimal) number).compareTo(BigDecimal.ZERO))))
      {
        toList.add(number);
      }
    }
    return toList;
  }

  private <T extends Number, C extends Collection<T>> C getListOfPositive(final C from, final C to)
  {
    for (final T number : from)
    {
      if ((number instanceof Long && 0 < number.longValue())
          || (number instanceof Float && 0f < number.floatValue())
          || (number instanceof Double && 0L < number.doubleValue())
          || ((number instanceof Integer || number instanceof Short || number instanceof Byte) && 0 < number.intValue())
          || ((number instanceof BigInteger && 0 < ((BigInteger) number).compareTo(BigInteger.ZERO)))
          || ((number instanceof BigDecimal && 0 < ((BigDecimal) number).compareTo(BigDecimal.ZERO))))
      {
        to.add(number);
      }
    }
    return to;
  }

  @SuppressWarnings("boxing")
    @Test
    public final void testBytesToArrayListSelector()
    {
      final CollectionCreater<Byte, List<Byte>> collectionCreater = new ByteListCreater();
  
      final List<Byte> byteList = BYTE_LIST;
  
      final NumbersSelector<Byte, ArrayList<Byte>> numbersToArrayListFilter = Numbers.bytesToArrayListSelector();
  
      final Condition1<Byte> negativeNumber = new Condition1<Byte>() {
        @Override
        public boolean isMet(final Byte input)
        {
          return input.byteValue() < 0;
        }
      };
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE + 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, (byte) i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, (byte) i, (byte) (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, (byte) i, (byte) (i + 1), (byte) (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, (byte) i, (byte) (i + 1), (byte) (i + 2), (byte) (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4)), is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), (byte) (i + 5)), is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), (byte) (i + 5), (byte) (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), (byte) (i + 5), (byte) (i + 6), (byte) (i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), byteList.toArray(new Byte[byteList.size()])),
            is(equalTo(getListOfNegative(byteList, getListOfNegative(collectionCreater, i, 5)))));
      }
  
      /* positive */
      final Condition1<Byte> positiveNumber = new Condition1<Byte>() {
        @Override
        public boolean isMet(final Byte input)
        {
          return input.byteValue() > 0;
        }
      };
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE + 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, (byte) i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, (byte) i, (byte) (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, (byte) i, (byte) (i + 1), (byte) (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, (byte) i, (byte) (i + 1), (byte) (i + 2), (byte) (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4)), is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), (byte) (i + 5)), is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), (byte) (i + 5), (byte) (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), (byte) (i + 5), (byte) (i + 6), (byte) (i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), byteList.toArray(new Byte[byteList.size()])),
            is(equalTo(getListOfPositive(byteList, getListOfPositive(collectionCreater, i, 5)))));
      }
    }

  @SuppressWarnings("boxing")
    @Test
    public final void testShortsToArrayListSelector()
    {
      final CollectionCreater<Short, List<Short>> collectionCreater = new ShortListCreater();
  
      final List<Short> shortList = SHORT_LIST;
  
      final NumbersSelector<Short, ArrayList<Short>> numbersToArrayListFilter = Numbers.shortsToArrayListSelector();
  
      /* negative */
      final Condition1<Short> negativeNumber = new Condition1<Short>() {
        @Override
        public boolean isMet(final Short input)
        {
          return input.shortValue() < 0;
        }
      };
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE + 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, (short) i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, (short) i, (short) (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, (short) i, (short) (i + 1), (short) (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, (short) i, (short) (i + 1), (short) (i + 2), (short) (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4)), is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), (short) (i + 5)), is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), (short) (i + 5), (short) (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), (short) (i + 5), (short) (i + 6), (short) (i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), shortList.toArray(new Short[shortList.size()])),
            is(equalTo(getListOfNegative(shortList, getListOfNegative(collectionCreater, i, 5)))));
      }
  
      /* positive */
      final Condition1<Short> positiveNumber = new Condition1<Short>() {
        @Override
        public boolean isMet(final Short input)
        {
          return input.shortValue() > 0;
        }
      };
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE + 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, (short) i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, (short) i, (short) (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, (short) i, (short) (i + 1), (short) (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, (short) i, (short) (i + 1), (short) (i + 2), (short) (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4)), is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), (short) (i + 5)), is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), (short) (i + 5), (short) (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), (short) (i + 5), (short) (i + 6), (short) (i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), shortList.toArray(new Short[shortList.size()])),
            is(equalTo(getListOfPositive(shortList, getListOfPositive(collectionCreater, i, 5)))));
      }
    }

  @SuppressWarnings("boxing")
    @Test
    public final void testIntegersToArrayListSelector()
    {
      final CollectionCreater<Integer, List<Integer>> collectionCreater = new IntegerListCreater();
  
      final List<Integer> integerList = INTEGER_LIST;
  
      final NumbersSelector<Integer, ArrayList<Integer>> numbersToArrayListFilter = Numbers.integersToArrayListSelector();
  
      /* negative */
      final Condition1<Integer> negativeNumber = new Condition1<Integer>() {
        @Override
        public boolean isMet(final Integer input)
        {
          return input.intValue() < 0;
        }
      };
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
      for (int i = Integer.MAX_VALUE, size = Integer.MAX_VALUE - RANGE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5),
            (i + 6), (i + 7)), is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5),
            (i + 6), (i + 7)), is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5),
            (i + 6), (i + 7)), is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                integerList.toArray(new Integer[integerList.size()])),
            is(equalTo(getListOfNegative(integerList, getListOfNegative(collectionCreater, i, 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                integerList.toArray(new Integer[integerList.size()])),
            is(equalTo(getListOfNegative(integerList, getListOfNegative(collectionCreater, i, 5)))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                integerList.toArray(new Integer[integerList.size()])),
            is(equalTo(getListOfNegative(integerList, getListOfNegative(collectionCreater, i, 5)))));
      }
  
      /* positive */
      final Condition1<Integer> positiveNumber = new Condition1<Integer>() {
        @Override
        public boolean isMet(final Integer input)
        {
          return input.intValue() > 0;
        }
      };
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5),
            (i + 6), (i + 7)), is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5),
            (i + 6), (i + 7)), is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5),
            (i + 6), (i + 7)), is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                integerList.toArray(new Integer[integerList.size()])),
            is(equalTo(getListOfPositive(integerList, getListOfPositive(collectionCreater, i, 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                integerList.toArray(new Integer[integerList.size()])),
            is(equalTo(getListOfPositive(integerList, getListOfPositive(collectionCreater, i, 5)))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                integerList.toArray(new Integer[integerList.size()])),
            is(equalTo(getListOfPositive(integerList, getListOfPositive(collectionCreater, i, 5)))));
      }
    }

  @SuppressWarnings("boxing")
    @Test
    public final void testLongsToArrayListSelector()
    {
      final CollectionCreater<Long, List<Long>> collectionCreater = new LongListCreater();
  
      final List<Long> longList = LONG_LIST;
  
      final NumbersSelector<Long, ArrayList<Long>> numbersToArrayListFilter = Numbers.longsToArrayListSelector();
  
      /* negative */
      final Condition1<Long> negativeNumber = new Condition1<Long>() {
        @Override
        public boolean isMet(final Long input)
        {
          return input.longValue() < 0;
        }
      };
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
      for (long i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
      for (long i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
      for (long i = Long.MAX_VALUE, size = Long.MAX_VALUE - RANGE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
      for (long i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
      for (long i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
      for (long i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
      for (long i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
      for (long i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5),
            (i + 6), (i + 7)), is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
      for (long i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5),
            (i + 6), (i + 7)), is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5),
            (i + 6), (i + 7)), is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                longList.toArray(new Long[longList.size()])),
            is(equalTo(getListOfNegative(longList, getListOfNegative(collectionCreater, i, 5)))));
      }
      for (long i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                longList.toArray(new Long[longList.size()])),
            is(equalTo(getListOfNegative(longList, getListOfNegative(collectionCreater, i, 5)))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                longList.toArray(new Long[longList.size()])),
            is(equalTo(getListOfNegative(longList, getListOfNegative(collectionCreater, i, 5)))));
      }
  
      /* positive */
      final Condition1<Long> positiveNumber = new Condition1<Long>() {
        @Override
        public boolean isMet(final Long input)
        {
          return input.longValue() > 0;
        }
      };
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
      for (long i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
      for (long i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
      for (long i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
      for (long i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
      for (long i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
      for (long i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
      for (long i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5),
            (i + 6), (i + 7)), is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
      for (long i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5),
            (i + 6), (i + 7)), is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5),
            (i + 6), (i + 7)), is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                longList.toArray(new Long[longList.size()])),
            is(equalTo(getListOfPositive(longList, getListOfPositive(collectionCreater, i, 5)))));
      }
      for (long i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                longList.toArray(new Long[longList.size()])),
            is(equalTo(getListOfPositive(longList, getListOfPositive(collectionCreater, i, 5)))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                longList.toArray(new Long[longList.size()])),
            is(equalTo(getListOfPositive(longList, getListOfPositive(collectionCreater, i, 5)))));
      }
    }

  private static Random random = new Random();

  private static float toFloat(final int num)
  {
    final float randomFloat = random.nextFloat();
    return 0 > num ? num + randomFloat : num - randomFloat;
  }

  @SuppressWarnings("boxing")
    @Test
    public final void testFloatsToArrayListSelector()
    {
      final CollectionCreater<Float, List<Float>> collectionCreater = new FloatListCreater();
  
      final List<Float> floatList = FLOAT_LIST;
  
      final NumbersSelector<Float, ArrayList<Float>> numbersToArrayListFilter = Numbers.floatsToArrayListSelector();
  
      /* negative */
      final Condition1<Float> negativeNumber = new Condition1<Float>() {
        @Override
        public boolean isMet(final Float input)
        {
          return input.floatValue() < 0;
        }
      };
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num),
            is(equalTo(getListOfNegative(collectionCreater, num, 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num),
            is(equalTo(getListOfNegative(collectionCreater, num, 1))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num),
            is(equalTo(getListOfNegative(collectionCreater, num, 1))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1)),
            is(equalTo(getListOfNegative(collectionCreater, num, 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1)),
            is(equalTo(getListOfNegative(collectionCreater, num, 2))));
      }
      for (int i = Short.MAX_VALUE, size = Short.MAX_VALUE - RANGE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1)),
            is(equalTo(getListOfNegative(collectionCreater, num, 2))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfNegative(collectionCreater, num, 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfNegative(collectionCreater, num, 3))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfNegative(collectionCreater, num, 3))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfNegative(collectionCreater, num, 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfNegative(collectionCreater, num, 4))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 2; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfNegative(collectionCreater, num, 4))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfNegative(collectionCreater, num, 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfNegative(collectionCreater, num, 5))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfNegative(collectionCreater, num, 5))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfNegative(collectionCreater, num, 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfNegative(collectionCreater, num, 6))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 4; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfNegative(collectionCreater, num, 6))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfNegative(collectionCreater, num, 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfNegative(collectionCreater, num, 7))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 5; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfNegative(collectionCreater, num, 7))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfNegative(collectionCreater, num, 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfNegative(collectionCreater, num, 8))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 6; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfNegative(collectionCreater, num, 8))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                floatList.toArray(new Float[floatList.size()])),
            is(equalTo(getListOfNegative(floatList, getListOfNegative(collectionCreater, num, 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                floatList.toArray(new Float[floatList.size()])),
            is(equalTo(getListOfNegative(floatList, getListOfNegative(collectionCreater, num, 5)))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                floatList.toArray(new Float[floatList.size()])),
            is(equalTo(getListOfNegative(floatList, getListOfNegative(collectionCreater, num, 5)))));
      }
  
      /* positive */
      final Condition1<Float> positiveNumber = new Condition1<Float>() {
        @Override
        public boolean isMet(final Float input)
        {
          return input.floatValue() > 0;
        }
      };
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num),
            is(equalTo(getListOfPositive(collectionCreater, num, 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num),
            is(equalTo(getListOfPositive(collectionCreater, num, 1))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num),
            is(equalTo(getListOfPositive(collectionCreater, num, 1))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1)),
            is(equalTo(getListOfPositive(collectionCreater, num, 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1)),
            is(equalTo(getListOfPositive(collectionCreater, num, 2))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1)),
            is(equalTo(getListOfPositive(collectionCreater, num, 2))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfPositive(collectionCreater, num, 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfPositive(collectionCreater, num, 3))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfPositive(collectionCreater, num, 3))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfPositive(collectionCreater, num, 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfPositive(collectionCreater, num, 4))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 2; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfPositive(collectionCreater, num, 4))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfPositive(collectionCreater, num, 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfPositive(collectionCreater, num, 5))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfPositive(collectionCreater, num, 5))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfPositive(collectionCreater, num, 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfPositive(collectionCreater, num, 6))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 4; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfPositive(collectionCreater, num, 6))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfPositive(collectionCreater, num, 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfPositive(collectionCreater, num, 7))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 5; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfPositive(collectionCreater, num, 7))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfPositive(collectionCreater, num, 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfPositive(collectionCreater, num, 8))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 6; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfPositive(collectionCreater, num, 8))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                floatList.toArray(new Float[floatList.size()])),
            is(equalTo(getListOfPositive(floatList, getListOfPositive(collectionCreater, num, 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                floatList.toArray(new Float[floatList.size()])),
            is(equalTo(getListOfPositive(floatList, getListOfPositive(collectionCreater, num, 5)))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                floatList.toArray(new Float[floatList.size()])),
            is(equalTo(getListOfPositive(floatList, getListOfPositive(collectionCreater, num, 5)))));
      }
    }

  private static double toDouble(final int num)
  {
    final double randomFloat = random.nextDouble();
    return 0 > num ? num + randomFloat : num - randomFloat;
  }

  @SuppressWarnings("boxing")
    @Test
    public final void testDoublesToArrayListSelector()
    {
      final CollectionCreater<Double, List<Double>> collectionCreater = new DoubleListCreater();
  
      final List<Double> numberList = DOUBLE_LIST;
  
      final NumbersSelector<Double, ArrayList<Double>> numbersToArrayListFilter = Numbers.doublesToArrayListSelector();
  
      /* negative */
      final Condition1<Double> negativeNumber = new Condition1<Double>() {
        @Override
        public boolean isMet(final Double input)
        {
          return input.doubleValue() < 0;
        }
      };
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num),
            is(equalTo(getListOfNegative(collectionCreater, num, 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num),
            is(equalTo(getListOfNegative(collectionCreater, num, 1))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num),
            is(equalTo(getListOfNegative(collectionCreater, num, 1))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1)),
            is(equalTo(getListOfNegative(collectionCreater, num, 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1)),
            is(equalTo(getListOfNegative(collectionCreater, num, 2))));
      }
      for (int i = Integer.MAX_VALUE, size = Integer.MAX_VALUE - RANGE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1)),
            is(equalTo(getListOfNegative(collectionCreater, num, 2))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfNegative(collectionCreater, num, 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfNegative(collectionCreater, num, 3))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfNegative(collectionCreater, num, 3))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfNegative(collectionCreater, num, 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfNegative(collectionCreater, num, 4))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 2; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfNegative(collectionCreater, num, 4))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfNegative(collectionCreater, num, 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfNegative(collectionCreater, num, 5))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfNegative(collectionCreater, num, 5))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfNegative(collectionCreater, num, 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfNegative(collectionCreater, num, 6))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 4; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfNegative(collectionCreater, num, 6))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfNegative(collectionCreater, num, 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfNegative(collectionCreater, num, 7))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 5; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfNegative(collectionCreater, num, 7))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfNegative(collectionCreater, num, 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfNegative(collectionCreater, num, 8))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 6; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfNegative(collectionCreater, num, 8))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Double[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, num, 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Double[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, num, 5)))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Double[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, num, 5)))));
      }
  
      /* positive */
      final Condition1<Double> positiveNumber = new Condition1<Double>() {
        @Override
        public boolean isMet(final Double input)
        {
          return input.doubleValue() > 0;
        }
      };
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num),
            is(equalTo(getListOfPositive(collectionCreater, num, 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num),
            is(equalTo(getListOfPositive(collectionCreater, num, 1))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num),
            is(equalTo(getListOfPositive(collectionCreater, num, 1))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1)),
            is(equalTo(getListOfPositive(collectionCreater, num, 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1)),
            is(equalTo(getListOfPositive(collectionCreater, num, 2))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1)),
            is(equalTo(getListOfPositive(collectionCreater, num, 2))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfPositive(collectionCreater, num, 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfPositive(collectionCreater, num, 3))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfPositive(collectionCreater, num, 3))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfPositive(collectionCreater, num, 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfPositive(collectionCreater, num, 4))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 2; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfPositive(collectionCreater, num, 4))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfPositive(collectionCreater, num, 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfPositive(collectionCreater, num, 5))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfPositive(collectionCreater, num, 5))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfPositive(collectionCreater, num, 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfPositive(collectionCreater, num, 6))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 4; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfPositive(collectionCreater, num, 6))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfPositive(collectionCreater, num, 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfPositive(collectionCreater, num, 7))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 5; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfPositive(collectionCreater, num, 7))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfPositive(collectionCreater, num, 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfPositive(collectionCreater, num, 8))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 6; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfPositive(collectionCreater, num, 8))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Double[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, num, 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Double[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, num, 5)))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Double[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, num, 5)))));
      }
    }

  @Test
    public final void testBigIntegersToArrayListSelector()
    {
      final CollectionCreater<BigInteger, List<BigInteger>> collectionCreater = new BigIntegerListCreater();
  
      final List<BigInteger> numberList = BIG_INTEGER_LIST;
  
      final NumbersSelector<BigInteger, ArrayList<BigInteger>> numbersToArrayListFilter =
        Numbers.bigIntegersToArrayListSelector();
  
      /* negative */
      final Condition1<BigInteger> negativeNumber = new Condition1<BigInteger>() {
        @Override
        public boolean isMet(final BigInteger input)
        {
          return 0 > input.compareTo(BigInteger.ZERO);
        }
      };
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 1))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 1))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 2))));
      }
      for (long i = Long.MAX_VALUE, size = Long.MAX_VALUE - RANGE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 2))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2)), is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2)), is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 3))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2)), is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 3))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 4))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 4))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 5))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 5))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5)), is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5)), is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 6))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5)), is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 6))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 7))));
      }
      for (long i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 7))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 7))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6), BigInteger.valueOf(i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6), BigInteger.valueOf(i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 8))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6), BigInteger.valueOf(i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 8))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                numberList.toArray(new BigInteger[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, BigInteger.valueOf(i), 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                numberList.toArray(new BigInteger[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, BigInteger.valueOf(i), 5)))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                numberList.toArray(new BigInteger[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, BigInteger.valueOf(i), 5)))));
      }
  
      /* positive */
      final Condition1<BigInteger> positiveNumber = new Condition1<BigInteger>() {
        @Override
        public boolean isMet(final BigInteger input)
        {
          return 0 < input.compareTo(BigInteger.ZERO);
        }
      };
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 1))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 1))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 2))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 2))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2)), is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2)), is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 3))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2)), is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 3))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 4))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 4))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 5))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 5))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5)), is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5)), is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 6))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5)), is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 6))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 7))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 7))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6), BigInteger.valueOf(i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6), BigInteger.valueOf(i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 8))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6), BigInteger.valueOf(i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 8))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                numberList.toArray(new BigInteger[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, BigInteger.valueOf(i), 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                numberList.toArray(new BigInteger[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, BigInteger.valueOf(i), 5)))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                numberList.toArray(new BigInteger[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, BigInteger.valueOf(i), 5)))));
      }
    }

  @Test
    public final void testBigDecimalsToArrayListSelector()
    {
      final CollectionCreater<BigDecimal, List<BigDecimal>> collectionCreater = new BigDecimalListCreater();
  
      final List<BigDecimal> numberList = BIG_DECIMAL_LIST;
  
      final NumbersSelector<BigDecimal, ArrayList<BigDecimal>> numbersToArrayListFilter =
        Numbers.bigDecimalsToArrayListSelector();
  
      /* negative */
      final Condition1<BigDecimal> negativeNumber = new Condition1<BigDecimal>() {
        @Override
        public boolean isMet(final BigDecimal input)
        {
          return 0 > input.compareTo(BigDecimal.ZERO);
        }
      };
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 1))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 1))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 2))));
      }
      for (long i = Long.MAX_VALUE, size = Long.MAX_VALUE - RANGE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 2))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2)), is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2)), is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 3))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2)), is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 3))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 4))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 4))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 5))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 5))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5)), is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5)), is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 6))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5)), is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 6))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 7))));
      }
      for (long i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 7))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 7))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6), BigDecimal.valueOf(i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6), BigDecimal.valueOf(i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 8))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6), BigDecimal.valueOf(i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 8))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                numberList.toArray(new BigDecimal[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                numberList.toArray(new BigDecimal[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 5)))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                numberList.toArray(new BigDecimal[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 5)))));
      }
  
      /* positive */
      final Condition1<BigDecimal> positiveNumber = new Condition1<BigDecimal>() {
        @Override
        public boolean isMet(final BigDecimal input)
        {
          return 0 < input.compareTo(BigDecimal.ZERO);
        }
      };
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 1))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 1))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 2))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 2))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2)), is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2)), is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 3))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2)), is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 3))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 4))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 4))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 5))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 5))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5)), is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5)), is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 6))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5)), is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 6))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 7))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 7))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6), BigDecimal.valueOf(i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6), BigDecimal.valueOf(i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 8))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6), BigDecimal.valueOf(i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 8))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                numberList.toArray(new BigDecimal[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                numberList.toArray(new BigDecimal[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 5)))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToArrayListFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                numberList.toArray(new BigDecimal[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 5)))));
      }
    }

  @Test
    public final void testNumbersToHashSetSelector()
    {
      final NumbersSelector<Byte, HashSet<Byte>> bytesToHashSetFilter = Numbers.numbersToHashSetSelector();
      assertThat(bytesToHashSetFilter, is(notNullValue()));
  
      final NumbersSelector<Short, HashSet<Short>> shortsToHashSetFilter = Numbers.numbersToHashSetSelector();
      assertThat(shortsToHashSetFilter, is(notNullValue()));
  
      final NumbersSelector<Integer, HashSet<Integer>> integersToHashSetFilter = Numbers.numbersToHashSetSelector();
      assertThat(integersToHashSetFilter, is(notNullValue()));
  
      final NumbersSelector<Long, HashSet<Long>> longsToHashSetFilter = Numbers.numbersToHashSetSelector();
      assertThat(longsToHashSetFilter, is(notNullValue()));
  
      final NumbersSelector<Float, HashSet<Float>> floatsToHashSetFilter = Numbers.numbersToHashSetSelector();
      assertThat(floatsToHashSetFilter, is(notNullValue()));
  
      final NumbersSelector<Double, HashSet<Double>> doublesToHashSetFilter = Numbers.numbersToHashSetSelector();
      assertThat(doublesToHashSetFilter, is(notNullValue()));
  
      final NumbersSelector<BigInteger, HashSet<BigInteger>> bigIntegersToHashSetFilter = Numbers.numbersToHashSetSelector();
      assertThat(bigIntegersToHashSetFilter, is(notNullValue()));
  
      final NumbersSelector<BigDecimal, HashSet<BigDecimal>> bigDecimalsToHashSetFilter = Numbers.numbersToHashSetSelector();
      assertThat(bigDecimalsToHashSetFilter, is(notNullValue()));
    }

  @SuppressWarnings("boxing")
    @Test
    public final void testBytesToHashSetSelector()
    {
      final CollectionCreater<Byte, Set<Byte>> collectionCreater = new ByteHashSetCreater();
  
      final List<Byte> byteList = BYTE_LIST;
  
      final NumbersSelector<Byte, HashSet<Byte>> numbersToHashSetFilter = Numbers.bytesToHashSetSelector();
  
      final Condition1<Byte> negativeNumber = new Condition1<Byte>() {
        @Override
        public boolean isMet(final Byte input)
        {
          return input.byteValue() < 0;
        }
      };
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (byte) i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (byte) i, (byte) (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (byte) i, (byte) (i + 1), (byte) (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, (byte) i, (byte) (i + 1), (byte) (i + 2), (byte) (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4)), is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), (byte) (i + 5)), is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), (byte) (i + 5), (byte) (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), (byte) (i + 5), (byte) (i + 6), (byte) (i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), byteList.toArray(new Byte[byteList.size()])),
            is(equalTo(getListOfNegative(byteList, getListOfNegative(collectionCreater, i, 5)))));
      }
  
      /* positive */
      final Condition1<Byte> positiveNumber = new Condition1<Byte>() {
        @Override
        public boolean isMet(final Byte input)
        {
          return input.byteValue() > 0;
        }
      };
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (byte) i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (byte) i, (byte) (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (byte) i, (byte) (i + 1), (byte) (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, (byte) i, (byte) (i + 1), (byte) (i + 2), (byte) (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4)), is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), (byte) (i + 5)), is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), (byte) (i + 5), (byte) (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), (byte) (i + 5), (byte) (i + 6), (byte) (i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), byteList.toArray(new Byte[byteList.size()])),
            is(equalTo(getListOfPositive(byteList, getListOfPositive(collectionCreater, i, 5)))));
      }
    }

  @SuppressWarnings("boxing")
    @Test
    public final void testShortsToHashSetSelector()
    {
      final CollectionCreater<Short, Set<Short>> collectionCreater = new ShortHashSetCreater();
  
      final List<Short> numberList = SHORT_LIST;
  
      final NumbersSelector<Short, HashSet<Short>> numbersToHashSetFilter = Numbers.shortsToHashSetSelector();
  
      /* negative */
      final Condition1<Short> negativeNumber = new Condition1<Short>() {
        @Override
        public boolean isMet(final Short input)
        {
          return input.shortValue() < 0;
        }
      };
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (short) i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (short) i, (short) (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (short) i, (short) (i + 1), (short) (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, (short) i, (short) (i + 1), (short) (i + 2), (short) (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4)), is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), (short) (i + 5)), is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), (short) (i + 5), (short) (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), (short) (i + 5), (short) (i + 6), (short) (i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), numberList.toArray(new Short[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, i, 5)))));
      }
  
      /* positive */
      final Condition1<Short> positiveNumber = new Condition1<Short>() {
        @Override
        public boolean isMet(final Short input)
        {
          return input.shortValue() > 0;
        }
      };
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (short) i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (short) i, (short) (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (short) i, (short) (i + 1), (short) (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, (short) i, (short) (i + 1), (short) (i + 2), (short) (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4)), is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), (short) (i + 5)), is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), (short) (i + 5), (short) (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), (short) (i + 5), (short) (i + 6), (short) (i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), numberList.toArray(new Short[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, i, 5)))));
      }
    }

  @SuppressWarnings("boxing")
    @Test
    public final void testIntegersToHashSetSelector()
    {
      final CollectionCreater<Integer, Set<Integer>> collectionCreater = new IntegerHashSetCreater();
  
      final List<Integer> numberList = INTEGER_LIST;
  
      final NumbersSelector<Integer, HashSet<Integer>> numbersToHashSetFilter = Numbers.integersToHashSetSelector();
  
      /* negative */
      final Condition1<Integer> negativeNumber = new Condition1<Integer>() {
        @Override
        public boolean isMet(final Integer input)
        {
          return input.intValue() < 0;
        }
      };
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
      for (int i = Integer.MAX_VALUE, size = Integer.MAX_VALUE - RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Integer[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, i, 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Integer[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, i, 5)))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Integer[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, i, 5)))));
      }
  
      /* positive */
      final Condition1<Integer> positiveNumber = new Condition1<Integer>() {
        @Override
        public boolean isMet(final Integer input)
        {
          return input.intValue() > 0;
        }
      };
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Integer[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, i, 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Integer[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, i, 5)))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Integer[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, i, 5)))));
      }
  
    }

  @SuppressWarnings("boxing")
    @Test
    public final void testLongsToHashSetSelector()
    {
      final CollectionCreater<Long, Set<Long>> collectionCreater = new LongHashSetCreater();
  
      final List<Long> numberList = LONG_LIST;
  
      final NumbersSelector<Long, HashSet<Long>> numbersToHashSetFilter = Numbers.longsToHashSetSelector();
  
      /* negative */
      final Condition1<Long> negativeNumber = new Condition1<Long>() {
        @Override
        public boolean isMet(final Long input)
        {
          return input.longValue() < 0;
        }
      };
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
      for (long i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
      for (long i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
      for (long i = Long.MAX_VALUE, size = Long.MAX_VALUE - RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
      for (long i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
      for (long i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
      for (long i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
      for (long i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
      for (long i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
      for (long i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Long[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, i, 5)))));
      }
      for (long i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Long[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, i, 5)))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Long[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, i, 5)))));
      }
  
      /* positive */
      final Condition1<Long> positiveNumber = new Condition1<Long>() {
        @Override
        public boolean isMet(final Long input)
        {
          return input.longValue() > 0;
        }
      };
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
      for (long i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
      for (long i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
      for (long i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
      for (long i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
      for (long i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
      for (long i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
      for (long i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
      for (long i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Long[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, i, 5)))));
      }
      for (long i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Long[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, i, 5)))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Long[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, i, 5)))));
      }
    }

  @SuppressWarnings("boxing")
    @Test
    public final void testFloatsToHashSetSelector()
    {
      final CollectionCreater<Float, Set<Float>> collectionCreater = new FloatHashSetCreater();
  
      final List<Float> numberList = FLOAT_LIST;
  
      final NumbersSelector<Float, HashSet<Float>> numbersToHashSetFilter = Numbers.floatsToHashSetSelector();
  
      /* negative */
      final Condition1<Float> negativeNumber = new Condition1<Float>() {
        @Override
        public boolean isMet(final Float input)
        {
          return input.floatValue() < 0;
        }
      };
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num),
            is(equalTo(getListOfNegative(collectionCreater, num, 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num),
            is(equalTo(getListOfNegative(collectionCreater, num, 1))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num),
            is(equalTo(getListOfNegative(collectionCreater, num, 1))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1)),
            is(equalTo(getListOfNegative(collectionCreater, num, 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1)),
            is(equalTo(getListOfNegative(collectionCreater, num, 2))));
      }
      for (int i = Short.MAX_VALUE, size = Short.MAX_VALUE - RANGE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1)),
            is(equalTo(getListOfNegative(collectionCreater, num, 2))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfNegative(collectionCreater, num, 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfNegative(collectionCreater, num, 3))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfNegative(collectionCreater, num, 3))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfNegative(collectionCreater, num, 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfNegative(collectionCreater, num, 4))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 2; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfNegative(collectionCreater, num, 4))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfNegative(collectionCreater, num, 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfNegative(collectionCreater, num, 5))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfNegative(collectionCreater, num, 5))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfNegative(collectionCreater, num, 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfNegative(collectionCreater, num, 6))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 4; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfNegative(collectionCreater, num, 6))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfNegative(collectionCreater, num, 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfNegative(collectionCreater, num, 7))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 5; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfNegative(collectionCreater, num, 7))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfNegative(collectionCreater, num, 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfNegative(collectionCreater, num, 8))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 6; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfNegative(collectionCreater, num, 8))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Float[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, num, 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Float[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, num, 5)))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Float[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, num, 5)))));
      }
  
      /* positive */
      final Condition1<Float> positiveNumber = new Condition1<Float>() {
        @Override
        public boolean isMet(final Float input)
        {
          return input.floatValue() > 0;
        }
      };
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num),
            is(equalTo(getListOfPositive(collectionCreater, num, 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num),
            is(equalTo(getListOfPositive(collectionCreater, num, 1))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num),
            is(equalTo(getListOfPositive(collectionCreater, num, 1))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1)),
            is(equalTo(getListOfPositive(collectionCreater, num, 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1)),
            is(equalTo(getListOfPositive(collectionCreater, num, 2))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1)),
            is(equalTo(getListOfPositive(collectionCreater, num, 2))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfPositive(collectionCreater, num, 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfPositive(collectionCreater, num, 3))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfPositive(collectionCreater, num, 3))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfPositive(collectionCreater, num, 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfPositive(collectionCreater, num, 4))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 2; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfPositive(collectionCreater, num, 4))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfPositive(collectionCreater, num, 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfPositive(collectionCreater, num, 5))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfPositive(collectionCreater, num, 5))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfPositive(collectionCreater, num, 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfPositive(collectionCreater, num, 6))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 4; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfPositive(collectionCreater, num, 6))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfPositive(collectionCreater, num, 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfPositive(collectionCreater, num, 7))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 5; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfPositive(collectionCreater, num, 7))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfPositive(collectionCreater, num, 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfPositive(collectionCreater, num, 8))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 6; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfPositive(collectionCreater, num, 8))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Float[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, num, 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Float[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, num, 5)))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Float[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, num, 5)))));
      }
    }

  @SuppressWarnings("boxing")
    @Test
    public final void testDoublesToHashSetSelector()
    {
      final CollectionCreater<Double, Set<Double>> collectionCreater = new DoubleHashSetCreater();
  
      final List<Double> numberList = DOUBLE_LIST;
  
      final NumbersSelector<Double, HashSet<Double>> numbersToHashSetFilter = Numbers.doublesToHashSetSelector();
  
      /* negative */
      final Condition1<Double> negativeNumber = new Condition1<Double>() {
        @Override
        public boolean isMet(final Double input)
        {
          return input.doubleValue() < 0;
        }
      };
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num),
            is(equalTo(getListOfNegative(collectionCreater, num, 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num),
            is(equalTo(getListOfNegative(collectionCreater, num, 1))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num),
            is(equalTo(getListOfNegative(collectionCreater, num, 1))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1)),
            is(equalTo(getListOfNegative(collectionCreater, num, 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1)),
            is(equalTo(getListOfNegative(collectionCreater, num, 2))));
      }
      for (int i = Integer.MAX_VALUE, size = Integer.MAX_VALUE - RANGE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1)),
            is(equalTo(getListOfNegative(collectionCreater, num, 2))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfNegative(collectionCreater, num, 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfNegative(collectionCreater, num, 3))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfNegative(collectionCreater, num, 3))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfNegative(collectionCreater, num, 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfNegative(collectionCreater, num, 4))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 2; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfNegative(collectionCreater, num, 4))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfNegative(collectionCreater, num, 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfNegative(collectionCreater, num, 5))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfNegative(collectionCreater, num, 5))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfNegative(collectionCreater, num, 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfNegative(collectionCreater, num, 6))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 4; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfNegative(collectionCreater, num, 6))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfNegative(collectionCreater, num, 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfNegative(collectionCreater, num, 7))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 5; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfNegative(collectionCreater, num, 7))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfNegative(collectionCreater, num, 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfNegative(collectionCreater, num, 8))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 6; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfNegative(collectionCreater, num, 8))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Double[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, num, 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Double[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, num, 5)))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Double[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, num, 5)))));
      }
  
      /* positive */
      final Condition1<Double> positiveNumber = new Condition1<Double>() {
        @Override
        public boolean isMet(final Double input)
        {
          return input.doubleValue() > 0;
        }
      };
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num),
            is(equalTo(getListOfPositive(collectionCreater, num, 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num),
            is(equalTo(getListOfPositive(collectionCreater, num, 1))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num),
            is(equalTo(getListOfPositive(collectionCreater, num, 1))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1)),
            is(equalTo(getListOfPositive(collectionCreater, num, 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1)),
            is(equalTo(getListOfPositive(collectionCreater, num, 2))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1)),
            is(equalTo(getListOfPositive(collectionCreater, num, 2))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfPositive(collectionCreater, num, 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfPositive(collectionCreater, num, 3))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfPositive(collectionCreater, num, 3))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfPositive(collectionCreater, num, 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfPositive(collectionCreater, num, 4))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 2; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfPositive(collectionCreater, num, 4))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfPositive(collectionCreater, num, 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfPositive(collectionCreater, num, 5))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfPositive(collectionCreater, num, 5))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfPositive(collectionCreater, num, 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfPositive(collectionCreater, num, 6))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 4; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfPositive(collectionCreater, num, 6))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfPositive(collectionCreater, num, 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfPositive(collectionCreater, num, 7))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 5; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfPositive(collectionCreater, num, 7))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfPositive(collectionCreater, num, 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfPositive(collectionCreater, num, 8))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 6; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfPositive(collectionCreater, num, 8))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Double[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, num, 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Double[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, num, 5)))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Double[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, num, 5)))));
      }
    }

  @Test
    public final void testBigIntegersToHashSetSelector()
    {
      final CollectionCreater<BigInteger, Set<BigInteger>> collectionCreater = new BigIntegerHashSetCreater();
  
      final List<BigInteger> numberList = BIG_INTEGER_LIST;
  
      final NumbersSelector<BigInteger, HashSet<BigInteger>> numbersToHashSetFilter = Numbers.bigIntegersToHashSetSelector();
  
      /* negative */
      final Condition1<BigInteger> negativeNumber = new Condition1<BigInteger>() {
        @Override
        public boolean isMet(final BigInteger input)
        {
          return 0 > input.compareTo(BigInteger.ZERO);
        }
      };
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 1))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 1))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 2))));
      }
      for (long i = Long.MAX_VALUE, size = Long.MAX_VALUE - RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 2))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2)), is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2)), is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 3))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2)), is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 3))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 4))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 4))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 5))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 5))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5)), is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5)), is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 6))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5)), is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 6))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 7))));
      }
      for (long i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 7))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 7))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6), BigInteger.valueOf(i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6), BigInteger.valueOf(i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 8))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6), BigInteger.valueOf(i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 8))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                numberList.toArray(new BigInteger[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, BigInteger.valueOf(i), 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                numberList.toArray(new BigInteger[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, BigInteger.valueOf(i), 5)))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                numberList.toArray(new BigInteger[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, BigInteger.valueOf(i), 5)))));
      }
  
      /* positive */
      final Condition1<BigInteger> positiveNumber = new Condition1<BigInteger>() {
        @Override
        public boolean isMet(final BigInteger input)
        {
          return 0 < input.compareTo(BigInteger.ZERO);
        }
      };
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 1))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 1))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 2))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 2))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2)), is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2)), is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 3))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2)), is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 3))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 4))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 4))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 5))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 5))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5)), is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5)), is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 6))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5)), is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 6))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 7))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 7))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6), BigInteger.valueOf(i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6), BigInteger.valueOf(i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 8))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6), BigInteger.valueOf(i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 8))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                numberList.toArray(new BigInteger[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, BigInteger.valueOf(i), 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                numberList.toArray(new BigInteger[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, BigInteger.valueOf(i), 5)))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                numberList.toArray(new BigInteger[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, BigInteger.valueOf(i), 5)))));
      }
    }

  @Test
    public final void testBigDecimalsToHashSetSelector()
    {
      final CollectionCreater<BigDecimal, Set<BigDecimal>> collectionCreater = new BigDecimalHashSetCreater();
  
      final List<BigDecimal> numberList = BIG_DECIMAL_LIST;
  
      final NumbersSelector<BigDecimal, HashSet<BigDecimal>> numbersToHashSetFilter = Numbers.bigDecimalsToHashSetSelector();
  
      /* negative */
      final Condition1<BigDecimal> negativeNumber = new Condition1<BigDecimal>() {
        @Override
        public boolean isMet(final BigDecimal input)
        {
          return 0 > input.compareTo(BigDecimal.ZERO);
        }
      };
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 1))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 1))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 2))));
      }
      for (long i = Long.MAX_VALUE, size = Long.MAX_VALUE - RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 2))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2)), is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2)), is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 3))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2)), is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 3))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 4))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 4))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 5))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 5))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5)), is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5)), is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 6))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5)), is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 6))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 7))));
      }
      for (long i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 7))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 7))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6), BigDecimal.valueOf(i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6), BigDecimal.valueOf(i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 8))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6), BigDecimal.valueOf(i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 8))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                numberList.toArray(new BigDecimal[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                numberList.toArray(new BigDecimal[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 5)))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                numberList.toArray(new BigDecimal[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 5)))));
      }
  
      /* positive */
      final Condition1<BigDecimal> positiveNumber = new Condition1<BigDecimal>() {
        @Override
        public boolean isMet(final BigDecimal input)
        {
          return 0 < input.compareTo(BigDecimal.ZERO);
        }
      };
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 1))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 1))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 2))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 2))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2)), is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2)), is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 3))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2)), is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 3))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 4))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 4))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 5))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 5))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5)), is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5)), is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 6))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5)), is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 6))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 7))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 7))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6), BigDecimal.valueOf(i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6), BigDecimal.valueOf(i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 8))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6), BigDecimal.valueOf(i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 8))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                numberList.toArray(new BigDecimal[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                numberList.toArray(new BigDecimal[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 5)))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                numberList.toArray(new BigDecimal[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 5)))));
      }
    }

  @Test
    public final void testNumbersToLinkedHashSetSelector()
    {
      final NumbersSelector<Byte, LinkedHashSet<Byte>> bytesToLinkedHashSetFilter = Numbers.numbersToLinkedHashSetSelector();
      assertThat(bytesToLinkedHashSetFilter, is(notNullValue()));
  
      final NumbersSelector<Short, LinkedHashSet<Short>> shortsToLinkedHashSetFilter =
        Numbers.numbersToLinkedHashSetSelector();
      assertThat(shortsToLinkedHashSetFilter, is(notNullValue()));
  
      final NumbersSelector<Integer, LinkedHashSet<Integer>> integersToLinkedHashSetFilter =
        Numbers.numbersToLinkedHashSetSelector();
      assertThat(integersToLinkedHashSetFilter, is(notNullValue()));
  
      final NumbersSelector<Long, LinkedHashSet<Long>> longsToLinkedHashSetFilter = Numbers.numbersToLinkedHashSetSelector();
      assertThat(longsToLinkedHashSetFilter, is(notNullValue()));
  
      final NumbersSelector<Float, LinkedHashSet<Float>> floatsToLinkedHashSetFilter =
        Numbers.numbersToLinkedHashSetSelector();
      assertThat(floatsToLinkedHashSetFilter, is(notNullValue()));
  
      final NumbersSelector<Double, LinkedHashSet<Double>> doublesToLinkedHashSetFilter =
        Numbers.numbersToLinkedHashSetSelector();
      assertThat(doublesToLinkedHashSetFilter, is(notNullValue()));
  
      final NumbersSelector<BigInteger, LinkedHashSet<BigInteger>> bigIntegersToLinkedHashSetFilter =
        Numbers.numbersToLinkedHashSetSelector();
      assertThat(bigIntegersToLinkedHashSetFilter, is(notNullValue()));
  
      final NumbersSelector<BigDecimal, LinkedHashSet<BigDecimal>> bigDecimalsToLinkedHashSetFilter =
        Numbers.numbersToLinkedHashSetSelector();
      assertThat(bigDecimalsToLinkedHashSetFilter, is(notNullValue()));
  
    }

  @SuppressWarnings("boxing")
    @Test
    public final void testBytesToLinkedHashSetSelector()
    {
      final CollectionCreater<Byte, Set<Byte>> collectionCreater = new ByteLinkedHashSetCreater();
  
      final List<Byte> byteList = BYTE_LIST;
  
      final NumbersSelector<Byte, LinkedHashSet<Byte>> numbersToHashSetFilter = Numbers.bytesToLinkedHashSetSelector();
  
      final Condition1<Byte> negativeNumber = new Condition1<Byte>() {
        @Override
        public boolean isMet(final Byte input)
        {
          return input.byteValue() < 0;
        }
      };
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (byte) i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (byte) i, (byte) (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (byte) i, (byte) (i + 1), (byte) (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, (byte) i, (byte) (i + 1), (byte) (i + 2), (byte) (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4)), is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), (byte) (i + 5)), is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), (byte) (i + 5), (byte) (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), (byte) (i + 5), (byte) (i + 6), (byte) (i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), byteList.toArray(new Byte[byteList.size()])),
            is(equalTo(getListOfNegative(byteList, getListOfNegative(collectionCreater, i, 5)))));
      }
  
      /* positive */
      final Condition1<Byte> positiveNumber = new Condition1<Byte>() {
        @Override
        public boolean isMet(final Byte input)
        {
          return input.byteValue() > 0;
        }
      };
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (byte) i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (byte) i, (byte) (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (byte) i, (byte) (i + 1), (byte) (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, (byte) i, (byte) (i + 1), (byte) (i + 2), (byte) (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4)), is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), (byte) (i + 5)), is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), (byte) (i + 5), (byte) (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), (byte) (i + 5), (byte) (i + 6), (byte) (i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
  
      for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (byte) i, (byte) (i + 1), (byte) (i + 2),
            (byte) (i + 3), (byte) (i + 4), byteList.toArray(new Byte[byteList.size()])),
            is(equalTo(getListOfPositive(byteList, getListOfPositive(collectionCreater, i, 5)))));
      }
    }

  @SuppressWarnings("boxing")
    @Test
    public final void testShortsToLinkedHashSetSelector()
    {
      final CollectionCreater<Short, Set<Short>> collectionCreater = new ShortLinkedHashSetCreater();
  
      final List<Short> numberList = SHORT_LIST;
  
      final NumbersSelector<Short, LinkedHashSet<Short>> numbersToHashSetFilter = Numbers.shortsToLinkedHashSetSelector();
  
      /* negative */
      final Condition1<Short> negativeNumber = new Condition1<Short>() {
        @Override
        public boolean isMet(final Short input)
        {
          return input.shortValue() < 0;
        }
      };
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (short) i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (short) i, (short) (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (short) i, (short) (i + 1), (short) (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, (short) i, (short) (i + 1), (short) (i + 2), (short) (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4)), is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), (short) (i + 5)), is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), (short) (i + 5), (short) (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), (short) (i + 5), (short) (i + 6), (short) (i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), numberList.toArray(new Short[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, i, 5)))));
      }
  
      /* positive */
      final Condition1<Short> positiveNumber = new Condition1<Short>() {
        @Override
        public boolean isMet(final Short input)
        {
          return input.shortValue() > 0;
        }
      };
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (short) i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (short) i, (short) (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (short) i, (short) (i + 1), (short) (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, (short) i, (short) (i + 1), (short) (i + 2), (short) (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4)), is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), (short) (i + 5)), is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), (short) (i + 5), (short) (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), (short) (i + 5), (short) (i + 6), (short) (i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, (short) i, (short) (i + 1), (short) (i + 2),
            (short) (i + 3), (short) (i + 4), numberList.toArray(new Short[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, i, 5)))));
      }
    }

  @SuppressWarnings("boxing")
    @Test
    public final void testIntegersToLinkedHashSetSelector()
    {
      final CollectionCreater<Integer, Set<Integer>> collectionCreater = new IntegerLinkedHashSetCreater();
  
      final List<Integer> numberList = INTEGER_LIST;
  
      final NumbersSelector<Integer, LinkedHashSet<Integer>> numbersToHashSetFilter =
        Numbers.integersToLinkedHashSetSelector();
  
      /* negative */
      final Condition1<Integer> negativeNumber = new Condition1<Integer>() {
        @Override
        public boolean isMet(final Integer input)
        {
          return input.intValue() < 0;
        }
      };
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
      for (int i = Integer.MAX_VALUE, size = Integer.MAX_VALUE - RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Integer[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, i, 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Integer[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, i, 5)))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Integer[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, i, 5)))));
      }
  
      /* positive */
      final Condition1<Integer> positiveNumber = new Condition1<Integer>() {
        @Override
        public boolean isMet(final Integer input)
        {
          return input.intValue() > 0;
        }
      };
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Integer[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, i, 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Integer[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, i, 5)))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Integer[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, i, 5)))));
      }
    }

  @SuppressWarnings("boxing")
    @Test
    public final void testLongsToLinkedHashSetSelector()
    {
      final CollectionCreater<Long, Set<Long>> collectionCreater = new LongLinkedHashSetCreater();
  
      final List<Long> numberList = LONG_LIST;
  
      final NumbersSelector<Long, LinkedHashSet<Long>> numbersToHashSetFilter = Numbers.longsToLinkedHashSetSelector();
  
      /* negative */
      final Condition1<Long> negativeNumber = new Condition1<Long>() {
        @Override
        public boolean isMet(final Long input)
        {
          return input.longValue() < 0;
        }
      };
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
      for (long i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i),
            is(equalTo(getListOfNegative(collectionCreater, i, 1))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
      for (long i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
      for (long i = Long.MAX_VALUE, size = Long.MAX_VALUE - RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, i, 2))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
      for (long i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfNegative(collectionCreater, i, 3))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
      for (long i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, i, 4))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
      for (long i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, i, 5))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
      for (long i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfNegative(collectionCreater, i, 6))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
      for (long i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, i, 7))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
      for (long i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfNegative(collectionCreater, i, 8))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Long[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, i, 5)))));
      }
      for (long i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Long[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, i, 5)))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Long[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, i, 5)))));
      }
  
      /* positive */
      final Condition1<Long> positiveNumber = new Condition1<Long>() {
        @Override
        public boolean isMet(final Long input)
        {
          return input.longValue() > 0;
        }
      };
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
      for (long i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i),
            is(equalTo(getListOfPositive(collectionCreater, i, 1))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
      for (long i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, i, 2))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
      for (long i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2)),
            is(equalTo(getListOfPositive(collectionCreater, i, 3))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
      for (long i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, i, 4))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
      for (long i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, i, 5))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
      for (long i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5)),
            is(equalTo(getListOfPositive(collectionCreater, i, 6))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
      for (long i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, i, 7))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
      for (long i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4), (i + 5), (i + 6),
            (i + 7)), is(equalTo(getListOfPositive(collectionCreater, i, 8))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Long[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, i, 5)))));
      }
      for (long i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Long[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, i, 5)))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, i, (i + 1), (i + 2), (i + 3), (i + 4),
                numberList.toArray(new Long[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, i, 5)))));
      }
    }

  @SuppressWarnings("boxing")
    @Test
    public final void testFloatsToLinkedHashSetSelector()
    {
      final CollectionCreater<Float, Set<Float>> collectionCreater = new FloatLinkedHashSetCreater();
  
      final List<Float> numberList = FLOAT_LIST;
  
      final NumbersSelector<Float, LinkedHashSet<Float>> numbersToHashSetFilter = Numbers.floatsToLinkedHashSetSelector();
  
      /* negative */
      final Condition1<Float> negativeNumber = new Condition1<Float>() {
        @Override
        public boolean isMet(final Float input)
        {
          return input.floatValue() < 0;
        }
      };
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num),
            is(equalTo(getListOfNegative(collectionCreater, num, 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num),
            is(equalTo(getListOfNegative(collectionCreater, num, 1))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num),
            is(equalTo(getListOfNegative(collectionCreater, num, 1))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1)),
            is(equalTo(getListOfNegative(collectionCreater, num, 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1)),
            is(equalTo(getListOfNegative(collectionCreater, num, 2))));
      }
      for (int i = Short.MAX_VALUE, size = Short.MAX_VALUE - RANGE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1)),
            is(equalTo(getListOfNegative(collectionCreater, num, 2))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfNegative(collectionCreater, num, 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfNegative(collectionCreater, num, 3))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfNegative(collectionCreater, num, 3))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfNegative(collectionCreater, num, 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfNegative(collectionCreater, num, 4))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 2; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfNegative(collectionCreater, num, 4))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfNegative(collectionCreater, num, 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfNegative(collectionCreater, num, 5))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfNegative(collectionCreater, num, 5))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfNegative(collectionCreater, num, 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfNegative(collectionCreater, num, 6))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 4; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfNegative(collectionCreater, num, 6))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfNegative(collectionCreater, num, 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfNegative(collectionCreater, num, 7))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 5; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfNegative(collectionCreater, num, 7))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfNegative(collectionCreater, num, 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfNegative(collectionCreater, num, 8))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 6; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfNegative(collectionCreater, num, 8))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Float[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, num, 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Float[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, num, 5)))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Float[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, num, 5)))));
      }
  
      /* positive */
      final Condition1<Float> positiveNumber = new Condition1<Float>() {
        @Override
        public boolean isMet(final Float input)
        {
          return input.floatValue() > 0;
        }
      };
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num),
            is(equalTo(getListOfPositive(collectionCreater, num, 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num),
            is(equalTo(getListOfPositive(collectionCreater, num, 1))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num),
            is(equalTo(getListOfPositive(collectionCreater, num, 1))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1)),
            is(equalTo(getListOfPositive(collectionCreater, num, 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1)),
            is(equalTo(getListOfPositive(collectionCreater, num, 2))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1)),
            is(equalTo(getListOfPositive(collectionCreater, num, 2))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfPositive(collectionCreater, num, 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfPositive(collectionCreater, num, 3))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 1; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfPositive(collectionCreater, num, 3))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfPositive(collectionCreater, num, 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfPositive(collectionCreater, num, 4))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 2; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfPositive(collectionCreater, num, 4))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfPositive(collectionCreater, num, 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfPositive(collectionCreater, num, 5))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfPositive(collectionCreater, num, 5))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfPositive(collectionCreater, num, 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfPositive(collectionCreater, num, 6))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 4; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfPositive(collectionCreater, num, 6))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfPositive(collectionCreater, num, 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfPositive(collectionCreater, num, 7))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 5; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfPositive(collectionCreater, num, 7))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfPositive(collectionCreater, num, 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfPositive(collectionCreater, num, 8))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 6; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfPositive(collectionCreater, num, 8))));
      }
  
      for (int i = Short.MIN_VALUE, size = Short.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Float[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, num, 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Float[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, num, 5)))));
      }
      for (int i = Short.MAX_VALUE - RANGE, size = Short.MAX_VALUE - 3; i < size; i++)
      {
        final float num = toFloat(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Float[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, num, 5)))));
      }
    }

  @SuppressWarnings("boxing")
    @Test
    public final void testDoublesToLinkedHashSetSelector()
    {
      final CollectionCreater<Double, Set<Double>> collectionCreater = new DoubleLinkedHashSetCreater();
  
      final List<Double> numberList = DOUBLE_LIST;
  
      final NumbersSelector<Double, LinkedHashSet<Double>> numbersToHashSetFilter = Numbers.doublesToLinkedHashSetSelector();
  
      /* negative */
      final Condition1<Double> negativeNumber = new Condition1<Double>() {
        @Override
        public boolean isMet(final Double input)
        {
          return input.doubleValue() < 0;
        }
      };
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num),
            is(equalTo(getListOfNegative(collectionCreater, num, 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num),
            is(equalTo(getListOfNegative(collectionCreater, num, 1))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num),
            is(equalTo(getListOfNegative(collectionCreater, num, 1))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1)),
            is(equalTo(getListOfNegative(collectionCreater, num, 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1)),
            is(equalTo(getListOfNegative(collectionCreater, num, 2))));
      }
      for (int i = Integer.MAX_VALUE, size = Integer.MAX_VALUE - RANGE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1)),
            is(equalTo(getListOfNegative(collectionCreater, num, 2))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfNegative(collectionCreater, num, 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfNegative(collectionCreater, num, 3))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfNegative(collectionCreater, num, 3))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfNegative(collectionCreater, num, 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfNegative(collectionCreater, num, 4))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 2; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfNegative(collectionCreater, num, 4))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfNegative(collectionCreater, num, 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfNegative(collectionCreater, num, 5))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfNegative(collectionCreater, num, 5))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfNegative(collectionCreater, num, 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfNegative(collectionCreater, num, 6))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 4; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfNegative(collectionCreater, num, 6))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfNegative(collectionCreater, num, 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfNegative(collectionCreater, num, 7))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 5; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfNegative(collectionCreater, num, 7))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfNegative(collectionCreater, num, 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfNegative(collectionCreater, num, 8))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 6; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfNegative(collectionCreater, num, 8))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Double[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, num, 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Double[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, num, 5)))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Double[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, num, 5)))));
      }
  
      /* positive */
      final Condition1<Double> positiveNumber = new Condition1<Double>() {
        @Override
        public boolean isMet(final Double input)
        {
          return input.doubleValue() > 0;
        }
      };
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num),
            is(equalTo(getListOfPositive(collectionCreater, num, 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num),
            is(equalTo(getListOfPositive(collectionCreater, num, 1))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num),
            is(equalTo(getListOfPositive(collectionCreater, num, 1))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1)),
            is(equalTo(getListOfPositive(collectionCreater, num, 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1)),
            is(equalTo(getListOfPositive(collectionCreater, num, 2))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1)),
            is(equalTo(getListOfPositive(collectionCreater, num, 2))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfPositive(collectionCreater, num, 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfPositive(collectionCreater, num, 3))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 1; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2)),
            is(equalTo(getListOfPositive(collectionCreater, num, 3))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfPositive(collectionCreater, num, 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfPositive(collectionCreater, num, 4))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 2; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3)),
            is(equalTo(getListOfPositive(collectionCreater, num, 4))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfPositive(collectionCreater, num, 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfPositive(collectionCreater, num, 5))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4)),
            is(equalTo(getListOfPositive(collectionCreater, num, 5))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfPositive(collectionCreater, num, 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfPositive(collectionCreater, num, 6))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 4; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4), (num + 5)),
            is(equalTo(getListOfPositive(collectionCreater, num, 6))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfPositive(collectionCreater, num, 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfPositive(collectionCreater, num, 7))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 5; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6)), is(equalTo(getListOfPositive(collectionCreater, num, 7))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfPositive(collectionCreater, num, 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfPositive(collectionCreater, num, 8))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 6; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
            (num + 5), (num + 6), (num + 7)), is(equalTo(getListOfPositive(collectionCreater, num, 8))));
      }
  
      for (int i = Integer.MIN_VALUE, size = Integer.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Double[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, num, 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Double[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, num, 5)))));
      }
      for (int i = Integer.MAX_VALUE - RANGE, size = Integer.MAX_VALUE - 3; i < size; i++)
      {
        final double num = toDouble(i);
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, num, (num + 1), (num + 2), (num + 3), (num + 4),
                numberList.toArray(new Double[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, num, 5)))));
      }
    }

  @Test
    public final void testBigIntegersToLinkedHashSetSelector()
    {
      final CollectionCreater<BigInteger, Set<BigInteger>> collectionCreater = new BigIntegerLinkedHashSetCreater();
  
      final List<BigInteger> numberList = BIG_INTEGER_LIST;
  
      final NumbersSelector<BigInteger, LinkedHashSet<BigInteger>> numbersToHashSetFilter =
        Numbers.bigIntegersToLinkedHashSetSelector();
  
      /* negative */
      final Condition1<BigInteger> negativeNumber = new Condition1<BigInteger>() {
        @Override
        public boolean isMet(final BigInteger input)
        {
          return 0 > input.compareTo(BigInteger.ZERO);
        }
      };
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 1))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 1))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 2))));
      }
      for (long i = Long.MAX_VALUE, size = Long.MAX_VALUE - RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 2))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2)), is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2)), is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 3))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2)), is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 3))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 4))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 4))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 5))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 5))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5)), is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5)), is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 6))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5)), is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 6))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 7))));
      }
      for (long i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 7))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 7))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6), BigInteger.valueOf(i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6), BigInteger.valueOf(i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 8))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6), BigInteger.valueOf(i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, BigInteger.valueOf(i), 8))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                numberList.toArray(new BigInteger[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, BigInteger.valueOf(i), 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                numberList.toArray(new BigInteger[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, BigInteger.valueOf(i), 5)))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                numberList.toArray(new BigInteger[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, BigInteger.valueOf(i), 5)))));
      }
  
      /* positive */
      final Condition1<BigInteger> positiveNumber = new Condition1<BigInteger>() {
        @Override
        public boolean isMet(final BigInteger input)
        {
          return 0 < input.compareTo(BigInteger.ZERO);
        }
      };
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 1))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 1))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 2))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 2))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2)), is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2)), is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 3))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2)), is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 3))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 4))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 4))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 5))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 5))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5)), is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5)), is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 6))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5)), is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 6))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 7))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 7))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6), BigInteger.valueOf(i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6), BigInteger.valueOf(i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 8))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                BigInteger.valueOf(i + 5), BigInteger.valueOf(i + 6), BigInteger.valueOf(i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, BigInteger.valueOf(i), 8))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                numberList.toArray(new BigInteger[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, BigInteger.valueOf(i), 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                numberList.toArray(new BigInteger[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, BigInteger.valueOf(i), 5)))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigInteger.valueOf(i), BigInteger.valueOf(i + 1),
                BigInteger.valueOf(i + 2), BigInteger.valueOf(i + 3), BigInteger.valueOf(i + 4),
                numberList.toArray(new BigInteger[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, BigInteger.valueOf(i), 5)))));
      }
    }

  @Test
    public final void testBigDecimalsToLinkedHashSetSelector()
    {
      final CollectionCreater<BigDecimal, Set<BigDecimal>> collectionCreater = new BigDecimalLinkedHashSetCreater();
  
      final List<BigDecimal> numberList = BIG_DECIMAL_LIST;
  
      final NumbersSelector<BigDecimal, LinkedHashSet<BigDecimal>> numbersToHashSetFilter =
        Numbers.bigDecimalsToLinkedHashSetSelector();
  
      /* negative */
      final Condition1<BigDecimal> negativeNumber = new Condition1<BigDecimal>() {
        @Override
        public boolean isMet(final BigDecimal input)
        {
          return 0 > input.compareTo(BigDecimal.ZERO);
        }
      };
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 1))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 1))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 2))));
      }
      for (long i = Long.MAX_VALUE, size = Long.MAX_VALUE - RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 2))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2)), is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2)), is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 3))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2)), is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 3))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 4))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 4))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 5))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 5))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5)), is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5)), is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 6))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5)), is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 6))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 7))));
      }
      for (long i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 7))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 7))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6), BigDecimal.valueOf(i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6), BigDecimal.valueOf(i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 8))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6), BigDecimal.valueOf(i + 7)),
            is(equalTo(getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 8))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                numberList.toArray(new BigDecimal[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                numberList.toArray(new BigDecimal[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 5)))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(negativeNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                numberList.toArray(new BigDecimal[numberList.size()])),
            is(equalTo(getListOfNegative(numberList, getListOfNegative(collectionCreater, BigDecimal.valueOf(i), 5)))));
      }
  
      /* positive */
      final Condition1<BigDecimal> positiveNumber = new Condition1<BigDecimal>() {
        @Override
        public boolean isMet(final BigDecimal input)
        {
          return 0 < input.compareTo(BigDecimal.ZERO);
        }
      };
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 1))));
      }
      for (int i = -RANGE, size = RANGE + 1; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 1))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 1))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 2))));
      }
      for (int i = -RANGE, size = RANGE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 2))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE; i < size; i++)
      {
        assertThat(numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 2))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2)), is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 3))));
      }
      for (int i = -RANGE, size = RANGE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2)), is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 3))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 1; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2)), is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 3))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 4))));
      }
      for (int i = -RANGE, size = RANGE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 4))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 2; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 4))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 5))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 5))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 5))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5)), is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 6))));
      }
      for (int i = -RANGE, size = RANGE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5)), is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 6))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 4; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5)), is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 6))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 7))));
      }
      for (int i = -RANGE, size = RANGE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 7))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 5; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 7))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6), BigDecimal.valueOf(i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 8))));
      }
      for (int i = -RANGE, size = RANGE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6), BigDecimal.valueOf(i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 8))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 6; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                BigDecimal.valueOf(i + 5), BigDecimal.valueOf(i + 6), BigDecimal.valueOf(i + 7)),
            is(equalTo(getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 8))));
      }
  
      for (long i = Long.MIN_VALUE, size = Long.MIN_VALUE + RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                numberList.toArray(new BigDecimal[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 5)))));
      }
      for (int i = -RANGE, size = RANGE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                numberList.toArray(new BigDecimal[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 5)))));
      }
      for (long i = Long.MAX_VALUE - RANGE, size = Long.MAX_VALUE - 3; i < size; i++)
      {
        assertThat(
            numbersToHashSetFilter.select(positiveNumber, BigDecimal.valueOf(i), BigDecimal.valueOf(i + 1),
                BigDecimal.valueOf(i + 2), BigDecimal.valueOf(i + 3), BigDecimal.valueOf(i + 4),
                numberList.toArray(new BigDecimal[numberList.size()])),
            is(equalTo(getListOfPositive(numberList, getListOfPositive(collectionCreater, BigDecimal.valueOf(i), 5)))));
      }
    }

}
