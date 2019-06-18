import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class FTest extends BaseTest{

    @Test
    public void Test1()  throws InterruptedException {

        Reporter.log("Переход на страницу первого фильма на главной странице");
        driver.navigate().to("https://rezka.ag/series/crime/30834-visting-2019.html");

        /*WebElement LinkSomeFilm = driver.findElement(By.cssSelector("#main > div.b-container.b-content.b-wrapper > div.b-content__inline > div > div.b-content__inline_items  :first-child :first-child"));
        ((WebElement) LinkSomeFilm).click();*/

        String Msg = "говно или норм?";
        String Log = "Константин Шаныгин";
        //li[class="comments-tree-item"]
        String LastCommentAutor = driver.findElement(By.cssSelector(".comments-tree-item span[class = \"name\"]")).getText();
        String LastCommentMsg = driver.findElement(By.cssSelector(".comments-tree-item div[class=\"text\"] > div")).getText();

        Reporter.log("asdfasdfasdf");
    }
}
