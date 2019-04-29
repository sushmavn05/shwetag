package Casestudy01;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestMeAppDDT {
	public WebDriver driver;
	public WebDriverWait wait;
	String appUrl="http://10.232.237.143:443/TestMeApp/";
	
	@BeforeTest
	public void beforeTest() throws MalformedURLException{
		System.setProperty("webdriver.chrome.driver", "C:\\training testing\\Driver\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		wait=new WebDriverWait(driver,30);
		driver.get(appUrl);
	}
	@Test(dataProvider="credentials")
	public void testMeAppLogin(String username,String password,String bankname,String bankUN,String bankPWD,String tranPWD)throws InterruptedException
	{
	System.out.println("Username: " +username+"Password: "+password+"BankName: "+bankname+"BankUsername: "+bankUN+"BankPassword: "+bankPWD+"TransactionPassword: "+tranPWD);
	driver.findElement(By.linkText("SignIn")).click();
	//Thread.sleep(2000);
	driver.findElement(By.name("userName")).sendKeys(username);
	driver.findElement(By.name("password")).sendKeys(password);
	driver.findElement(By.name("Login")).click();
	WebElement signout=wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("SignOut")));
	
	Assert.assertNotNull(signout);
	
	WebElement menu1=driver.findElement(By.xpath("//*[@id=\"menu3\"]/li[2]/a/span"));
	
	//span[contains(text(),'All Categories')]
	menu1.click();
	Actions act1= new Actions(driver);
	act1.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Electronics')]"))).click().build().perform();
	act1.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Head Phone')]"))).click().build().perform();
	
	driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a")).click();//add to cart
	driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/a[2]")).click();//cart
	String title=driver.getTitle();
	Assert.assertEquals("View Cart",title);
	
	driver.findElement(By.partialLinkText("Checkout")).click();
	driver.findElement(By.xpath("//input[@value='Proceed to Pay']")).click();
	
	switch(bankname) {
	case "Andhra Bank":
		driver.findElement(By.xpath("//label[contains(text(),'Andhra Bank')]")).click();
		driver.findElement(By.id("btn")).click();
		bankCredentials(bankUN,bankPWD,tranPWD);
		break;
		
	case "HDFC Bank":
		driver.findElement(By.xpath("//label[contains(text(),'HDFC Bank')]")).click();
		driver.findElement(By.id("btn")).click();
		bankCredentials(bankUN,bankPWD,tranPWD);
		break;	
		
	case "IDBI Bank":
		driver.findElement(By.xpath("//label[contains(text(),'IDBI Bank')]")).click();
		driver.findElement(By.id("btn")).click();
		bankCredentials(bankUN,bankPWD,tranPWD);
		break;
		
	case "UCO Bank":
		driver.findElement(By.xpath("//label[contains(text(),'UCO Bank')]")).click();
		driver.findElement(By.id("btn")).click();
		bankCredentials(bankUN,bankPWD,tranPWD);
		break;
	
	default:
		System.out.println("Switch Default Case");
		break;
	}
	
	String title1=driver.getTitle();
	Assert.assertEquals("Order Details", title1);
	driver.findElement(By.linkText("SignOut")).click();
	}
	
	public void  bankCredentials(String buser, String bpassword, String btpassword) 
	{
	driver.findElement(By.name("username")).sendKeys(buser);
	driver.findElement(By.name("password")).sendKeys(bpassword);
	driver.findElement(By.xpath("//input[@value='LOGIN']")).click();
	driver.findElement(By.name("transpwd")).sendKeys(btpassword);
	driver.findElement(By.xpath("//input[@value='PayNow']")).click();
	}
	
	@DataProvider(name="credentials")
	public Object[][] getData() throws IOException
	{
		return ReadTestMeData.ReadData();
	}
	
	@AfterTest
	public void afterTest()
	{
		driver.close();
		
	}
		

}
