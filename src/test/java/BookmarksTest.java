import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

public class BookmarksTest extends BaseTest{

    private String NameOfFilm;
    //-----------------------------------Tests-----------------------------------
    @Test(priority=1)
    public void AuthorizationOnTheSite(){

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
            WebElement AuthButton = driver.findElement(By.cssSelector("#top-head > div > div.b-tophead-right.pull-right  > a.b-tophead__login"));
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

        }
        else
            Reporter.log("На данной машине авторизация уже пройдена");

        Reporter.log("");
        Reporter.log("*********************************************************************");
        Reporter.log("АВТОРИЗАЦИЯ НА САЙТЕ УСПЕШНО ПРОЙДЕНА");
        Reporter.log("*********************************************************************");
    }

    @Test(priority=2)
    public void AddingBookmarkTest(){

        Reporter.log("---------------------------------------------------------------------");
        Reporter.log("ДОБАВЛЕНИЕ ФИЛЬМА В ЗАКЛАДКИ");
        Reporter.log("---------------------------------------------------------------------");

        Reporter.log("Переход на страницу первого фильма на главной странице");
        WebElement LinkSomeFilm = driver.findElement(By.cssSelector("#main > div.b-container.b-content.b-wrapper > div.b-content__inline > div > div.b-content__inline_items  :first-child :first-child"));
        ((WebElement) LinkSomeFilm ).click();

        Reporter.log("Проверка наличия кнопки добавления в закладки");
        Assert.assertTrue(driver.findElements(By.cssSelector(".b-sideactions__fav button")).size() > 0, "Нет кнопки добавления в закладки");

        WebElement AddingBookmarkBtn = driver.findElement(By.cssSelector(".b-sideactions__fav button"));
        ((WebElement) AddingBookmarkBtn ).click();

        Reporter.log("Проверка появления формы добавления в закладки");
        Assert.assertTrue(driver.findElement(By.cssSelector("#user-favorites-holder")).getAttribute("style").equals("") != true, "Нет формы добавления в закладки");


        /* Если нет папки для закладок - она создаеться и выбираеться в ином случае выбираеться первый по списку*/
        Reporter.log("Проверка наличия папки для закладок");
        if(!(driver.findElements(By.className("hd-label-row")).size() > 0))
        {
            Reporter.log("Создание папки");

            Reporter.log("Проверка наличия кнопки создания папки");
            Assert.assertTrue(driver.findElements(By.cssSelector("#addcat-fav-add > a")).size() > 0, "Нет кнопки создания папки для закладки");

            Reporter.log("Нажатие на кнопку создания папки");
            driver.findElement(By.cssSelector("#addcat-fav-add > a")).click();

            Reporter.log("Проверка наличия поля ввода имени папки");
            Assert.assertTrue(driver.findElements(By.cssSelector("#addcat-fav-name")).size() > 0, "Нет поля для ввода имени папки");

            Reporter.log("Проверка наличия кнопки для подверждения создания папки");
            Assert.assertTrue(driver.findElements(By.cssSelector("#addcat-fav-addbt")).size() > 0, "Нет кнопки подверждения создания папки");

            Reporter.log("Ввод имени папки");
            driver.findElement(By.cssSelector("#addcat-fav-name")).sendKeys("Закладки");

            Reporter.log("Нажатие на кнопку создания папки");
            driver.findElement(By.cssSelector("#addcat-fav-addbt")).click();

            Reporter.log("Проверка создания папки");
            if(!(driver.findElements(By.cssSelector("#user-favorites-list > div.hd-label-row")).size() > 0))
                Assert.assertTrue(driver.findElements(By.cssSelector("hd-label-row")).size() > 0, "Папка не создалась");
            else
            {
                Reporter.log("Проверка автоматического выбора созданной папки");
                Assert.assertTrue(driver.findElement(By.cssSelector("#user-favorites-list > div.hd-label-row :first-child")).isSelected(), "Папка не выбралась автоматически");
            }
        }
        else {
            Reporter.log("Выбор папки закладок с помощью чекбокса");
            WebElement SelectingDirectoryChBox = driver.findElement(By.cssSelector("#user-favorites-list > div.hd-label-row > input"));
            if (!SelectingDirectoryChBox.isSelected()) {
                WebElement SelectingDirectoryDiv = driver.findElement(By.cssSelector("#user-favorites-list > div.hd-label-row"));
                SelectingDirectoryDiv.click();
            }

            /*ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
            driver.switchTo().window(tabs2.get(1));
            driver.close();
            Thread.sleep();
            driver.switchTo().window(tabs2.get(0));*/
        }

        NameOfFilm = driver.findElement(By.cssSelector("#main > div.b-container.b-wrapper > div > div.b-content__columns.pdt.clearfix > div.b-content__main > div.b-post__title > h1")).getText();
    }

    @Test(priority=3)
    public void AddCheckBookmarkTest() {
        Reporter.log("");
        Reporter.log("---------------------------------------------------------------------");
        Reporter.log("ПРОВЕРКА ПОЯВЛЕНИЯ ДОБАВЛЕННОГО ФИЛЬМА В ЗАКЛАДКАХ");
        Reporter.log("---------------------------------------------------------------------");

        Reporter.log("Проверка наличия кнопки перехода в раздел закладок");
        Assert.assertTrue(driver.findElements(By.cssSelector("#top-head > div > div.b-tophead-right.user-things.pull-right > a:nth-child(3)")).size() > 0, "Нет кнопки перехода закладки");

        Reporter.log("Переход в раздел закладок");
        driver.findElement(By.cssSelector("#top-head > div > div.b-tophead-right.user-things.pull-right > a:nth-child(3)")).click();

        Reporter.log("Проверка наличия закладок");
        Assert.assertTrue(driver.findElements(By.cssSelector(".b-content__inline div[class = \"b-content__inline_item\"]")).size() > 0, "Нет закладок. Закладка не добавилась");

        Reporter.log("Проверка того, что последняя добавленная закладка - это именно тот фильм который был добавлен в ходе теста");
        Assert.assertTrue(driver.findElement(By.cssSelector(".b-content__inline div[class = \"b-content__inline_item\"] > .b-content__inline_item-link > a")).getText().equals(NameOfFilm), "Последний добавл. фильм в закладки - НЕ тот, который был добавлен в ходе теста");

        Reporter.log("");
        Reporter.log("*********************************************************************");
        Reporter.log("ТЕСТ ДЛЯ ПРОВЕРКИ ДОБАВЛЕНИЯ ФИЛЬМА В ЗАКЛАДКИ УСПЕШНО ПРОЙДЕН");
        Reporter.log("*********************************************************************");
    }

    @Test(priority=4)
    public void DelletingBookmarkTest() throws InterruptedException{
        Reporter.log("---------------------------------------------------------------------");
        Reporter.log("УДАЛЕНИЕ ФИЛЬМА ИЗ ЗАКЛАДОК");
        Reporter.log("---------------------------------------------------------------------");

        Reporter.log("Проверка наличия чекбокса удаления");
        Assert.assertTrue(driver.findElements(By.cssSelector(".b-content__inline div[class = \"b-content__inline_item\"] > .b-content__inline_item-cover input[type=\"checkbox\"]")).size() > 0, "Нет кнопки удаления фильма из закладок");

        Reporter.log("Нажатие на чекбокс удаления");
        driver.findElement(By.cssSelector(".b-content__inline div[class = \"b-content__inline_item-cover\"] label")).click();

        Reporter.log("Проверка появления панели удаления");
        Assert.assertTrue(driver.findElements(By.cssSelector("div[class = \"b-favorites_content__cats_list dad-draggable dad-container dad-active\"] div[class = \"b-favorites_content__cats_edit_holder dads-children\"]")).size() > 0, "Нет панели удаления фильма из закладок");

        Reporter.log("Проверка наличия кнопки удаления");
        Assert.assertTrue(driver.findElements(By.cssSelector("div[class = \"b-favorites_content__cats_list dad-draggable dad-container dad-active\"] div[class = \"b-favorites_content__cats_edit_holder dads-children\"] > button[class = \"btn btn-attention btn-mini remove-items\"]")).size() > 0, "Нет кнопки удаления");

        Reporter.log("Нажатие на кнопку удаления");
        driver.findElement(By.cssSelector("div[class = \"b-favorites_content__cats_list dad-draggable dad-container dad-active\"] div[class = \"b-favorites_content__cats_edit_holder dads-children\"] > button[class = \"btn btn-attention btn-mini remove-items\"]")).click();

        Reporter.log("Подтвержение удаления во всплывающем окне");
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();

        Thread.sleep(2000);
        String LastBookmark;
        Reporter.log("Проверка того, что последняя добавленная закладка - это фильм отличающийся от того который был удален");
        if(driver.findElements(By.cssSelector("div[class = \"b-content__inline_item\"] > div[class = \"b-content__inline_item-link\"] > a")).size() != 0)
        {
            LastBookmark = driver.findElement(By.cssSelector("div[class = \"b-content__inline_item\"] > div[class = \"b-content__inline_item-link\"] > a")).getText();
            Assert.assertTrue(!(LastBookmark.equals(NameOfFilm)), "Фильм не был удален");
        }

        Reporter.log("");
        Reporter.log("*********************************************************************");
        Reporter.log("ТЕСТ ДЛЯ ПРОВЕРКИ УДАЛЕНИЯ ФИЛЬМА ИЗ ЗАКЛАДОК УСПЕШНО ПРОЙДЕН");
        Reporter.log("*********************************************************************");
    }
}
