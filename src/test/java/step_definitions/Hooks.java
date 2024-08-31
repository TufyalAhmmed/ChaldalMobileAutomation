package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

import java.net.MalformedURLException;

import static utilities.DriverSetup.*;

public class Hooks {
   @BeforeAll
    public static void beforeAll() throws MalformedURLException {
        setAndroidDriver();
    }
//    @After
//    public void after(Scenario scenario){
//        takeScreenShot(scenario);
//    }


    @AfterAll
    public static void afterAll(){
        quiteAndroidDriver();
    }

}