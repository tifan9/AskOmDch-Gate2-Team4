package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import static io.cucumber.testng.CucumberOptions.SnippetType.CAMELCASE;

@CucumberOptions(
        features = "src/test/resources/features/ApplyCoupon.feature",
        glue = {"steps","hooks","constants","factory","pages","utils"},
        tags = "@firstone",

        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json"

        },
        monochrome = true
)

public class RunnerTest extends AbstractTestNGCucumberTests {

}
