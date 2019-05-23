import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class UserCommentingTest extends BaseTest {

    String msg = "Классный фильм";
    String MsgAfterEditing = "Классный фильм!";

    String LastCommentAutor;
    String LastCommentMsg;


    //-----------------------------------Tests-----------------------------------
    @Test(priority = 1)
    public void AuthorizationOnTheSite() {

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

        } else
            Reporter.log("На данной машине авторизация уже пройдена");

        Reporter.log("");
        Reporter.log("*********************************************************************");
        Reporter.log("АВТОРИЗАЦИЯ НА САЙТЕ УСПЕШНО ПРОЙДЕНА");
        Reporter.log("*********************************************************************");
    }

    @Test(priority = 2)
    public void AddingCommentTest() throws InterruptedException {

        Reporter.log("---------------------------------------------------------------------");
        Reporter.log("ДОБАВЛЕНИЕ КОММЕНТАРИЯ К ФИЛЬМУ И ЕГО УДАЛЕНИЕ");
        Reporter.log("---------------------------------------------------------------------");

        Reporter.log("Переход на страницу первого фильма на главной странице");
        WebElement LinkSomeFilm = driver.findElement(By.cssSelector("#main > div.b-container.b-content.b-wrapper > div.b-content__inline > div > div.b-content__inline_items  :first-child :first-child"));
        ((WebElement) LinkSomeFilm).click();

        Reporter.log("Проверка наличия поля для ввода комментария");
        Assert.assertTrue(driver.findElements(By.id("comments-0")).size() > 0, "Нет поля для добавления комментария");

        WebElement CommentField = driver.findElement(By.id("comments-0"));

        Reporter.log("Нажатие на поле для ввода");
        CommentField.click();

        Thread.sleep(1000);
        Reporter.log("Проверка появления правил использования комментариев");
        if (driver.findElements(By.id("ps-overlay-wrap")).size() > 0) {
            Reporter.log("Соглашение с правилами");
            driver.findElement(By.cssSelector("div[class = \"ps-agreement-buttons\"] button")).click();
            Thread.sleep(500);
        }

        Reporter.log("Ввод в поле сообщения");

        CommentField.sendKeys(msg);

        Reporter.log("Подтверждения добавления");
        CommentField.submit();

        Thread.sleep(3000);
        String LastCommentAutor = driver.findElement(By.cssSelector(".comments-tree-item span[class = \"name\"]")).getText();
        String LastCommentMsg = driver.findElement(By.cssSelector(".comments-tree-item div[class=\"text\"] > div")).getText();

        Reporter.log("Проверка добавления комментария");
        Assert.assertTrue(LastCommentAutor.equals(log) && LastCommentMsg.equals(msg), "Последний добавленный комментарий имеет некоректного автора или некоректное сообщение");


        /*Reporter.log("Проверка наличия кнопки добавления комментария");
        Assert.assertTrue(driver.findElements(By.cssSelector("div[class=\"actions-left\"] button")).size() > 0, "Нет кнопки для добавления комментария");*/

        Reporter.log("");
        Reporter.log("*********************************************************************");
        Reporter.log("ТЕСТ УСПЕШНО ПРОЙДЕН");
        Reporter.log("*********************************************************************");
    }

    @Test(priority = 3)
    public void EditingCommentTest() throws InterruptedException {

        Reporter.log("---------------------------------------------------------------------");
        Reporter.log("ПРОВЕРКА ИЗМЕНЕНИЯ КОММЕНТАРИЯ");
        Reporter.log("---------------------------------------------------------------------");

        Reporter.log("Проверка наличия кнопки изменения комментария");
        Assert.assertTrue(driver.findElements(By.cssSelector("ul[class=\"edit\"] :nth-child(1) a")).size() > 0, "Нет кнопки изменения комментария");

        Reporter.log("Нажатие на кнопку изменения комментария");
        driver.findElement(By.cssSelector("ul[class=\"edit\"] :nth-child(1) a")).click();

        Reporter.log("Проверка наличия поля для изменения комментария");
        Assert.assertTrue(driver.findElements(By.id("comments-0")).size() > 0, "Нет поля для изменения комментария");

        WebElement EditingCommentField = driver.findElement(By.id("comments-0"));
        EditingCommentField.clear();
        EditingCommentField.sendKeys(MsgAfterEditing);
        EditingCommentField.submit();

        Reporter.log("Проверка наличия ссылки для перехода на страницу с фильмом");
        Assert.assertTrue(driver.findElements(By.cssSelector("div[class=\"b-info__message\"] a")).size() > 0, "Нет ссылки для перехода на страницу с фильмом");

        Reporter.log("Переход на страницу с фильмом");
        driver.findElement(By.cssSelector("div[class=\"b-info__message\"] a")).click();

        Thread.sleep(3000);

        LastCommentAutor = driver.findElement(By.cssSelector(".comments-tree-item span[class = \"name\"]")).getText();
        LastCommentMsg = driver.findElement(By.cssSelector(".comments-tree-item div[class=\"text\"] > div")).getText();

        Reporter.log("Проверка наличия комментария, а так же его изменения");
        Assert.assertTrue(LastCommentAutor.equals(log) && LastCommentMsg.equals(MsgAfterEditing), "Комментария нет, либо комментарий не был изменен");

        Reporter.log("");
        Reporter.log("*********************************************************************");
        Reporter.log("ТЕСТ УСПЕШНО ПРОЙДЕН");
        Reporter.log("*********************************************************************");
    }

    @Test(priority = 4)
    public void DelletingCommentTest() throws InterruptedException {

        Reporter.log("---------------------------------------------------------------------");
        Reporter.log("ПРОВЕРКА УДАЛЕНИЯ КОММЕНТАРИЯ");
        Reporter.log("---------------------------------------------------------------------");

        Reporter.log("Проверка наличия кнопки удаления");
        Assert.assertTrue(driver.findElements(By.cssSelector("ul[class=\"edit\"] :nth-child(2) a")).size() > 0, "Нет кнопки удаления");

        Reporter.log("Нажатие на кнопку удаления комментария");
        driver.findElement(By.cssSelector("ul[class=\"edit\"] :nth-child(2) a")).click();

        Reporter.log("Подтвержение удаления комментария во всплывающем окне");
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();

        Thread.sleep(3000);
        LastCommentAutor = driver.findElement(By.cssSelector(".comments-tree-item span[class = \"name\"]")).getText();
        LastCommentMsg = driver.findElement(By.cssSelector(".comments-tree-item div[class=\"text\"] > div")).getText();

        Reporter.log("Проверка удаления комментария");
        Assert.assertTrue(!(LastCommentAutor.equals(log) && LastCommentMsg.equals(msg)), "Комментарий не был удален");

        Reporter.log("");
        Reporter.log("*********************************************************************");
        Reporter.log("ТЕСТ УСПЕШНО ПРОЙДЕН");
        Reporter.log("*********************************************************************");
    }


}
