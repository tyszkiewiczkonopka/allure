package selenium.basic.basicTab;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.basic.BaseTest;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Basic Tab")
public class AlertsTest extends BaseTest {
    @Test
    @Description("Alert tests")
    void simple_alert_should_show_message_after_clicking_ok() {
        switchToAlert("simple-alert").accept();

        String expectedOkMessage = "OK button pressed";
        String actualOkMessage = driver.findElement(By.id("simple-alert-label")).getText();

        assertThat(actualOkMessage).isEqualTo(expectedOkMessage);
    }

    @Test
    @Description("Alert tests")
    void prompt_alert_should_show_message_after_clicking_ok() {
        Alert promtptAlert = switchToAlert("prompt-alert");

        promtptAlert.sendKeys("Lord Vader");
        promtptAlert.accept();

        String expectedOkMessage = "Hello Lord Vader! How are you today?";
        String actualOkMessage = driver.findElement(By.id("prompt-label")).getText();

        assertThat(actualOkMessage).isEqualTo(expectedOkMessage);
    }

    @Test
    @Description("Alert tests")
    void confirm_alert_should_show_messages_after_clicking_ok_and_cancel() {
        switchToAlert("confirm-alert").accept();

        String expectedOkMessage = "You pressed OK!";
        String actualOkMessage = driver.findElement(By.id("confirm-label")).getText();

        assertThat(actualOkMessage).isEqualTo(expectedOkMessage);

        driver.findElement(By.id("confirm-alert")).click();
        driver.switchTo().alert().dismiss();

        String expectedDismissMessage = "You pressed Cancel!";
        String actualDismissMessage = driver.findElement(By.id("confirm-label")).getText();

        assertThat(actualDismissMessage).isEqualTo(expectedDismissMessage);

    }

    @Test
    @Description("Alert tests")
    void delayed_alert_should_show_message_after_clicking_ok() {
        switchToDelayedAlert("delayed-alert").accept();

        String expectedOkMessage = "OK button pressed";
        String actualOkMessage = driver.findElement(By.id("delayed-alert-label")).getText();

        assertThat(actualOkMessage).isEqualTo(expectedOkMessage);

    }

    private Alert switchToAlert(String alertId) {
        driver.get(BASE_URL + "/alerts.php");
        driver.findElement(By.id(alertId)).click();
        return driver.switchTo().alert();
    }

    private Alert switchToDelayedAlert(String alertId) {
        driver.get(BASE_URL + "/alerts.php");
        driver.findElement(By.id(alertId)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }
}
