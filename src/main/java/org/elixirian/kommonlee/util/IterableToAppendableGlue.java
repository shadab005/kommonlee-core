package org.elixirian.kommonlee.util;

import static org.elixirian.kommonlee.util.Objects.*;

import java.io.IOException;
import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

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
 * @version 0.0.1 (2010-07-05)
 */
public final class IterableToAppendableGlue implements ToAppendableGlue<Iterable<?>>
{
  private final AppendingAction appendingAction;

  private IterableToAppendableGlue(@NonNull final AppendingAction appendingAction)
  {
    this.appendingAction = appendingAction;
  }

  AppendingAction getAppendingAction()
  {
    return appendingAction;
  }

  @NonNull
  public static IterableToAppendableGlue withSeparator(@Nullable final String separator)
  {
    return new IterableToAppendableGlue(SimpleAppendingAction.with(separator));
  }

  @NonNull
  public static IterableToAppendableGlue withoutSeparator()
  {
    return new IterableToAppendableGlue(SimpleAppendingAction.withoutSeparator());
  }

  @NonNull
  private <A extends Appendable, E> A glue0(@NonNull final A appendable, @NonNull final Iterable<E> iterable)
      throws IOException
  {
    final Iterator<E> iterator = iterable.iterator();
    if (iterator.hasNext())
    {
      SimpleAppendingAction.APPENDING_ACTION_WITHOUT_SEPARATOR.append(appendable, iterator.next());
      while (iterator.hasNext())
      {
        appendingAction.append(appendable, iterator.next());
      }
    }
    return appendable;
  }

  @NonNull
  @Override
  public <A extends Appendable> A glue(final A appendable, final Iterable<?> iterable)
  {
    try
    {
      /* @formatter:off */
			return glue0(notNull(appendable),
						  notNull(iterable));
			/* @formatter:on */
    }
    catch (final IOException e)
    {
      throw new IllegalArgumentException(e);
    }
  }
}