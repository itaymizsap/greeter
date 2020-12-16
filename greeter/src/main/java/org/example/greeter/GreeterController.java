package org.example.greeter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreeterController {

    private final DayTimeClient dayTimeClient;

    public GreeterController(@Autowired DayTimeClient dayTimeClient) {
        this.dayTimeClient = dayTimeClient;
    }

    @GetMapping(path = "/greet/{person}")
    public String greetPerson(@PathVariable String person) {
        return "Good " + dayTimeClient.getDayTimeText() + " " + person;
    }

    @GetMapping(path = "/greet/{person}", params = "hour")
    public String greetPerson(@PathVariable String person, @RequestParam(name = "hour") String hour) {
        return "Good " + dayTimeClient.getDayTimeText(hour) + " " + person;
    }
}
