package org.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.car.App;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(features = "features", glue = {"org.test"}, plugin = {"html:target/cucumber-reports.html"})

public class RunCucumberTest {
    App myApp;
    public RunCucumberTest(App myApp){
       this.myApp=myApp;
    }
}
