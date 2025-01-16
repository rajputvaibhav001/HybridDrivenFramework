package testCase;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumGridAlone {
	@SuppressWarnings("deprecation")
	public static void main(String args[]) throws MalformedURLException {
		String huburl= "http://192.168.0.199:4444"+"/wd/hub";
		DesiredCapabilities cap= new DesiredCapabilities();
		cap.setPlatform(Platform.WIN11);
		cap.setBrowserName("chrome");
		WebDriver driver= new RemoteWebDriver(new URL(huburl), cap);
		driver.get("https://demo.nopcommerce.com/");
		System.out.println(driver.getTitle());
	}
}
