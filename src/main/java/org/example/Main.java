package org.example;


import org.example.time.ZonedDateTimeRange;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        User user1 = createUser1();
        User user2 = createUser2();

        Scheduler scheduler = new Scheduler();
        scheduler.getUsers().add(user1);
        scheduler.getUsers().add(user2);

        List<ZonedDateTimeRange> intersectionRanges = scheduler.getIntersectionRange();

        System.out.println("Intersection of schedules:");
        for (var range : intersectionRanges) {
            System.out.println(range.getStart() + " to " + range.getEnd());
        }

        int requiredHours = 2;
        ZonedDateTimeRange rangeAtLeastNHours = scheduler.getTimeRangeAtLeastNHours(requiredHours);
        if (rangeAtLeastNHours != null) {
            System.out.println("Found a time range with at least " + requiredHours + " hours: " +
                    rangeAtLeastNHours.getStart() + " to " + rangeAtLeastNHours.getEnd());
        } else {
            System.out.println("No time range found with at least " + requiredHours + " hours.");
        }

        ZonedDateTimeRange maxDurationRange = scheduler.getMaxIntersectionRangeDuration();
        if (maxDurationRange != null) {
            System.out.println("Maximum intersection range duration: " +
                    maxDurationRange.getStart() + " to " + maxDurationRange.getEnd());
        } else {
            System.out.println("No intersection ranges available.");
        }

    }

    private static User createUser1() {
        List<ZonedDateTimeRange> ranges = new ArrayList<>();
        ranges.add(new ZonedDateTimeRange(
                ZonedDateTime.parse("2024-10-01T09:00:00Z"),
                ZonedDateTime.parse("2024-10-01T15:00:00Z")
        ));
        ranges.add(new ZonedDateTimeRange(
                ZonedDateTime.parse("2024-10-01T14:00:00Z"),
                ZonedDateTime.parse("2024-10-01T16:00:00Z")
        ));
        return new User("Alice", ranges);
    }

    private static User createUser2() {
        List<ZonedDateTimeRange> ranges = new ArrayList<>();
        ranges.add(new ZonedDateTimeRange(
                ZonedDateTime.parse("2024-10-01T10:00:00Z"),
                ZonedDateTime.parse("2024-10-01T14:00:00Z")
        ));
        ranges.add(new ZonedDateTimeRange(
                ZonedDateTime.parse("2024-10-01T15:00:00Z"),
                ZonedDateTime.parse("2024-10-01T17:00:00Z")
        ));
        return new User("Bob", ranges);
    }
}