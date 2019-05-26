package pl.pawel.linkshell.speechy.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class MainPageController {

    private static final Logger LOG = LoggerFactory.getLogger(MainPageController.class);

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;
    private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

    @GetMapping("/")
    public String indexPage(final Model model) {
        model.addAttribute("message", message);
        model.addAttribute("tasks", tasks);

        return "welcome"; //view
    }

    @GetMapping("/hello")
    public String indexWithParam(@RequestParam(name = "name", required = false, defaultValue = StringUtils.EMPTY) final String name, final Model model) {
        model.addAttribute("message", name);

        return "welcome"; //view
    }


}
