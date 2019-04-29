package Casestudy01;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class cs_01 {
	WebDriver driver;
	@BeforeTest
	public void configurebrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\training testing\\chromedriver_win32 (1)\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://10.232.237.143:443/TestMeApp/");
		driver.manage().window().maximize();
	}
	
	@Test(priority=0)
	public void testLogin() {
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/ul/li[1]/a")).click();//signin
		driver.findElement(By.xpath("//*[@id=\"userName\"]")).sendKeys("lalitha");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("password123");
		driver.findElement(By.name("Login")).click();
		
		Assert.assertEquals(driver.getTitle(),"Home");
		
	}
	@Test(priority=1)
	public void testCart1() {
		WebElement search=driver.findElement(By.xpath("//*[@id=\"myInput\"]"));//search product
		search.sendKeys("Hand Bag");
		driver.findElement(By.xpath("/html/body/div[1]/form/input")).click();//find details
	}
	@Test(priority=2)
	public void checkHandbag() {
		String message1=driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[1]/center[1]/h4")).getText();
		Assert.assertEquals(message1,"Hand bag");
		driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a")).click();//add to cart
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/a[2]")).click();//cart
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"cart\"]/tfoot/tr[2]/td[5]/a")).click();//checkout
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
	@Test(priority=4)
	public void testPayment() {
		driver.findElement(By.xpath("/html/body/b/div/div/div[1]/div/div[2]/div[3]/div/form[2]/input")).click();//proceed to pay
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"swit\"]/div[1]/div/label")).click();//selecting the bank
		driver.findElement(By.xpath("//*[@id=\"btn\"]")).click();//continue
		driver.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/input[1]")).sendKeys("123456");//username
		driver.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/input[2]")).sendKeys("Pass@456");//password
		driver.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/div/div[3]/input")).click();//login
		driver.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/input")).sendKeys("Trans@456");//transaction id
		driver.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/div/div[2]/input")).click();//paynow
		
	}
	@AfterTest
	public void closeApplication() {
		driver.close();
	}
	

}
