package TestCases;

import org.testng.annotations.Test;

import main.base.BaseSetup;
import main.pages.RecipesPage_POM;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;;


@Listeners(main.utilities.TestNGListener.class)
public class TestRecipesPage extends BaseSetup {
	
	String baseUrl;
	RecipesPage_POM recipesPage;
	
  @BeforeMethod
  public void beforeClass() {	  	
	  initialize();
	  log.info("Application launched successfully");
	  recipesPage = new RecipesPage_POM(driver);
  }
  
  
  @Test(priority=1)
  public void test_shouldDisplayText_whenUserInLandingPage() {
	  String landingPageText = recipesPage.getLandingPageText();
	  Assert.assertEquals(landingPageText, "Please select a recipe!");
	  log.info("Test Passed");
  }
  
  
  @Test(priority=2)
  public void test_shouldDisplayRecipesList_whenUserInLandingPage() {
	  Assert.assertTrue(recipesPage.recipesListDisplayed());
	  log.info("Test Passed");
  }
 
  
  @Test(priority=3)
  public void test_shouldAddNewRecipe_whenCorrectValuesEntered() {
	  recipesPage.navigateToNewRecipe();
	  recipesPage.enterRecipeName("momo");
	  recipesPage.enterRecipeUrl("dummy momo image url");
	  recipesPage.enterRecipeDescription("testy nepali cuisine");
	  recipesPage.clickSaveButton();
	  String newRecipe = driver.findElement(By.xpath("//h4[contains(text(), 'momo')]")).getText();
	  Assert.assertEquals(newRecipe, "momo");
	  log.info("Test Passed");
  }
  
  @AfterMethod
  public void afterClass() throws Exception {
	  //Thread.sleep(2000);
	  driver.quit();
	  log.info("Browser terminated");
	  log.info("----------------------------------------------");
  }

}
