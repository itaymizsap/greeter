package org.example.daytime;

import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class DayTimeResolver {
    String resolve() {
        return DayTimeEnum.eval().getName();
    }

    public String resolve(short hour) {
        return DayTimeEnum.eval(hour).getName();
    }

    private enum DayTimeEnum {
        MORNING("morning",4, 12),
        AFTERNOON("afternoon",MORNING.end, 17),
        EVENING("evening", AFTERNOON.end, 21),
        NIGHT("night", EVENING.end, MORNING.start);

        private final String name;
        private final int start;
        private final int end;

        String getName() {
            return name;
        }

        DayTimeEnum(String name, int start, int end) {
            this.name = name;
            this.start = start;
            this.end = end;
        }

        static DayTimeEnum eval() {
            int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            return getDayTimeEnumByHour(currentHour);
        }

        static DayTimeEnum eval(short hour) {
            return getDayTimeEnumByHour(hour);
        }

        private static DayTimeEnum getDayTimeEnumByHour(int hour) {
            if (hour < 0 || hour > 23 ) {
                throw new IllegalArgumentException("Could not evaluate day-time hour: " + hour);
            }

            for (DayTimeEnum dayTime: DayTimeEnum.values()) {
                if (dayTime.start <= hour && hour < dayTime.end ||
                    //night time logic:
                    (dayTime.start > dayTime.end && (dayTime.start <= hour || hour < dayTime.end))) {
                    return dayTime;
                }
            }

            throw new IllegalArgumentException("Could not evaluate day-time hour: " + hour);
        }
    }
}
