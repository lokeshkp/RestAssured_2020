package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features ="C:/Users/lokes/eclipse-workspace/Sep2020RestAssured/src/test/java/features", 
					glue= {"stepdefs"},tags = "@sanity", 
					plugin = "json:target/jsonRepos/cucumber-report.json")
public class RestRunner {}
