import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class AddingMarkForFilmTest extends BaseTest {

    //-----------------------------------Tests-----------------------------------
    @Test(priority = 1)
    public void AuthorizationOnTheSite() throws InterruptedException  {

        Reporter.log("---------------------------------------------------------------------");
        Reporter.log("АВТОРИЗАЦИЯ НА САЙТЕ");
        Reporter.log("---------------------------------------------------------------------");

        Reporter.log("Открытие главной страницы");
        driver.navigate().to("http://www.rezka.ag/");

        Reporter.log("Начало авторизации");
        if (!(driver.findElements(By.cssSelector("#top-head > div > div.b-tophead-right.user-things.pull-right")).size() > 0)) {
            Reporter.log("Проверка наличия кнопки открытия формы для авторизации");
            Assert.assertTrue(driver.findElements(By.cssSelector("#top-head > div > div.b-tophead-right.pull-right > a.b-tophead__login")).size() > 0, "Нет кнопки окрытия формы авторизации");

            Reporter.log("Нажатие на кнопку авторизации");
            WebElement AuthButton = driver.findElement(By.cssSelector("#top-head > div > div.b-tophead-right.pull-right > a.b-tophead__login"));
            ((WebElement) AuthButton).click();


            Thread.sleep(1000);
            checkNewTabsAndDeleting();

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

            checkNewTabsAndDeleting();

            Reporter.log("Проверка успешно пройденой авторизации");
            Assert.assertTrue(driver.findElements(By.cssSelector("#top-head > div > div.b-tophead-right.user-things.pull-right")).size() > 0, "Ошибка авторизации");

        } else
            Reporter.log("На данной машине авторизация уже пройдена");

        Reporter.log("");
        Reporter.log("*********************************************************************");
        Reporter.log("АВТОРИЗАЦИЯ НА САЙТЕ УСПЕШНО ПРОЙДЕНА");
        Reporter.log("*********************************************************************");
    }

    @Test(priority = 2)
    public void AddingMarkForFilm() throws InterruptedException {

        Reporter.log("---------------------------------------------------------------------");
        Reporter.log("ПРОВЕРКА ДОБАВЛЕНИЯ ОЦЕНКИ");
        Reporter.log("---------------------------------------------------------------------");

        driver.get(SpecialParameterForTest);

        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        if(tabs.size() > 1){
            driver.switchTo().window(tabs.get(1));
            driver.close();
            driver.switchTo().window(tabs.get(0));
        }
        Reporter.log("Запись в переменную значение счетчика колличества оценок");
        Integer NumberMarks = Integer.parseInt(driver.findElement(By.cssSelector(".votes > span")).getText());

        Reporter.log("Добавление оценки");
        driver.findElement(By.cssSelector("ul[class=\"b-post__rating_layer\"] :nth-child(5) a")).click();

        Thread.sleep(3000);
        Reporter.log("Запись в переменную текущего значения счетчика колличества оценок");
        Integer NumberMarksAfterAdding = Integer.parseInt(driver.findElement(By.cssSelector("span[title=\"Количество голосов\"] > span")).getText());

        Reporter.log("Проверка добавления оценки (счетчик должен увеличиться на один)");
        Assert.assertTrue(NumberMarksAfterAdding == NumberMarks+1, "Счетчик не увеличился");

        Reporter.log("");
        Reporter.log("*********************************************************************");
        Reporter.log("ТЕСТ УСПЕШНО ПРОЙДЕН");
        Reporter.log("*********************************************************************");
    }
}
