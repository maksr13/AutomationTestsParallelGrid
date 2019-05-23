import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

public class RegistrationFormFieldsTest extends BaseTest{


    //-----------------------------------Tests-----------------------------------

    /* ПЕРЕХОД НА РЕГИСТРАЦИОННУЮ ФОРМУ */
    @Test
    public void GoToRegistrationForm() {

        Reporter.log("---------------------------------------------------------------------");
        Reporter.log("ПЕРЕХОД НА РЕГИСТР. ФОРМУ И ПРОВЕРКА НАЛИЧИЯ ПОЛЕЙ");
        Reporter.log("---------------------------------------------------------------------");

        Reporter.log("Открытие главной страницы");
        driver.navigate().to("http://www.rezka.ag/");

        Reporter.log("Проверка наличия кнопки открытия формы для регистриции");
        Assert.assertTrue(driver.findElements(By.className("b-tophead__register")).size() > 0, "Нет кнопки окрытия регистрационной формы");

        Reporter.log("Нажатие на кнопку регистрации");
        WebElement registrButton = driver.findElement(By.className("b-tophead__register"));
        ((WebElement) registrButton).click();

        Reporter.log("Проверка появления формы регистрации");
        Assert.assertTrue(driver.findElement(By.id("register-popup")).getAttribute("style").equals("") != true, "Регистрационная форма не открылась");

        Reporter.log("Проверка наличия поля email");
        Assert.assertTrue(driver.findElements(By.id("email")).size() > 0, "Нет поля email");

        Reporter.log("Проверка наличия поля login");
        Assert.assertTrue(driver.findElements(By.id("name")).size() > 0, "Нет поля login");

        Reporter.log("Проверка наличия поля password");
        Assert.assertTrue(driver.findElements(By.id("password1")).size() > 0, "Нет поля password");

        Reporter.log("Проверка наличия кнопки подверждения регистрации");
        Assert.assertTrue(driver.findElements(By.name("submit")).size() > 0, "Нет кнопки подвеждения регистрации");

        Reporter.log("");
        Reporter.log("*********************************************************************");
        Reporter.log("ТЕСТ ДЛЯ ПРОВЕРКА РЕГИСТРАЦИОННОЙ ФОРМЫ УСПЕШНО ПРОЙДЕН");
        Reporter.log("*********************************************************************");
    }


    /* ИНФОРМАЦИЯ ДЛЯ ПРОВЕРКИ ПОЛЯ ДЛЯ ВВОДА EMAIL */
    @DataProvider
    public static Object[][] DataForTestEmailField() {

        return new Object[][] {

                /* НЕВАЛИДНЫЕ НАБОРЫ ДАННЫХ */
                { "asdfasdfasdfasdfasdf", "Введен неверный email адрес","Введен неверный email адрес", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "invalid.email.com", "Введен неверный email адрес","Введен неверный email адрес", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "invalid@test@email.com", "Введен неверный email адрес","Введен неверный email адрес", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "i”n(v)a;l:id@email.com", "Введен неверный email адрес","Введен неверный email адрес", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "invalid”test”@email.com", "Введен неверный email адрес","Введен неверный email адрес", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "this is”not\\allowed@email.com", "Введен неверный email адрес","Введен неверный email адрес", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { " invalid…test@email.com", "Введен неверный email адрес","Введен неверный email адрес", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { " invalid@email…com", "Введен неверный email адрес","Введен неверный email адрес", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "email@111.222.333.44444", "Введен неверный email адрес","Введен неверный email адрес", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { " invalid@-email.com", "Введен неверный email адрес","Введен неверный email адрес", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { ".invalid@email.com", "Введен неверный email адрес","Введен неверный email адрес", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "invalid.@email.com", "Введен неверный email адрес","Введен неверный email адрес", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "invalid@email.com (tester)", "Введен неверный email адрес","Введен неверный email адрес", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "@email.com", "Введен неверный email адрес","Введен неверный email адрес", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "invalid@email…com", "Введен неверный email адрес","Введен неверный email адрес", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "invalid@email…com\"", "Введен неверный email адрес","Введен неверный email адрес", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "invalid@test@email.com", "Введен неверный email адрес","Введен неверный email адрес", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},

                /* ВАЛИДНЫЕ НАБОРЫ ДАННЫХ */
                { "q@gmail.com", "Можно использовать данный email для регистрации", "Данный email уже зарегистрирован", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "simpleEmail@email.com", "Можно использовать данный email для регистрации", "Данный email уже зарегистрирован", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "simple.email@email.com", "Можно использовать данный email для регистрации", "Данный email уже зарегистрирован", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "plus+symbol@email.com", "Можно использовать данный email для регистрации", "Данный email уже зарегистрирован", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "dash-symbol@email.com", "Можно использовать данный email для регистрации", "Данный email уже зарегистрирован", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "\"unusual\"@email.com", "Можно использовать данный email для регистрации", "Данный email уже зарегистрирован", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "dash-symbol@email-dash.com", "Можно использовать данный email для регистрации", "Данный email уже зарегистрирован", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "test@emailServer", "Можно использовать данный email для регистрации", "Данный email уже зарегистрирован", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "\" \"@email.com", "Можно использовать данный email для регистрации", "Данный email уже зарегистрирован", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "user@[IPv6:2001:DB8::1]", "Можно использовать данный email для регистрации", "Данный email уже зарегистрирован", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "example@s.solutions", "Можно использовать данный email для регистрации", "Данный email уже зарегистрирован", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},
                { "12345@email.com", "Можно использовать данный email для регистрации", "Данный email уже зарегистрирован", "Имя пользователя не может быть пустым", "Длина пароля должна быть не менее 6 символов"},

        };
    }

    /* ПРОВЕРКА ПОЛЯ EMAIL НА ВАЛИДНОСТЬ */
    @Test(dataProvider = "DataForTestEmailField")
    public void RegistrationEmailTest(String emailString, String FirstMsgEm, String SecondMsgEm, String MsgName, String MsgPsw) throws InterruptedException {

        Reporter.log("*");
        Reporter.log(emailString);
        Reporter.log("*");

        WebElement emailInput = driver.findElement(By.id("email"));
        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement pswInput = driver.findElement(By.id("password1"));
        Reporter.log("Очистка всех полей");
        Thread.sleep(1400);
        emailInput.clear(); nameInput.clear(); pswInput.clear();

        Reporter.log("Ввод в поле email");
        emailInput.sendKeys(emailString);

        Reporter.log("Нажатие на кнопку подверждения регистрации");
        driver.findElement(By.cssSelector("#registration > div > div > button")).click();

        Reporter.log("Проверка выведенного сообщения");
        //driver.getPageSource().contains(MsgPsw)
        Boolean CheckCorrectMsg;

        Thread.sleep(1400);
        //ArrayList<WebElement> FinalString1 = driver.findElements(By.xpath("//*[@id=\"result-registration-email\"]/span[text()=" + FirstMsg));
        if( (driver.getPageSource().contains(FirstMsgEm) || driver.getPageSource().contains(SecondMsgEm)) &&
                driver.getPageSource().contains(MsgName) && driver.getPageSource().contains(MsgPsw))
        {
            CheckCorrectMsg = true;
            Reporter.log("Корректное сообщение выведено");
        }
        else
        {
            CheckCorrectMsg = false;
            Assert.assertTrue(CheckCorrectMsg, "Не выведенно корректное сообщение, или выведенное сообщение - некорректно");
        }
        Reporter.log("\n\n");
    }




    /* ИНФОРМАЦИЯ ДЛЯ ПРОВЕРКИ ПОЛЯ ДЛЯ ВВОДА LOGIN */
    @DataProvider
    public static Object[][] DataForTestLoginField() {

        return new Object[][]{

                /* НЕВАЛИДНЫЕ НАБОРЫ ДАННЫХ */
                {"q", "Логин не может быть менее 3 символов и более 30 символов", "Данный логин уже зарегистрирован", "Введен неверный email адрес", "Длина пароля должна быть не менее 6 символов"},
                {"qw", "Логин не может быть менее 3 символов и более 30 символов", "Данный логин уже зарегистрирован", "Введен неверный email адрес", "Длина пароля должна быть не менее 6 символов"},
                {"", "Имя пользователя не может быть пустым","Имя пользователя не может быть пустым", "Введен неверный email адрес", "Длина пароля должна быть не менее 6 символов"},
                {"?log", "Вы используете недопустимое имя", "Данный логин уже зарегистрирован", "Введен неверный email адрес", "Длина пароля должна быть не менее 6 символов"},
                {"!log", "Вы используете недопустимое имя", "Данный логин уже зарегистрирован", "Введен неверный email адрес", "Длина пароля должна быть не менее 6 символов"},
                {"\"log", "Вы используете недопустимое имя", "Данный логин уже зарегистрирован", "Введен неверный email адрес", "Длина пароля должна быть не менее 6 символов"},
                {"$log", "Вы используете недопустимое имя", "Данный логин уже зарегистрирован", "Введен неверный email адрес", "Длина пароля должна быть не менее 6 символов"},
                {"&log", "Вы используете недопустимое имя", "Данный логин уже зарегистрирован", "Введен неверный email адрес", "Длина пароля должна быть не менее 6 символов"},
                {"*log", "Вы используете недопустимое имя", "Данный логин уже зарегистрирован", "Введен неверный email адрес", "Длина пароля должна быть не менее 6 символов"},
                {"%log", "Вы используете недопустимое имя", "Данный логин уже зарегистрирован", "Введен неверный email адрес", "Длина пароля должна быть не менее 6 символов"},
                {"~log", "Вы используете недопустимое имя", "Данный логин уже зарегистрирован", "Введен неверный email адрес", "Длина пароля должна быть не менее 6 символов"},
                {"/log", "Вы используете недопустимое имя", "Данный логин уже зарегистрирован", "Введен неверный email адрес", "Длина пароля должна быть не менее 6 символов"},
                {"rewqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq", "Логин не может быть менее 3 символов и более 30 символов", "Данный логин уже зарегистрирован", "Введен неверный email адрес", "Длина пароля должна быть не менее 6 символов"},


                /* ВАЛИДНЫЕ НАБОРЫ ДАННЫХ */
                {"1log", "Можно использовать данный логин для регистрации", "Данный логин уже зарегистрирован", "Введен неверный email адрес", "Длина пароля должна быть не менее 6 символов"},
                {"login12341234", "Можно использовать данный логин для регистрации", "Данный логин уже зарегистрирован", "Введен неверный email адрес", "Длина пароля должна быть не менее 6 символов"},
                {"#log", "Можно использовать данный логин для регистрации", "Данный логин уже зарегистрирован", "Введен неверный email адрес", "Длина пароля должна быть не менее 6 символов"},
                {"^log", "Можно использовать данный логин для регистрации", "Данный логин уже зарегистрирован", "Введен неверный email адрес", "Длина пароля должна быть не менее 6 символов"},
                {";log", "Можно использовать данный логин для регистрации", "Данный логин уже зарегистрирован", "Введен неверный email адрес", "Длина пароля должна быть не менее 6 символов"},
                {":log", "Можно использовать данный логин для регистрации", "Данный логин уже зарегистрирован", "Введен неверный email адрес", "Длина пароля должна быть не менее 6 символов"},
                {"`log", "Можно использовать данный логин для регистрации", "Данный логин уже зарегистрирован", "Введен неверный email адрес", "Длина пароля должна быть не менее 6 символов"},
                {"log", "Можно использовать данный логин для регистрации", "Данный логин уже зарегистрирован", "Введен неверный email адрес", "Длина пароля должна быть не менее 6 символов"},

        };
    }

    /* ПРОВЕРКА ПОЛЯ LOGIN НА ВАЛИДНОСТЬ */
    @Test(dataProvider = "DataForTestLoginField")
    public void RegistrationLoginTest(String loginString, String MsgLogin1, String MsgLogin2, String MsgEm, String MsgPsw) throws InterruptedException {

        Reporter.log("*");
        Reporter.log(loginString);
        Reporter.log("*");

        WebElement emailInput = driver.findElement(By.id("email"));
        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement pswInput = driver.findElement(By.id("password1"));
        Reporter.log("Очистка всех полей");
        Thread.sleep(1400);
        emailInput.clear(); nameInput.clear(); pswInput.clear();


        Reporter.log("Ввод в поле login");
        nameInput.sendKeys(loginString);

        Reporter.log("Нажатие на кнопку подверждения регистрации");
        driver.findElement(By.cssSelector("#registration > div > div > button")).click();

        Reporter.log("Проверка выведенного сообщения");
        //driver.getPageSource().contains(MsgPsw)

        Thread.sleep(1400);
        Boolean CheckCorrectMsg;
        //ArrayList<WebElement> FinalString1 = driver.findElements(By.xpath("//*[@id=\"result-registration-email\"]/span[text()=" + FirstMsg));
        if( (driver.getPageSource().contains(MsgLogin1) || driver.getPageSource().contains(MsgLogin2)) &&
                driver.getPageSource().contains(MsgEm) && driver.getPageSource().contains(MsgPsw))
        {
            CheckCorrectMsg = true;
            Reporter.log("Корректное сообщение выведено");
        }
        else
            CheckCorrectMsg = false;
        Assert.assertTrue(CheckCorrectMsg, "Не выведенно корректное сообщение, или выведенное сообщение - некорректно");
        Reporter.log("\n\n");
    }




    /* ИНФОРМАЦИЯ ДЛЯ ПРОВЕРКИ ПОЛЯ ДЛЯ ВВОДА PASSWORD */
    @DataProvider
    public static Object[][] DataForTestPswField() {

        return new Object[][]{

                /* НЕВАЛИДНЫЕ НАБОРЫ ДАННЫХ */
                {"q", "Длина пароля должна быть не менее 6 символов", "Логин не может быть менее 3 символов и более 30 символов","Введен неверный email адрес"},
                {"qqq", "Длина пароля должна быть не менее 6 символов", "Логин не может быть менее 3 символов и более 30 символов","Введен неверный email адрес"},
                {"qqqq", "Длина пароля должна быть не менее 6 символов", "Логин не может быть менее 3 символов и более 30 символов","Введен неверный email адрес"},
                {"qqqqq", "Длина пароля должна быть не менее 6 символов", "Логин не может быть менее 3 символов и более 30 символов","Введен неверный email адрес"},
                {"", "Длина пароля должна быть не менее 6 символов", "Логин не может быть менее 3 символов и более 30 символов","Введен неверный email адрес"},
                {"q", "Длина пароля должна быть не менее 6 символов", "Логин не может быть менее 3 символов и более 30 символов","Введен неверный email адрес"},
                {"q", "Длина пароля должна быть не менее 6 символов", "Логин не может быть менее 3 символов и более 30 символов","Введен неверный email адрес"},
                {"q", "Длина пароля должна быть не менее 6 символов", "Логин не может быть менее 3 символов и более 30 символов","Введен неверный email адрес"},
                {"q", "Длина пароля должна быть не менее 6 символов", "Логин не может быть менее 3 символов и более 30 символов","Введен неверный email адрес"},
        };
    }

    /* ПРОВЕРКА ПОЛЯ PASSWORD НА ВАЛИДНОСТЬ */
    @Test(dataProvider = "DataForTestPswField")
    public void RegistrationPswTest(String pswString, String MsgPsw, String MsgLogin, String MsgEm) throws InterruptedException {

        Reporter.log("*");
        Reporter.log(pswString);
        Reporter.log("*");

        WebElement emailInput = driver.findElement(By.id("email"));
        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement pswInput = driver.findElement(By.id("password1"));
        Reporter.log("Очистка всех полей");
        Thread.sleep(1400);
        pswInput.clear(); nameInput.clear(); emailInput.clear();


        Reporter.log("Ввод в поле password");
        pswInput.sendKeys(pswString);

        Reporter.log("Нажатие на кнопку подверждения регистрации");
        driver.findElement(By.cssSelector("#registration > div > div > button")).click();

        Reporter.log("Проверка выведенного сообщения");
        //driver.getPageSource().contains(MsgPsw)
        Boolean CheckCorrectMsg;

        Thread.sleep(1400);
        //ArrayList<WebElement> FinalString1 = driver.findElements(By.xpath("//*[@id=\"result-registration-email\"]/span[text()=" + FirstMsg));
        if( driver.getPageSource().contains(MsgPsw) && driver.getPageSource().contains(MsgLogin) &&
                driver.getPageSource().contains(MsgEm))
        {
            CheckCorrectMsg = true;
            Reporter.log("Корректное сообщение выведено");
        }
        else
            CheckCorrectMsg = false;
        Assert.assertTrue(CheckCorrectMsg, "Не выведенно корректное сообщение, или выведенное сообщение - некорректно");
        Reporter.log("\n\n");

    }




    /* КОД ВЫПОЛНЯЮЩИЙСЯ ПОСЛЕ ТЕСТОВ */
    //-----------------------------------Test TearDown-----------------------------------
    @AfterClass
    public void teardownTest() {
        driver.quit();  // ЗАКРЫТИЕ БРАУЗЕРА
    }
}