package utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static java.time.LocalDateTime.now;
import static java.util.Objects.isNull;

public final class DateTimeUtils {

    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    private static final ZoneId UTC_ZONE_ID = ZoneId.of("UTC");

    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static ZonedDateTime toZonedDateTime(final Instant dateTime) {
        return isNull(dateTime) ? null : ZonedDateTime.ofInstant(dateTime, UTC_ZONE_ID);
    }

    public static ZonedDateTime toZonedDateTime(final LocalDateTime dateTime) {
        return toZonedDateTime(dateTime, ZoneId.systemDefault());
    }

    public static ZonedDateTime toZonedDateTime(final LocalDateTime dateTime, final ZoneId zoneId) {
        return isNull(dateTime) ? null : ZonedDateTime.of(dateTime, isNull(zoneId) ? ZoneId.systemDefault() : zoneId);
    }

    public static LocalDateTime toLocalDateTime(final ZonedDateTime dateTime) {
        return isNull(dateTime) ? null : dateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime toLocalDateTime(final Instant dateTime) {
        return isNull(dateTime) ? null : LocalDateTime.ofInstant(dateTime, UTC_ZONE_ID);
    }

    public static LocalDate toLocalDate(final Instant dateTime) {
        return isNull(dateTime) ? null : LocalDate.ofInstant(dateTime, UTC_ZONE_ID);
    }

    public static Instant toInstant(final ZonedDateTime dateTime) {
        return isNull(dateTime) ? null : dateTime.toInstant();
    }

    public static Instant toInstantNullsafe(final ZonedDateTime dateTime) {
        return isNull(dateTime) ? Instant.now() : dateTime.toInstant();
    }

    public static Instant toInstant(final LocalDate dateTime) {
        return isNull(dateTime) ? null : dateTime.atStartOfDay(ZoneId.systemDefault()).toInstant();
    }

    public static ZonedDateTime parseZonedDateTime(final String val) {
        return ZonedDateTime.parse(val, ISO);
    }

    public static LocalDateTime getNow() {
        return now(UTC_ZONE_ID);
    }

    public static LocalDate getDateOrCreate(final LocalDate rawDate) {
        return isNull(rawDate) ? LocalDate.now() : rawDate;
    }

    public static Instant toNonStrictBorder(final Instant time) {
        return time.minusMillis(1);
    }

    public static long calcElapsedTime(final long startTime) {
        return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startTime);
    }

    private DateTimeUtils() {
        super();
    }
}

