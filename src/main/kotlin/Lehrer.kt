import org.openqa.selenium.By
import org.openqa.selenium.firefox.FirefoxDriver

fun main(args: Array<String>) {
    System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver")
    val driver = FirefoxDriver()
    driver.get("https://neilo.webuntis.com/WebUntis/?school=tgm#/basic/main")
    driver.findElement(By.cssSelector("input[type=text][placeholder='Benutzer']")).sendKeys("xx");
    driver.findElement(By.cssSelector("input[type=password]")).sendKeys("xx");
    driver.findElement(By.cssSelector("button[title=Login]")).click()


}
