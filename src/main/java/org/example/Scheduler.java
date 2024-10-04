package org.example;

import org.example.time.DateTimeRangeIntersector;
import org.example.time.LocalDateTimeRange;
import org.example.time.ZonedDateTimeRange;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Scheduler {
    private List<User> users;
    private List<ZonedDateTimeRange> intersectionRange;

    public Scheduler(List<User> users) {
        this.users = users;
    }

    public Scheduler() {
        users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public List<ZonedDateTimeRange> getIntersectionRange() {
        if (intersectionRange == null) {
            var usersDateRanges = users.stream()
                    .map(user -> user.getRanges()
                            .stream()
                            .map(z -> z.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime())
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());

            DateTimeRangeIntersector intersector = DateTimeRangeIntersector.createWithSortedRanges(usersDateRanges);
            intersectionRange = intersector.intersectAll().stream().map(l -> l.atZone(ZoneId.of("UTC"))).collect(Collectors.toList());
        }

        return intersectionRange;
    }

    public ZonedDateTimeRange getTimeRangeAtLeastNHours(int hours)
    {
        if(intersectionRange == null || intersectionRange.isEmpty())
            return null;

        for (ZonedDateTimeRange range : getIntersectionRange()) {
            if(Duration.between(range.getStart(), range.getEnd()).toHours() >= hours)
                return range;
        }
        return null;
    }

    public ZonedDateTimeRange getMaxIntersectionRangeDuration()
    {
        if(intersectionRange == null || intersectionRange.isEmpty())
            return null;

        ZonedDateTimeRange maxRange = getIntersectionRange().get(0);
        Duration maxDuration = Duration.between(maxRange.getStart(), maxRange.getEnd());
        for (ZonedDateTimeRange range : getIntersectionRange()) {
            if(Duration.between(range.getStart(), range.getEnd()).compareTo(maxDuration) > 0) {
                maxRange = range;
                maxDuration = Duration.between(range.getStart(), range.getEnd());
            }
        }

        return maxRange;
    }
}
