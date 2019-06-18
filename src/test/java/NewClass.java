import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class NewClass extends BaseTest  {

    //-----------------------------------Tests-----------------------------------
    /* ПЕРЕХОД НА РЕГИСТРАЦИОННУЮ ФОРМУ */
    @Test (priority=1)
    public void GoToRegistrationForm() throws InterruptedException {
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

        Thread.sleep(1000);
        checkNewTabsAndDeleting();
        Thread.sleep(1000);

        if(driver.findElement(By.id("register-popup")).getAttribute("class").equals("b-popup b-popup__absolute b-popup__opened") != true ){
            ((WebElement) registrButton).click();
        }
        if(driver.findElement(By.id("register-popup")).getAttribute("class").equals("b-popup b-popup__absolute b-popup__opened") != true ){
            ((WebElement) registrButton).click();
        }

        Reporter.log("Проверка появления формы регистрации");
        Assert.assertTrue(driver.findElement(By.id("register-popup")).getAttribute("class").equals("b-popup b-popup__absolute b-popup__opened") == true, "Регистрационная форма не открылась");

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
}


