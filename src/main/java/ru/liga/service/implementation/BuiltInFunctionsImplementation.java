package ru.liga.service.implementation;

import org.springframework.stereotype.Component;
import ru.liga.service.BuiltInFunctions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class BuiltInFunctionsImplementation implements BuiltInFunctions {

    public String getCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now).toString();
    }

    @Override
    public String getStringFromFunction(String functionName) {
        if (functionName.contentEquals("getCurrentDate"))
            return getCurrentTime();
        return null;
    }
}
