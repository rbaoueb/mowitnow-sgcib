package com.sgcib.mowitnow;

import com.sgcib.mowitnow.exception.CommandInvalidException;
import com.sgcib.mowitnow.exception.GroundFileInvalidException;
import com.sgcib.mowitnow.exception.GroundFileNotFoundException;
import com.sgcib.mowitnow.service.FileReader;
import com.sgcib.mowitnow.service.MowerFileReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileTest {

    private String fileName;
    private List<String> lines;
    private final List<RuntimeException> exceptions = new ArrayList<>();

    @Given("the following file name {string}")
    public void the_following_file_name(String fileName) {
        this.fileName = fileName;
    }

    @When("I parse the file")
    public void i_parse_the_file() {
        FileReader reader = MowerFileReader.getInstance();
        try {
            lines = reader.parse(fileName);
        }catch(RuntimeException ex) {
            exceptions.add(ex);
        }
    }

    @Then("I got a result")
    public void i_got_a_result(int result) {
        assertNotNull(lines);
        assertEquals(lines.size(), result);
        assertEquals((lines.size() - 1) % 2, 0);
    }

    @Then("I got a result is multiple of {int} with header")
    public void i_got_a_result_is_multiple_of_with_header(Integer factor) {
        assertNotNull(lines);
        assertTrue(lines.size() >= 1);
        assertEquals((lines.size() - 1) % factor, 0);
    }

    @Then("I got GroundFileInvalidException")
    public void i_got_ground_file_invalid_exception() {
        assertEquals(exceptions.size(), 1);
        assertTrue(exceptions.get(0) instanceof GroundFileInvalidException);
    }

    @Then("I got GroundFileInvalidException with result not multiple of {int} with header")
    public void i_got_ground_file_invalid_exception_with_result_not_multiple_of_with_header(Integer factor) {
        assertEquals(exceptions.size(), 1);
        assertTrue(exceptions.get(0) instanceof GroundFileInvalidException);
    }

    @Then("I got GroundFileNotFoundException")
    public void i_got_ground_file_not_found_exception() {
        assertEquals(exceptions.size(), 1);
        assertTrue(exceptions.get(0) instanceof GroundFileNotFoundException);
    }

    @Then("I got any exception")
    public void i_got_any_exception() {
        System.err.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertEquals(exceptions.size(), 1);
    }
}
