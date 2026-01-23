package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import static io.cucumber.testng.CucumberOptions.SnippetType.CAMELCASE;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps","hooks","constants","customTypes"},
        tags = "@All",
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json"

        },
        monochrome = true
)

public class RunnerTest extends AbstractTestNGCucumberTests {

}
