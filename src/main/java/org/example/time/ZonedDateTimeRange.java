package org.example.time;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeRange {
    private ZonedDateTime start;
    private ZonedDateTime end;

    public ZonedDateTimeRange(ZonedDateTime start, ZonedDateTime end) {
        this.start = start;
        this.end = end;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }

    public LocalDateTimeRange toLocalDateTime() {
        return new LocalDateTimeRange(getStart().toLocalDateTime(), getEnd().toLocalDateTime());
    }

    public ZonedDateTimeRange withZoneSameInstant(ZoneId zoneId) {
        ZonedDateTime newStart = this.start.withZoneSameInstant(zoneId);
        ZonedDateTime newEnd = this.end.withZoneSameInstant(zoneId);
        return new ZonedDateTimeRange(newStart, newEnd);
    }
}
