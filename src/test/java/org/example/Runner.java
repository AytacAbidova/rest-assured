package org.example;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
        features = "src/test/resources",
        glue = "org.example",
        plugin = "pretty"
)
public class Runner extends AbstractTestNGCucumberTests {



}
