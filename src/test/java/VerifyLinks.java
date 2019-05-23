import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;


public class VerifyLinks extends BaseTest {

    static private List<WebElement> links;


   @BeforeClass
   public void GetLinks() throws InterruptedException {
       driver.navigate().to("http://www.rezka.ag/");

       Thread.sleep(8);
       links = driver.findElements(By.tagName("a"));
   }

    @DataProvider
    public static Object[][] DataForVerifyAllLinks() throws InterruptedException {


        Object[][] obj = new Object[links.size()][1];
        for(int i=0; i<links.size(); i++){
            obj[i][0] = (links.get(i)).getAttribute("href");
        }
        return obj;
    }
//-----------------------------------Tests-----------------------------------

    @Test(dataProvider = "DataForVerifyAllLinks")
    public void VerifyAllLinks(String url) throws InterruptedException {
        //Sometimes we may face exception "java.net.MalformedURLException". Keep the code in try catch block to continue the broken link analysis
        try {
            //Use URL Class - Create object of the URL Class and pass the urlLink as parameter
            URL link = new URL(url);
            // Create a connection using URL object (i.e., link)
            HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
            //Set the timeout for 2 seconds
            httpConn.setConnectTimeout(2000);
            //connect using connect method
            httpConn.connect();

            String msg = httpConn.getResponseMessage();

            Reporter.log("Ссылка: " + url);
            Reporter.log("Cooбщение: " + msg);
            Assert.assertTrue(msg.equals("OK"), "Ссылка не работоспособна." + msg);
            Reporter.log("");
        }
//getResponseCode method returns = IOException - if an error occurred connecting to the server.
        catch (Exception e) {
            //e.printStackTrace();
        }
}
}
