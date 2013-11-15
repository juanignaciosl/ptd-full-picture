package com.juanignaciosl.ptd.fullpicture.bucket;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BucketTemperatureMonitoringPage {

	private WebDriver driver;

	@FindBy(id = "temperature")
	private WebElement temperature;

	@FindBy(css = ".weekTemperature")
	private List<WebElement> weekTemperature;

	public BucketTemperatureMonitoringPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.driver
				.get("http://localhost:8080/ptd-full-picture/bucket_temperature_monitor.jsf");
	}

	public String getTemperature() {
		return temperature.getText();
	}

	public List<String> getWeekTemperature() {
		final List<String> temperatures = new ArrayList<String>();
		for(final WebElement weeklyTemperature: weekTemperature) {
			temperatures.add(weeklyTemperature.getText());
		}
		return temperatures;
	}

	public String getTemperatureColor() {
		return temperature.getCssValue("color");
	}

}
