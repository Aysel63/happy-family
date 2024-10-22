package az.edu.turing.model.constant;

import java.time.format.DateTimeFormatter;

public class AppConstant {

    public static final DateTimeFormatter BIRTH_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static final DateTimeFormatter LOG_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
}
