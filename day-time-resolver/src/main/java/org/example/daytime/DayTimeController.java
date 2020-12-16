package org.example.daytime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class DayTimeController {

    private static final String KEY = "daytime";

    private final DayTimeResolver dayTimeTextService;

    public DayTimeController(@Autowired DayTimeResolver dayTimeTextService) {
        this.dayTimeTextService = dayTimeTextService;
    }

    @GetMapping(path = "/api/day-time-simple")
    public Map<String, String> getCurrentDayTimeText() {
        Map<String, String> responseBodyJson = Collections.singletonMap(KEY, this.dayTimeTextService.resolve());
        return responseBodyJson;
    }

    @GetMapping(path = "/api/day-time", params = "hour")
    public Map<String, String> getDayTimeTextForQueryHour(@RequestParam("hour") String hour) {
        Map<String, String> responseBodyJson;

        if ("now".equals(hour)) {
            return getCurrentDayTimeText();
        }

        try {
            responseBodyJson = Collections.singletonMap(KEY, this.dayTimeTextService.resolve(Short.parseShort(hour)));
        } catch (NumberFormatException e) {
            throw new RuntimeException("Illegal hour", e);
        }

        return responseBodyJson;
    }
}
