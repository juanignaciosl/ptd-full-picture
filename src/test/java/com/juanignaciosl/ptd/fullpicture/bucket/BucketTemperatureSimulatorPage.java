package com.juanignaciosl.ptd.fullpicture.bucket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BucketTemperatureSimulatorPage {

	private WebDriver driver;

	@FindBy(name = "bucket:temperature")
	private WebElement temperatureBox;

	@FindBy(name = "bucket:submit")
	private WebElement submitButton;

	public BucketTemperatureSimulatorPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.driver
				.get("http://localhost:8080/ptd-full-picture/bucket_temperature_simulator.jsf");
	}

	public void setTemperature(String inputTemperature) {
		temperatureBox.clear();
		temperatureBox.sendKeys(inputTemperature);
		submitButton.submit();
	}

}
