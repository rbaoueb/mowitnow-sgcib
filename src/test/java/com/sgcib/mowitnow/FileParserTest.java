package com.sgcib.mowitnow;

import com.sgcib.mowitnow.exception.GroundFileInvalidException;
import com.sgcib.mowitnow.exception.GroundFileNotFoundException;
import com.sgcib.mowitnow.service.FileReader;
import com.sgcib.mowitnow.service.MowerFileReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileParserTest {

    private String fileName;
    private List<String> lines;
    private final List<RuntimeException> exceptions = new ArrayList<>();

    @Given("the following file name {string}")
    public void theFollowingFileName(String fileName) {
        this.fileName = fileName;
    }

    @When("I parse the file")
    public void iParseTheFile() {
        FileReader reader = MowerFileReader.getInstance();
        try {
            lines = reader.parse(fileName);
        }catch(RuntimeException ex) {
            exceptions.add(ex);
        }
    }

    @Then("I got a result")
    public void iGotResult(int result) {
        assertNotNull(lines);
        assertEquals(lines.size(), result);
        assertEquals((lines.size() - 1) % 2, 0);
    }

    @Then("I got a result is multiple of {int} with header")
    public void iGotResultIsMultipleOfWithHeader(Integer factor) {
        assertNotNull(lines);
        assertTrue(lines.size() >= 1);
        assertEquals((lines.size() - 1) % factor, 0);
    }

    @Then("I got GroundFileInvalidException")
    public void iGotGroundFileInvalidException() {
        assertEquals(exceptions.size(), 1);
        assertTrue(exceptions.get(0) instanceof GroundFileInvalidException);
    }

    @Then("I got GroundFileInvalidException with result not multiple of {int} with header")
    public void iGotGroundFileInvalidExceptionWithResultNotMultipleOfWithHeader(Integer factor) {
        assertEquals(exceptions.size(), 1);
        assertTrue(exceptions.get(0) instanceof GroundFileInvalidException);
    }

    @Then("I got GroundFileNotFoundException")
    public void iGotGroundFileNotFoundException() {
        assertEquals(exceptions.size(), 1);
        assertTrue(exceptions.get(0) instanceof GroundFileNotFoundException);
    }

    @Then("I got any exception")
    public void iGotAnyException() {
        assertEquals(exceptions.size(), 1);
    }
}
