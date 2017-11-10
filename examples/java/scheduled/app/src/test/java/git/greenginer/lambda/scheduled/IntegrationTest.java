package git.greenginer.lambda.scheduled;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(tags = {"~@nyi"}) //,plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"})
public class IntegrationTest {
}