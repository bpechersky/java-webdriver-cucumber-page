package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static support.TestContext.getDriver;
import static support.TestContext.getWait;

public class Page {

    private String url;

    public Page() {
        PageFactory.initElements(getDriver(), this);
    }

    public static void clickWithJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)getDriver();
        executor.executeScript("arguments[0].click();", element);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void open() {
        getDriver().get(url);
    }

    public void click(WebElement element) {
        try {
            element.click();
        } catch (WebDriverException e) {
            clickWithJS(element);
        }
    }

    public void sendKeys(WebElement element, String value) {
        waitForVisible(element);
        element.sendKeys(value);
    }

    public void waitForVisible(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
    }

}
