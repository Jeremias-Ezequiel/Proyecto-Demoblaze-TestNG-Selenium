package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {
    // private final boolean runServer = System.getenv("JOB_NAME") != null; 
    private final boolean runServer = false; 

    private enum Browser{
        CHROME,
        FIREFOX,
        EDGE,
        SAFARI
    }

    public void buildDriver(){
        if(runServer){
            // buildRemoteDriver();
        }else{
            buildLocalDriver(); 
        }
    }

    private void buildLocalDriver(){
        Logs.debug("Inicializando el driver");
        String browserProperty = System.getProperty("browser"); 
        String headlessMode = System.getProperty("headless");

        if(browserProperty == null){
            Logs.debug("Asignando driver por defecto a Chrome");
            browserProperty = "CHROME"; 
        }

        Browser browser = Browser.valueOf(browserProperty.toUpperCase()); 
        Logs.debug("Inicianlizando driver : %s",browser);
        WebDriver driver  = switch(browser){
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions(); 
                if(headlessMode != null){
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--disable-gpu");
                }
                yield new ChromeDriver(chromeOptions);
            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions(); 
                if(headlessMode != null){
                    edgeOptions.addArguments("--headless=new");
                    edgeOptions.addArguments("--disable-gpu");

                }
                yield new EdgeDriver(edgeOptions); 
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions(); 
                if(headlessMode != null){
                    firefoxOptions.addArguments("--headless");
                    firefoxOptions.addArguments("--disable-gpu");

                }
                yield new FirefoxDriver(firefoxOptions); 
            }
            case SAFARI -> {
                yield new SafariDriver(); 
            }
        }; 

        Logs.debug("Maximizando pantalla");
        driver.manage().window().maximize();

        Logs.debug("Borrando las cookies");
        driver.manage().deleteAllCookies();

        Logs.debug("Asignando el driver al web driver provider");
        new WebdriverProvider().set(driver);
    }

    public void killDriver(){
        Logs.debug("Matando el driver");
        new WebdriverProvider().get().quit();
    }
}
