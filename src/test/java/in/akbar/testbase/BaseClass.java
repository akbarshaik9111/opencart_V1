package in.akbar.testbase;

import java.io.File;
import java.io.FileInputStream;
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
	public Properties props;
	
	@Parameters({"browser", "os"})
	@BeforeClass(groups= {"Sanity", "Master", "Regression"})
	public void setup(String br, String os) throws IOException {
		FileInputStream fis = new FileInputStream("src\\test\\resources\\config.properties");
		//FileReader file=new FileReader(".//src//test//resources//config.properties");
		props = new Properties();
		props.load(fis);
		logger = LogManager.getLogger(this.getClass());
		
		if(props.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			} else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else {
				System.out.println("No os match found..!");
				return;
			}
			
			switch(br.toLowerCase()) {
				case "chrome" : capabilities.setBrowserName("chrome"); break;
				case "edge"   : capabilities.setBrowserName("MicrosoftEdge");  break;
				case "firefox": capabilities.setBrowserName("firefox"); break;
				default       : System.out.println("No browser matches found..!"); return;
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}
		
		if(props.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (br.toLowerCase()) {
				case "chrome": driver = new ChromeDriver(); break;
				case "edge" : driver = new EdgeDriver(); break;
				case "firefox" : driver = new FirefoxDriver(); break;
				default : System.out.println("No matching browser.."); return;
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(props.getProperty("appurl"));
	}
	
	@AfterClass(groups= {"Sanity", "Master", "Regression"})
	public void tearDown() {
		driver.quit();
	}
	
	public String randomString() {
		String genearateRandomString = RandomStringUtils.randomAlphabetic(5, 8);
		return genearateRandomString;
	}
	
	public String randomNumber() {
		String generateRandomAlphaNumeric = RandomStringUtils.randomNumeric(10);
		return generateRandomAlphaNumeric;
	}
	
	public String randomAlphaNumeric() {
		String genearateRandomString = RandomStringUtils.randomAlphabetic(5, 8);
		String generateRandomNumeric = RandomStringUtils.randomNumeric(10);
		return genearateRandomString+"@"+generateRandomNumeric;
	}
	
	public String captureScreenshot(String testcasename) {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+testcasename+"_"+timeStamp+".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}
}