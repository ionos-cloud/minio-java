package com.ionoscloud.s3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/** KMS type of Server-side encryption. */
public class ServerSideEncryptionKms extends ServerSideEncryption {
  private static final ObjectMapper objectMapper = new ObjectMapper();
  private final Map<String, String> headers;

  public ServerSideEncryptionKms(String keyId, Map<String, String> context)
      throws JsonProcessingException {
    if (keyId == null) {
      throw new IllegalArgumentException("Key ID cannot be null");
    }

    Map<String, String> headers = new HashMap<>();
    headers.put("X-Amz-Server-Side-Encryption", "aws:kms");
    headers.put("X-Amz-Server-Side-Encryption-Aws-Kms-Key-Id", keyId);
    if (context != null) {
      headers.put(
          "X-Amz-Server-Side-Encryption-Context",
          Base64.getEncoder()
              .encodeToString(
                  objectMapper.writeValueAsString(context).getBytes(StandardCharsets.UTF_8)));
    }

    this.headers = Collections.unmodifiableMap(headers);
  }

  @Override
  public final Map<String, String> headers() {
    return headers;
  }

  @Override
  public String toString() {
    return "SSE-KMS";
  }
}
