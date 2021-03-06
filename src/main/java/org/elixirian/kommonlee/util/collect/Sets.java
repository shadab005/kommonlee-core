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
package org.elixirian.kommonlee.util.collect;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import org.elixirian.kommonlee.util.CommonConstants;

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
 *     ___  _____                                _____
 *    /   \/    /_________  ___ ____ __ ______  /    /   ______  ______
 *   /        / /  ___ \  \/  //___// //     / /    /   /  ___ \/  ___ \
 *  /        \ /  _____/\    //   //   __   / /    /___/  _____/  _____/
 * /____/\____\\_____/   \__//___//___/ /__/ /________/\_____/ \_____/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2011-04-03)
 */
public final class Sets
{
  private Sets() throws IllegalAccessException
  {
    throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
  }

  /**
   * Returns an immutable empty set returned from Collections.emptySet() if the given set parameter contains a null
   * reference. Otherwise it returns the parameter set back to the caller.
   * 
   * @param set
   *          the given Set object. It can be null.
   * @return an immutable empty set returned from Collections.emptySet() if the given set parameter contains a null
   *         reference. Otherwise it returns the parameter set back to the caller.
   */
  public static <E> Set<E> immutableEmptySetIfNull(final Set<E> set)
  {
    return null == set ? Collections.<E> emptySet() : set;
  }

  public static <E> HashSet<E> newHashSet()
  {
    return new HashSet<E>();
  }

  public static <E> HashSet<E> newHashSet(final Collection<? extends E> elements)
  {
    return new HashSet<E>(elements);
  }

  public static <E> HashSet<E> newHashSet(final Iterable<? extends E> elements)
  {
    final HashSet<E> set = newHashSet();
    for (final E e : elements)
      set.add(e);
    return set;
  }

  public static <E> HashSet<E> newHashSet(final Iterator<? extends E> elements)
  {
    final HashSet<E> set = newHashSet();
    while (elements.hasNext())
      set.add(elements.next());
    return set;
  }

  public static <E> HashSet<E> newHashSet(final E... elements)
  {
    final HashSet<E> set = newHashSet();
    Collections.addAll(set, elements);
    return set;
  }

  public static <E> HashSet<E> newHashSetWithInitialCapacity(final int initialCapacity)
  {
    return new HashSet<E>(initialCapacity);
  }

  public static <E> LinkedHashSet<E> newLinkedHashSet()
  {
    return new LinkedHashSet<E>();
  }

  public static <E> LinkedHashSet<E> newLinkedHashSet(final Collection<? extends E> elements)
  {
    return new LinkedHashSet<E>(elements);
  }

  public static <E> LinkedHashSet<E> newLinkedHashSet(final Iterable<? extends E> elements)
  {
    final LinkedHashSet<E> set = newLinkedHashSet();
    for (final E e : elements)
      set.add(e);
    return set;
  }

  public static <E> LinkedHashSet<E> newLinkedHashSet(final Iterator<? extends E> elements)
  {
    final LinkedHashSet<E> set = newLinkedHashSet();
    while (elements.hasNext())
      set.add(elements.next());
    return set;
  }

  public static <E> LinkedHashSet<E> newLinkedHashSet(final E... elements)
  {
    final LinkedHashSet<E> set = newLinkedHashSet();
    Collections.addAll(set, elements);
    return set;
  }

  public static <E> LinkedHashSet<E> newLinkedHashSetWithInitialCapacity(final int initialCapacity)
  {
    return new LinkedHashSet<E>(initialCapacity);
  }

  public static <E extends Comparable<E>> TreeSet<E> newTreeSet()
  {
    return new TreeSet<E>();
  }

  public static <E extends Comparable<E>> TreeSet<E> newTreeSet(final Collection<? extends E> elements)
  {
    return new TreeSet<E>(elements);
  }

  public static <E extends Comparable<E>> TreeSet<E> newTreeSet(final Iterable<? extends E> elements)
  {
    final TreeSet<E> set = newTreeSet();
    for (final E e : elements)
      set.add(e);
    return set;
  }

  public static <E extends Comparable<E>> TreeSet<E> newTreeSet(final Iterator<? extends E> elements)
  {
    final TreeSet<E> set = newTreeSet();
    while (elements.hasNext())
      set.add(elements.next());
    return set;
  }

  public static <E extends Comparable<E>> TreeSet<E> newTreeSet(final E... elements)
  {
    final TreeSet<E> set = newTreeSet();
    Collections.addAll(set, elements);
    return set;
  }

  public static <E> TreeSet<E> newTreeSet(final Comparator<? super E> comparator)
  {
    return new TreeSet<E>(comparator);
  }

  public static <E> TreeSet<E> newTreeSet(final Comparator<? super E> comparator, final Collection<? extends E> elements)
  {
    final TreeSet<E> set = new TreeSet<E>(comparator);
    set.addAll(elements);
    return set;
  }

  public static <E> TreeSet<E> newTreeSet(final Comparator<? super E> comparator, final Iterable<? extends E> elements)
  {
    final TreeSet<E> set = new TreeSet<E>(comparator);
    for (final E e : elements)
      set.add(e);
    return set;
  }

  public static <E> TreeSet<E> newTreeSet(final Comparator<? super E> comparator, final Iterator<? extends E> elements)
  {
    final TreeSet<E> set = new TreeSet<E>(comparator);
    while (elements.hasNext())
      set.add(elements.next());
    return set;
  }

  public static <E> TreeSet<E> newTreeSet(final Comparator<? super E> comparator, final E... elements)
  {
    final TreeSet<E> set = new TreeSet<E>(comparator);
    Collections.addAll(set, elements);
    return set;
  }
}