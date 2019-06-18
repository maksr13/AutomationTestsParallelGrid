import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class WatchFilmLaterTest  extends BaseTest{


    String NameOfFilm;

    //-----------------------------------Tests-----------------------------------
    @Test(priority=1)
    public void AuthorizationOnTheSite() throws InterruptedException{

        Reporter.log("---------------------------------------------------------------------");
        Reporter.log("АВТОРИЗАЦИЯ НА САЙТЕ");
        Reporter.log("---------------------------------------------------------------------");

        Reporter.log("Открытие главной страницы");
        driver.navigate().to("http://www.rezka.ag/");

        Reporter.log("Начало авторизации");
        if(!(driver.findElements(By.cssSelector("#top-head > div > div.b-tophead-right.user-things.pull-right")).size() > 0))
        {
            Reporter.log("Проверка наличия кнопки открытия формы для авторизации");
            Assert.assertTrue(driver.findElements(By.cssSelector("#top-head > div > div.b-tophead-right.pull-right > a.b-tophead__login")).size() > 0, "Нет кнопки окрытия формы авторизации");

            Reporter.log("Нажатие на кнопку авторизации");
            WebElement AuthButton = driver.findElement(By.cssSelector("#top-head > div > div.b-tophead-right.pull-right > a.b-tophead__login"));
            ((WebElement) AuthButton).click();

            Thread.sleep(1000);
            ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
            if(tabs.size() > 1){
                driver.switchTo().window(tabs.get(1));
                driver.close();
                driver.switchTo().window(tabs.get(0));
            }

            Reporter.log("Проверка появления формы авторизации");
            Assert.assertTrue(driver.findElement(By.cssSelector("#login-popup")).getAttribute("style").equals("") != true, "Нет формы авторизации");

            Reporter.log("Проверка наличия поля для ввода логина");
            Assert.assertTrue(driver.findElements(By.cssSelector("#login_name")).size() > 0, "Нет  поля для ввода логина");

            Reporter.log("Проверка наличия поля для ввода пароля");
            Assert.assertTrue(driver.findElements(By.cssSelector("#login_password")).size() > 0, "Нет поля для ввода пароля");

            Reporter.log("Проверка наличия кнопки подверждения авторизации");
            Assert.assertTrue(driver.findElements(By.cssSelector("#login-popup > div.b-popup__content > div > div.b-login__popup-form > form > div.row.clearfix > div > button")).size() > 0, "Нет кнопки подверждения авторизации");

            WebElement LoginInput = driver.findElement(By.cssSelector("#login_name"));
            ((WebElement) LoginInput).sendKeys(log);

            WebElement PassInput = driver.findElement(By.cssSelector("#login_password"));
            ((WebElement) PassInput).sendKeys(psw);

            WebElement SubmitBtn = driver.findElement(By.cssSelector("#login-popup > div.b-popup__content > div > div.b-login__popup-form > form > div.row.clearfix > div > button"));
            ((WebElement) SubmitBtn).click();

            Reporter.log("Проверка успешно пройденой авторизации");
            Assert.assertTrue(driver.findElements(By.cssSelector("#top-head > div > div.b-tophead-right.user-things.pull-right")).size() > 0, "Ошибка авторизации");

        }
        else
            Reporter.log("На данной машине авторизация уже пройдена");

        Reporter.log("");
        Reporter.log("*********************************************************************");
        Reporter.log("АВТОРИЗАЦИЯ НА САЙТЕ УСПЕШНО ПРОЙДЕНА");
        Reporter.log("*********************************************************************");
    }

    @Test(priority=2)
    public void AddingWatchFilmLaterTest()  throws InterruptedException{

        Reporter.log("---------------------------------------------------------------------");
        Reporter.log("ДОБАВЛЕНИЕ ФИЛЬМА В РАЗДЕЛ ДОСМОТРЕТЬ");
        Reporter.log("---------------------------------------------------------------------");


        Reporter.log("Сохранение количества фильмов в разделе Досмотреть (счетчик на верхней панеле)");
        int CurrentAmtFilms = Integer.parseInt(driver.findElement(By.id("saves-count")).getText());

        Reporter.log("Переход на страницу фильма заданного в параметре .xml документа");
        driver.get(SpecialParameterForTest);

        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        if(tabs.size() > 1){
            driver.switchTo().window(tabs.get(1));
            driver.close();
            driver.switchTo().window(tabs.get(0));
        }

        Thread.sleep(400);
        Reporter.log("Проверка наличия плеера");
        Assert.assertTrue(driver.findElements(By.cssSelector("div[class = \"b-player\"]")).size() > 0, "Нет плеера");

        driver.findElement(By.cssSelector("div[class = \"b-player\"]")).click();
        driver.findElement(By.cssSelector("div[class = \"b-player\"]")).click();

        Thread.sleep(80000);

        Reporter.log("Проверка увеличения счетчика количества фильмов в разделе Досмотреть");
        Assert.assertTrue(Integer.parseInt(driver.findElement(By.id("saves-count")).getText()) == CurrentAmtFilms+1, "Счетчик количества фильмов в разделе Досмотреть не увеличился ");

        NameOfFilm = driver.findElement(By.cssSelector("#main > div.b-container.b-wrapper > div > div.b-content__columns.pdt.clearfix > div.b-content__main > div.b-post__title > h1")).getText();

        //Assert.assertTrue(driver.findElements(By.cssSelector("div[class = \"b-player\"]")).size() > 0, "Нет плеера");


        Reporter.log("Переход в раздел Досмотреть");
        driver.findElement(By.cssSelector("div[class=\"b-tophead-right user-things pull-right\"] a")).click();

        Reporter.log("Проверка наличия в разеделе Досмотреть фильма который добавлялся в ходе теста");

        String NameOfFilmCurrent = driver.findElement(By.cssSelector("div[class = \"b-videosaves__list_item\"] > div[class = \"td title\"] > a")).getText();

        Assert.assertTrue(NameOfFilmCurrent.equals(NameOfFilm), "Добавленого в ходе теста фильма нет в разделе");



        Reporter.log("");
        Reporter.log("---------------------------------------------------------------------");
        Reporter.log("ТЕСТ УСПЕШНО ПРОЙДЕН");
        Reporter.log("---------------------------------------------------------------------");
    }

}
