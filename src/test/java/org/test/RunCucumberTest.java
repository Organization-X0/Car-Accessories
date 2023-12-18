package org.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.car.App;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

@RunWith(Cucumber.class)
@CucumberOptions(features = "features", glue = {"org.test"}, plugin = {"html:target/cucumber-reports.html"})

public class RunCucumberTest {
    @Mock
    App myApp;

    @org.junit.Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    public RunCucumberTest(){
    }
}