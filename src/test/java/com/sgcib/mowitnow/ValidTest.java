package com.sgcib.mowitnow;

import com.sgcib.mowitnow.exception.CommandInvalidException;
import com.sgcib.mowitnow.exception.HeaderFileInvalidException;
import com.sgcib.mowitnow.exception.PositionInvalidException;
import com.sgcib.mowitnow.model.GardenGround;
import com.sgcib.mowitnow.service.MowerService;
import com.sgcib.mowitnow.service.MowerServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ValidTest {

    private List<String> content;
    private GardenGround ground;
    private final List<RuntimeException> exceptions = new ArrayList<>();

    @BeforeEach
    public void beforeEach() {
        exceptions.clear();
    }

    @Given("the following lines:")
    public void the_following_lines(List<String> content) {
        this.content = content;
    }

    @When("I process lines")
    public void i_process_lines() {
        MowerService service = MowerServiceImpl.getInstance();
        try {
            ground = service.process(content);
        }catch (RuntimeException ex) {
            exceptions.add(ex);
        }
    }
    @Then("I got the result")
    public void got_the_result(List<String> result) {
        assertNotNull(ground.mowers());
        assertEquals(ground.mowers().size(), result.size());
        for(int i=0; i<ground.mowers().size(); i++) {
            assertEquals(ground.mowers().get(i).toString(), result.get(i));
        }
    }

    @Then("I got HeaderFileInvalidException")
    public void i_got_header_file_invalid_exception() {
        assertEquals(exceptions.size(), 1);
        assertTrue(exceptions.get(0) instanceof HeaderFileInvalidException);
    }

    @Then("I got PositionInvalidException")
    public void i_got_position_invalid_exception() {
        assertEquals(exceptions.size(), 1);
        assertTrue(exceptions.get(0) instanceof PositionInvalidException);
    }

    @Then("I got CommandInvalidException")
    public void i_got_command_invalid_exception() {
        assertEquals(exceptions.size(), 1);
        assertTrue(exceptions.get(0) instanceof CommandInvalidException);
    }

}
