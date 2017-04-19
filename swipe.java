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
	public void swipe() throws IOException, BiffException {
   
		Dimension size = driver.manage().window().getSize();

        //Find swipe start and end point from screen's with and height.
        //Find startx point which is at right side of screen.
        int startx = (int) (size.width * 0.70);
        //Find endx point which is at left side of screen.
        int endx = (int) (size.width * 0.30);
        //Find vertical point where you wants to swipe. It is in middle of screen height.
        int starty = size.height / 4;

        for (int i=0; i<8;i++){

            //Swipe from Right to Left.
            driver.swipe(startx, starty, endx, starty, 3000);

        }

        //Find vertical point where you wants to swipe. It is in middle of screen height.
        starty = size.height*4/5;

        for (int i=0; i<8;i++){

            //Swipe from Right to Left.
            driver.swipe(startx, starty, endx, starty, 3000);

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