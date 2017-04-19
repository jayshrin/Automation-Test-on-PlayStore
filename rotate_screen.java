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
    public void rotateScreen(){
        
        if(driver.getOrientation() == ScreenOrientation.PORTRAIT)
    		driver.rotate(ScreenOrientation.LANDSCAPE);
    	else{
    		
    		driver.rotate(ScreenOrientation.PORTRAIT);
    	}
    	
    	//make sure screen does not rotate abruptly
    	pause();
		
		if(driver.getOrientation() == ScreenOrientation.LANDSCAPE)
    		driver.rotate(ScreenOrientation.PORTRAIT);
    	else{
    		
    		driver.rotate(ScreenOrientation.LANDSCAPE);
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