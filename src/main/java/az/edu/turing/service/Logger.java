package az.edu.turing.service;

import az.edu.turing.model.constant.AppConstant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class Logger {

    private static final Path RESOURCE_PATH = Paths.get("src/main/java/resource/");
    private static final Path LOGGER_FILE_PATH = RESOURCE_PATH.resolve("applicationLog.txt");

    public static void info(String message) {
        String log = LocalDateTime.now().format(AppConstant.LOG_FORMATTER).concat(" DEBUG " + message);
        saveToFile(log);
    }

    public static void error(String message) {
        String log = LocalDateTime.now().format(AppConstant.LOG_FORMATTER).concat(" ERROR " + message);
        saveToFile(log);
    }

    private static void saveToFile(String log) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOGGER_FILE_PATH.toString(), true))) {
            bw.write(log + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
