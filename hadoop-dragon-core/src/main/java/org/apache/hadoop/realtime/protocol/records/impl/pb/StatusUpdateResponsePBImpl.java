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

package org.apache.hadoop.realtime.protocol.records.impl.pb;

import org.apache.hadoop.realtime.protocol.records.StatusUpdateResponse;
import org.apache.hadoop.yarn.api.records.ProtoBase;
import org.apache.hadoop.yarn.proto.DragonServiceProtos.StatusUpdateResponseProto;
import org.apache.hadoop.yarn.proto.DragonServiceProtos.StatusUpdateResponseProtoOrBuilder;

public class StatusUpdateResponsePBImpl extends
    ProtoBase<StatusUpdateResponseProto> implements StatusUpdateResponse {
  StatusUpdateResponseProto proto = StatusUpdateResponseProto
      .getDefaultInstance();
  StatusUpdateResponseProto.Builder builder = null;
  boolean viaProto = false;

  private Boolean result = null;

  public StatusUpdateResponsePBImpl() {
    builder = StatusUpdateResponseProto.newBuilder();
  }

  public StatusUpdateResponsePBImpl(StatusUpdateResponseProto proto) {
    this.proto = proto;
    viaProto = true;
  }

  public StatusUpdateResponseProto getProto() {
    mergeLocalToProto();
    proto = viaProto ? proto : builder.build();
    viaProto = true;
    return proto;
  }

  private void mergeLocalToBuilder() {
    if (this.result != null) {
      builder.setResult(this.result);
    }
  }

  private void mergeLocalToProto() {
    if (viaProto)
      maybeInitBuilder();
    mergeLocalToBuilder();
    proto = builder.build();
    viaProto = true;
  }

  private void maybeInitBuilder() {
    if (viaProto || builder == null) {
      builder = StatusUpdateResponseProto.newBuilder(proto);
    }
    viaProto = false;
  }

  @Override
  public boolean getResult() {
    StatusUpdateResponseProtoOrBuilder p = viaProto ? proto : builder;
    if (this.result != null) {
      return this.result;
    }
    this.result = p.getResult();
    return this.result;
  }

  @Override
  public void setResult(boolean result) {
    maybeInitBuilder();
    this.result = result;
  }

}
