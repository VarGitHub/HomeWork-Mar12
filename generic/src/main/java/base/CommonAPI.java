package base;

import org.junit.AfterClass;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonAPI {
    public WebDriver driver;
    WebDriverWait wait;

    @Parameters({"url"})
    @BeforeMethod
    public void setUp(String url) {
        System.setProperty("webdriver.chrome.driver", "../generic/driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 1);
        driver.navigate().to(url);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void cleanUp() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
        driver.quit();
    }

    public void searchByElement(String locator, String value) {
        try {
            driver.findElement(By.id(locator)).sendKeys(value, Keys.ENTER);
        } catch (Exception ex1) {
            try {
                driver.findElement(By.name(locator)).sendKeys(value, Keys.ENTER);
            } catch (Exception ex2) {
                try {
                    driver.findElement(By.className(locator)).sendKeys(value, Keys.ENTER);
                } catch (Exception ex3) {
                    try {
                        driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.ENTER);
                    } catch (Exception ex4) {
                        driver.findElement(By.xpath(locator)).sendKeys(value, Keys.ENTER);
                    }
                }
            }
        }
    }

    public String checkNavigationToOtherPages(String locator) {
        try {
            driver.findElement(By.id(locator)).click();
        } catch (Exception e) {
            try {
                driver.findElement(By.name(locator)).click();
            } catch (Exception e1) {
                try {
                    driver.findElement(By.className(locator)).click();
                } catch (Exception e2) {
                    try {
                        driver.findElement(By.cssSelector(locator)).click();
                    } catch (Exception e3) {
                        driver.findElement(By.xpath(locator)).click();
                    }
                }
            }
        }
        String signInPageTitle = driver.getTitle();
        return signInPageTitle;
    }

    public String searchButton(String locator1, String locator2) {
        driver.findElement(By.id(locator1)).sendKeys("books");
        driver.findElement(By.xpath(locator2)).click();
        String searchedUrl = driver.getCurrentUrl();
        return searchedUrl;
    }

    public String mouseHoverActions(String locator1, String locator2) {
        WebElement mainElement = driver.findElement(By.xpath(locator1));
        Actions action = new Actions(driver);
        action.moveToElement(mainElement).perform();
        WebElement subElement = driver.findElement(By.xpath(locator2));
        action.moveToElement(subElement).click().perform();
        String title = driver.getTitle();
        return title;
    }

    public String navigateBackAndForward(String subUrl) {
        driver.navigate().to(subUrl);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().back();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().forward();
        return driver.getCurrentUrl();
    }

    public List<WebElement> dropDownOptionsTest(String locator) {
        WebElement element = driver.findElement(By.xpath(locator));
        Select select = new Select(element);
        return select.getOptions();
    }

    public String dropDownSelectionByValue(String locator1, String value, String locator2) {
        WebElement element = driver.findElement(By.xpath(locator1));
        Select select = new Select(element);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        select.selectByValue(value);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id(locator2)).sendKeys("books", Keys.ENTER);
        return driver.getCurrentUrl();
    }

    public String linkText(String locator1, String locator2) {
       driver.findElement(By.linkText(locator1)).click();
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator2)));
       driver.findElement(By.xpath(locator2)).click();
       return driver.getCurrentUrl();
    }

    public String signInErrorMessage(String signInLocator, String userLocator, String pwdLocator, String submitLocator) {

        driver.findElement(By.id(signInLocator)).click();
        driver.findElement(By.id(userLocator)).sendKeys("test@test.com");
        driver.findElement(By.id(pwdLocator)).sendKeys("abc123");
        driver.findElement(By.id(submitLocator)).click();
        try {
            return driver.findElement(By.id("auth-warning-message-box")).getText();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
