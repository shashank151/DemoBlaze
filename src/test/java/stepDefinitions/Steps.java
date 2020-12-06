package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Steps {
	static WebDriver driver;
	static JavascriptExecutor jse;

	private static String baseUrl = "https://www.demoblaze.com/index.html";
	private static String cart_value;
	private static String name = "Xyz";
	private static String country = "India";
	private static String city = "Delhi";
	private static String cardNo = "276763736376878";
	private static String month = "11";
	private static String year = "22";
	private static By homeButton = By.xpath("//*[contains(text(),'Home')]");
	private static By cartButton = By.xpath("//*[contains(text(),'Cart')]");
	private static By laptop_link = By.xpath("//a[contains(text(),'Laptops')]");
	private static By sony_vaio_i5_link = By.xpath("//a[contains(text(),'Sony vaio i5')]");
	private static By Dell_i7_8gb_link = By.xpath("//a[contains(text(),'Dell i7 8gb')]");
	private static By Add_to_cart = By.xpath("//a[contains(text(),'Add to cart')]");
	private static By sony_vaio_i5_cart = By.xpath("//td[contains(text(),'Sony vaio i5')]");
	private static By dell_i7_8gb_cart = By.xpath("//td[contains(text(),'Dell i7 8gb')]");
	private static By sony_vaio_i5_cart_delete = By.xpath(
			"//td[contains(text(),'Sony vaio i5')]/following-sibling::td/descendant::a[contains(text(),'Delete')]");
	private static By dell_i7_8gb_cart_delete = By.xpath(
			"//td[contains(text(),'Dell i7 8gb')]/following-sibling::td/descendant::a[contains(text(),'Delete')]");

	private static By placeOrderbutton = By.xpath("//button[contains(text(),'Place Order')]");
	private static By placeOrder_form = By.xpath("//*[contains(text(),'Place order')]");
	private static By name_form = By.xpath("//input[@id = 'name']");
	private static By country_form = By.xpath("//input[@id = 'country']");
	private static By city_form = By.xpath("//input[@id='city']");
	private static By card_form = By.xpath("//input[@id='card']");
	private static By month_form = By.xpath("//input[@id='month']");
	private static By year_form = By.xpath("//input[@id='year']");
	private static By purchase_button = By.xpath("//button[contains(text(),'Purchase')]");
	private static By purchase_confirmation = By.xpath("//h2[contains(text(),'Thank you')]");
	private static By purchaseId = By.xpath("//p[contains(text(),'Id')]");
	private static By confirmOk = By.xpath("//button[contains(text(),'OK')]");

	@Given("I launch the website")
	public void i_launch_the_website() {
		driver = new ChromeDriver();
		jse = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Assert.assertEquals("STORE", driver.getTitle());
	}

	@When("I click on laptop under product categories")
	public void i_click_on_laptop_under_product_categories() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		jse.executeScript("window.scrollBy(0,250)", "");
		driver.findElement(laptop_link).click();
	}

	@Then("I expect to see laptop {string}")
	public void i_expect_to_see_laptop(String laptop) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		if (laptop.equals("sony_vaio_i5")) {
			jse.executeScript("window.scrollBy(0,250)", "");
			Assert.assertTrue(driver.findElement(sony_vaio_i5_link).isDisplayed());
		} else if (laptop.equals("Dell_i7_8gb")) {
			jse.executeScript("window.scrollBy(0,400)", "");
			Assert.assertTrue(driver.findElement(Dell_i7_8gb_link).isDisplayed());
		} else {
			System.out.println("Setup not done for Webelement");
		}

	}

	@When("I open {string} from catalog")
	public void i_open_from_catalog(String laptop) {
		if (laptop.equals("sony_vaio_i5")) {
			driver.findElement(sony_vaio_i5_link).click();

		} else if (laptop.equals("Dell_i7_8gb")) {
			jse.executeScript("window.scrollBy(0,250)", "");
			driver.findElement(Dell_i7_8gb_link).click();

		} else {
			System.out.println("Setup not done for Webelement");
		}
	}

	@When("I add item to cart")
	public void i_add_item_to_cart() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(Add_to_cart).click();
	}

	@Then("I expect to see product added alert")
	public void i_expect_to_see_product_added_alert() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals("Product added", driver.switchTo().alert().getText());
	}

	@When("I close the alert")
	public void i_close_the_alert() {
		driver.switchTo().alert().accept();
	}

	@When("I click on home button")
	public void i_click_on_home_button() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(homeButton).click();
	}

	@Then("I expect to navigate to home screen")
	public void i_expect_to_navigate_to_home_screen() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Assert.assertEquals(baseUrl, driver.getCurrentUrl());
	}

	@When("I click on cart button")
	public void i_click_on_cart_button() {
		driver.findElement(cartButton).click();
	}

	@Then("I expect to see added items")
	public void i_expect_to_see_added_items() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Assert.assertTrue(driver.findElement(sony_vaio_i5_cart).isDisplayed());
		Assert.assertTrue(driver.findElement(dell_i7_8gb_cart).isDisplayed());
	}

	@When("I delete {string} from cart")
	public void i_delete_from_cart(String laptop) throws InterruptedException {
		if (laptop.equals("sony_vaio_i5")) {
			driver.findElement(sony_vaio_i5_cart_delete).click();

		} else if (laptop.equals("Dell_i7_8gb")) {
			driver.findElement(dell_i7_8gb_cart_delete).click();
		} else {
			System.out.println("Setup not done for Webelement");
		}
		Thread.sleep(10000);
	}

	@Then("I expect to not see {string} in the cart")
	public void i_expect_to_not_see_in_the_cart(String laptop) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		if (laptop.equals("sony_vaio_i5")) {
			Assert.assertTrue(driver.findElements(sony_vaio_i5_cart).size() < 1);

		} else if (laptop.equals("Dell_i7_8gb")) {
			Assert.assertTrue(driver.findElements(dell_i7_8gb_cart).size() < 1);

		} else {
			System.out.println("Setup not done for Webelement");
		}
	}

	@When("I place order for the added item")
	public void i_place_order_for_the_added_item() throws InterruptedException {
		cart_value = driver.findElement(By.id("totalp")).getText();
		driver.findElement(placeOrderbutton).click();
		Thread.sleep(10000);
	}

	@Then("I expect to see place order form")
	public void i_expect_to_see_place_order_form() {
		Assert.assertTrue(driver.findElement(placeOrder_form).isDisplayed());
	}

	@When("I fill all the details in the form")
	public void i_fill_all_the_details_in_the_form() {
		driver.findElement(name_form).sendKeys(name);
		driver.findElement(country_form).sendKeys(country);
		driver.findElement(city_form).sendKeys(city);
		driver.findElement(card_form).sendKeys(cardNo);
		jse.executeScript("window.scrollBy(0,500)", "");
		driver.findElement(month_form).sendKeys(month);
		driver.findElement(year_form).sendKeys(year);
	}

	@When("I click on puchase button")
	public void i_click_on_puchase_button() {
		driver.findElement(purchase_button).click();
	}

	@Then("I expect to see purchase confirmation box")
	public void i_expect_to_see_purchase_confirmation_box() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assert.assertTrue(driver.findElement(purchase_confirmation).isDisplayed());

	}

	@Then("I expect to see purchase id")
	public void i_expect_to_see_purchase_id() {
		Assert.assertTrue(driver.findElement(purchaseId).isDisplayed());
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("./ScreenShots/Confirmation_screen.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	@Then("I expect amount should be correct")
	public void i_expect_amount_should_be_correct() {
		String text = driver.findElement(purchaseId).getText();
		String[] sentences = text.split("\\n");
		System.out.println(sentences[0]);
		System.out.println(sentences[1]);
		Assert.assertTrue(sentences[1].contains(cart_value));
	}

	@When("I close confirmation screen")
	public void i_close_confirmation_screen() throws InterruptedException {
		Thread.sleep(10000);
		driver.findElement(confirmOk).click();
		Thread.sleep(10000);
	}

	@Then("I close browser")
	public void i_close_browser() {
		driver.close();
	}

}
