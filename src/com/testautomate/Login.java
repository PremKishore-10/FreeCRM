package com.testautomate;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.excel.utility.Xls_Reader;

public class Login {

	WebDriver driver;

	@BeforeMethod
	@Parameters({ "email", "password" })
	public void setup(String email, String password) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:/Users/Prem Kishore/Downloads/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://ui.freecrm.com/");
		Thread.sleep(7000);

		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//div[text()='Login']")).click();
	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

//	@Test(priority = 1)
//	public void login() {
//		Boolean b = driver.findElement(By.xpath("//div[@class='header item']")).isDisplayed();
//		Assert.assertTrue(b);
//	}
//
//	@Test(priority = 2)
//	public void calendar() {
//		Actions action = new Actions(driver);
//
//		action.moveToElement(driver.findElement(By.xpath("//a[@class='item']/following::i[@class='calendar icon']")))
//				.build().perform();
//		driver.findElement(By.xpath("//a[@class='item']/following::i[@class='calendar icon']")).click();
//		
//	}
	
	@Test(priority=1)
	@Parameters({"contactname","editcontact"})
	public void contacts(String contactname, String editcontact) throws InterruptedException {
		//a[contains(text(),'John Doe')]/parent::td//preceding-sibling::td//input[@name='id']
		Actions action = new Actions(driver);

		action.moveToElement(driver.findElement(By.xpath("//a[@class='item']/following::i[@class='users icon']")))
				.build().perform();
		driver.findElement(By.xpath("//a[@class='item']/following::i[@class='users icon']")).click();
		Thread.sleep(5000);
		action.moveToElement(driver.findElement(By.xpath("//th[text()='Category']"))).build().perform();
		Thread.sleep(2000);
		//driver.findElement(By.xpath("//th[text()='Category']")).click();
		//Thread.sleep(5000);
		
		
		//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//a[text()='"+contactname+"']/parent::td//preceding-sibling::td//input[@name='id']")));
		
		action.moveToElement(driver.findElement(By.xpath("//a[text()='"+contactname+"']/parent::td//preceding-sibling::td//input[@name='id']"))).click().build().perform();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[text()='"+editcontact+"']/parent::td//following-sibling::td//i[@class='edit icon']")).click();
		Thread.sleep(3000);
		
		Xls_Reader reader=new Xls_Reader("G:/Eclipse-workspace/FreeCRM/src/com/testdata/freecrm.xlsx");
		
		String Company=reader.getCellData("Contacts", "Company", 2);
		String Description=reader.getCellData("Contacts", "Description", 2);
		String Position=reader.getCellData("Contacts", "Position", 2);
		String Department=reader.getCellData("Contacts", "Department", 2);
		
		driver.findElement(By.xpath("//label[text()='Company']/parent::div//child::div//input")).clear();
		driver.findElement(By.xpath("//label[text()='Company']/parent::div//child::div//input")).sendKeys(Company);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[text()='Description']//following::textarea")).clear();
		driver.findElement(By.xpath("//label[text()='Description']//following::textarea")).sendKeys(Description);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[text()='Position']/parent::div//child::div//input")).clear();
		driver.findElement(By.xpath("//label[text()='Position']/parent::div//child::div//input")).sendKeys(Position);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[text()='Department']/parent::div//child::div//input")).clear();
		driver.findElement(By.xpath("//label[text()='Department']/parent::div//child::div//input")).sendKeys(Department);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[text()='Category']//following::div[@name='category']")).click();
		driver.findElement(By.xpath("//span[text()='Lead']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[@class='ui linkedin button']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//div[text()='Notes']//following::button")).click();
		driver.findElement(By.xpath("//form[@class='ui form']//textarea")).sendKeys("This is a test");
		driver.findElement(By.xpath("//button[@class='ui green button']")).click();
	}
	
	
}
