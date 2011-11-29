/**
 * 
 */
package org.elixirian.kommonlee.collect;

import java.util.Iterator;

import org.elixirian.kommonlee.type.Clearable;
import org.elixirian.kommonlee.type.EmptinessCheckable;
import org.elixirian.kommonlee.type.LengthMeasurable;

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
 * @version 0.0.1 (2011-09-18)
 */
public interface WritableCollection<E> extends Kollection<E>, Iterable<E>, LengthMeasurable, EmptinessCheckable,
    Clearable
{
  @Override
  Iterator<E> iterator();

  @Override
  int length();

  @Override
  boolean isEmpty();

  @Override
  boolean isNotEmpty();

  @Override
  boolean contains(Object element);

  @Override
  boolean containsAll(Kollection<?> kollection);

  boolean remove(E element);

  boolean removeAll(Kollection<?> elements);

  boolean retainAll(Kollection<?> elements);

  @Override
  void clear();

  boolean add(E element);

  boolean addAll(Kollection<? extends E> commonCollection);

  @Override
  int hashCode();

  @Override
  boolean equals(Object element);
}
