package org.elixirian.kommonlee.functional.collect;

import static org.elixirian.kommonlee.util.collect.Sets.*;

import java.util.HashSet;

import org.elixirian.kommonlee.type.functional.Condition1;
import org.elixirian.kommonlee.type.selector.ArraySelector;

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
 * @version 0.0.1 (2011-02-18)
 * @param <E>
 *          type of element to be filtered to remove all the null element in the given array.
 * @param <C>
 */
public final class ArrayToHashSetSelector<E> implements ArraySelector<E, Condition1<? super E>, HashSet<E>>
{
  ArrayToHashSetSelector()
  {
  }

  @Override
  public HashSet<E> select(final E[] source, final Condition1<? super E> condition)
  {
    final HashSet<E> set = newHashSet();
    for (final E each : source)
    {
      if (condition.isMet(each))
      {
        set.add(each);
      }
    }
    return set;
  }
}