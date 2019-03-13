package amazontests;

import base.CommonAPI;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class AmazonTests extends CommonAPI {

    @Test
    public void searchById() {
        searchByElement("twotabsearchtextbox", "books");
    }
    //@Test
    public void searchByName() {
       searchByElement("field-keywords", "laptops");
    }

    @Test
    public void titleTest() {
        String title = driver.getTitle();
        Assert.assertEquals(title, "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more");
    }

    @Test
    public void signInCheck() {
        String title = checkNavigationToOtherPages("nav-link-accountList");
        Assert.assertEquals(title, "Amazon Sign In");
    }

    @Test
    public void searchButtonTest() {
        String searchUrl = searchButton("twotabsearchtextbox", "//*[@id=\'nav-search\']/form/div[2]/div/input" );
        //System.out.println(searchUrl);
        Assert.assertEquals(searchUrl, "https://www.amazon.com/s/ref=nb_sb_noss_2?url=search-alias%3Daps&field-keywords=books");
    }

    @Test
    public void testTryPrime() {
        String title = mouseHoverActions("//*[@id=\'nav-logo\']/a[2]", "//*[@id=\"nav-prime-tooltip\"]/div[3]/a");
        System.out.println(title);
        Assert.assertEquals(title, "Amazon.com: Amazon Prime");
    }

    @Test
    public void testNavigation() {
        String subUrl = navigateBackAndForward("https://www.google.com/");
        System.out.println(subUrl);
        Assert.assertEquals(subUrl, "https://www.google.com/");
    }

    @Test
    public void getAllDropDownOptions() {
        List<WebElement> options = dropDownOptionsTest("//*[@id=\'searchDropdownBox\']");
        for (WebElement w : options) {
            System.out.println(w.getText());
        }
    }

    @Test
    public void testAllDropDown() {
        String endUrl = dropDownSelectionByValue("//*[@id=\'searchDropdownBox\']", "search-alias=gift-cards", "twotabsearchtextbox");
        //System.out.println(endUrl);
        Assert.assertEquals(endUrl, "https://www.amazon.com/s?k=books&i=gift-cards&ref=nb_sb_noss");
    }

    @Test
    public void testLinkText() {
        String currentUrl = linkText("Today's Deals", "//*[@id=\"nav-logo\"]/a[1]/span[1]");
        Assert.assertEquals(currentUrl, "https://www.amazon.com/ref=nav_logo");
    }

    @Test
    public void signInErrorTest() {
        String alertText = signInErrorMessage("nav-link-accountList", "ap_email", "ap_password", "signInSubmit");
        System.out.println(alertText);
    }

}

