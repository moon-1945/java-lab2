package org.example;

import org.example.time.ZonedDateTimeRange;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private final List<ZonedDateTimeRange> ranges;

    public User(String name) {
        this(name, new ArrayList<>());
    }

    public User(String name, List<ZonedDateTimeRange> ranges) {
        this.name = name;
        this.ranges = ranges;
    }

    public String getName() {
        return name;
    }

    public List<ZonedDateTimeRange> getRanges() {
        return ranges;
    }
}
