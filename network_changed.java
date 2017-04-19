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
    public void networkChanges(){
	
		driver.findElement(By.id("com.android.vending:id/navigation_button")).click();
    	driver.findElement(By.name("My apps & games")).click();

    	//make sure code waits
    	new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.vending:id/play_card")));
    	
    	List<AndroidElement> allItems = driver.findElements(By.id("com.android.vending:id/play_card"));
        System.out.println(allItems.size());
        try{
        	allItems.get(0).click();
        }
        catch(Exception e){
        	System.out.println("No Updates available");
        }
        
        
        //Start update
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.vending:id/update_button"))).click();
        
        //Stop network        
        NetworkConnectionSetting connection = new NetworkConnectionSetting(true, false, false);
        driver.setNetworkConnection(connection);

        //Restart network        
        NetworkConnectionSetting connection = new NetworkConnectionSetting(false, true, true);
        driver.setNetworkConnection(connection);

	}




	
	public void pause(){
		try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
}