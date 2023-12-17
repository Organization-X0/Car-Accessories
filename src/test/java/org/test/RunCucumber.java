package org.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.car.App;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(features = "features", glue = {"org.Test"}, plugin = {"html:target/cucumber-reports.html"})

public class RunCucumber {
    App myApp;
    public RunCucumber(App myApp){
       this.myApp=myApp;
    }
}
