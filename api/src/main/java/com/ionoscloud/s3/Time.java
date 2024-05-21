package com.ionoscloud.s3;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/** Time formatters for S3 APIs. */
public class Time {
  public static final ZoneId UTC = ZoneId.of("Z");

  public static final DateTimeFormatter AMZ_DATE_FORMAT =
      DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'", Locale.US).withZone(UTC);

  public static final DateTimeFormatter RESPONSE_DATE_FORMAT =
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':'mm':'ss'.'SSS'Z'", Locale.US).withZone(UTC);

  // Formatted string is convertible to LocalDate only, not to LocalDateTime or ZonedDateTime.
  // Below example shows how to use this to get ZonedDateTime.
  // LocalDate.parse("20200225", SIGNER_DATE_FORMAT).atStartOfDay(UTC);
  public static final DateTimeFormatter SIGNER_DATE_FORMAT =
      DateTimeFormatter.ofPattern("yyyyMMdd", Locale.US).withZone(UTC);

  public static final DateTimeFormatter HTTP_HEADER_DATE_FORMAT =
      DateTimeFormatter.ofPattern("EEE',' dd MMM yyyy HH':'mm':'ss 'GMT'", Locale.US).withZone(UTC);

  public static final DateTimeFormatter EXPIRATION_DATE_FORMAT = RESPONSE_DATE_FORMAT;

  private Time() {}
}
