package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Driver {

    private WebDriver driver;

    public Driver(){}

    public WebDriver getDriver(String driverName){

        if(driver==null){
            switch (driverName){
                case "firefoxOnGrid":
                    String urlFireFox = "http://18.222.145.71:4444";
                    File ffFile = new File("C:\\Users\\Administrator\\Downloads\\geckodriver.exe");
                    System.setProperty("webdriver.geckodriver.driver", ffFile.getAbsolutePath());
                    DesiredCapabilities caps = DesiredCapabilities.firefox();
                    caps.setBrowserName("firefox");
                    caps.setPlatform(Platform.ANY);
                    try {
                        driver = new RemoteWebDriver(new URL(urlFireFox), caps);
                    }catch (MalformedURLException e){
                        throw new RuntimeException(e);
                    }
                    break;

                case "chromeOnGrid":
                    String url = "http://18.222.145.71:4444";
                    File file = new File("C:\\Users\\Administrator\\Downloads\\chromedriver.exe");
                    System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
                    DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
                    desiredCapabilities.setBrowserName("chrome");
                    desiredCapabilities.setPlatform(Platform.ANY);
                    try {
                        driver = new RemoteWebDriver(new URL(url), desiredCapabilities);
                    }catch (MalformedURLException ex){
                        throw new RuntimeException(ex);
                    }
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver();
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver();
                    break;
            }
        }
        driver.manage().window().maximize();

        return driver;
    }

}
