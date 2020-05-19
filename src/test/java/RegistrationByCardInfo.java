import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class RegistrationByCardInfo
{
    private final String name;
    private final String phoneNumber;
    private final LocalDate date;
    private final String city;

}