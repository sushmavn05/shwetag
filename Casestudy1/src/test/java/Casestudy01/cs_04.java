package Casestudy01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class cs_04 {
	WebDriver driver;
	@BeforeTest
	public void openapplication() {
		System.setProperty("webdriver.chrome.driver", "C:\\training testing\\Driver\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://10.232.237.143:443/TestMeApp/");
		driver.manage().window().maximize();
	}
	@Test
	public void testRegistration() throws InterruptedException   {
		
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/ul/li[2]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"userName\"]")).sendKeys("Sushma");
		
		
		driver.findElement(By.xpath("//*[@id=\"firstName\"]")).sendKeys("Sushma");
		
		driver.findElement(By.xpath("//*[@id=\"lastName\"]")).sendKeys("Vadher");
		
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("sushmavn05");
		driver.findElement(By.xpath("//*[@id=\"pass_confirmation\"]")).sendKeys("sushmavn05");
		
		driver.findElement(By.xpath("//input[@value='Female']")).click();
		
		driver.findElement(By.xpath("//*[@id=\"emailAddress\"]")).sendKeys("sushmasvn0596@gmail.com");
		driver.findElement(By.xpath("//*[@id=\"mobileNumber\"]")).sendKeys("8971635679");
		
		
		driver.findElement(By.xpath("//*[@id=\"dob\"]")).sendKeys("10/05/1996");
		
		driver.findElement(By.xpath("//*[@id=\"address\"]")).sendKeys("Anugraha,Davanagere,Karnataka");
		WebElement selectElement=driver.findElement(By.xpath("//*[@id=\"securityQuestion\"]"));
		Select dropdown=new Select(selectElement);
		dropdown.selectByIndex(2);
		driver.findElement(By.xpath("//*[@id=\"answer\"]")).sendKeys("Lavender");
		driver.findElement(By.xpath("/html/body/main/div/div/form/fieldset/div/div[13]/div/input[1]")).click();
		Thread.sleep(5000);
	}
	@AfterTest
	public void closeApplication() {
		driver.close();
	}

}
