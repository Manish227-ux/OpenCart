package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
public static WebDriver driver;
public Logger logger;
public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setUp(String os, String browser) throws IOException {
		
		logger = LogManager.getLogger(this.getClass());
		
		FileReader file = new FileReader("C:\\Users\\manis\\eclipse-workspace\\OpenCart\\src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);
		
		//Remote Environment
		if(p.getProperty("execution_environment").equalsIgnoreCase("remote")) {
			
			String url = "http://192.168.1.4:4444";
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//OS
			if(os.equalsIgnoreCase("windows")) {
				
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux")) {
				
				capabilities.setPlatform(Platform.LINUX);
			}
			
			else {
				
				System.out.println("OS not supported");
				
				return;
			}
			
			//Browser
			
			if(browser.equalsIgnoreCase("chrome")) {
				
				capabilities.setBrowserName("chrome");
			}
			else if(browser.equalsIgnoreCase("firefox")) {
				
				capabilities.setBrowserName("firefox");
			}
			else if(browser.equalsIgnoreCase("edge")) {
				
				capabilities.setBrowserName("edge");
			}
			else if(browser.equalsIgnoreCase("safari")) {
				
				capabilities.setBrowserName("safari");
			}
			else {
				
				System.out.println("Browser not supported");
				return;
				
			}
			
			driver = new RemoteWebDriver(new URL(url), capabilities);
			
		}
		
		
		//Local Environment
		if(p.getProperty("execution_environment").equalsIgnoreCase("local")) {
			
			if(browser.equalsIgnoreCase("chrome")) {
				
				driver = new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("firefox")) {
				
				driver = new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("edge")) {
				
				driver = new EdgeDriver();
			}
			else {
				
				return;
				
			}
		}
		
		
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	
	
	public String randomString() {
		
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		
		return generatedString;
	}
	
	public String randomNumericString() {
		
		String generatedString = RandomStringUtils.randomNumeric(10);
		
		return generatedString;
	}
	
	public String randomAlphaNumericString() {
		
		String generatedString1 = RandomStringUtils.randomAlphabetic(3);
		String generatedString2 = RandomStringUtils.randomNumeric(3);
		
		return generatedString1 + generatedString2;
	}
	
	public String captureScreenShot(String testCaseName) {
		
		//TakesScreenshot ts = ;
		
		String name = new SimpleDateFormat("yyyy.MM.dd.h.mm.ss_a", Locale.ENGLISH).format(new Date());


		String path = System.getProperty("user.dir") +"\\screenshots\\" + testCaseName + name +".png";
		
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		File destination = new File(path);
		
		source.renameTo(destination);
		
		return path;
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown() {
		
		driver.quit();
	}

}
