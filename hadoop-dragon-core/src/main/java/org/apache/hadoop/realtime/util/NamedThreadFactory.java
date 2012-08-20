/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.realtime.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 */
public class NamedThreadFactory implements ThreadFactory {

  static final AtomicInteger poolNumber = new AtomicInteger(1);

  final AtomicInteger threadNumber = new AtomicInteger(1);
  final ThreadGroup group;
  final String namePrefix;
  final boolean isDaemon;

  public NamedThreadFactory() {
    this("pool");
  }

  public NamedThreadFactory(String name) {
    this(name, false);
  }

  public NamedThreadFactory(String preffix, boolean daemon) {
    SecurityManager s = System.getSecurityManager();
    group =
        (s != null) ? s.getThreadGroup() : Thread.currentThread()
            .getThreadGroup();
    namePrefix = preffix + "-" + poolNumber.getAndIncrement() + "-thread-";
    isDaemon = daemon;
  }

  public Thread newThread(Runnable r) {
    Thread t =
        new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
    t.setDaemon(isDaemon);
    if (t.getPriority() != Thread.NORM_PRIORITY) {
      t.setPriority(Thread.NORM_PRIORITY);
    }
    return t;
  }

}