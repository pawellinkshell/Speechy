package pl.pawel.linkshell.speechy.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MainPageController.class)
class MainPageControllerTest {

    @Value("${welcome.message}")
    private String message;

    @Autowired
    private MockMvc mockMvc;

    private final List<String> expectedList = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

    @DisplayName("Should Perform / Page And Check That Message Is Same Like In Properties For Root Page")
    @Test
    public void shouldPerformWelcomePageAndCheckThatMessageIsSameLikeInPropertiesForRootPage() throws Exception {
        // given
        String speechy = "Speechy";
        final ResultActions resultActions = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"))
                .andExpect(model().attribute("message", equalTo(message)))
                .andExpect(model().attribute("tasks", is(expectedList)))
                .andExpect(content().string(containsString("Hello, " + message)));
        // when
        final MvcResult mvcResult = resultActions.andReturn();

        // then
        final ModelAndView mv = mvcResult.getModelAndView();
    }

    // Get request with Param
    @DisplayName("Should Perform Hello Page With Getting Param")
    @Test
    public void shouldPerformHelloPageWithGettingParam() throws Exception {
        // given
        final ResultActions resultActions = mockMvc.perform(get("/hello").param("name", "I Love Kotlin!"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"))
                .andExpect(model().attribute("message", equalTo("I Love Kotlin!")))
                .andExpect(content().string(containsString("Hello, I Love Kotlin!")));

        // when
        final MvcResult mvcResult = resultActions.andReturn();

        // then
        final ModelAndView mv = mvcResult.getModelAndView();
    }
}