package pl.pawel.linkshell.speechy.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.pawel.linkshell.speechy.model.SpeechData;
import pl.pawel.linkshell.speechy.model.SpeechForm;
import pl.pawel.linkshell.speechy.service.impl.DefaultTextService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainPageController {

    private static final Logger LOG = LoggerFactory.getLogger(MainPageController.class);

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    @Autowired
    private DefaultTextService defaultTextService;

    @GetMapping("/")
    public String indexPage(final Model model) {
        initPage(model);

        SpeechData speechData = defaultTextService.lastSpeech();
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", message);
        parameters.put("speech", speechData);

        model.addAttribute("controller", parameters);

        return getPage(Page.WELCOME);
    }

    private void initPage(final Model model) {
        model.addAttribute("command", new SpeechForm());
    }

    @GetMapping("/hello")
    public String indexWithParam(@RequestParam(name = "path", required = false, defaultValue = StringUtils.EMPTY) final String name, final Model model) {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", name);
        model.addAttribute("controller", parameters);

        return getPage(Page.WELCOME);
    }

    @PostMapping("/analyze")
    public String analyzePage(@ModelAttribute("command") SpeechForm command, final Model model) {
        int wordsPerMinute = command.getWordsPerMinute() != 0 ? command.getWordsPerMinute() : 120; //FIXME: add WPM on page
        defaultTextService.analyze(command.getTextareaField(), wordsPerMinute);

        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", StringUtils.EMPTY);
        model.addAttribute("controller", parameters);

        return "redirect:" + getPage(Page.ROOT);
    }

    private String getPage(final Page page) {
        return page.getPath();
    }

    enum Page {
        ROOT("/"),
        WELCOME("welcome");

        private String path;

        Page(final String pageName) {
            this.path = pageName;
        }

        public String getPath() {
            return path;
        }
    }

}
