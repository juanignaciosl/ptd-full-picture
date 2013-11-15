package com.juanignaciosl.ptd.fullpicture.bucket;

import org.concordion.api.extension.Extensions;
import org.concordion.ext.ScreenshotExtension;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

@RunWith(ConcordionRunner.class)
@Extensions(ScreenshotExtension.class)
public class BucketTemperatureMonitoringFixture {
	WebDriver driver;

	BucketTemperatureSimulatorPage simulatorPage;
	BucketTemperatureMonitoringPage monitoringPage;
	
	@Before
	public void init() {
		 HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver();
		 htmlUnitDriver.setJavascriptEnabled(true);
		 this.driver = htmlUnitDriver;
	}

	public String bucketTemperature(String inputTemperature) {
		setTemperature(inputTemperature);
		monitoringPage = new BucketTemperatureMonitoringPage(driver);
		return monitoringPage.getTemperature();
	}
	
	public boolean weeklyChartDisplayed() {
		monitoringPage = new BucketTemperatureMonitoringPage(driver);
		return monitoringPage.getWeekTemperature().size() == 7;
	}
	
	public boolean isRed(String inputTemperature) {
		setTemperature(inputTemperature);
		monitoringPage = new BucketTemperatureMonitoringPage(driver);
		return "red".equals(monitoringPage.getTemperatureColor());
	}

	private void setTemperature(String inputTemperature) {
		simulatorPage = new BucketTemperatureSimulatorPage(driver);
		simulatorPage.setTemperature(inputTemperature);
	}

}
