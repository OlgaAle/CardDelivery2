import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    private RegistrationByCardInfo registration;

    @BeforeEach
    void setUpAll(){
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
