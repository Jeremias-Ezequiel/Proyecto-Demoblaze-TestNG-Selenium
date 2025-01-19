package utilities;

import org.openqa.selenium.WebDriver;

public class WebdriverProvider {

    private static final ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    public void set (WebDriver driver){
        threadLocal.set(driver);
    }

    public WebDriver get(){
        WebDriver driver = threadLocal.get(); 
        if(driver == null){
            throw new IllegalStateException("WebDriver is not initialized. Please call Driver"); 
        }
        return threadLocal.get();
    }
}
