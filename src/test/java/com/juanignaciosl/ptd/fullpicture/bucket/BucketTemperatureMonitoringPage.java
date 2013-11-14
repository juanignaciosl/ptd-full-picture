package com.juanignaciosl.ptd.fullpicture.bucket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BucketTemperatureMonitoringPage {

	private WebDriver driver;
	
	@FindBy(id = "temperature")
	private WebElement temperatureBox;

	public BucketTemperatureMonitoringPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.driver.get("http://localhost:8080/ptd-full-picture/bucket_temperature_monitor.jsf");
	}

	public String getTemperature() {
		return temperatureBox.getText();
	}

}
