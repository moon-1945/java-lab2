package org.example.time;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeRange {
    private LocalDateTime start;
    private LocalDateTime end;

    public LocalDateTimeRange(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public ZonedDateTimeRange atZone(ZoneId zoneId) {
        return new ZonedDateTimeRange(getStart().atZone(zoneId), getEnd().atZone(zoneId));
    }
}
