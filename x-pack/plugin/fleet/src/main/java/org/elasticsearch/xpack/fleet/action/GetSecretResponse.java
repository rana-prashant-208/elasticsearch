/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0; you may not use this file except in compliance with the Elastic License
 * 2.0.
 */

package org.elasticsearch.xpack.fleet.action;

import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.xcontent.ToXContent;
import org.elasticsearch.xcontent.ToXContentObject;
import org.elasticsearch.xcontent.XContentBuilder;

import java.io.IOException;
import java.util.Objects;

public class GetSecretResponse extends ActionResponse implements ToXContentObject {

    private String id;
    private final String secretValue;

    public GetSecretResponse(StreamInput in) throws IOException {
        super(in);
        id = in.readString();
        secretValue = in.readString();
    }

    public GetSecretResponse(String id, String secretValue) {
        this.id = id;
        this.secretValue = secretValue;
    }

    public String id() {
        return id;
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeString(id);
        out.writeString(secretValue);
    }

    @Override
    public XContentBuilder toXContent(XContentBuilder builder, ToXContent.Params params) throws IOException {
        builder.startObject();
        builder.field("id", id);
        builder.field("value", secretValue);
        return builder.endObject();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetSecretResponse that = (GetSecretResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(secretValue, that.secretValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, secretValue);
    }
}
