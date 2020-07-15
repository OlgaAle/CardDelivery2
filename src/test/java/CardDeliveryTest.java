import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    private RegistrationByCardInfo registration;

    @BeforeAll
    static void setUpAllure() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUpAll() {
        registration = DataGenerator.Registration.generateByCard("ru");
    }

    @Test
    void ifFormIsCorrectShouldReturnSuccess() {
        {
            open("http://localhost:9999");
            SelenideElement form = $("form");
            form.$("[data-test-id=city] input").setValue(registration.getCity());
            form.$("[data-test-id=date] input").setValue(registration.getDate().toString());
            form.$("[data-test-id=name] input").setValue(registration.getName());
            form.$("[data-test-id=phone] input").setValue(registration.getPhoneNumber());
            form.$("[data-test-id=agreement]").click();

            $$("button").find(exactText("Запланировать")).click();
            $(withText("Успешно!")).waitUntil(visible, 15000);

            form.$("[data-test-id=date] input").setValue(registration.getNewDate().toString());

            $$("button").find(exactText("Запланировать")).click();
            $(withText("Необходимо подтверждение")).waitUntil(visible, 15000);

            $$("button").find(exactText("Перепланировать")).click();
            $(withText("Успешно!")).waitUntil(visible, 15000);
        }
    }
}
