import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class Registration {
        private Registration() {
        }

        public static RegistrationByCardInfo generateByCard(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationByCardInfo(faker.name().fullName(),
                    faker.phoneNumber().cellPhone(),
                    LocalDate.now().plusDays(3),
                    faker.address().cityName());
        }
    }
}