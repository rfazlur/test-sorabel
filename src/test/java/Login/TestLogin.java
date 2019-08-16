package Login;

import Utils.Constan;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestLogin {

    WebDriver webdriver;
    String cur_url;

    @Parameters("browser")
    @BeforeTest
    public void setUp(String browser){
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            webdriver = new ChromeDriver();
            webdriver.get(Constan.URL + "/index.php");
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            webdriver = new FirefoxDriver();
            webdriver.get(Constan.URL + "/index.php");
        } else {
            System.out.println("Sorry, can't found browser settings!");
        }
    }

    @AfterTest
    public void tearDown(){
        if (webdriver != null){
            webdriver.quit();
        }
    }

    @Test
    public void loginSuccess() throws InterruptedException {
        WebElement btnSignIn = webdriver.findElement(By.xpath("//a[@class='login']"));
        btnSignIn.click();
        WebElement txtEmail = webdriver.findElement(By.id("email"));
        txtEmail.sendKeys(Constan.EMAIL);
        WebElement txtPassword = webdriver.findElement(By.id("passwd"));
        txtPassword.sendKeys(Constan.PASSWORD);
        WebElement btnLogin = webdriver.findElement(By.id("SubmitLogin"));
        btnLogin.click();
        TimeUnit.SECONDS.sleep(3);
        cur_url = webdriver.getCurrentUrl();
        Assert.assertEquals("http://automationpractice.com/index.php?controller=my-account", cur_url);
    }

}
