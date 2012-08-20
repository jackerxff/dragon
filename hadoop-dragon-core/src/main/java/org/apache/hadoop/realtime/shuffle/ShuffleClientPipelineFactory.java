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
package org.apache.hadoop.realtime.shuffle;

import org.apache.hadoop.conf.Configuration;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelUpstreamHandler;
import org.jboss.netty.channel.DefaultChannelPipeline;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 */
public class ShuffleClientPipelineFactory implements ChannelPipelineFactory {

  private SimpleChannelUpstreamHandler handler;
  private Configuration conf;

  public ShuffleClientPipelineFactory(Configuration conf,
      SimpleChannelUpstreamHandler handler) {
    this.conf = conf;
    this.handler = handler;
  }

  @Override
  public ChannelPipeline getPipeline() throws Exception {
    ChannelPipeline pipeline = new DefaultChannelPipeline();
    pipeline.addLast("decoder", new ResponseDecoder(conf));
    pipeline.addLast("encoder", new RequestEncoder(conf));
    pipeline.addLast("handler", handler);
    return pipeline;
  }

}
