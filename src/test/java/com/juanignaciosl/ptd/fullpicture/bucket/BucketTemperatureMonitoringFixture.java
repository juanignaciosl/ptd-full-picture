package com.juanignaciosl.ptd.fullpicture.bucket;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

@RunWith(ConcordionRunner.class)
public class BucketTemperatureMonitoringFixture {

	public String bucketTemperature(String inputTemperature) {
		final WebDriver driver = new HtmlUnitDriver();

		final BucketTemperatureSimulatorPage simulatorPage = new BucketTemperatureSimulatorPage(
				driver);
		simulatorPage.setTemperature(inputTemperature);

		final BucketTemperatureMonitoringPage monitoringPage = new BucketTemperatureMonitoringPage(
				driver);
		return monitoringPage.getTemperature();
	}

}
