package com.juanignaciosl.ptd.fullpicture.bucket;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

@RunWith(ConcordionRunner.class)
public class BucketTemperatureMonitoringFixture {
	WebDriver driver = new HtmlUnitDriver();

	BucketTemperatureSimulatorPage simulatorPage;
	BucketTemperatureMonitoringPage monitoringPage;

	public String bucketTemperature(String inputTemperature) {
		simulatorPage = new BucketTemperatureSimulatorPage(driver);
		simulatorPage.setTemperature(inputTemperature);
		monitoringPage = new BucketTemperatureMonitoringPage(driver);
		return monitoringPage.getTemperature();
	}
	
	public boolean weeklyChartDisplayed() {
		monitoringPage = new BucketTemperatureMonitoringPage(driver);
		return monitoringPage.getWeekTemperature().size() == 7;
	}

}
