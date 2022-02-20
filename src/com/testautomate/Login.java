package com.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

import com.excel.utility.Xls_Reader;

public class Login {

	WebDriver driver;
	// String[] monthArr=new String[2];
	String monthVal;
	String monthName;
	String dateyear;
	int monthYear;

	@BeforeMethod
	@Parameters({ "email", "password" })
	public void setup(String email, String password) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:/eclipse/chromedriver.exe");
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

//	@AfterMethod
//	public void closeBrowser() {
//		driver.quit();
//	}
//
//	@Test(priority = 1)
//	public void login() {
//		Boolean b = driver.findElement(By.xpath("//div[@class='header item']")).isDisplayed();
//		Assert.assertTrue(b);
//	}

	@Test(priority = 2)
	@Parameters({"Date"})
	public void calendar(String Date) throws InterruptedException {
		// div[@class='rbc-calendar']//child::span[text()='February 2022']

		String date = Date;
		String dateArr[] = date.split("/");
		String day = dateArr[0];
		String month = dateArr[1];
		String DateYear=dateArr[1]+' '+dateArr[2];
		System.out.println(DateYear);
		int year = Integer.parseInt(dateArr[2]);

		Actions action = new Actions(driver);

		action.moveToElement(driver.findElement(By.xpath("//a[@class='item']/following::i[@class='calendar icon']")))
				.build().perform();
		driver.findElement(By.xpath("//a[@class='item']/following::i[@class='calendar icon']")).click();

		monthVal = driver.findElement(By.xpath("//*[@id=\"main-content\"]/div/div[2]/div/div[2]/div/div[1]/span[2]"))
				.getText();

		String monthArr[] = monthVal.split(" ");
		monthName = monthArr[0];
		monthYear = Integer.parseInt(monthArr[1]);
		dateyear=monthArr[0]+' '+monthArr[1];

		boolean b = false;
		
		if(monthYear<year) {

		while (b == false) {

			while (DateYear != dateyear) {

//				System.out.println(monthName);

				driver.findElement(By.xpath("//i[@class='chevron right icon']")).click();
				Thread.sleep(100);

				monthVal = driver
						.findElement(By.xpath("//*[@id=\"main-content\"]/div/div[2]/div/div[2]/div/div[1]/span[2]"))
						.getText();

				String montharr[] = monthVal.split(" ");
				monthName = montharr[0];
				monthYear = Integer.parseInt(montharr[1]);
				dateyear=montharr[0]+' '+montharr[1];
				System.out.println(dateyear);

				if (DateYear.equalsIgnoreCase(dateyear)) {
					b = true;
					break;
				}

			}
			if (b) {
				break;
			}
		}}else {
			while (b == false) {

				while (DateYear != dateyear) {

//					System.out.println(monthName);
//					System.out.println("'" + month + "'");

					driver.findElement(By.xpath("//i[@class='chevron left icon']")).click();
					Thread.sleep(100);

					monthVal = driver
							.findElement(By.xpath("//*[@id=\"main-content\"]/div/div[2]/div/div[2]/div/div[1]/span[2]"))
							.getText();

					String montharray[] = monthVal.split(" ");
					monthName = montharray[0];
					monthYear = Integer.parseInt(montharray[1]);
					dateyear=montharray[0]+' '+montharray[1];
					System.out.println(dateyear);

					if (DateYear.equalsIgnoreCase(dateyear)) {
						b = true;
						break;
					}

				}
				if (b) {
					break;
				}
			}
		}

		String beforeXpath = "//*[@id=\"main-content\"]/div/div[2]/div/div[2]/div/div[2]/div[";
		String afterXpath = "]/div[2]/div/div[";
		String endXpath = "]/a";

		final int totalWeekDays = 7;
		boolean flag = false;

		for (int rowNum = 2; rowNum <= 7; rowNum++) {
			for (int colNum = 1; colNum <= totalWeekDays; colNum++) {
				String dayVal = driver.findElement(By.xpath(beforeXpath + rowNum + afterXpath + colNum + endXpath))
						.getText();
				System.out.println(dayVal);
				if (dayVal.equalsIgnoreCase(day)) {
					driver.findElement(By.xpath(beforeXpath + rowNum + afterXpath + colNum + endXpath)).click();
					flag = true;
					break;
				}
			}
			if (flag) {
				break;
			}
		}

	}

//	@Test(priority = 3)
//	@Parameters({ "contactname", "editcontact" })
//	public void contacts(String contactname, String editcontact) throws InterruptedException {
//		// a[contains(text(),'John
//		// Doe')]/parent::td//preceding-sibling::td//input[@name='id']
//		Actions action = new Actions(driver);
//
//		action.moveToElement(driver.findElement(By.xpath("//a[@class='item']/following::i[@class='users icon']")))
//				.build().perform();
//		driver.findElement(By.xpath("//a[@class='item']/following::i[@class='users icon']")).click();
//		Thread.sleep(5000);
//		action.moveToElement(driver.findElement(By.xpath("//th[text()='Category']"))).build().perform();
//		Thread.sleep(2000);
//		// driver.findElement(By.xpath("//th[text()='Category']")).click();
//		// Thread.sleep(5000);
//
//		// WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
//		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//a[text()='"+contactname+"']/parent::td//preceding-sibling::td//input[@name='id']")));
//
//		action.moveToElement(driver.findElement(
//				By.xpath("//a[text()='" + contactname + "']/parent::td//preceding-sibling::td//input[@name='id']")))
//				.click().build().perform();
//		Thread.sleep(2000);
//
//		driver.findElement(
//				By.xpath("//a[text()='" + editcontact + "']/parent::td//following-sibling::td//i[@class='edit icon']"))
//				.click();
//		Thread.sleep(3000);
//
//		Xls_Reader reader = new Xls_Reader("D:/eclipse/eclipse-workspace/FreeCRM/src/com/testdata/freecrm.xlsx");
//
//		String Company = reader.getCellData("Contacts", "Company", 2);
//		String Description = reader.getCellData("Contacts", "Description", 2);
//		String Position = reader.getCellData("Contacts", "Position", 2);
//		String Department = reader.getCellData("Contacts", "Department", 2);
//
//		driver.findElement(By.xpath("//label[text()='Company']/parent::div//child::div//input")).clear();
//		driver.findElement(By.xpath("//label[text()='Company']/parent::div//child::div//input")).sendKeys(Company);
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//label[text()='Description']//following::textarea")).clear();
//		driver.findElement(By.xpath("//label[text()='Description']//following::textarea")).sendKeys(Description);
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//label[text()='Position']/parent::div//child::div//input")).clear();
//		driver.findElement(By.xpath("//label[text()='Position']/parent::div//child::div//input")).sendKeys(Position);
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//label[text()='Department']/parent::div//child::div//input")).clear();
//		driver.findElement(By.xpath("//label[text()='Department']/parent::div//child::div//input")).sendKeys(Department);
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//label[text()='Category']//following::div[@name='category']")).click();
//		driver.findElement(By.xpath("//span[text()='Lead']")).click();
//		Thread.sleep(2000);
//
//		driver.findElement(By.xpath("//button[@class='ui linkedin button']")).click();
//		Thread.sleep(2000);
//
//		driver.findElement(By.xpath("//div[text()='Notes']//following::button")).click();
//		driver.findElement(By.xpath("//form[@class='ui form']//textarea")).sendKeys("This is a test");
//		driver.findElement(By.xpath("//button[@class='ui green button']")).click();
//	}

	@Test(priority=1, dataProvider="getTestData")
	public void newcontact(String FirstName, String LastName, String Company, String Description, String Position, String Department) throws InterruptedException {
		Actions action = new Actions(driver);

		action.moveToElement(driver.findElement(By.xpath("//a[@class='item']/following::i[@class='users icon']")))
				.build().perform();
		driver.findElement(By.xpath("//a[@class='item']/following::i[@class='users icon']")).click();
		Thread.sleep(5000);
		action.moveToElement(driver.findElement(By.xpath("//button[text()='Create']"))).click().build().perform();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//label[text()='First Name']/parent::div//child::div//input")).clear();
		driver.findElement(By.xpath("//label[text()='First Name']/parent::div//child::div//input")).sendKeys(FirstName);
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Last Name']/parent::div//child::div//input")).clear();
		driver.findElement(By.xpath("//label[text()='Last Name']/parent::div//child::div//input")).sendKeys(LastName);
		Thread.sleep(500);
		
		driver.findElement(By.xpath("//label[text()='Company']/parent::div//child::div//input")).clear();
		driver.findElement(By.xpath("//label[text()='Company']/parent::div//child::div//input")).sendKeys(Company);
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Description']//following::textarea")).clear();
		driver.findElement(By.xpath("//label[text()='Description']//following::textarea")).sendKeys(Description);
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Position']/parent::div//child::div//input")).clear();
		driver.findElement(By.xpath("//label[text()='Position']/parent::div//child::div//input")).sendKeys(Position);
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Department']/parent::div//child::div//input")).clear();
		driver.findElement(By.xpath("//label[text()='Department']/parent::div//child::div//input")).sendKeys(Department);
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Category']//following::div[@name='category']")).click();
		driver.findElement(By.xpath("//span[text()='Lead']")).click();
		Thread.sleep(500);
		
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(2000);
		
		action.moveToElement(driver.findElement(By.xpath("//a[@class='item']/following::i[@class='users icon']")))
		.build().perform();
        driver.findElement(By.xpath("//a[@class='item']/following::i[@class='users icon']")).click();

        Boolean newcontactcheck=driver.findElement(By.xpath("//a[text()='"+FirstName+" "+LastName+"']")).isDisplayed();
        Assert.assertTrue(newcontactcheck);
        
	}
	
	@DataProvider
	public Iterator<Object[]> getTestData(){
		ArrayList<Object[]> testData = TestDataUtil.getDataFromExcel();
		return testData.iterator();
	}
}
