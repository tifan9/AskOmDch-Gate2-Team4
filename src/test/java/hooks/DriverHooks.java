package hooks;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class DriverHooks {
    private WebDriver driver;

    @Before
    public void before(){
        driver = DriverFactory.setUpDriver(System.getProperty("browser","chrome"));
    }

//    @After
//    public void after(){
//        driver.quit();
//    }
}
