package scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Waiter;

import java.util.List;
import java.util.Objects;

public class CarvanaTest extends Base {
    @Test(testName = "Validate Carvana Homepage Title and URL", priority = 1)
    public void validateCarvanaHomepageTitleAndUrl() {
        driver.get("https://www.carvana.com/");
        String actualTitle = driver.getTitle();
        String actualURL = driver.getCurrentUrl();
        String expectedTitle = "Carvana | Buy & Finance Used Cars Online | At Home Delivery";
        String expectedURL = "https://www.carvana.com/";

        Assert.assertEquals(actualTitle, expectedTitle);
        Assert.assertEquals(actualURL, expectedURL);
    }

    @Test(testName = "Validate Carvana Homepage logo", priority = 2)
    public void validateCarvanaLogo() {
        driver.get("https://www.carvana.com/");
        WebElement logo = driver.findElement(By.className("ae-mutation-ignore"));
        Assert.assertTrue(logo.isDisplayed(), "Logo is not displayed");
    }

    @Test(testName = "Validate the main navigation section items", priority = 3)
    public void validateTheMainNavigationSectionItems() {
        driver.get("https://www.carvana.com/");
        WebElement howItWorkLink = driver.findElement(By.xpath("(//h5[@role='heading'])[3]"));
        WebElement aboutCarvanaLink = driver.findElement(By.xpath("(//h5[@role='heading'])[4]"));
        WebElement supportContactLink = driver.findElement(By.xpath("(//h5[@role='heading'])[5]"));
        Assert.assertTrue(howItWorkLink.isDisplayed(), "Logo is not displayed");
        Assert.assertTrue(aboutCarvanaLink.isDisplayed(), "Logo is not displayed");
        Assert.assertTrue(supportContactLink.isDisplayed(), "Logo is not displayed");
    }

    @Test(testName = "Validate the sign in error message", priority = 4)
    public void validateTheSignInErrorMessage() {
        driver.get("https://www.carvana.com/");
        driver.findElement(By.xpath("//div[@data-qa='desktop-wrapper']")).click();
        Waiter.pause(3);

        WebElement email = driver.findElement(By.id("usernameField"));
        email.sendKeys("johndoe@gmail.com");
        WebElement password = driver.findElement(By.id("passwordField"));
        password.sendKeys("abcd1234");
        WebElement signInButton = driver.findElement(By.xpath("//button[@data-qa='button-base']"));
        signInButton.click();

        WebElement resetYourPasswordError = driver.findElement(By.xpath("//div[@data-qa='error-message-container']"));
        Assert.assertTrue(resetYourPasswordError.isDisplayed(), "Error message is not displayed");
    }

    @Test(testName = "Validate the search filter options and search button", priority = 5)
    public void validateTheSearchFilterOptionsAndSearchButton() {
        driver.get("https://www.carvana.com/");
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://www.carvana.com/cars";
        if (Objects.equals(actualURL, expectedURL)) {
            driver.findElement(By.xpath("(//a[@data-test='TrackLink'])[1]")).click();
        } else {
            driver.findElement(By.xpath("(//a[@data-test='TrackLink'])[2]")).click();
        }
        Waiter.pause(3);
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        WebElement searchBar = driver.findElement(By.xpath("//input[@data-qa='search-bar-input']"));
        searchBar.sendKeys("Tesla");
        WebElement searchInputBoxes = driver.findElement(By.id("search-header"));
        Assert.assertTrue(searchInputBoxes.isDisplayed(), "Search Input Boxes is not displayed");
        Assert.assertTrue(searchInputBoxes.isDisplayed(), "Logo is not enabled");

        WebElement goButton = driver.findElement(By.xpath("//button[@data-qa='go-button']"));
        Assert.assertTrue(goButton.isDisplayed(), "Go Button is displayed");
        Assert.assertTrue(goButton.isEnabled(), "Go Button is enabled");
    }

    @Test(testName = "Validate the search result tiles", priority = 6)
    public void validateTheSearchResultTiles() {
        driver.get("https://www.carvana.com/");
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://www.carvana.com/cars";
        if (Objects.equals(actualURL, expectedURL)) {
            driver.findElement(By.xpath("(//a[@data-test='TrackLink'])[1]")).click();
        } else {
            driver.findElement(By.xpath("(//a[@data-test='TrackLink'])[2]")).click();
        }

        WebElement searchBar = driver.findElement(By.xpath("//input[@data-qa='search-bar-input']"));
        searchBar.sendKeys("mercedes-benz");
        WebElement searchInputBoxes = driver.findElement(By.id("search-header"));
        Assert.assertTrue(searchInputBoxes.isDisplayed(), "Search Input Boxes is not displayed");
        Assert.assertTrue(searchInputBoxes.isDisplayed(), "Logo is not enabled");

        driver.findElement(By.xpath("//button[@data-qa='go-button']")).click();
        Waiter.pause(3);
        Assert.assertTrue(driver.getCurrentUrl().contains("mercedes-benz"));

        List<WebElement> addToFavoriteButton = driver.findElements(By.xpath("//div[@role='button']"));
        List<WebElement> images = driver.findElements(By.xpath("//picture[@class='vehicle-image']"));
        List<WebElement> titleBody = driver.findElements(By.xpath("//div[@class='tk-frame middle-frame']"));
        List<WebElement> freeShipping = driver.findElements(By.xpath("//div[@class='tk-frame bottom-frame']"));
        List<WebElement> downPayment = driver.findElements(By.xpath("//div[@class='down-payment']"));
        List<WebElement> monthlyPayment = driver.findElements(By.xpath("//div[@class='monthly-payment']"));
        List<WebElement> price = driver.findElements(By.xpath("//div[@class='price-variant ']"));
        List<WebElement> trimMileInfo = driver.findElements(By.xpath("//div[@data-qa='trim-mileage']"));
        List<WebElement> trimModelInfo = driver.findElements(By.xpath("//div[@class='year-make-experiment']"));
        List<WebElement> inventoryType = driver.findElements(By.xpath("//div[@data-test='BaseInventoryType']"));

        for (int i = 0; i < images.size(); i++) {
            Assert.assertTrue(images.get(i).isDisplayed(), "Image " + (i + 1) + " is not displayed");

            Assert.assertTrue(titleBody.get(i).isDisplayed(), "Title of Body " + (i + 1) + " is not displayed");

            Assert.assertTrue(addToFavoriteButton.get(i).isDisplayed(), "Favorite Button " + (i + 1) + " is not displayed");

            Assert.assertTrue(inventoryType.get(i).isDisplayed(), "Inventory Type  " + (i + 1) + " is not displayed");
            Assert.assertNotNull(inventoryType.get(i).getText(), "Inventory Type  " + (i + 1) + " is null");

            Assert.assertTrue(trimModelInfo.get(i).isDisplayed(), "Year/Make/Model " + (i + 1) + " is not displayed");
            Assert.assertNotNull(trimModelInfo.get(i).getText(), "Year/Make/Model txt " + (i + 1) + " is null");

            Assert.assertTrue(trimMileInfo.get(i).isDisplayed(), "Trim of Mileage  " + (i + 1) + " is not displayed");
            Assert.assertNotNull(trimMileInfo.get(i).getText(), "Trim of Mileage  " + (i + 1) + " is null");

            String num = price.get(i).getText().substring(price.indexOf("$") + 2).replaceAll(",", "");
            Assert.assertTrue(Integer.parseInt(num) > 0, "Price Title " + (i + 1) + " is zero or less");

            Assert.assertTrue(monthlyPayment.get(i).isDisplayed(), "Monthly Payment " + (i + 1) + " is not displayed");
            Assert.assertNotNull(monthlyPayment.get(i).getText(), "Monthly Payment " + (i + 1) + " is null");

            Assert.assertTrue(downPayment.get(i).isDisplayed(), "DownPayment " + (i + 1) + " is not displayed");
            Assert.assertNotNull(downPayment.get(i).getText(), "DownPayment " + (i + 1) + " is null");

            Assert.assertTrue(freeShipping.get(i).isDisplayed(), "delivery txt " + (i + 1) + " is not displayed");
            Assert.assertEquals(freeShipping.get(i).getText(), "Free Shipping", "delivery txt " + (i + 1) + " is null");
        }
    }
}