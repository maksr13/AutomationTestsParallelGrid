import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected String log;
    protected String psw;
    protected String SpecialParameterForTest;

    private WebDriver getLocalDriver(int browser){
        switch(browser) {

            case 1:
            default:
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +
                        "/drivers/chromedriver.exe");
                return new ChromeDriver();
            case 2:
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
                        "/drivers/geckodriver.exe");
                return new FirefoxDriver();
            case 3:
                System.setProperty("webdriver.opera.driver", System.getProperty("user.dir") +
                        "/drivers/operadriver.exe");
                OperaOptions optionsOpera = new OperaOptions();
                optionsOpera.setBinary("C:\\Users\\Maks\\AppData\\Local\\Programs\\Opera\\60.0.3255.95\\opera.exe");

                return new OperaDriver(optionsOpera);

                /*Headless drivers*/
            case 4:
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +
                        "/drivers/chromedriver.exe");
                ChromeOptions optionsChromeH = new ChromeOptions();
                optionsChromeH.addArguments("headless");
                return new ChromeDriver(optionsChromeH);
            case 5:
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
                        "/drivers/geckodriver.exe");
                FirefoxOptions optionsFirefoxH = new FirefoxOptions();
                optionsFirefoxH.addArguments("--headless");
                return new FirefoxDriver(optionsFirefoxH);
            case 6:
                System.setProperty("webdriver.opera.driver", System.getProperty("user.dir") +
                        "/drivers/operadriver.exe");
                OperaOptions optionsOperaH = new OperaOptions();
                optionsOperaH.addArguments("--headless");
                optionsOperaH.setBinary("C:\\Users\\Maks\\AppData\\Local\\Programs\\Opera\\60.0.3255.95\\opera.exe");
                return new OperaDriver(optionsOperaH);

        }
    }


    private RemoteWebDriver getRemoteDriver(int browser, int platform, String node) throws MalformedURLException {
        Platform currentPlatform;
        switch(platform){
            case 1:
            default:
                currentPlatform = Platform.ANDROID;
            case 2:
                currentPlatform = Platform.IOS;
            case 3:
                currentPlatform = Platform.LINUX;
            case 4:
                currentPlatform = Platform.MAC;
            case 5:
                currentPlatform = Platform.UNIX;
            case 6:
                currentPlatform = Platform.VISTA;
            case 7:
                currentPlatform = Platform.WIN8;
            case 8:
                currentPlatform = Platform.WIN8_1;
            case 9:
                currentPlatform = Platform.WIN10;
            case 10:
                currentPlatform = Platform.WINDOWS;
            case 11:
                currentPlatform = Platform.XP;
            case 0:
                currentPlatform = Platform.ANY;
        }

        switch(browser){
            case 1:
            default:
                DesiredCapabilities capabilitiesChrome = DesiredCapabilities.chrome();
                capabilitiesChrome.setPlatform(currentPlatform);
                return new RemoteWebDriver(new URL(node), capabilitiesChrome);
            case 2:
                DesiredCapabilities capabilitiesFirefox = DesiredCapabilities.firefox();
                capabilitiesFirefox.setPlatform(currentPlatform);
                return new RemoteWebDriver(new URL(node), capabilitiesFirefox);
            case 3:
                DesiredCapabilities capabilitiesOpera = DesiredCapabilities.operaBlink();
                capabilitiesOpera.setPlatform(currentPlatform);
                return new RemoteWebDriver(new URL(node), capabilitiesOpera);

                /*Headless drivers*/
            case 4:
                DesiredCapabilities capabilitiesChromeH = DesiredCapabilities.chrome();
                capabilitiesChromeH.setPlatform(currentPlatform);
                capabilitiesChromeH.setCapability("headless", true);
                return new RemoteWebDriver(new URL(node), capabilitiesChromeH);
            case 5:
                DesiredCapabilities capabilitiesFirefoxH = DesiredCapabilities.firefox();
                capabilitiesFirefoxH.setPlatform(currentPlatform);
                capabilitiesFirefoxH.setCapability("--headless", true);
                return new RemoteWebDriver(new URL(node), capabilitiesFirefoxH);
            case 6:
                DesiredCapabilities capabilitiesOperaH = DesiredCapabilities.operaBlink();
                capabilitiesOperaH.setPlatform(currentPlatform);
                capabilitiesOperaH.setCapability("--headless", true);
                return new RemoteWebDriver(new URL(node), capabilitiesOperaH);
        }
    }


    @BeforeClass
    @Parameters({"selenium.browser", "platform", "NodeAdress", "SpecialParameter", "Log", "Psw", "mode"})
    public void setUp(String browserString, String platformString, String node, String SpecParam, String log, String psw, String mode)throws MalformedURLException {

        int browser = Integer.parseInt(browserString);
        int platform = Integer.parseInt(platformString);

        if(mode.equals("local"))
            driver = getLocalDriver(browser);
        else
            if(mode.equals("remote"))
                driver = getRemoteDriver(browser, platform, node);

        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        //driver.manage().timeouts().pageLoadTimeout(40,TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 12);


        this.log = log;
        this.psw = psw;
        this.SpecialParameterForTest = SpecParam;
    }

    @AfterClass
    public void tearDown(){
        if(driver !=null){
            driver.quit();
        }
    }
}
