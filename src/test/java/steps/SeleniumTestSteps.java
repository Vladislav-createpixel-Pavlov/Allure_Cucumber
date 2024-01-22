package steps;


import io.cucumber.java.ru.И;
import managers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTestSteps {
     String ADD = "//button[@data-toggle='modal']" ;
     String NAME = "//input[@id='name']";
    String DROPDOWN = "//input[@id ='type']" ;
    String VEGETABLE = "//option[@value='VEGETABLE']";
    String SAVE = "//input[@id ='save']";
    String FRUIT = "//option[@value='FRUIT']";
    String EXOTIC = "//input[@id ='exotic']";
    String TITLE = "//h5";
    private WebDriver driver;
    private final DriverManager driverManager = DriverManager.getDriverManager();

    @И("открыта страница по адресу {string}")
    public void открыта_страница_по_адресу(String string) {
        System.out.println("Открытие страницы в браузере");
        driverManager.getDriver().get(string);
        driverManager.getDriver().manage().window().maximize();
        driverManager.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driverManager.getDriver().manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }

    @И("выполнено нажатие на {string}")
    public void выполнено_нажатие_на(String string) {
        String STR = null;
        switch (string) {
            case "Добавить":
                STR = ADD;
                System.out.println("Нажимаю на кнопку 'Добавить'");
                break;
            case "Сохранить":
                    STR = SAVE;
                System.out.println("Нажимаю на кнопку 'Сохранить'");
                    break;
            case "Экзотический":
                STR = EXOTIC;
                System.out.println("Активирую чекбокс 'Экзотический'");
                break;
            default:

        }
        WebElement btn = driver.findElement(By.xpath(STR));
        btn.click();
    }


    @И("поле Наименование заполняется значением {string}")
    public void поле_заполняется_значением(String string) {
        System.out.println("Заполняю рамку значениями");
        WebElement name = driver.findElement(By.id("name"));
        name.sendKeys(string);
    }
    @И("выпадающее меню Тип выбирается тип {string}")
    public void выпадающее_меню_Тип_выбирается_тип(String string) {
        String STR = null;
        switch (string) {
            case "Фрукт":
                STR = FRUIT;
                System.out.println("Выбираю тип: Овощ");
                break;
            case "Овощ":
                STR = VEGETABLE;
                System.out.println("Выбираю тип: Фрукт");
                break;
            default:
        }
        WebElement btn = driver.findElement(By.xpath(STR));
        btn.click();
    }

    @И("валидация страницы Список товаров")
    public void валидация_страницы() {
        System.out.println("Валидация страницы");
        assertEquals("Список товаров",driver.findElement(By.xpath(TITLE)).getText(),"Заголовок не соответствует странице");
    }
    @И("Проверка результата добавления {string}")
    public void Проверка_результата_добавления (String string) {
        System.out.println("Проверяю результат добавления");
        assertEquals(string,driver.findElement(By.xpath("//tbody/tr[5]/td[1]")).getText(),"Название не соответствует новой записи");
        assertEquals(string,driver.findElement(By.xpath("//tbody/tr[5]/td[2]")).getText(),"Тип не соответствует новой записи");
        assertEquals(string,driver.findElement(By.xpath("//tbody/tr[5]/td[3]")).getText(),"Чекбокс 'Экзотический' не соответствует новой записи");
    }
    @И("Закрыть страницу")
    public void Закрыть_страницу() {
        System.out.println("Закрываю браузер");
        driverManager.quitDriver();
    }

}
