import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class test{
	
	public  static final String TestTag = "";
	private boolean result =false;
	private AndroidDriver<AndroidElement> driver;

    @Before
    public void setUp() throws Exception {
        // set up appium
        
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","ZX1D62ZRCP");
        capabilities.setCapability("appPackage", "com.android.vending");
        capabilities.setCapability("appActivity", "com.google.android.finsky.activities.MainActivity");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        
    }

    @After
    public void tearDown() throws Exception {
       ExcelSheetLibrary excel = new ExcelSheetLibrary("data/automation_sheet.xls");
       ExcelSheetLibrary.saveData(TestTag,driver.getCapabilities().getCapability("platformVersion").toString(),result);

       driver.quit();
    }

    @Test
public void validationRedeem() throws IOException, BiffException {
   //WebElement el = driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'digit_6')]"));
   //el.click();

   ExcelSheetLibrary excel = new ExcelSheetLibrary("data/automation_sheet.xls");

   driver.findElement(By.id("com.android.vending:id/navigation_button")).click();
   //driver.findElement(By.id("com.android.vending:id/header_more")).click();
   driver.scrollTo("Redeem");
   driver.findElement(By.name("Redeem")).click();

   ((MobileElement)driver.findElement(By.id("com.android.vending:id/pin_entry"))).sendKeys(ExcelSheetLibrary.ReadCell(ExcelSheetLibrary.GetCell("Coupon"), 1));

   driver.findElement(By.id("com.android.vending:id/continue_button")).click();

   WebDriverWait wait = new WebDriverWait(driver, 15);
   wait.until(ExpectedConditions.presenceOfElementLocated(By
           .id("com.android.vending:id/error")));

   if(driver.findElement(By.id("com.android.vending:id/error")).isDisplayed()){
       System.out.print(ExcelSheetLibrary.ReadCell(ExcelSheetLibrary.GetCell("Coupon"), 1) + "Coupon Failed!!!");
   }else{
       System.out.print("Successfully redeem!!!");
   }
 }


	
	public void pause(){
		try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
}