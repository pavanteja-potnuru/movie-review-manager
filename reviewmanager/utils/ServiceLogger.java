package reviewmanager.utils;

import reviewmanager.model.Color;

public class ServiceLogger implements IServiceLogger {

    public void logInfo(String message) {
        logInfo(message, Color.ANSI_GREEN);
    }
    public void logInfo(String message, String color) {
        System.out.println(String.format("Log: %s", color + message + Color.ANSI_RESET));
    }

    public void logError(String message) {
        logError(message, Color.ANSI_CYAN);
    }
    public void logError(String message, String color) {
        System.out.println(String.format("Error: %s", color + message + Color.ANSI_RESET));
    }
}
