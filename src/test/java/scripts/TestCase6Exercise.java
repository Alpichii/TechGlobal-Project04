package scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Waiter;

import java.util.List;
import java.util.Objects;

public class TestCase6Exercise extends Base {
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
            Assert.assertTrue(images.get(i).isDisplayed(), "image "+(i+1)+" is not displayed");

            Assert.assertTrue(addToFavoriteButton.get(i).isDisplayed(), "faveButton " + (i+1) + " is not displayed");

            Assert.assertTrue(inventoryType.get(i).isDisplayed(), "InventoryType txt " + (i+1) + " is not displayed");
            Assert.assertNotNull(inventoryType.get(i).getText(), "InventoryType txt " + (i + 1) + " is null");

            Assert.assertTrue(trimModelInfo.get(i).isDisplayed(), "yearMakeModel txt " + (i+1) + " is not displayed");
            Assert.assertNotNull(trimModelInfo.get(i).getText(), "yearMakeModel txt " + (i + 1) + " is null");

            Assert.assertTrue(trimMileInfo.get(i).isDisplayed(), "trimMileage txt " + (i+1) + " is not displayed");
            Assert.assertNotNull(trimMileInfo.get(i).getText(), "trimMileage txt " + (i + 1) + " is null");

            String num = price.get(i).getText().substring(price.indexOf("$")+2).replaceAll(",", "");
            Assert.assertTrue(Integer.parseInt(num)>0, "price tile " + (i+1) + " is zero or less");

            Assert.assertTrue(monthlyPayment.get(i).isDisplayed(), "monthlyPayment txt " + (i+1) + " is not displayed");
            Assert.assertNotNull(monthlyPayment.get(i).getText(), "monthlyPayment txt " + (i + 1) + " is null");

            Assert.assertTrue(downPayment.get(i).isDisplayed(), "downPayment txt " + (i+1) + " is not displayed");
            Assert.assertNotNull(downPayment.get(i).getText(), "downPayment txt " + (i + 1) + " is null");

            Assert.assertTrue(freeShipping.get(i).isDisplayed(), "delivery txt " + (i+1) + " is not displayed");
            Assert.assertEquals(freeShipping.get(i).getText(), "Free Shipping","delivery txt " + (i + 1) + " is null");
        }

    }



   /*

    List<WebElement> addToFavoriteButton = driver.findElements(By.xpath("//div[@role='button']"));
    List<WebElement> images = driver.findElements(By.xpath("//picture[@class='vehicle-image']"));
    List<WebElement> titleBody = driver.findElements(By.xpath("//div[@class='tk-frame middle-frame']"));
    List<WebElement> freeShipping = driver.findElements(By.xpath("//div[@class='tk-frame bottom-frame']"));
    List<WebElement> downPayment = driver.findElements(By.xpath("//div[@class='down-payment']"));
    List<WebElement> monthlyPayment = driver.findElements(By.xpath("//div[@class='monthly-payment']"));
    List<WebElement> price = driver.findElements(By.xpath("//div[@class='price-variant ']"));
    List<WebElement> trimMileInfo = driver.findElements(By.xpath("data-qa='trim-mileage']"));
    List<WebElement> trimModelInfo = driver.findElements(By.xpath("//div[@class='year-make-experiment']"));
    List<WebElement> inventoryType = driver.findElements(By.xpath("//div[@data-test='BaseInventoryType']"));

for (int i = 0; i < images.size(); i++) {
    Assert.assertTrue(images.get(i).isDisplayed(), "image "+(i+1)+" is not displayed");

    Assert.assertTrue(faveButton.get(i).isDisplayed(), "faveButton " + (i+1) + " is not displayed");


    Assert.assertTrue(inventoryType.get(i).isDisplayed(), "InventoryType txt " + (i+1) + " is not displayed");
    Assert.assertNotNull(inventoryType.get(i).getText(), "InventoryType txt " + (i + 1) + " is null");

    Assert.assertTrue(yearMakeModel.get(i).isDisplayed(), "yearMakeModel txt " + (i+1) + " is not displayed");
    Assert.assertNotNull(yearMakeModel.get(i).getText(), "yearMakeModel txt " + (i + 1) + " is null");

    Assert.assertTrue(trimMileage.get(i).isDisplayed(), "trimMileage txt " + (i+1) + " is not displayed");
    Assert.assertNotNull(trimMileage.get(i).getText(), "trimMileage txt " + (i + 1) + " is null");

    String num = price.get(i).getText().substring(price.indexOf("$")+2).replaceAll(",", "");
    Assert.assertTrue(Integer.parseInt(num)>0, "price tile " + (i+1) + " is zero or less");

    Assert.assertTrue(monthlyPayment.get(i).isDisplayed(), "monthlyPayment txt " + (i+1) + " is not displayed");
    Assert.assertNotNull(monthlyPayment.get(i).getText(), "monthlyPayment txt " + (i + 1) + " is null");

    Assert.assertTrue(downPayment.get(i).isDisplayed(), "downPayment txt " + (i+1) + " is not displayed");
    Assert.assertNotNull(downPayment.get(i).getText(), "downPayment txt " + (i + 1) + " is null");

    Assert.assertTrue(delivery.get(i).isDisplayed(), "delivery txt " + (i+1) + " is not displayed");
    Assert.assertEquals(delivery.get(i).getText(), "Free Shipping","delivery txt " + (i + 1) + " is null");
}
















    String[] titles = {addToFavoriteButton, pictures, titleBody, freeShipping, downPayment , monthlyPayment,
            price, trimMileInfo, trimModelInfo, inventoryType};

    for (int i = 0; i < addToFavoriteButton.size(); i++){

    }



            List<WebElement> pictures = driver.findElements(By.xpath("//picture[@class='vehicle-image']"));

            for (int i = 0; i < pictures.size(); i++) {
                System.out.println("Validation of " + pictures.get(i).getText() +  " "
                        + (pictures.get(i).isDisplayed() ? "PASSED" : "FAILED")) ;

            }
           // Assert.assertEquals(actualURL, expectedURL);


            driver.get("https://www.carvana.com/");
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://www.carvana.com/cars";
        driver.findElement(By.xpath("//a[@class='track-link event-link-button secondary-button']")).click();

        Waiter.waitForURLToBe(driver, 30, expectedURL);
        Assert.assertEquals(actualURL, expectedURL);

        WebElement searchBar = driver.findElement(By.xpath("//input[@data-qa='search-bar-input']"));
        searchBar.sendKeys("Tesla");
        WebElement searchInputBoxes = driver.findElement(By.id("search-header"));
        Assert.assertTrue(searchInputBoxes.isDisplayed(), "Search Input Boxes is not displayed");
        Assert.assertTrue(searchInputBoxes.isDisplayed(), "Logo is not enabled");

        WebElement goButton = driver.findElement(By.xpath("//button[@data-qa='go-button']"));
        Assert.assertTrue(goButton.isDisplayed(), "Go Button is displayed");
        Assert.assertTrue(goButton.isEnabled(), "Go Button is enabled");
    }
 */


}


