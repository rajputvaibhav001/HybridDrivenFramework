package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class baseClass {
public static WebDriver driver;
	public Logger logger;
	public Properties p;
		
	@BeforeClass(groups= {"Sanity","Regression","Master", "DataDriven"})
	@Parameters({"browser","os"})
	public void setup(String br, String os) throws InterruptedException, IOException {
		FileReader file= new FileReader(".//src//test//resources//config.properties");
		p= new Properties();
		p.load(file);
		logger= LogManager.getLogger(this.getClass());
		ChromeOptions options= new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
		
		
		//commented due to grid part 
		/*  
		switch(br.toLowerCase()){
		case "chrome": driver= new ChromeDriver(options); break;
		case "edge": driver= new EdgeDriver(); break;
		case "firefox": driver= new FirefoxDriver(); break;
		default: System.out.println("Enter valid browser"); return;
		}*/
		
		
		
		//grid part
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities cap= new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("Invalid Operating System");
				return;
			}
			
			//browser
			switch(br.toLowerCase()) {
			case "chrome": cap.setBrowserName("chrome"); break;
			case "edge": cap.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("No matching browser"); return;
			}
			
			driver= new RemoteWebDriver(new URL("http://192.168.0.199:4444/wd/hub"),cap);
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch(br.toLowerCase()){
			case "chrome": driver= new ChromeDriver(options); break;
			case "edge": driver= new EdgeDriver(); break;
			case "firefox": driver= new FirefoxDriver(); break;
			default: System.out.println("Enter valid browser"); return;
			}
		}
			
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
		
		//Thread.sleep(5000);
	}
		
	@AfterClass(groups= {"Sanity","Regression","Master", "DataDriven"})
	void tearDown() {
		driver.quit();
	}

	
	public String randomString() {
		String gString= RandomStringUtils.randomAlphabetic(5);
		return gString;
	}
	
	public String randomNumber() {
		String gNum= RandomStringUtils.randomNumeric(10);
		return gNum;
	}
	
	public String alphaNumeric() {
		String gAlpha= RandomStringUtils.randomAlphabetic(3);
		String gNum= RandomStringUtils.randomNumeric(3);
		String gAlphaNum= gAlpha+"@"+ gNum;
		return gAlphaNum;
	}

	public String captureScreen(String tname) {
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot takesSS= (TakesScreenshot) driver;
		File sourceFile= takesSS.getScreenshotAs(OutputType.FILE);
		String targetFilePath= System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+ timeStamp+".png";
		File targetFile= new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}
