package org.example.time;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DateTimeRangeIntersector {
    private final List<List<LocalDateTimeRange>> timeRanges;

    private DateTimeRangeIntersector(List<List<LocalDateTimeRange>> timeRanges) {
        this.timeRanges = timeRanges;
    }

    public static DateTimeRangeIntersector createWithSortedRanges(List<List<LocalDateTimeRange>> timeRanges) {
        for (List<LocalDateTimeRange> rangeList : timeRanges) {
            rangeList.sort(Comparator.comparing(LocalDateTimeRange::getStart));
        }
        return new DateTimeRangeIntersector(timeRanges);
    }

    public List<List<LocalDateTimeRange>> getTimeRanges() {
        return timeRanges;
    }

    public List<LocalDateTimeRange> intersectAll() {
        if (timeRanges.isEmpty()) {
            return new ArrayList<>();
        }

        List<LocalDateTimeRange> intersected = new ArrayList<>(timeRanges.get(0));

        for (int i = 1; i < timeRanges.size(); i++) {
            intersected = Intersect(intersected, timeRanges.get(i));
            if (intersected.isEmpty()) {
                break;
            }
        }

        return intersected;
    }

    private List<LocalDateTimeRange> Intersect(List<LocalDateTimeRange> timeRanges1, List<LocalDateTimeRange> timeRanges2) {
        List<LocalDateTimeRange> intersected = new ArrayList<>();

        int index1 = 0;
        int index2 = 0;
        while (index1 < timeRanges1.size() && index2 < timeRanges2.size()) {
            var timeRange1 = timeRanges1.get(index1);
            var timeRange2 = timeRanges2.get(index2);

            LocalDateTimeRange intersection = getIntersection(timeRange1, timeRange2);
            if (intersection != null) {
                intersected.add(intersection);
            }

            if (timeRange1.getEnd().isBefore(timeRange2.getEnd())) {
                index1++;
            } else {
                index2++;
            }
        }

        return intersected;
    }

    private LocalDateTimeRange getIntersection(LocalDateTimeRange range1, LocalDateTimeRange range2) {
        LocalDateTime startMax = range1.getStart().isAfter(range2.getStart()) ? range1.getStart() : range2.getStart();
        LocalDateTime endMin = range1.getEnd().isBefore(range2.getEnd()) ? range1.getEnd() : range2.getEnd();
        if (startMax.isBefore(endMin)) {
            return new LocalDateTimeRange(startMax, endMin);
        }
        return null;
    }

}
