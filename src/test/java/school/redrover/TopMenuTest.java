package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopMenuTest extends BaseTest {

    public List<String> getNamesOfLists(List<WebElement> elements) {
        List<String> texts = new ArrayList<>();

        for (WebElement element : elements) {
            texts.add(element.getText());
        }

        return texts;
    }

    @Test
    public void testCheckPeopleButton() {
        WebElement buttonPeople = getDriver().findElement(By.linkText("People"));
        boolean actualResult = buttonPeople.isDisplayed();

        Assert.assertTrue(actualResult);
    }

    @Test
    public void testUserButton() {
        WebElement buttonAdmin = getDriver().findElement(By.xpath("//header/div/a[@class = 'model-link']"));
        buttonAdmin.click();

        WebElement userIDDescription = getDriver().findElement(By.xpath("//div[@id='main-panel']//div[contains(text(), " +
                "'Jenkins User ID:')]"));
        String userID = userIDDescription.getText().split(": ")[1];

        Assert.assertEquals(userID, "admin");
    }

    @Test
    public void testTopMenuUser(){
        WebElement topMenuUser = getDriver().findElement(By.xpath("//span[@class='hidden-xs hidden-sm'][text()='admin']"));

        String actualResult1 = topMenuUser.getText();

        Assert.assertEquals(actualResult1, "admin");
    }

    @Test
    public void testTopMenuUserDropDown() {
        Actions actions = new Actions(getDriver());
        WebElement topMenuUserDropDown = getDriver().findElement(By.xpath("//div/a[@class='model-link']/button[@class='jenkins-menu-dropdown-chevron']"));
        actions.click(topMenuUserDropDown).perform();

        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='first-of-type']/li/a")));
        List<WebElement> listTopElements = getDriver().findElements(By.xpath("//ul[@class='first-of-type']/li"));
        List<String> expected = Arrays.asList("Builds", "Configure", "My Views", "Credentials");
        List<String>  actual = getNamesOfLists(listTopElements);

        Assert.assertEquals(actual,expected);
    }
}