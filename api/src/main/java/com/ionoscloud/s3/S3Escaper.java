

package com.ionoscloud.s3;

import com.google.common.escape.Escaper;
import com.google.common.net.UrlEscapers;

public class S3Escaper {
  private static final Escaper ESCAPER = UrlEscapers.urlPathSegmentEscaper();

  /** Returns S3 encoded string. */
  public static String encode(String str) {
    if (str == null) {
      return "";
    }

    StringBuilder builder = new StringBuilder();
    for (char ch : ESCAPER.escape(str).toCharArray()) {
      switch (ch) {
        case '!':
          builder.append("%21");
          break;
        case '$':
          builder.append("%24");
          break;
        case '&':
          builder.append("%26");
          break;
        case '\'':
          builder.append("%27");
          break;
        case '(':
          builder.append("%28");
          break;
        case ')':
          builder.append("%29");
          break;
        case '*':
          builder.append("%2A");
          break;
        case '+':
          builder.append("%2B");
          break;
        case ',':
          builder.append("%2C");
          break;
        case '/':
          builder.append("%2F");
          break;
        case ':':
          builder.append("%3A");
          break;
        case ';':
          builder.append("%3B");
          break;
        case '=':
          builder.append("%3D");
          break;
        case '@':
          builder.append("%40");
          break;
        case '[':
          builder.append("%5B");
          break;
        case ']':
          builder.append("%5D");
          break;
        default:
          builder.append(ch);
      }
    }
    return builder.toString();
  }

  /** Returns S3 encoded string of given path where multiple '/' are trimmed. */
  public static String encodePath(String path) {
    final StringBuilder encodedPath = new StringBuilder();
    for (String pathSegment : path.split("/")) {
      if (!pathSegment.isEmpty()) {
        if (encodedPath.length() > 0) {
          encodedPath.append("/");
        }
        encodedPath.append(S3Escaper.encode(pathSegment));
      }
    }

    if (path.startsWith("/")) {
      encodedPath.insert(0, "/");
    }
    if (path.endsWith("/")) {
      encodedPath.append("/");
    }

    return encodedPath.toString();
  }
}
