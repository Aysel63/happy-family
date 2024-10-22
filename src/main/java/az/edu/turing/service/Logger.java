package az.edu.turing.service;

import az.edu.turing.model.constant.AppConstant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;

public class Logger {

    private static final String RESOURCE_PATH = "src/main/java/resource/";
    private static final String LOGGER_FILE_PATH = RESOURCE_PATH.concat("applicationLog.txt");

    public static void info(String message) {
        String log = LocalDateTime.now().format(AppConstant.LOG_FORMATTER).concat(" DEBUG " + message);
        saveToFile(log);
    }

    public static void error(String message) {
        String log = LocalDateTime.now().format(AppConstant.LOG_FORMATTER).concat(" ERROR " + message);
        saveToFile(log);
    }

    private static void saveToFile(String log) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOGGER_FILE_PATH, true))) {
            bw.write(log + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
