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
package org.elixirian.kommonlee.collect.primitive.mutable;

import java.util.List;

import org.elixirian.kommonlee.collect.primitive.ByteList;
import org.elixirian.kommonlee.type.functional.primitive.ByteCondition1;
import org.elixirian.kommonlee.type.functional.primitive.ByteToByteFunction1;
import org.elixirian.kommonlee.type.mutable.Clearable;
import org.elixirian.kommonlee.type.mutable.Trimmable;

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
 * @version 0.0.1 (2013-07-13)
 */
public interface MutableByteList extends ByteList, Trimmable<MutableByteList>, Clearable
{
  @Override
  MutableByteList select(ByteCondition1 condition);

  @Override
  MutableByteList mapToByte(ByteToByteFunction1 function);

  @Override
  MutableByteList mapToByteSelectively(ByteCondition1 condition, ByteToByteFunction1 function);

  @Override
  List<Byte> convertTo();

  @Override
  MutableByteList subList(int fromIndex, int toIndex);

  boolean add(byte element);

  boolean addAll(byte[] elements);

  boolean addAll(ByteList elements);

  byte removeAt(int index);

  boolean remove(byte element);

  int getIncreaseBy();

  @Override
  MutableByteList trim();

  @Override
  void clear();
}