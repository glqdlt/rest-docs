package com.glqdlt.ex.springrestdocs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jhun
 * 2019-04-25
 */
@RestController
public class CounterRestController {

    public CounterRestController(@Autowired CounterService greetingService) {
        this.greetingService = greetingService;
    }

    private CounterService greetingService;

    @GetMapping("/api/{user}/greeting")
    public String greeting(@PathVariable(name = "user") String user) {
        return greetingService.greeting(user);
    }
}
